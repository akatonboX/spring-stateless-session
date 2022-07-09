package info.sw0.spring.sample_app;

import java.util.Date;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class StatelessBodyAdvice<T> implements ResponseBodyAdvice<T> {

  private StatelessSessionHelper statelessSessionHelper;

  public StatelessBodyAdvice(StatelessSessionHelper statelessSessionHelper){
    this.statelessSessionHelper = statelessSessionHelper;
  }

  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  public T beforeBodyWrite(T body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    if(request instanceof ServletServerHttpRequest && response instanceof ServletServerHttpResponse) {
      var req= ((ServletServerHttpRequest)(request)).getServletRequest();
      var res= ((ServletServerHttpResponse)(response)).getServletResponse();
      var session = req.getSession();
      if(session instanceof StatelessHttpSession){
        var sessionData = ((StatelessHttpSession)session).getSessionData();
        sessionData.setLastAccessedTime(new Date().getTime());
        this.statelessSessionHelper.setSessionData(res, sessionData);
      }
    }
    return body;
  }
  
  
}
