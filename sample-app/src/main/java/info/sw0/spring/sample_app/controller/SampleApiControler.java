package info.sw0.spring.sample_app.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SampleApiControler {
  @Autowired
  private HttpSession session;
  
  @GetMapping("/public/api/sample/set")
  public Message setSessionValue(@RequestParam Map<String, Object> allRequestParams) {
    // session.getServletContext().getFilterRegistrations().entrySet().forEach(entry ->{
    //   var key = entry.getKey();
    //   var filterRegistration = entry.getValue();
    //   log.info("*{}={}", key, filterRegistration.getClassName());
    //   filterRegistration.getInitParameters().entrySet().forEach(entry2 ->{
    //     log.info("   {}={}", entry2.getKey(), entry2.getValue());
    //   });

      
    // });
    allRequestParams.entrySet().forEach(entry -> {
      this.session.setAttribute(entry.getKey(), entry.getValue());
    });
    return new Message("I wrote the session information.", allRequestParams); 
	}
  @GetMapping("/public/api/sample/get")
  public Message getSessionValue(HttpServletRequest request ) {
    // request.getServletContext().getFilterRegistrations().entrySet().stream().forEach(item -> {
    //   log.info("*{} = {}", item.getKey(), item.getValue().getName());
    // });
    var data = Collections.list(this.session.getAttributeNames()).stream().collect(Collectors.toMap(key -> key, key -> this.session.getAttribute(key)));
    return new Message("Obtained session information.", data); 
	}

  @Getter
  @Setter
  @AllArgsConstructor
  public static class Message{
    private String message;
    private Map<String, Object> data;
  }


  
}
