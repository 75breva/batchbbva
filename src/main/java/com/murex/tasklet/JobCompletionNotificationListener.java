package com.murex.tasklet;

import com.murex.processor.UniqueCounterPartWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

  private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
  private final UniqueCounterPartWriter writer;

  @Autowired
  public JobCompletionNotificationListener(UniqueCounterPartWriter writer) {
    this.writer = writer;
  }

  @Override
  public void beforeJob(JobExecution jobExecution) {
    logger.info("🚀 Iniciando Job");
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    logger.info("✅ Job Finalizado - Total de registros insertados: {}", writer.getTotalInsertedRecords());
  }
}