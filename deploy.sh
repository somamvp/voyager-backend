#!/bin/bash

# 1
REPOSITORY=/Users/juwon/IdeaProjects
PROJECT_NAME=teamMVP

# 2
echo "> 프로젝트 디렉토리로 이동"
cd $REPOSITORY/$PROJECT_NAME/

# 3
echo "> 프로젝트 Build 시작"
sudo ./gradlew build

# 4
echo "> jar 파일 ec2로 전송"
scp -i ~/.ssh/somaMVP.pem -r ./build/libs/demo-0.0.1-SNAPSHOT.jar ec2-user@ec2-13-125-200-220.ap-northeast-2.compute.amazonaws.com:~/deploy