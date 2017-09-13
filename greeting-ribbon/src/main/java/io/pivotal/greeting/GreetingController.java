package io.pivotal.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class GreetingController {

  private final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private final LoadBalancerClient loadBalancerClient;

  public GreetingController(LoadBalancerClient loadBalancerClient) {
    this.loadBalancerClient = loadBalancerClient;
  }

  @RequestMapping("/")
  String getGreeting(Model model) {

    logger.debug("Adding greeting");
    model.addAttribute("msg", "Greetings!!!");

    RestTemplate restTemplate = new RestTemplate();
    String fortune = restTemplate.getForObject(fetchFortuneServiceUrl(), String.class);

    logger.debug("Adding fortune: {}", fortune);
    model.addAttribute("fortune", fortune);

    return "greeting"; // resolves to the greeting.ftl template
  }

  private String fetchFortuneServiceUrl() {
    ServiceInstance instance = loadBalancerClient.choose("fortune-service");

    logger.debug("uri: {}", instance.getUri().toString());
    logger.debug("serviceId: {}", instance.getServiceId());

    return instance.getUri().toString();
  }

}
