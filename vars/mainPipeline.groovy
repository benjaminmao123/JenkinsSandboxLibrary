import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    def queue = Jenkins.instance.queue
    queue = null
    // def items = queue.items // Retrieve all items in the queue

    // if (items.length > 0) {
    //     println("Jobs in the queue:")
    //     items.each { item ->
    //         def jobName = item.task.name // Extract serializable data

    //         // Print the extracted data
    //         println("Job Name: ${jobName}")
    //     }
    // } 
    // else {
    //     println("The build queue is empty.")
    // }

    node
    {
        sleep 500
    }
}