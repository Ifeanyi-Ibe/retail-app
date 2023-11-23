pipeline {
    agent any
    environment {
        REPO_VERSION = "1.0"
    }
    stages {

        stage("build") {
            steps {
                echo "building the retail app version ${REPO_VERSION}..."
            }
        }

        stage("test") {
            when {
                expression {
                    env.BRANCH_NAME == 'dev'
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