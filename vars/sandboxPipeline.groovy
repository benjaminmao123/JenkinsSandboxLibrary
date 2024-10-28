import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    node
    {
        String blockingBranchName = getBlockingBranches(env.BRANCH_NAME)
        boolean useBuildBlocker = blockingBranchName != ''
        String blockingJobs = env.JOB_NAME + '-' + determineFolderName(blockingBranchName)

        echo "Blocking jobs: ${blockingJobs}"

        properties([
            [
                $class: 'BuildBlockerProperty',
                useBuildBlocker: useBuildBlocker,
                blockLevel: 'GLOBAL',
                blockingJobs: blockingJobs
            ]
        ])

        stage ('Clean Workspace')
        {
            cleanWs()
        }

        stage ('Checkout')
        {
            checkout scm
        }

        stage ('Build')
        {
            // Specify your solution or project file
            def solutionFile = 'JenkinsSandbox.sln'

            try {
                bat "\"${tool 'msbuild'}\" ${solutionFile} /p:Configuration=Release /p:Platform=x64 /t:Build"
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

String getBlockingBranches(String branchName) {
    if (branchName == 'develop') {
        return 'main'
    }

    return ''
}