package com.murex.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConverter implements Converter<String, Date> {

  private static final String INPUT_DATE_FORMAT = "dd/MM/yyyy"; // Formato de entrada
  private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd"; // Formato de salida

  @Override
  public Date convert(String source) {
    SimpleDateFormat inputFormat = new SimpleDateFormat(INPUT_DATE_FORMAT);
    SimpleDateFormat outputFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
    try {
      Date date = inputFormat.parse(source);
      return outputFormat.parse(outputFormat.format(date));
    } catch (ParseException e) {
      throw new IllegalArgumentException("Formato de fecha inválido. Por favor, usa " + INPUT_DATE_FORMAT);
    }
  }
}
