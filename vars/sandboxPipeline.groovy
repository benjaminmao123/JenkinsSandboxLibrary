import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    node
    {
        properties([
            disableConcurrentBuilds(), // "Do not allow concurrent builds"
            parameters([  // "This project is parameterized"
                // The job already had this parameter, to distinguish runs.
                string(
                    name: 'ItemId',
                    trim: true,
                    description: 'The ID of the item to be processed.',
                ),
                // We add this parameter to give higher-priority runs the
                // ability to start sooner, ahead of lower-priority runs.
                choice(
                    name: 'BuildPriority',
                    choices: ['5', '4', '3', '2', '1'],
                    description: 'Lower number means higher priority. ' +
                                'Runs with equal priority will execute in the order they were queued.',
                ),
            ]),
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