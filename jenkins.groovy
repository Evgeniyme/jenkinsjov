pipeline {
    agent any
    parameters {
        choice(name: 'OS', choices: ['linux', 'darwin', 'windows'], description: 'Pick OS')
        choice(name: 'ARCH', choices: ['x86_64', 'arm64'], description: 'Architecture to build for')
    }
    stages {
        stage('Build') {
            steps {
                echo "Building for platform: ${params.OS}"
                echo "Building for architecture: ${params.ARCH}"
                
                script {
                    if (params.OS == 'linux' && params.ARCH == 'x86_64') {
                        sh 'make linux'
                    } else if (params.OS == 'darwin' && params.ARCH == 'x86_64') {
                        sh 'make macOS'
                    } else if (params.OS == 'windows' && params.ARCH == 'x86_64') {
                        sh 'make windows'
                    } else if (params.OS == 'linux' && params.ARCH == 'arm64') {
                        sh 'make arm'
                    }
                }
            }
        }
    }
}