import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    def q = Jenkins.instance.queue
    q.items.each { item ->
        println "Queue item: ${item.task.name}"
    }

    node
    {
        stage ('Checkout')
        {
            sleep 500
            // checkout scm

            // config.updateDescription(this)

        }

        stage ('Build')
        {
            // sleep 30
        }
    }
}