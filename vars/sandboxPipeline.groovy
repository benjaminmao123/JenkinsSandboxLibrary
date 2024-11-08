import org.foo.*

def call(PipelineConfiguration config) {
    node
    {
        stage ('Clean Workspace')
        {
            // cleanWs()
        }

        stage ('Checkout')
        {
            // checkout scm
        }

        stage('Build')
        {
            // // Specify your solution or project file
            // def solutionFile = 'JenkinsSandbox.sln'

            // try {
            //     bat "\"${tool 'msbuild'}\" ${solutionFile} /p:Configuration=Release /p:Platform=x64 /t:Build"
            // } catch (Exception e) {
            //     echo "Build failed: ${e}"
            //     currentBuild.result = 'FAILURE'
            //     error "MSBuild failed"
            // }
        }
    }
}