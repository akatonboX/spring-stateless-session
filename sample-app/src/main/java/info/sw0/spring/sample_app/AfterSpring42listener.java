package info.sw0.spring.sample_app;

import javax.servlet.ServletContext;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ch.qos.logback.core.filter.Filter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AfterSpring42listener {
  public AfterSpring42listener(){
    var a = "";
  }
  @EventListener()
  public void processContextStartedEvent(ContextRefreshedEvent event) {
    log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa bye");
    //var parent = event.getApplicationContext().servle
   
    var aa = 1;
  }

  
}
