import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    node
    {
        properties([
            disableConcurrentBuilds(),

            parameters([
                string(
                    name: 'ItemID',
                    trim: true,
                    description: 'The ID of the item to be processed',
                ),

                choice(
                    name: 'BuildPriority',
                    choices: ['5', '4', '3', '2', '1'],
                    description: 'Lower number means higher priority.',
                ),
            ])
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
            sleep 30
        }
    }
}