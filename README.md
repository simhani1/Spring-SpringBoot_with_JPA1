<div align="center">
    <h2>실전! 스프링 부트와 JPA 활용</h2>
</div>

#### gradle 의존관계 확인법
- 터미널로 이동하여 `./gradlew dependecies`를 입력하면 의존관계가 출력된다.
- slf4j: 로그를 찍는 인터페이스 모음이라고 생각하면 된다.

#### 스프링 부트 thymeleaf viewName 자동 매칭
- 스프링 부트는 templates 폴더 내부의 html파일명을 자동으로 매칭시켜준다.

#### 꿀팁!
- `implementation 'org.springframework.boot:spring-boot-devtools'`를 의존성으로 추가하면 html 파일을 `build -> recompile`만 실행해주면 실시간으로 변화를 볼 수 있다.

