package com.murex.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final StringToLocalDateConverter stringToLocalDateConverter;

  public WebConfig(StringToLocalDateConverter stringToLocalDateConverter) {
    this.stringToLocalDateConverter = stringToLocalDateConverter;
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(stringToLocalDateConverter);
  }
}