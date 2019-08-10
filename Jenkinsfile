pipeline {

    agent any

    tools {

        maven "M3"

        jdk "java8"

    }



    environment {

        app_name = 'trading-app-dev'

    }



    stages {

        stage('Build') {

            steps {

                sh 'mvn clean package -DskipTests'

                echo "app_name is ${env.app_name} "

                archiveArtifacts 'target/*zip'

            }

        }

        stage('Deploy_dev') {

            when { branch 'developer' }

            steps {

                echo "Current Branch is: ${env.GIT_BRANCH}"

                sh "bash ./scripts/eb_deploy.sh trading-app-dev TradingAppDev-env"

            }

        }

        stage('Deploy_prod') {

            when { branch 'master' }

            steps {

                echo "Current Branch is: ${env.GIT_BRANCH}"

                sh "bash ./scripts/eb_deploy.sh trading-app-dev TradingApp-prod"

            }

        }

    }

}