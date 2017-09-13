package io.pivotal.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

  private final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private final FortuneServiceClient fortuneServiceClient;

  public GreetingController(FortuneServiceClient fortuneServiceClient) {
    this.fortuneServiceClient = fortuneServiceClient;
  }

  @RequestMapping("/")
  String getGreeting(Model model) {

    logger.debug("Adding greeting");
    model.addAttribute("msg", "Greetings!!!");

    String fortune = fortuneServiceClient.getFortune();

    logger.debug("Adding fortune");
    model.addAttribute("fortune", fortune);

    return "greeting"; // resolves to the greeting.ftl template
  }


}
