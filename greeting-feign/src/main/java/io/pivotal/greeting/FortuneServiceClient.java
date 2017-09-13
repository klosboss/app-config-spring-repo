package io.pivotal.greeting;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("fortune-service")
public interface FortuneServiceClient {

  @GetMapping(value = "/")
  String getFortune();
}
