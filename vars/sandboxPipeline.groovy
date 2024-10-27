import org.foo.*

def call(PipelineConfiguration config) {
    node
    {
        def folderName = determineFolderName(env.BRANCH_NAME)
        def jobName = env.JOB_BASE_NAME

        stage('Organize Job by Folder')
        {
            echo "Organizing job '${jobName}' in folder '${folderName}'"

            jobDsl scriptText: """
                folder("${folderName}") {
                    description("Folder for ${folderName} jobs")
                }

                job("${folderName}/${jobName}") {
                    description("A dynamically placed job based on the branch or environment")
                }
            """
        }

        stage ('Checkout')
        {
            checkout scm
        }

        stage('Build')
        {
            // Specify your solution or project file
            def solutionFile = 'JenkinsSandbox.sln'

            try {
                bat "\"${tool 'msbuild'}\" ${solutionFile} /p:Configuration=Release /p:Platform=x64 /t:Build /verbosity:detailed"
            } catch (Exception e) {
                echo "Build failed: ${e}"
                currentBuild.result = 'FAILURE'
                error "MSBuild failed"
            }
        }
    }
}

// Function to determine folder name based on branch name
def determineFolderName(branchName) {
    if (branchName == 'main') {
        return 'MainJobs'
    } else if (branchName == 'develop') {
        return 'DevelopJobs'
    }

    return 'custom'
}
