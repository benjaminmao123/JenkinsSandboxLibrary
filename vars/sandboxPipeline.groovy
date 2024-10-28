import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    node
    {
        String blockingBranchName = getBlockingBranches(env.BRANCH_NAME)
        boolean useBuildBlocker = blockingBranchName != ''
        String blockingJobs = env.JOB_NAME.split('/')[0] + '/' + blockingBranchName

        echo "Blocking jobs: ${blockingJobs}"

        properties([
            [
                $class: 'BuildBlockerProperty',
                useBuildBlocker: useBuildBlocker,
                blockLevel: 'GLOBAL',
                blockingJobs: blockingJobs
                scanQueueFor: 'ALL'
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
            // def solutionFile = 'JenkinsSandbox.sln'

            // try {
            //     bat "\"${tool 'MSBuild'}\" ${solutionFile} /p:Configuration=Release /p:Platform=x64 /t:Build"
            // } catch (Exception e) {
            //     echo "Build failed: ${e}"
            //     currentBuild.result = 'FAILURE'
            //     error "MSBuild failed"
            // }
            
            // introduce artificial delay
            sleep 120
        }
    }
}

String getBlockingBranches(String branchName) {
    if (branchName == 'develop') {
        return 'main'
    }

    return ''
}