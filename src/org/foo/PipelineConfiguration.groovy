package org.foo

import jenkins.model.Jenkins

class PipelineConfiguration
{
    void updateDescription(def context) {
        def commit = context.bat(returnStdout: true, script: 'git log -1 --oneline').trim()

        String commitMsg = commit.substring( commit.indexOf(' ') ).trim()

        context.echo "Commit message: ${commitMsg}"

        String jobDescription = ""
        context.echo "Job name: ${context.env.JOB_NAME}"

        if (commitMsg.contains('HOTFIX'))
        {
            context.echo "HOTFIX detected"
            jobDescription = "HOTFIX"
        }
        else
        {
            context.echo "HOTFIX not detected"
        }

        final job = Jenkins.instance.getItemByFullName(context.env.JOB_NAME)
        job.setDescription(jobDescription)
        job.save()
    }

    void getQueueItems(def context) {
        def queue = Jenkins.instance.queue
        def items = queue.items // Retrieve all items in the queue

        if (items.length > 0) {
            context.echo("Jobs in the queue:")
            items.each { item ->
                def jobName = item.task.name // Extract serializable data

                // Print the extracted data
                context.echo("Job Name: ${jobName}")
            }
        } 
        else {
            context.echo("The build queue is empty.")
        }
    }

    void printJobInfo(def context) {
        context.echo "Job name: ${context.env.JOB_NAME}"
        context.echo "Job display name: ${context.env.JOB_DISPLAY_NAME}"
        context.echo "Job full name: ${context.env.JOB_FULL_NAME}"
        context.echo "Job URL: ${context.env.JOB_URL}"
    }
}
