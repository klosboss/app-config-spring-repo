package io.pivotal.greeting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GreetingControllerTest {

  @Autowired
  GreetingController controller;

  @MockBean
  FortuneServiceClient client;

  @Before
  public void setup() {
    when(client.getFortune()).thenReturn("im ein ani li mi li");
  }

  @Test
  public void shouldGetFortune() {

    ExtendedModelMap model = new ExtendedModelMap();
    controller.getGreeting(model);

    assertThat(model.get("fortune")).isEqualTo("im ein ani li mi li");
  }
}
