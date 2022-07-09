package info.sw0.spring.sample_app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import info.sw0.spring.sample_app.StatelessSessionFilter;

@Configuration
public class Config {
  
  @Bean
  public FilterRegistrationBean<StatelessSessionFilter> statelessSessionFilter() {
      // FilterのコンストラクタにBeanを渡す
      var bean = new FilterRegistrationBean<StatelessSessionFilter>(new StatelessSessionFilter("cookieName", 600, "secret"));
      bean.setOrder(Integer.MIN_VALUE + 1);
      return bean;
  }
}
