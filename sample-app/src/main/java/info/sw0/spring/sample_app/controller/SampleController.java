package info.sw0.spring.sample_app.controller;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

  @Autowired
  private HttpSession session;

  @GetMapping("/setSessionValue")
  public ModelAndView setSessionValue(@RequestParam Map<String, Object> allRequestParams, ModelAndView  modelAndView) {

    allRequestParams.entrySet().forEach(entry -> {
      this.session.setAttribute(entry.getKey(), entry.getValue());
    });
    modelAndView.addObject("title", "setSessionValue");
    modelAndView.addObject("data", allRequestParams);
    modelAndView.setViewName("viewMap");
    return  modelAndView;
  } 
  @GetMapping("/getSessionValue")
  public ModelAndView getSessionValue(@RequestParam Map<String, Object> allRequestParams, ModelAndView  modelAndView) {
    var data = Collections.list(this.session.getAttributeNames()).stream().collect(Collectors.toMap(key -> key, key -> this.session.getAttribute(key)));
    modelAndView.addObject("title", "getSessionValue");
    modelAndView.addObject("data", data);
    modelAndView.setViewName("viewMap");
      return  modelAndView;
  } 
}
