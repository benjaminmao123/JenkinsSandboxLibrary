import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    // config.getQueueItems(this)
    config.printJobInfo(this)

    node {
        sleep 500
    }
}