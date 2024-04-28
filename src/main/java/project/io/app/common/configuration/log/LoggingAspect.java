package project.io.app.common.configuration.log;

import lombok.extern.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.stereotype.*;

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
