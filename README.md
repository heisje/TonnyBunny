# 🐰TonnyBunny🐰

## 🥕 서비스 소개

코로나 19의 영향력이 점차 감소하면서 해외 여행의 수요가 늘어나고 있습니다. 언어가 다른 장소에서 급한 용건이나 중요한 업무들을 수행하고 싶지만, AI 통번역 서비스는 아직 오역 사례가 종종 나와서 의사가 제대로 전달되고 있는지 우려가 되곤 합니다.
<br><br>
TonnyBunny는 이러한 분들을 위해 통역사와 고객을 매칭하고, 화상통화로 통역사가 실시간 통역을 해주는 **실시간 동시 통역 서비스**를 제공합니다.
또한, 통역 뿐만 아니라 고객이 번역을 편리하게 의뢰할 수 있는 **번역 요청 게시판**을 제공합니다.
<br><br>
TonnyBunny는 서비스를 제공하는 통번역가 분들을 **헬퍼**라고 부르고 있습니다. 아래는 헬퍼와 고객을 위한 TonnyBunny 서비스를 설명하고 있습니다. **잘해5조**팀의 7주간의 여정을 재밌게 봐주세요🥕🥕

## 🥕 소개 영상 [↗ 영상 링크](./_introduce/UCC.mp4)

## 🥕 프로젝트 진행 기간

`2023.01.02` ~ `2023.02.17`

## 🥕 주요 기능

-   회원 가입
    -   고객 또는 헬퍼 유형을 선택해서 가입합니다.
-   로그인
-   마이페이지
    -   포인트 관리
        -   포인트 충전 및 조회, 이용 내역을 확인합니다.
    -   헬퍼 프로필 관리
        -   통역, 번역 경력을 어필할 수 있는 자기 소개 페이지 등을 관리합니다.
    -   이용 내역 조회
        -   고객의 경우 의뢰한 내역을, 헬퍼의 경우 맡은 업무의 내역을 조회합니다.
    -   차단하기
        -   마음에 들지 않는 사용자를 차단할 수 있습니다.
    -   즐겨찾기
        -   마음에 드는 헬퍼를 즐겨찾기할 수 있습니다.
-   즉시 통역
    -   대기 중인 통역사와 즉시 매칭하고 실시간으로 통역할 수 있는 서비스입니다.
    -   고객이 즉시 통역 서비스를 요청하면 헬퍼가 실시간으로 요청을 확인하고, 요청 대기 신청을 합니다.
    -   고객은 대기 신청한 헬퍼 중, 금액이나 스펙을 확인하고 마음에 드는 헬퍼를 수락합니다.
    -   고객과 헬퍼는 화상 통화 화면으로 이동해서 실시간 통역을 수행합니다.
-   예약 통역
    -   고객이 통역 예약 일정과 업무에 대한 내용을 공고에 올리면, 헬퍼가 그것을 보고 신청합니다.
    -   매칭이 성공하면 예약한 일정에 즉시 통역 서비스와 같이 실시간 통역을 수행합니다.
-   번역가 매칭
    -   고객이 번역 일정과 업무에 대한 내용을 공고에 올리면, 헬퍼가 그것을 보고 신청합니다.
    -   고객은 헬퍼들이 신청한 목록을 보고, 헬퍼와 채팅으로 상담을 합니다.
    -   헬퍼는 번역 업무에 대한 견적서를 작성해서 고객에게 제시합니다.
    -   고객이 견적서가 마음에 들면 수락하고, 헬퍼에게 번역을 맡기게 됩니다.
-   커뮤니티
    -   간단한 번역을 부탁하거나 유저끼리 소통할 수 있는 게시판입니다.
-   리뷰 작성
    -   즉시 통역, 예약 통역, 번역 업무를 완수하고 나면 고객은 헬퍼에 대한 리뷰를 작성할 수 있습니다.
    -   헬퍼의 서비스에 문제가 있는 경우, 고객은 헬퍼를 신고할 수 있습니다.
-   채팅
    -   번역가 매칭을 하기 전, 고객과 번역가는 실시간 채팅을 통해 서로의 요구 사항을 조율하고 상담할 수 있습니다.
-   실시간 알림
    -   통번역 서비스 진행 중 발생하는 매칭 요청들이나 채팅 알림을 실시간으로 확인할 수 있습니다.

## 🥕 서비스 화면

### 메인 페이지

![image](./_introduce/main_page.png)

### [**↗ 주요 서비스 화면 시나리오는 여기로! 🐰**](./_introduce/%F0%9F%A5%95TonnyBunny%E2%80%99s%20Scenario%F0%9F%A5%95%20015243f0992646c7b3efe3240d97ccc9.md)

<br><br>

## 🥕 기술 스택

### ✔️ DB

-   `MySQL`
-   `Redis`

### ✔️ Backend

-   `Spring Boot`
-   `Spring Security`
-   `Spring Data JPA`
-   `Spring Data Redis`
-   `Spring Boot Websocket`
-   `Swagger-ui`
-   `Stomp-websocket`
-   `JWT`
-   `Gradle`

### ✔️ Frontend

-   `Nodejs`
-   `Vue 3`
-   `Bootstrap 5`
-   `Vuex`
-   `Vuetify`
-   `Vue3-lottie`
-   `Sockjs`
-   `Toastify-js`
-   `Webstomp`

### ✔️ Deploy

-   `AWS EC2`
-   `Ubuntu 20.04`
-   `Docker`
-   `Docker-compose`
-   `Nginx`

### ✔️ Communication

-   형상 관리 - `Gitlab`
-   이슈 및 스크럼 관리 - `Jira`
-   `Notion`
-   `Discord`
-   `Mattermost`

<br>

## 🥕 아키텍처 구조

![](./_introduce/architecture.png)

<br>

## 🥕 팀원 소개

<table>
  <tbody>
    <tr>
        <td align="center">
        <a href="https://github.com/baebug">
            <img src="https://avatars.githubusercontent.com/u/76019523?v=4" width="100px;"/>
            <br />
            <sub>👑 <b>배충현</b></sub>
        </a>
        </td>
        <td>
            <ul>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </td>
        </tr>
        <tr>
      <td align="center">
        <a href="https://github.com/yeomss">
            <img src="https://avatars.githubusercontent.com/u/50233862?v=4" width="100px;"/>
            <br />
            <sub>🐰 <b>염정아</b></sub>
        </a>
        </td>
        <td>
            <ul>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </td>
        </tr>
        <tr>
        <td align="center">
        <a href="https://github.com/heisje">
            <img src="https://avatars.githubusercontent.com/u/109322450?v=4" width="100px;"/>
            <br />
            <sub>🐰 <b>김희제</b></sub>
        </a>
        </td>
        <td>
            <ul>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </td>
        </tr>
        <tr>
        <td align="center">
        <a href="https://github.com/SeungtaeRyu">
            <img src="https://avatars.githubusercontent.com/u/81846487?v=4" width="100px;"/>
            <br />
            <sub>🐰 <b>류승태</b></sub>
        </a>
        </td>
        <td>
            <ul>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </td>
        </tr>
        <tr>
        <td align="center">
        <a href="https://github.com/Hello1Robot">
            <img src="https://avatars.githubusercontent.com/u/109326426?v=4" width="100px;"/>
            <br />
            <sub>🐰 <b>최권민</b></sub>
        </a>
        </td>
        <td>
            <ul>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </td>
        </tr>
        <tr>
        <td align="center">
        <a href="https://github.com/noonmap">
            <img src="https://avatars.githubusercontent.com/u/36250213?v=4" width="100px;"/>
            <br />
            <sub>🐰 <b>정채원</b></sub>
        </a>
        </td>
        <td>
            <ul>
                <li>실시간 채팅 구현</li>
                <li>히스토리 내역 구현</li>
                <li>포인트 관리 구현</li>
                <li>CI/CD 구축</li>
            </ul>
        </td>
        </tr>
    </tr>
    </tbody>
</table>
