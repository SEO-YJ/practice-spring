# Section 1 - 프로젝트 환경설정

## 1. 프로젝트 생성
### 순서
1. Java 11 설치 
    - 여기서 맥 설정 맞춰서 Java 11 설치: [openjdk](https://www.azul.com/downloads/?package=jdk#download-openjdk)
    - 환경변수 설정
    참조자료
    [맥 java 11 설치 방법](https://velog.io/@ccorgi1997/Mac-Apple-M1-Java-11-jdk-11-Eclipse-%EC%84%A4%EC%B9%98%EB%B2%95)
    

2. IntelliJ 설치 (무료 배포판으로 설치)
    - 여기서 IntelliJ 설치: [jetbrain](www.jetbrains.com)

3. Spring Boot 프로젝트 간편 생성
    - 여기서 생성: [start spring](https://start.spring.io/)
    - 속성
        - Project: Gradle - Groovy
        - Language: Java
        - Spring Boot: 2.7.13
            - 3 버전 부터는 Java 17로 해야되는 거 같음
            - Snapshot, M1 이런거 붙은거는 아직 정식판 아니므로 사용 x
        - Project Metadata
            - 이름 짓는 부분
    - Dependencies
        - 프로젝트에 필요한 라이브러리 가져오기
            - Spring Web: 웹 페이지 만들기 위해 필요
            - Thymeleaf: HTML 관리를 위해 필요
        - 여기서 선택하여 가져오면, 프로젝트에서 src -> build.gradle -> dependencies 에서 확인 가능

4. IntelliJ에서 Open으로 build.gradle 파일 열기

5. Spring 프로젝트 파일 구성
    - src
        - main
            - 우리가 다루어야 할 부분은 main의 java 폴더 파일
            - main의 .java 파일을 실행하여 프로젝트 실행 가능
        - build.gradle
            - 
                java {
                        sourceCompatibility = '11'
                    }
              - sourceCompatibility는 java의 '버전'을 관리하는 부분
              - 프로젝트 실행 안되면 이 부분을 java 버전에 맞게 수정해보자
              - Refresh Gradle을 통해, 수정한 코드를 저장하는 방법
                - [Refresh Gradle 방법](https://lifere.tistory.com/entry/Intellij-%EC%9D%B8%ED%85%94%EB%A6%AC%EC%A0%9C%EC%9D%B4-Refresh-Gradle-Dependencies)
              
6. 프로젝트 실행을 빨리하는 방법
    - IntelliJ -> Preferences -> Build, Execution, Deployment -> Gradle
        -> Build and run using => IntelliJ IDEA
        -> Run tests using => IntelliJ IDEA
        -> Gradle JVM => 다운로드한 java 버전으로
    - Project structures -> Project settings -> Project -> SDK => 다운로드한 java 버전으로
    
7. 프로젝트 실행 확인
    - main -> .java 파일 실행
    - http://localhost:8080/ 여기서 Whitelabel Error Page 라고 뜨면 실행 성공

## 2. 라이브러리 살펴보기
### 라이브러리 확인
- src -> build.gradle
    - dependencies -> Implementation 

- External Libraries
    - 땡겨온 라이브러리들 확인 가능

### gradle의 역할
- 예를 들어, 우리가 https://start.spring.io/ 여기서 스프링 프로젝트를 만들 때, dependencies에서 필요한 라이브러리를 선택하였었다.
- 그러면, gradle은 우리가 선택한 라이브러리들과 그 라이브러리와 의존관계가 있는 라이브러리들을 다 땡겨온다.
- ex) 선택한 라이브러리 <img src="./img/libraries.png" width="100">
- ex) 선택한 라이브러리와 의존관계에 있는 라이브러리 <img src="./img/dependencies.png" width="100">

+ Tip! window tool bar에 gradle 추가 방법
<img src="./img/window-tool-bar-gradle.png" width="100">

### Spring boot 기본 라이브러리
- 스프링 부트 라이브러리
    - spring-boot-starter-web
        - spring-boot-starter-tomcat: 톰캣 (웹서버)
        - spring-webmvc: 스프링 웹 MVC
    - spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
    - spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
        - spring-boot
            - spring-core
        - spring-boot-starter-logging
            - logback
            - slf4j

- 테스트 라이브러리
    - spring-boot-starter-test
        - junit: 테스트 프레임워크
        - mockito: 목 라이브러리
        - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
        - spring-test: 스프링 통합 테스트 지원

<img src="./img/window-tool-bar-gradle-dependencies.png" width="100">

+ Question? 왜 tomcap 임베디드 서버의 기본 포트가 8080일까?
[tomcat 8080인 이유](https://brocess.tistory.com/158)


## 3. View 환경설정


