package sample.web;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Map<String, Object> model) {
    model.put("message", "Hello World");
    model.put("title", "Hello Home");
    model.put("date", new Date());
    return "home";
  }

  @RequestMapping("/foo")
  public String foo() {
    throw new RuntimeException("Expected exception in controller");
  }

}
