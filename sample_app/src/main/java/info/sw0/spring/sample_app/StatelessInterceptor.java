package info.sw0.spring.sample_app;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatelessInterceptor implements HandlerInterceptor{

  private StatelessSessionHelper statelessSessionHelper;

  public StatelessInterceptor(StatelessSessionHelper statelessSessionHelper){
    this.statelessSessionHelper = statelessSessionHelper;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    var session = request.getSession();
    if(session instanceof StatelessHttpSession){
      log.info("set cookie");
      var sessionData = ((StatelessHttpSession)session).getSessionData();
      sessionData.setLastAccessedTime(new Date().getTime());
      this.statelessSessionHelper.setSessionData(response, sessionData);
    }
        log.info("TestInterceptor!! isCommitted={}", response.isCommitted());
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    
  }



}
