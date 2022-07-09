package info.sw0.spring.sample_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleControler {
  @GetMapping("/api/sample/get1")
  public String get1() {
    return "hello1";
	}
  @GetMapping("/api/sample/get2")
  public String get2() {
    return "hello1";
	}
}
