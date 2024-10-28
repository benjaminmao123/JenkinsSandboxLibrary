import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    node
    {
        int jobPriority = determinePriority(env.BRANCH_NAME)

        stage ('Prepare Job')
        {
            echo "Prepare job: '${env.JOB_NAME}' with priority: ${jobPriority}"

            jobDsl scriptText: """
                job('${env.JOB_NAME}') {
                    properties {
                        priority(${jobPriority})
                    }
                }
            """
        }

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

int determinePriority(String branchName) {
    if (branchName == 'main') {
        return 1
    } else if (branchName == 'develop') {
        return 2
    }

    return 3
}