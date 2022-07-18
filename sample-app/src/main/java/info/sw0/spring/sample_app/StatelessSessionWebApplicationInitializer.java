package info.sw0.spring.sample_app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatelessSessionWebApplicationInitializer implements WebApplicationInitializer{

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    // TODO Auto-generated method stub
    var a = 1;
    log.info("hhhhhhhhhhhhhhhhhhhhhhhhhh");
  }
  
}
