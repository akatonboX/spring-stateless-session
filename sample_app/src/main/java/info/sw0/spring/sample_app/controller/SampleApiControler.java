package info.sw0.spring.sample_app.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
public class SampleApiControler {
  @Autowired
  private HttpSession session;
  
  @GetMapping("/public/api/sample/get1")
  public Message setSessionValue(@RequestParam Map<String, Object> allRequestParams) {
    allRequestParams.entrySet().forEach(entry -> {
      this.session.setAttribute(entry.getKey(), entry.getValue());
    });
    return new Message("I wrote the session information.", allRequestParams); 
	}
  @GetMapping("/public/api/sample/get2")
  public Message getSessionValue() {
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
