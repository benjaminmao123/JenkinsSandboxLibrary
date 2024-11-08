import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    properties([
    parameters([])
    ])
    node
    {
        stage ('Build')
        {
            // sleep 30
        }
    }
}