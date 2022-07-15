#!/bin/bash

REPOSITORY=deploy
PROJECT_NAME=demo
LOG_DIR=log
USER=juwon
# 1
echo "> deploy 디렉토리로 이동"
#sudo su $USER # ec2-user 사용자 계정 전환
#sleep 0.001
cd ~/deploy
# 2
echo "> 현재 구동중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)
# 3
echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"
# 4
if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    sudo kill -15 $CURRENT_PID
    sleep 8
fi
# 5
echo "> 새 애플리케이션 배포"
# 6
JAR_NAME=$(ls -tr ~/$REPOSITORY/ | grep jar | tail -n 1)
echo "> JAR Name: $JAR_NAME"
# 7
#nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &
echo "> 애플리케이션 배포 성공!!"
echo "> 로그 기록"
sudo nohup java -jar ~/$REPOSITORY/$JAR_NAME > ~/${LOG_DIR}/${JAR_NAME}.txt 2>&1