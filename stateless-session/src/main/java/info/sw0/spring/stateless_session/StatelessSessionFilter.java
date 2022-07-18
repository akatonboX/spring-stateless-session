package info.sw0.spring.stateless_session;

import java.io.IOException;
import java.util.function.Supplier;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.filter.OrderedFilter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class StatelessSessionFilter implements OrderedFilter{

  private StatelessSessionHelper statelessSessionHelper;

  public StatelessSessionFilter(StatelessSessionHelper statelessSessionHelper){
    this.statelessSessionHelper = statelessSessionHelper;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
    log.info("start doFilter");
    if(request instanceof HttpServletRequest){
      log.debug("get cookie");
      //CookieからSessionDataを取得するか、新規作成
      var sessionData = this.statelessSessionHelper.getSessionData((HttpServletRequest)request).orElse(
        this.statelessSessionHelper.createSessionData()
      );


      //warpperの用意
      var requestWrapper = new StatelessHttpServletRequestWrapper((HttpServletRequest)request, sessionData);
      Runnable setSessionData = () -> {
        log.info("isCommitted={}", response.isCommitted());
        if(!response.isCommitted()){
          this.statelessSessionHelper.setSessionData((HttpServletResponse)response, sessionData);
        }
      };
      var responseWrapper = new StatelessHttpServletResponseWrapper((HttpServletResponse)response, setSessionData);
      
      //chain実行
      chain.doFilter(requestWrapper, responseWrapper);

      //Cookieの書き込み
      setSessionData.run();
      
      
      //終了処理
      requestWrapper.close();

      
    }
    else{
      log.debug("skip");
      chain.doFilter(request, response);
    }   
  }

  @Override
  public int getOrder() {
    return Integer.MIN_VALUE;
  }
}
