# 회원 관리 

회원 관리 프로젝트 예제. 

<br/><br/><br/><br/>

## 프로그램 실행

빌드 후, 프로젝트 실행 합니다. 

```shell
./gradlew build
```

> 실행 전 JDK 1.8이 설치된 지 반드시 확인해주세요.

<br/><br/><br/><br/>

## 📦 패키지 구조

```shell
└── recruitment
    ├── common                         # 설정 및 공통 클래스 패키지
     ── core                           # 도메인 및 애플리케이션 코어 패키지 
```

<br/><br/><br/><br/>

**`프로그램 실행 후`** 특정 URL로 접속하면 H2-Console과 Swagger-UI를 사용할 수 있습니다. API 명세는 간단하게 Swagger를 사용했습니다.

- [H2-Console 접속](http://localhost:8080/h2-console/)
- [Swagger-UI 접속](http://localhost:8080/swagger-ui/index.html)

<br/><br/><br/><br/>

## 구현 사항

1. 회원가입 API 

- `회원id` (필수): 사용자의 고유 식별자입니다.
- `비밀번호` (필수): 계정의 비밀번호입니다.
- `닉네임` (필수): 사용자의 별명입니다.
- `이름` (필수): 사용자의 실명입니다.
- `전화번호` (필수): 사용자의 전화번호입니다.
- `이메일주소` (필수): 사용자의 이메일 주소입니다.

<br/><br/>

2. 회원 목록 조회

쿼리스트링을 통해 다음 파라미터를 지정합니다.

- `page`: 조회할 페이지 번호입니다.
- `pageSize`: 한 페이지에 표시될 최대 회원 수입니다.
- `sort`: 정렬 기준을 지정합니다 (예: `가입일 순`, `이름 순`).
- 성공적으로 처리된 경우, 응답 코드 `200`을 반환합니다.
- 프론트엔드에서 페이징 처리를 수행할 수 있도록, 회원 데이터와 함께 페이징 정보를 포함한 적절한 형태의 응답을 제공합니다.

<br/><br/>

3. 회원 정보 수정

요청 본문(Body)을 통해 다음 정보를 전송합니다.

- `비밀번호` (선택적): 새로운 비밀번호.
- `닉네임` (선택적): 새로운 닉네임.
- `이름` (선택적): 새로운 이름.
- `전화번호` (선택적): 새로운 전화번호.
- `이메일주소` (선택적): 새로운 이메일 주소.

<br/><br/><br/><br/>

## 구현 내용.

### 로깅.

AOP를 통한 횡단 관심사 분리. AOP를 사용해 애플리케이션에 들어오는 모든 로그를 추적합니다. 로그는 간략하게 인자 정도만 체크했습니다.

```java
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* project.io.app.core.user.presentation.UserJoinAPI.join(..))")
    public void userJoinApiJoinMethod() {
    }

    @Pointcut("execution(* project.io.app.core.user.application.service.UserWriteService.join(..))")
    public void userWriteServiceJoinMethod() {
    }

    @Around("userJoinApiJoinMethod() || userWriteServiceJoinMethod()")
    public Object logAroundJoinMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String requestId = MDC.get("requestId");
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("[Presentation] [{}], Arg {}: {}", requestId, i, args[i]);
        }

        Object result = joinPoint.proceed();

        log.info("[Service] [{}] [{}] Return: {}",
            requestId,
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName()
        );
        return result;
    }
}
```

<br/><br/><br/><br/>

애플리케이션 진입 시, 부여되는 UUID를 통해 한 사용자의 고유 요청을 처리할 수 있습니다.

```java
@Component
public class MdcLogEnhancerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    )
        throws ServletException, IOException {
        try {
            String requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId);
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
```

<br/><br/><br/><br/>

### 통합 테스트 시 스프링 컨테이너 관리

상속 구조를 통해, 통합 테스트 시, 한 번 스프링 빈들이 초기화 되면, 더 이상 스프링 빈을 초기화하지 않아 테스트 속도가 빨라지게 됩니다. 

```java
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    @Autowired
    protected UserJpaRepository userJpaRepository;

    @BeforeEach
    void afterEach() {
        userJpaRepository.deleteAll();
    }
}
```

> 도메인이 사용자 하나 밖에 존재하지 않기 때문에, UserRepository로 매 데이터를 초기화했습니다.
