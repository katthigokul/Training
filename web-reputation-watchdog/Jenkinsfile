pipeline {
    agent any

    environment {
        repo_path = '$(basename $PWD)'
    }

    stages {
        stage('sync source code') {
            when{ branch 'v1.0.1'}
            steps {
                sh "rsync -rva ../${repo_path} ubuntu@10.20.1.254:/home/ubuntu/"
            }
        }
        stage('build') {
            when { branch 'v1.0.1' }
            steps {
                sh "ssh ubuntu@10.20.1.254 'cd ~/'${repo_path}' ; mvn clean package -DskipTests'"
            }
        }
        stage('Deploy') {
            when { branch 'v1.0.1' }
            steps {
                sh "ssh ubuntu@10.20.1.254 'cd ~/'${repo_path}' ; sudo docker-compose up --build -d'"
            }
        }
        stage('Deployment status') {
            when { branch 'v1.0.1' }
            steps {
                sh "ssh ubuntu@10.20.1.254 'cd ~/'${repo_path}' ; sleep 30 ; sudo docker ps'"
            }
        }
    }
}
