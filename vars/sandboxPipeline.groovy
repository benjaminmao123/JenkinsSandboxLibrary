import org.foo.*

def call(PipelineConfiguration config) {
    node
    {
        properties([
            parameters([  // "This project is parameterized"
                // The job already had this parameter, to distinguish runs.
                string(
                    name: 'ItemId',
                    trim: true,
                    description: 'The ID of the item to be processed.',
                ),
                // We add this parameter to give higher-priority runs the
                // ability to start sooner, ahead of lower-priority runs.
                choice(
                    name: 'BuildPriority',
                    choices: ['5', '4', '3', '2', '1'],
                    description: 'Lower number means higher priority. ' +
                                'Runs with equal priority will execute in the order they were queued.',
                ),
            ]),
        ])

        stage('Build')
        {
            echo 'Building..'
        }
    }
}
