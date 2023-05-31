# SpringBoot-Kmarket
스프링 부트 + 웹 쇼핑몰 사이트
![image](https://user-images.githubusercontent.com/111489868/234802292-5d4d411d-9100-4c23-b2ad-b21c7d1c4339.png)

## ERD
![image](https://github.com/JGwanghou/Kmarket/assets/111489868/1ed869b0-a238-41c0-b56c-487ee229c031)

## 🖥️  프로젝트 소개 
자유롭게 물건을 사고 팔며 판매자와 사용자에 친화적인 쇼핑몰 개발에 방점을 두고 제작한 웹 쇼핑몰 프로젝트입니다.
<br>
## 🧑‍ 참여 인원
 -  팀장 박종협 (lazca9527@gmail.com)
 - 팀원1 조광호 (jgh0609@gmail.com)
 - 팀장2 이왕근 (wanggeuni37@gmail.com)
## 🕰️ 개발 기간
 - 23.2.7일 ~ 23.2.24일
 
### 🧑‍🤝‍🧑 멤버 구성
 -  팀장 박종협 : - ERD, 상품 등록/구매, 장바구니, 마이페이지, 통합관리
 - 팀원1 조광호 : - ERD, 로그인, 회원가입, 관리자 상품리스트 검색 및 수정/삭제 
 - 팀원2 이왕근 : - 고객센터(Qna, Faq, Notice) 기능 담당

### ⚙️ 개발 환경
 - `Java 11` `javascript`, `thymeleaf`
 - **IDE** : IntelliJ IDEA Community Edition 2022.3.1
 - **Framework** : Spring Boot(2.7.8)
 - **DataBase** : Maria DB 5.5.68
 - **ORM** : Mybatis
 - **형상관리** : Git
 - **커뮤니케이션** : Slack

## 📌 담당 기능

#### 회원가입 [ 상세보기 - WIKI ](https://github.com/JGwanghou/Kmarket/wiki/1.-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85))
 - 카카오 주소 API 사용
 - 클라이언트 유효성 검사
 - ID 중복 체크
 - 
#### 로그인 [상세보기 - WIKI](https://github.com/JGwanghou/Kmarket/wiki/2.-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EB%A1%9C%EA%B7%B8%EC%9D%B8))
 - DB 값 검증
 - Spring Security 활용
 - 자동로그인

#### 관리자 상품리스트 [상세보기 - WIKI](https://github.com/JGwanghou/Kmarket/wiki/3.-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EC%83%81%ED%92%88%EB%AA%A9%EB%A1%9D-%EA%B2%80%EC%83%89-%EC%A1%B0%ED%9A%8C-%EC%88%98%EC%A0%95-%EC%82%AD%EC%A0%9C))
 - 판매 상품 조회
 - 상품 검색
 - 페이징처리
 - 상품 수정/삭제
