package somaMVP.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import somaMVP.annotation.RunningTime;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    // 특정 대상 어노테이션을 지정
    @Pointcut("@annotation(somaMVP.annotation.RunningTime)")
    private void enableRunningTIme(){}
    // 기본 패키지의 모든 메소드
    @Pointcut("execution(* somaMVP..*.*(..))")
    private void cut(){}
    // 실행 시점을 설정: 두 조건을 모두 만족하는 대상을 전후로 부가기능을 삽입하여 실행
    @Around("cut() && enableRunningTIme()")
    public void loggingRunningTIme(ProceedingJoinPoint joinPoint) throws Throwable {
        // 실행 시작 시간
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 실행
        joinPoint.proceed();
        // 실행 종료 시간
        stopWatch.stop();
        // 메소드 이름 가져오기
        String methodName = joinPoint.getSignature().getName();
        log.info("{}의 총 수행 시간 => {} sec", methodName, stopWatch.getTotalTimeSeconds());
    }
}
