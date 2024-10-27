import org.foo.*

def call() {
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
