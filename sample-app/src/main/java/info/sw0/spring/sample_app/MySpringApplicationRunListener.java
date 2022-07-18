package info.sw0.spring.sample_app;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;

public class MySpringApplicationRunListener implements SpringApplicationRunListener{

  @Override
  public void starting(ConfigurableBootstrapContext bootstrapContext) {
    // TODO Auto-generated method stub
    SpringApplicationRunListener.super.starting(bootstrapContext);
    
  }
   
}
