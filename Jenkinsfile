pipeline {
    agent any

    stages {

        stage("build") {
            steps {
                echo 'building the retail app...'
            }
        }

        stage("test") {
            when {
                expression {
                    BRANCH_NAME == 'dev'
                }
            }
            steps {
                  echo 'testing the app...'
            }
        }

        stage("deploy") {
            steps {
                echo 'deploying the app...'
            }
        }
    }
}