코로나 알림 어플리케이션입니다.

서버는 java(spring boot), 프론트는 vuejs 기반으로 구성 되어있습니다.

기능 
 1. 확진자 데이터 수집 기능 
 2. 앱으로 푸시 알림 기능

확진자의 데이터를 매 9시 ~ 10 시 사이에 1분 단위로 공공 데이터를 수집하여 db에 저장 합니다.
10시에 수집된 데이터를 기반으로 오늘의 확진자를 app으로 푸시 알림을 발송 합니다.

실행 방법

[백엔드]
 해당 프로젝트를 import 후 application properties.yaml 의 db 및 2가지 키에 대한 정의를 합니다.
 
[프론트 엔드]
 ./front  에 cd로 접근하여
   npm install
   npm run serve로 작동합니다.
   localhost:8184로 접속
   
