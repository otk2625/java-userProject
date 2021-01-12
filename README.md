# JSP UserList Page

## 환경
- JDK1.8
- Tomcat9.0
- STS 툴
- MySQL8.0
- Windows
- lombok.jar
- gson.jar(json파싱)
- jstl-1.2.jar
- 인코딩 utf-8
- git

## MySQL 데이터베이스 생성 및 사용자 생성

```sql
create user 'test1'@'%' identified by '1234';
grant all privileges on *.* to 'test1'@'%';
create database test;
use test;
```

## MySQL 테이블생성
- bloguser 사용자로 접속

``` sql
create table user (
	 id int primary key auto_increment,
    username varchar(50),
    password varchar(50),
    email varchar(50),
    role varchar(5) default 'user' 
);


```

## 프로젝트 구조 
- 패키지별로 관리 (controller, dao, dto, service, config, util)


## 기능 구현 목록
- 회원가입기능 구현
- 로그인 기능 구현
- 로그아웃 기능 구현
- 유저 리스트 목록 테이블 구현
- 접속한 user 삭제 구현
- 관리자로 접속시 관리자가아닌 user삭제 구현


