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

            if (commitMsg.contains('HOTFIX'))
            {
                echo "HOTFIX detected"
                final job = Jenkins.instance.getItem(env.JOB_NAME)
                job.setDescription('HOTFIX')
                job.save()
            }
        }

        stage ('Build')
        {
            // sleep 30
        }
    }
}