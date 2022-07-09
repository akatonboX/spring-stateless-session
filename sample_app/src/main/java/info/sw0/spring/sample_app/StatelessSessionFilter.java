package info.sw0.spring.sample_app;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class StatelessSessionFilter implements Filter{

  private ApplicationContext context;
  private StatelessSessionHelper statelessSessionHelper;

  public StatelessSessionFilter(ApplicationContext context, StatelessSessionHelper statelessSessionHelper){
    this.context = context;
    this.statelessSessionHelper = statelessSessionHelper;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
    if(request instanceof HttpServletRequest){
      //CookieからSessionDataを取得するか、新規作成
      var sessionData = this.statelessSessionHelper.getSessionData((HttpServletRequest)request).orElse(
        this.statelessSessionHelper.createSessionData()
      );

      //getSettionを変更したRequestWrappperを用いてchainを実行
      var requestWrapper = new StatelessHttpServletRequestWrapper((HttpServletRequest)request, sessionData);
      chain.doFilter(requestWrapper, response);

      //終了処理
      requestWrapper.close();

    }
    else{
      chain.doFilter(request, response);
    }   
  }
}
