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
}
