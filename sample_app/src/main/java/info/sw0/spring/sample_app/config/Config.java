package info.sw0.spring.sample_app.config;

import javax.swing.plaf.nimbus.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

import info.sw0.spring.sample_app.StatelessBodyAdvice;
import info.sw0.spring.sample_app.StatelessSessionFilter;
import info.sw0.spring.sample_app.StatelessSessionHelper;

@Configuration
public class Config {
  
  @Autowired
  ApplicationContext context;

  @Bean
  public StatelessSessionHelper statelessSessionHelper(){
    return new StatelessSessionHelper("cookieName", 600, "secret");
  }

  @Bean
  public FilterRegistrationBean<StatelessSessionFilter> statelessSessionFilter() {
      // FilterのコンストラクタにBeanを渡す
      var bean = new FilterRegistrationBean<StatelessSessionFilter>(new StatelessSessionFilter(this.context, this.statelessSessionHelper()));
      bean.setOrder(Integer.MIN_VALUE + 1);
      return bean;
  }


}
