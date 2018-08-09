package com.ymmihw.spring.shell.simple;

import static org.jline.utils.AttributedStyle.DEFAULT;
import static org.jline.utils.AttributedStyle.YELLOW;
import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider {
  @Bean
  public PromptProvider promptProvider() {
    return () -> new AttributedString("Planets > ", DEFAULT.foreground(YELLOW));
  }
}
