package com.practice.mollu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @GetMapping("/mollu")
  @ResponseBody
  public String index() {
    return "몰?루";
  }

  @GetMapping("/")
  public String root() {
    return "redirect:/question/list";
  }
}
