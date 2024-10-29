import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    // properties([
    //     parameters([
    //         string(
    //             name: 'ItemID',
    //             trim: true,
    //             description: 'The ID of the item to be processed',
    //         ),

    //         choice(
    //             name: 'BuildPriority',
    //             choices: ['5', '4', '3', '2', '1'],
    //             description: 'Lower number means higher priority.',
    //         ),
    //     ])
    // ])

    node
    {
        stage ('Build')
        {
            sleep 30
        }
    }
}