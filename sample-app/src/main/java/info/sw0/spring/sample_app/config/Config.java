package info.sw0.spring.sample_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import info.sw0.spring.sample_app.StatelessSessionFilter;
import info.sw0.spring.sample_app.StatelessSessionHelper;
import info.sw0.spring.sample_app.StatelessInterceptor;

@Configuration
@EnableAspectJAutoProxy
@Order(Ordered.HIGHEST_PRECEDENCE)
@AutoConfigureBefore(org.springframework.context.ApplicationContextInitializer.class)
public class Config implements WebMvcConfigurer {
  
  public Config(){
    var a = 0;
    
  }
  // @Bean
  // public StatelessSessionHelper statelessSessionHelper(){
  //   return new StatelessSessionHelper("cookieName", 60 * 60 * 1000, "secret");
  // }


  // @Bean
  // @Order(Ordered.HIGHEST_PRECEDENCE)
  // public FilterRegistrationBean<StatelessSessionFilter> statelessSessionFilter() {
    
  //     // FilterのコンストラクタにBeanを渡す
  //     var bean = new FilterRegistrationBean<StatelessSessionFilter>(new StatelessSessionFilter(this.statelessSessionHelper()));
  //     bean.addUrlPatterns("/**");
  //     bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

  //     return bean;
  // }
  // @Bean
  // public StatelessSessionFilter statelessSessionFilter(){
  //   return new StatelessSessionFilter(this.statelessSessionHelper());
  // }

  @Override 
  public void addInterceptors(InterceptorRegistry registry) { 
    // registry.addInterceptor(new StatelessInterceptor(this.statelessSessionHelper())) //←おかしい
    //         .addPathPatterns("/**");
 
    //上記のパスへのリクエストのときだけ、hogehogeHandlerInterceptorが実行される。 
    //.excludePathPatterns("/"); // 除外もある。 
  } 

}
