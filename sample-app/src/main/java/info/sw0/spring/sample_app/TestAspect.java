package info.sw0.spring.sample_app;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;

import org.apache.catalina.Wrapper;
import org.apache.catalina.core.ApplicationFilterChain;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
  
  @Around("execution(org.apache.catalina.core.ApplicationFilterChain org.apache.catalina.core.ApplicationFilterFactory.createFilterChain​(javax.servlet.ServletRequest, org.apache.catalina.Wrapper, javax.servlet.Servlet))")
  public ApplicationFilterChain createFilterChainAround(ProceedingJoinPoint pjp) throws Throwable{
    var result = (ApplicationFilterChain)pjp.proceed();    
    
    return result;

  }

  @Around("execution(void org.springframework.boot.web.servlet.ServletContextInitializerBeans.addServletContextInitializerBean(..))")
  public void aaa(ProceedingJoinPoint pjp) throws Throwable{
    var aa = pjp.getArgs();
    var result = (ApplicationFilterChain)pjp.proceed();    

  }
}
