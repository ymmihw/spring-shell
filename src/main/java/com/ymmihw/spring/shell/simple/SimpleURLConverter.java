package com.ymmihw.spring.shell.simple;

import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SimpleURLConverter implements Converter<String, URL> {

  @Override
  public URL convert(String source) {
    try {
      return new URL(source);
    } catch (MalformedURLException ex) {
      // Ignore
    }
    return null;
  }
}
