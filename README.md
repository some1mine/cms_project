## 개요
간단한 커머스 프로젝트

Use : Spring, Jpa, Mysql, Redis, Docker, Aws

Goal : 셀러와 구매자 사이를 중개해 주는 커머스 서버를 구축한다.

## 회원
### 공통
  - [X] 이메일을 통한 인증번호 인증 가입
  
## 고객
  - [X] 회원 가입
  - [X] 인증 처리 ( 이메일 )
  - [X] 로그인 토큰 발행
  - [X] 로그인 토큰을 이용한 제어 확인 (JWT, Filter)
  - [X] 예치금 관리

## 셀러
  - [X] 상품 CRUD ( Create, Read, Update, Delete)
  - [X] 회원 가입

## 주문 서버

### 셀러
  - [X] 상춤 등록, 수정
  - [X] 상품 삭제

### 구매자
  - [X] 장바구니를 위한 Redis 연동
  - [X] 상품 검색
  - [X] 장바구니에 물건 추가
  - [X] 장바구니 확인
  - [X] 장바구니 변경
  - [X] 주문하기
 