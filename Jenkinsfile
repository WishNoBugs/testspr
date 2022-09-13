#!groovy
pipeline {
  agent any

//工作日4点执行
//     triggers {
//     triggers{ cron('H H(9-16)/2 * * 1-5') }
// once in every two hours slot between 9 AM and 5 PM every weekday (perhaps at 10:38 AM, 12:38 PM, 2:38 PM, 4:38 PM)
//           cron('H 4 * * 1-5')
//       }

  stages {
      stage('拉取代吗') {
        steps {
          echo 'start poll source form gitlab'
           git(poll: true, url: 'http://github.com/WishNoBugs/testspr.git', branch: 'develop', changelog: true, credentialsId: 'cf40d121-9530-4f0a-bfe4-cd801d386499')
//        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'cf40d121-9530-4f0a-bfe4-cd801d386499', url: 'https://github.com/WishNoBugs/testspr.git']]])
        }
      }

   stage('编译打包') {
        steps {
          dir(path: './') {
            script {
              sh 'source /etc/profile && mvn clean package  -Dmaven.test.skip=true'
            }

          }

        }
      }


//    stage('代码检查') {
//      steps {
//        echo '代码检查'
//        dir(path: './') {
//          script {
//            sh "/usr/local/bin/sonar-scanner"  //执行命令开始扫描代码(前提要maven编译生成classes文件)
//          }
//
//        }
//
//      }
//    }

    stage('测试部署') {
      steps {
        echo "测试部署"
      }
    }

//      stage('清除工作空间') {
//          steps {
//            echo "清理工作目录: ${WORKSPACE}"
//            deleteDir()
//          }
//        }

  }
}