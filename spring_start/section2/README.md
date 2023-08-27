# Section 2 - 스프링 웹 개발 기초

## 1. 정적 컨텐츠
- 정적 컨텐츠
- 파일을 고객에게 그대로 전달해 주는 것

- MVC와 템플릿 엔진
- 서버에서 html 파일을 변형해서 내려주는 방식

- API
- Json이라는 데이터 포맷으로 클라이언트에게 전달하는 방식
ex> 서버-서버, 서버-클라이언트

### 정적 컨텐츠
- 정적 컨텐츠는 spring boot에서 자동으로 제공

### 정적 컨텐츠 - 동작 원리
![ex_screenshot](/img/static-content-process.png)
1. web에서 /hello-static.html라고 처리되면(localhost:8080/hello-static.html) 
2. 내장된 톰캣 서버에서 url 주소를 받아서 spring에게 물어봄
3. spring이 hello-static이라는 controller가 있는지 찾아봄
4. hello-static이라는 controller가 없으므로 resources에서 hello-static.html을 찾음 
5. resources에 있으므로 html 그대로 web에 반환

- 결과
![ex_screenshot](/img/static-content-execution.png)

## 2. MVC와 템플릿 엔진
- MVC?
    - Model
    : Controller에서 View에 필요한 데이터를 넘겨주는데 사용하는 부분
    - View
    : 화면을 그리는데 역량을 집중하는 부분
    - Controller
    : 비즈니스 로직, 내부적인 것을 처리하는데 집중하는 부분

### MVC와 템플릿 엔진
- Thymeleaf의 장점
: 파일의 Absolute path를 통해 서버를 실행하지 않아도 html을 web에서 확인해볼 수 있다.
- 템플릿 엔진 장점
: model에 데이터를 담아 내용을 치환할 수 있다.

+Tip! 인텔리제이에서 파라미터 정보 확인 방법
: command + p

### Thymeleaf 템플릿 엔진 - 동작 원리
![ex_screenshot](/img/thymeleaf-mvc-process.png)
1. web에서 /hello-mvc라고 처리되면(localhost:8080/hello-mvc) GetMapping(‘hello-mvc’) 메서드 호출
2. 내장된 톰캣 서버에서 url 주소를 받아서 spring에게 물어봄
3. helloController에 hello-mvc라는 것이 mapping(GetMapping)되어 있으므로, 그 메서드 호출
4. http 통신에서 GetMapping은 Get 역할을 함, 이를 통해 GetMapping(‘hello-mvc’)로 url 매치
5. controller의 GetMapping 메서드에서, model에 key: ”name”, value: name 매개변수로 추가
6. controller의 GetMapping 메서드에서, return 문자열 값은 resources -> templates -> 해당 문자열과 일치하는 파일 호출, 이는, data를 model에 담아, hello-template.html 화면을 렌더링 하는 과정이다.
    (controller에서 반환 값으로 문자열을 반환하면 뷰 리졸버가 화면을 찾아서 처리)
    (스프링 부트 템플릿 엔진 기본 viewName 매핑 방법: ‘resources:templates’/+(ViewName)+’.html’ 방식으로 뷰. 리졸버가 html 파일을 찾아 렌더링한다는 것!)
7. hello-template.html 파일에서 ${name}에 model의 key값이 ‘name’이면, 그에 따르는 value 값이 들어가게 된다.
8. 템플릿 엔진으로 인해 변환된 html 파일이 렌더링되어 화면으로 보여짐

- 결과
1. 매개변수 전달 안 할시
- 서버 경고 발생
![ex_screenshot](/img/none-parameter.png)
- 웹 실행 결과
![ex_screenshot](/img/none-parameter-execution.png)

2. 매개변수 전달 시
![ex_screenshot](/img/exist-parameter-execution.png)
- url에 매개변수 전달 방법
: url?매개변수명=값


## 3. API
- 화면을 가져오는 방식
1. html 가져오기: 정적 컨텐츠, 템플릿 엔진
2. 데이터 가져오기: API

- ResponseBody?
: http는 헤더부와 바디부가 존재하는데, 바디부에 데이터를 직접 넣어주기 위함
```java
// API 예시
@Getmapping('hello-string')
@ResponseBody
public string helloString(@RequestParam("name"), String name){
    return "hello" + name;
}
```
- 코드 설명 
: 응답(Response) body 부분에 직접 데이터를 넣어주겠다.

+Tip! 실행 중인 서버, 강제 종료하는 법
1. 터미널 키기
2. lsof -i tcp:'포트번호'로 실행중인 프로세스 확인
3. sudo kill -9 {PID번호}로 실행중인 프로세스 종료      
[실행중인 프로세스 터미널로 강제종료 하는 법](https://dundung.tistory.com/148)   

### API와 템플릿 엔진
- API와 템플릿 엔진의 차이
1. API는 View가 없다.
- 페이지 소스 검사 결과
![ex_screenshot](/img/api-execution.png)

2. 템플릿 엔진은 View라는 화면을 조작하는 방식이다. 

+Tip! 인텔리제이 Getter, Setter 단축키    
: command + n    

+Tip! 인텔리제이 문장 자동 완성
: command + shift + enter 

### json
: 데이터 전달 형식
1. 최근 데이터 전달 형식은 xml보다 json을 많이 사용한다.
2. Spring에서는 @ResponseBody에서 객체를 반환하면, json으로 반환을 하는 것이 기본이다.

### API - 동작 원리
![ex_screenshot](/img/api-process.png)
1. @ResponseBody
- @ResponseBody를 사용 X: viewResolver에게 전달
- @ResponseBody를 사용 O: Http에게 데이터를 직접 전달 
2. viewResolver 대신에 HttpMessageConverter 가 동작
3. 반환 타입 
- 반환 값이 String 타입(문자열 객체)일 경우: HTTP의 BODY에 문자 내용을 직접 반환 
  기본 문자처리: StringHttpMessageConverter 
- 반환 값이 객체 타입일 경우: Json으로 반환(객체 -> Json)
  기본 객체처리: MappingJackson2HttpMessageConverter
4. byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음




