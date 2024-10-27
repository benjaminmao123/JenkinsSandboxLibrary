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

        stage ('Cleanup')
        {
            cleanWs()
        }

        stage ('Checkout')
        {
            checkout scm
        }

        stage('Build')
        {
            // Specify your solution or project file
            def solutionFile = 'JenkinsSandbox.sln'
            def vsDevCmdPath = '"C:\\Program Files (x86)\\Microsoft Visual Studio\\2022\\BuildTools\\Common7\\Tools\\VsDevCmd.bat"'

            try {
                bat """
                    call ${vsDevCmdPath}
                    msbuild ${solutionFile} /p:Configuration=Release /p:Platform=\"x64\"
                """
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
