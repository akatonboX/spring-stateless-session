package info.sw0.spring.sample_app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import info.sw0.spring.sample_app.StatelessSessionFilter;
import info.sw0.spring.sample_app.StatelessSessionHelper;
import info.sw0.spring.sample_app.StatelessInterceptor;

@Configuration
public class Config implements WebMvcConfigurer {
  
  @Bean
  public StatelessSessionHelper statelessSessionHelper(){
    return new StatelessSessionHelper("cookieName", 60 * 60 * 1000, "secret");
  }


  @Bean
  public FilterRegistrationBean<StatelessSessionFilter> statelessSessionFilter() {
      // FilterのコンストラクタにBeanを渡す
      var bean = new FilterRegistrationBean<StatelessSessionFilter>(new StatelessSessionFilter(this.statelessSessionHelper()));
      bean.setOrder(Integer.MIN_VALUE + 1);
      return bean;
  }
  

  @Override 
  public void addInterceptors(InterceptorRegistry registry) { 
    registry.addInterceptor(new StatelessInterceptor(this.statelessSessionHelper())) //←おかしい
            .addPathPatterns("/**");
 
    //上記のパスへのリクエストのときだけ、hogehogeHandlerInterceptorが実行される。 
    //.excludePathPatterns("/"); // 除外もある。 
  } 

}
