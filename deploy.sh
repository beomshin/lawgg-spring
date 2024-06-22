#!/bin/bash

# lg-server 서비스의 상태를 확인
service_status=$(systemctl is-active lg-server)

# lg-server 서비스가 실행 중인 경우
if [ "$service_status" == "active" ]; then
    # formdang-sp-was 서비스를 중지
    systemctl stop lg-server
else
    echo "lg-server 서비스는 이미 중지되어 있습니다."
fi

cd /home/sp/deploy/web

cp /home/sp/source/web/lg-web/target/*.jar app.jar

rm -rf /home/sp/source/web/lg-web

# lg-server 서비스를 시작
systemctl start lg-server
echo "lg-server 서비스를 시작했습니다."