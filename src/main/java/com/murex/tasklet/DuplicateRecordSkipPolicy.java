package com.murex.tasklet;

import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.dao.DuplicateKeyException;

public class DuplicateRecordSkipPolicy implements SkipPolicy {
  @Override
  public boolean shouldSkip(Throwable t, int skipCount) {
    if (t instanceof DuplicateKeyException) {
      return true; // Omitir registros duplicados
    }
    return false; // No omitir otras excepciones
  }
}