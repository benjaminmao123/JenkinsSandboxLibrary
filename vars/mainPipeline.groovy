import org.foo.*
import hudson.model.ParametersDefinitionProperty
import hudson.model.StringParameterDefinition
import jenkins.model.Jenkins

def call(PipelineConfiguration config) {
    node
    {
        stage ('Checkout')
        {
            checkout scm

            def commit = bat(returnStdout: true, script: 'git log -1 --oneline').trim()

            String commitMsg = commit.substring( commit.indexOf(' ') ).trim()

            echo "Commit message: ${commitMsg}"

            String jobDescription = ""
            echo "Job name: ${env.JOB_NAME}"

            if (commitMsg.contains('HOTFIX'))
            {
                echo "HOTFIX detected"
                jobDescription = "HOTFIX"
            }
            else
            {
                echo "HOTFIX not detected"
                jobDescription = "MAIN"
            }

            final job = Jenkins.instance.getItemByFullName(env.JOB_NAME)
            job.setDescription()
            job.save()
        }

        stage ('Build')
        {
            // sleep 30
        }
    }
}