import org.foo.*

def call(PipelineConfiguration config) {
    node
    {
        def folderName = determineFolderName(env.BRANCH_NAME)
        def jobName = env.JOB_BASE_NAME

        stage('Organize Job by Folder')
        {
            echo "Organizing job '${jobName}' in folder '${folderName}'"

            // Use Job DSL to create folder and job
            jobDsl scriptText: """
                folder('${folderName}') {
                    description('Folder for ${folderName} branch jobs')
                }
            """
        }

        stage('Build')
        {
            echo 'Building..'
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
