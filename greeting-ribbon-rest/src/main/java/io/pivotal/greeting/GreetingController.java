package io.pivotal.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class GreetingController {

  private final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private final RestTemplate restTemplate;

  public GreetingController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @RequestMapping("/")
  String getGreeting(Model model) {

    logger.debug("Adding greeting");
    model.addAttribute("msg", "Greetings!!!");

    String fortune = restTemplate.getForObject("http://fortune-service", String.class);

    logger.debug("Adding fortune: {}", fortune);
    model.addAttribute("fortune", fortune);

    return "greeting"; // resolves to the greeting.ftl template
  }

}
