import com.org.foo.*

def call(PipelineConfiguration config) {
    node
    {
        stage('Build')
        {
            echo 'Building..'
        }
        stage('Test')
        {
            echo 'Testing..'
        }
        stage('Deploy')
        {
            echo 'Deploying..'
        }
    }
}
