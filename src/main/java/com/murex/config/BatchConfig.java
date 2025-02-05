package com.murex.config;

import com.murex.model.Ariadna;
import com.murex.model.Murex;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.murex.model.catologo.CounterPartCatalogo;
import com.murex.model.catologo.CurlPlCatalogo;
import com.murex.model.catologo.NumoperfrontCatalogo;
import com.murex.model.fenergo.FenergoWrapper;
import com.murex.model.fenergo.JsonEntity;
import com.murex.model.fenergo.workflow.Workflow;
import com.murex.model.pruebaJson.MyObject;
import com.murex.processor.*;
import com.murex.processor.fenergoprocessor.WorkFlowWriter;
import com.murex.processor.fenergoprocessor.WorkflowProcessor;
import com.murex.tasklet.DuplicateRecordSkipPolicy;
import com.murex.tasklet.JobCompletionNotificationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  private static final Logger LOG = LoggerFactory.getLogger(BatchConfig.class);

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final DataSource dataSource;
//  @Value("classpath:Fencloud_lightSimple.json")
//  private Resource jsonFile;
  public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
    this.dataSource = dataSource;
  }
  @Bean
  @StepScope
  public JsonItemReader<MyObject> jsonItemReaderPrueba() {
    return new JsonItemReaderBuilder<MyObject>()
            .jsonObjectReader(new JacksonJsonObjectReader<>(MyObject.class))
            .resource(new ClassPathResource("prueba.json"))
            .name("MyObjectJsonItemReader")
            .build();
  }
  @Bean
  public ItemProcessor<MyObject, MyObject> jsonItemProcessor() {
    return user -> {
      // Process user if needed
      System.out.println(" " + user.getISin() + " " + user.getCustomer() + " " + user.getQuantity() );
      return user;
    };
  }
  @Bean
  public JsonFileItemWriter<MyObject> jsonItemWriter() {
    return new JsonFileItemWriterBuilder<MyObject>()
            .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
            .resource(new FileSystemResource("output.json"))
            .name("jsonItemWriter")
            .build();
  }
   // PARA tipo de fichero ([{}, {}, ...])  -> ("1", "41", etc.).
 /* @Bean
  public JsonItemReader<JsonEntity> jsonItemReader() {
    ObjectMapper objectMapper = new ObjectMapper();
    JacksonJsonObjectReader<JsonEntity> jsonObjectReader = new JacksonJsonObjectReader<>(objectMapper, JsonEntity.class);
    jsonObjectReader.setMapper(objectMapper);

    JsonItemReader<JsonEntity> reader = new JsonItemReader<>();
    reader.setResource(new ClassPathResource("Fencloud_lightSimple.json"));
    reader.setJsonObjectReader(jsonObjectReader);
    // reader.setMaxItemCount(1);
    LOG.info("Reader [Fencloud_lightSimple.json] initialized: {} ", reader);
    return reader;
*//*
    return new JsonItemReaderBuilder<JsonEntity>()
            .name("FencloudReader")
            .jsonObjectReader(jsonObjectReader)
            .resource(jsonFile)
            .build();
*//*

  }*/

/*
  @Bean
  public JsonItemReader<FenergoWrapper> jsonItemReader() {

    ObjectMapper objectMapper = new ObjectMapper();
    JacksonJsonObjectReader<FenergoWrapper> jsonObjectReader = new JacksonJsonObjectReader<>(FenergoWrapper.class);
    jsonObjectReader.setMapper(objectMapper);

    JsonItemReader<FenergoWrapper> reader = new JsonItemReader<>();
    reader.setResource(new ClassPathResource("Fencloud_light.json"));
    reader.setJsonObjectReader(jsonObjectReader);
    LOG.info("Reader [Fencloud_light.json] initialized: {} ", reader);
    return reader;
  }
*/

  /////////////////////////////////// INICIO ARIADNA //////////////////////////////////////////////////////////////////////////
  @Bean
  public FlatFileItemReader<Ariadna> ariadnaReader() {
    FlatFileItemReader<Ariadna> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("MOCK_DATA.csv"));
    reader.setLinesToSkip(1);
    DefaultLineMapper<Ariadna> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

    tokenizer.setDelimiter(","); // Add this line
    tokenizer.setNames(new String[]{"codestr", "producto", "descripcion"});
    tokenizer.toString();
    BeanWrapperFieldSetMapper<Ariadna> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Ariadna.class);
    lineMapper.setLineTokenizer(tokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    reader.setLineMapper(lineMapper);
    LOG.info("Reader [AriadnaBM.csv] initialized: {} ", reader);
    return reader;
  }
//  @Bean
//  public JdbcBatchItemWriter<Ariadna> writerAriadna(DataSource dataSource) {
//    JdbcBatchItemWriter<Ariadna> writer = new JdbcBatchItemWriter<>();
//    writer.setDataSource(dataSource);
//    writer.setSql("""
//            INSERT INTO ariadna (codestr, producto, descripcion)
//            VALUES (:codestr, :producto, :descripcion)
//        """);
//    writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//    LOG.info("Writer [AriadnaBM.csv] initialized: {} ", writer);
//    return writer;
//  }

  /////////////////////////////////// FIN ARIADNA //////////////////////////////////////////////////////////////////////////

  @Bean
  public FlatFileItemReader<Murex> reader() {
    FlatFileItemReader<Murex> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("data.dat"));
    reader.setLinesToSkip(1);

    DefaultLineMapper<Murex> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

    tokenizer.setDelimiter(";"); // Add this line
    tokenizer.setNames(new String[]{"fec_proceso", "cod_nominalpago", "cod_nominalrecib", "imp_npvpago", "imp_npvrec",
        "cod_cur_pl", "cod_numoperfront", "imp_irdfpv1", "imp_irdfpv2", "imp_csfi", "imp_csnf", "imp_fe", "imp_fefi",
        "imp_fpnfcp", "imp_fpnfrv", "imp_ftfi", "imp_delta", "imp_gamma", "imp_rho", "imp_theta", "imp_vega", "imp_zcsnv",
        "cod_counterpart", "cod_cntrplb", "xti_isinternal", "imp_lqty", "cod_portfolio", "imp_rtccp0", "imp_rtccp1",
        "xti_rtpr0", "xti_rtpr1", "cod_family", "cod_grupo", "cod_tipoproducto", "imp_mvnfcp", "imp_mvnfrv", "cod_tipoperacion",
        "fec_expirydate", "fec_lastflow", "cod_status", "fec_tetrn", "fec_initialpay", "cod_seccod", "imp_nominal", "cod_strument",
        "cod_cd", "imp_rtacr0", "imp_rtacr1", "imp_iqty", "xti_delivsettle", "cod_strtgy", "cod_accsct", "cod_operaorigen",
        "cod_lastmrktoper", "fec_lastmrktoper", "cod_dtefst", "cod_valstat", "cod_entity", "imp_volatility", "imp_forwardpago",
        "imp_pot", "imp_nomientrfs", "imp_nomientrfx", "imp_nomisalidafs", "imp_nomisalidafx", "imp_fmv", "imp_nonfmv",
        "cod_divisanomi", "imp_tipocambio", "cod_diventradafx", "cod_divsalidafx", "cod_csatype", "cod_nomvivopago",
        "cod_nomvivorecibo", "fec_nextfixrecibo", "fec_nextfixpago", "cod_nextfixpatarec", "cod_nextfixpatapago",
        "cod_m_collagcat", "imp_pl_csnfcp2", "imp_pl_csnfrv2", "imp_pl_rvu2", "cod_pl_inscur", "cod_vegaquot", "imp_total_ffc",
        "cod_tp_dtefxgf", "cod_tp_dtefxgl", "cod_tp_dtefst", "cod_tp_dtelst", "imp_tp_rtamc00", "imp_tp_rtamc10",
        "cod_tp_rtdpn00", "cod_tp_rtdpn10", "cod_tp_rtdxc00", "cod_tp_rtdxc10", "cod_tp_rtdxg00", "cod_tp_rtdxg10",
        "imp_tp_rtfxc00", "imp_tp_rtfxc10", "cod_model", "fec_dtesys", "cod_tp_nomcur", "cod_divpago", "cod_divrecibo",
        "xti_compraventa", "fec_ultfixpago", "fec_ultfixrecibo", "imp_mvpago", "imp_mvrecibo", "imp_mvcontrasala",
        "imp_mvcontraeur", "imp_plpago", "imp_plrecibo", "imp_plcontrasala", "imp_plcontraeur", "cod_aplicacionfront",
        "ind_adj", "des_ficorig", "xti_murex", "cod_region"});
    BeanWrapperFieldSetMapper<Murex> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Murex.class);
    lineMapper.setLineTokenizer(tokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    reader.setLineMapper(lineMapper);
    LOG.info("Reader initialized: {} ", reader);
    return reader;
  }



  @Bean
  public JdbcBatchItemWriter<Murex> writer(DataSource dataSource) {
    JdbcBatchItemWriter<Murex> writer = new JdbcBatchItemWriter<>();
    writer.setDataSource(dataSource);
    writer.setSql("""
            INSERT INTO murex (
                fec_proceso, cod_nominalpago, cod_nominalrecib, imp_npvpago, imp_npvrec, cod_cur_pl, cod_numoperfront,
                imp_irdfpv1, imp_irdfpv2, imp_csfi, imp_csnf, imp_fe, imp_fefi, imp_fpnfcp, imp_fpnfrv, imp_ftfi,
                imp_delta, imp_gamma, imp_rho, imp_theta, imp_vega, imp_zcsnv, cod_counterpart, cod_cntrplb,
                xti_isinternal, imp_lqty, cod_portfolio, imp_rtccp0, imp_rtccp1, xti_rtpr0, xti_rtpr1, cod_family,
                cod_grupo, cod_tipoproducto, imp_mvnfcp, imp_mvnfrv, cod_tipoperacion, fec_expirydate, fec_lastflow,
                cod_status, fec_tetrn, fec_initialpay, cod_seccod, imp_nominal, cod_strument, cod_cd, imp_rtacr0,
                imp_rtacr1, imp_iqty, xti_delivsettle, cod_strtgy, cod_accsct, cod_operaorigen, cod_lastmrktoper,
                fec_lastmrktoper, cod_dtefst, cod_valstat, cod_entity, imp_volatility, imp_forwardpago, imp_pot,
                imp_nomientrfs, imp_nomientrfx, imp_nomisalidafs, imp_nomisalidafx, imp_fmv, imp_nonfmv, cod_divisanomi,
                imp_tipocambio, cod_diventradafx, cod_divsalidafx, cod_csatype, cod_nomvivopago, cod_nomvivorecibo,
                fec_nextfixrecibo, fec_nextfixpago, cod_nextfixpatarec, cod_nextfixpatapago, cod_m_collagcat, imp_pl_csnfcp2,
                imp_pl_csnfrv2, imp_pl_rvu2, cod_pl_inscur, cod_vegaquot, imp_total_ffc, cod_tp_dtefxgf, cod_tp_dtefxgl,
                cod_tp_dtefst, cod_tp_dtelst, imp_tp_rtamc00, imp_tp_rtamc10, cod_tp_rtdpn00, cod_tp_rtdpn10, cod_tp_rtdxc00,
                cod_tp_rtdxc10, cod_tp_rtdxg00, cod_tp_rtdxg10, imp_tp_rtfxc00, imp_tp_rtfxc10, cod_model, fec_dtesys,
                cod_tp_nomcur, cod_divpago, cod_divrecibo, xti_compraventa, fec_ultfixpago, fec_ultfixrecibo, imp_mvpago,
                imp_mvrecibo, imp_mvcontrasala, imp_mvcontraeur, imp_plpago, imp_plrecibo, imp_plcontrasala, imp_plcontraeur,
                cod_aplicacionfront, ind_adj, des_ficorig, xti_murex, cod_region
            ) VALUES (
                :fec_proceso, :cod_nominalpago, :cod_nominalrecib, :imp_npvpago, :imp_npvrec, :cod_cur_pl, :cod_numoperfront,
                :imp_irdfpv1, :imp_irdfpv2, :imp_csfi, :imp_csnf, :imp_fe, :imp_fefi, :imp_fpnfcp, :imp_fpnfrv, :imp_ftfi,
                :imp_delta, :imp_gamma, :imp_rho, :imp_theta, :imp_vega, :imp_zcsnv, :cod_counterpart, :cod_cntrplb,
                :xti_isinternal, :imp_lqty, :cod_portfolio, :imp_rtccp0, :imp_rtccp1, :xti_rtpr0, :xti_rtpr1, :cod_family,
                :cod_grupo, :cod_tipoproducto, :imp_mvnfcp, :imp_mvnfrv, :cod_tipoperacion, :fec_expirydate, :fec_lastflow,
                :cod_status, :fec_tetrn, :fec_initialpay, :cod_seccod, :imp_nominal, :cod_strument, :cod_cd, :imp_rtacr0,
                :imp_rtacr1, :imp_iqty, :xti_delivsettle, :cod_strtgy, :cod_accsct, :cod_operaorigen, :cod_lastmrktoper,
                :fec_lastmrktoper, :cod_dtefst, :cod_valstat, :cod_entity, :imp_volatility, :imp_forwardpago, :imp_pot,
                :imp_nomientrfs, :imp_nomientrfx, :imp_nomisalidafs, :imp_nomisalidafx, :imp_fmv, :imp_nonfmv, :cod_divisanomi,
                :imp_tipocambio, :cod_diventradafx, :cod_divsalidafx, :cod_csatype, :cod_nomvivopago, :cod_nomvivorecibo,
                :fec_nextfixrecibo, :fec_nextfixpago, :cod_nextfixpatarec, :cod_nextfixpatapago, :cod_m_collagcat, :imp_pl_csnfcp2,
                :imp_pl_csnfrv2, :imp_pl_rvu2, :cod_pl_inscur, :cod_vegaquot, :imp_total_ffc, :cod_tp_dtefxgf, :cod_tp_dtefxgl,
                :cod_tp_dtefst, :cod_tp_dtelst, :imp_tp_rtamc00, :imp_tp_rtamc10, :cod_tp_rtdpn00, :cod_tp_rtdpn10, :cod_tp_rtdxc00,
                :cod_tp_rtdxc10, :cod_tp_rtdxg00, :cod_tp_rtdxg10, :imp_tp_rtfxc00, :imp_tp_rtfxc10, :cod_model, :fec_dtesys,
                :cod_tp_nomcur, :cod_divpago, :cod_divrecibo, :xti_compraventa, :fec_ultfixpago, :fec_ultfixrecibo, :imp_mvpago,
                :imp_mvrecibo, :imp_mvcontrasala, :imp_mvcontraeur, :imp_plpago, :imp_plrecibo, :imp_plcontrasala, :imp_plcontraeur,
                :cod_aplicacionfront, :ind_adj, :des_ficorig, :xti_murex, :cod_region
            )
        """);
    writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
    LOG.info("Writer initialized: {} ", writer);
    return writer;
  }

  //Numoperfront
  @Bean
  public FlatFileItemReader<NumoperfrontCatalogo> numoperfrontReader(){
    FlatFileItemReader<NumoperfrontCatalogo> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("data.dat"));
    reader.setLinesToSkip(1);

    DefaultLineMapper<NumoperfrontCatalogo> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setDelimiter(";");
    tokenizer.setNames(new String[]{"fec_proceso", "cod_nominalpago", "cod_nominalrecib", "imp_npvpago", "imp_npvrec",
        "cod_cur_pl", "cod_numoperfront", "imp_irdfpv1", "imp_irdfpv2", "imp_csfi", "imp_csnf", "imp_fe", "imp_fefi",
        "imp_fpnfcp", "imp_fpnfrv", "imp_ftfi", "imp_delta", "imp_gamma", "imp_rho", "imp_theta", "imp_vega", "imp_zcsnv",
        "cod_counterpart", "cod_cntrplb", "xti_isinternal", "imp_lqty", "cod_portfolio", "imp_rtccp0", "imp_rtccp1",
        "xti_rtpr0", "xti_rtpr1", "cod_family", "cod_grupo", "cod_tipoproducto", "imp_mvnfcp", "imp_mvnfrv", "cod_tipoperacion",
        "fec_expirydate", "fec_lastflow", "cod_status", "fec_tetrn", "fec_initialpay", "cod_seccod", "imp_nominal", "cod_strument",
        "cod_cd", "imp_rtacr0", "imp_rtacr1", "imp_iqty", "xti_delivsettle", "cod_strtgy", "cod_accsct", "cod_operaorigen",
        "cod_lastmrktoper", "fec_lastmrktoper", "cod_dtefst", "cod_valstat", "cod_entity", "imp_volatility", "imp_forwardpago",
        "imp_pot", "imp_nomientrfs", "imp_nomientrfx", "imp_nomisalidafs", "imp_nomisalidafx", "imp_fmv", "imp_nonfmv",
        "cod_divisanomi", "imp_tipocambio", "cod_diventradafx", "cod_divsalidafx", "cod_csatype", "cod_nomvivopago",
        "cod_nomvivorecibo", "fec_nextfixrecibo", "fec_nextfixpago", "cod_nextfixpatarec", "cod_nextfixpatapago",
        "cod_m_collagcat", "imp_pl_csnfcp2", "imp_pl_csnfrv2", "imp_pl_rvu2", "cod_pl_inscur", "cod_vegaquot", "imp_total_ffc",
        "cod_tp_dtefxgf", "cod_tp_dtefxgl", "cod_tp_dtefst", "cod_tp_dtelst", "imp_tp_rtamc00", "imp_tp_rtamc10",
        "cod_tp_rtdpn00", "cod_tp_rtdpn10", "cod_tp_rtdxc00", "cod_tp_rtdxc10", "cod_tp_rtdxg00", "cod_tp_rtdxg10",
        "imp_tp_rtfxc00", "imp_tp_rtfxc10", "cod_model", "fec_dtesys", "cod_tp_nomcur", "cod_divpago", "cod_divrecibo",
        "xti_compraventa", "fec_ultfixpago", "fec_ultfixrecibo", "imp_mvpago", "imp_mvrecibo", "imp_mvcontrasala",
        "imp_mvcontraeur", "imp_plpago", "imp_plrecibo", "imp_plcontrasala", "imp_plcontraeur", "cod_aplicacionfront",
        "ind_adj", "des_ficorig", "xti_murex", "cod_region"});

    BeanWrapperFieldSetMapper<NumoperfrontCatalogo> fieldSetMapper = new BeanWrapperFieldSetMapper<>(){
      @Override
      public NumoperfrontCatalogo mapFieldSet(FieldSet fielSet) {
        NumoperfrontCatalogo numoperfrontCatalogo = new NumoperfrontCatalogo();
        numoperfrontCatalogo.setValue(fielSet.readLong("cod_numoperfront"));
        return numoperfrontCatalogo;
      }
    };

    lineMapper.setLineTokenizer(tokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    reader.setLineMapper(lineMapper);
    return reader;

  }

  @Bean
  public JdbcBatchItemWriter<NumoperfrontCatalogo> numoperfrontWriter(DataSource dataSource) {
    JdbcBatchItemWriter<NumoperfrontCatalogo> writer = new JdbcBatchItemWriter<>();
    writer.setDataSource(dataSource);

    writer.setSql("""
            INSERT INTO numoperfront (value) VALUES (:value)
            """);
    writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
    LOG.info("Writer: {}", writer);
    return writer;

  }

  //CurlPl
  @Bean
  public FlatFileItemReader<CurlPlCatalogo> curlPlReader(){
    FlatFileItemReader<CurlPlCatalogo> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("data.dat"));
    reader.setLinesToSkip(1);

    DefaultLineMapper<CurlPlCatalogo> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setDelimiter(";");
    tokenizer.setNames(new String[]{"fec_proceso", "cod_nominalpago", "cod_nominalrecib", "imp_npvpago", "imp_npvrec",
        "cod_cur_pl", "cod_numoperfront", "imp_irdfpv1", "imp_irdfpv2", "imp_csfi", "imp_csnf", "imp_fe", "imp_fefi",
        "imp_fpnfcp", "imp_fpnfrv", "imp_ftfi", "imp_delta", "imp_gamma", "imp_rho", "imp_theta", "imp_vega", "imp_zcsnv",
        "cod_counterpart", "cod_cntrplb", "xti_isinternal", "imp_lqty", "cod_portfolio", "imp_rtccp0", "imp_rtccp1",
        "xti_rtpr0", "xti_rtpr1", "cod_family", "cod_grupo", "cod_tipoproducto", "imp_mvnfcp", "imp_mvnfrv", "cod_tipoperacion",
        "fec_expirydate", "fec_lastflow", "cod_status", "fec_tetrn", "fec_initialpay", "cod_seccod", "imp_nominal", "cod_strument",
        "cod_cd", "imp_rtacr0", "imp_rtacr1", "imp_iqty", "xti_delivsettle", "cod_strtgy", "cod_accsct", "cod_operaorigen",
        "cod_lastmrktoper", "fec_lastmrktoper", "cod_dtefst", "cod_valstat", "cod_entity", "imp_volatility", "imp_forwardpago",
        "imp_pot", "imp_nomientrfs", "imp_nomientrfx", "imp_nomisalidafs", "imp_nomisalidafx", "imp_fmv", "imp_nonfmv",
        "cod_divisanomi", "imp_tipocambio", "cod_diventradafx", "cod_divsalidafx", "cod_csatype", "cod_nomvivopago",
        "cod_nomvivorecibo", "fec_nextfixrecibo", "fec_nextfixpago", "cod_nextfixpatarec", "cod_nextfixpatapago",
        "cod_m_collagcat", "imp_pl_csnfcp2", "imp_pl_csnfrv2", "imp_pl_rvu2", "cod_pl_inscur", "cod_vegaquot", "imp_total_ffc",
        "cod_tp_dtefxgf", "cod_tp_dtefxgl", "cod_tp_dtefst", "cod_tp_dtelst", "imp_tp_rtamc00", "imp_tp_rtamc10",
        "cod_tp_rtdpn00", "cod_tp_rtdpn10", "cod_tp_rtdxc00", "cod_tp_rtdxc10", "cod_tp_rtdxg00", "cod_tp_rtdxg10",
        "imp_tp_rtfxc00", "imp_tp_rtfxc10", "cod_model", "fec_dtesys", "cod_tp_nomcur", "cod_divpago", "cod_divrecibo",
        "xti_compraventa", "fec_ultfixpago", "fec_ultfixrecibo", "imp_mvpago", "imp_mvrecibo", "imp_mvcontrasala",
        "imp_mvcontraeur", "imp_plpago", "imp_plrecibo", "imp_plcontrasala", "imp_plcontraeur", "cod_aplicacionfront",
        "ind_adj", "des_ficorig", "xti_murex", "cod_region"});

    BeanWrapperFieldSetMapper<CurlPlCatalogo> fieldSetMapper = new BeanWrapperFieldSetMapper<>(){
      @Override
      public CurlPlCatalogo mapFieldSet(FieldSet fielSet) {
        CurlPlCatalogo curlPlCatalogo = new CurlPlCatalogo();
        curlPlCatalogo.setCur_pl(fielSet.readString("cod_cur_pl"));
        return curlPlCatalogo;
      }
    };

    lineMapper.setLineTokenizer(tokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    reader.setLineMapper(lineMapper);
    return reader;

  }

  /**
   * READER COUNTERPART
   */
  @Bean
  public FlatFileItemReader<CounterPartCatalogo> counterPartReader(){
    FlatFileItemReader<CounterPartCatalogo> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("data.dat"));
    reader.setLinesToSkip(1);

    DefaultLineMapper<CounterPartCatalogo> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setDelimiter(";");
    tokenizer.setNames(new String[]{"fec_proceso", "cod_nominalpago", "cod_nominalrecib", "imp_npvpago", "imp_npvrec",
        "cod_cur_pl", "cod_numoperfront", "imp_irdfpv1", "imp_irdfpv2", "imp_csfi", "imp_csnf", "imp_fe", "imp_fefi",
        "imp_fpnfcp", "imp_fpnfrv", "imp_ftfi", "imp_delta", "imp_gamma", "imp_rho", "imp_theta", "imp_vega", "imp_zcsnv",
        "cod_counterpart", "cod_cntrplb", "xti_isinternal", "imp_lqty", "cod_portfolio", "imp_rtccp0", "imp_rtccp1",
        "xti_rtpr0", "xti_rtpr1", "cod_family", "cod_grupo", "cod_tipoproducto", "imp_mvnfcp", "imp_mvnfrv", "cod_tipoperacion",
        "fec_expirydate", "fec_lastflow", "cod_status", "fec_tetrn", "fec_initialpay", "cod_seccod", "imp_nominal", "cod_strument",
        "cod_cd", "imp_rtacr0", "imp_rtacr1", "imp_iqty", "xti_delivsettle", "cod_strtgy", "cod_accsct", "cod_operaorigen",
        "cod_lastmrktoper", "fec_lastmrktoper", "cod_dtefst", "cod_valstat", "cod_entity", "imp_volatility", "imp_forwardpago",
        "imp_pot", "imp_nomientrfs", "imp_nomientrfx", "imp_nomisalidafs", "imp_nomisalidafx", "imp_fmv", "imp_nonfmv",
        "cod_divisanomi", "imp_tipocambio", "cod_diventradafx", "cod_divsalidafx", "cod_csatype", "cod_nomvivopago",
        "cod_nomvivorecibo", "fec_nextfixrecibo", "fec_nextfixpago", "cod_nextfixpatarec", "cod_nextfixpatapago",
        "cod_m_collagcat", "imp_pl_csnfcp2", "imp_pl_csnfrv2", "imp_pl_rvu2", "cod_pl_inscur", "cod_vegaquot", "imp_total_ffc",
        "cod_tp_dtefxgf", "cod_tp_dtefxgl", "cod_tp_dtefst", "cod_tp_dtelst", "imp_tp_rtamc00", "imp_tp_rtamc10",
        "cod_tp_rtdpn00", "cod_tp_rtdpn10", "cod_tp_rtdxc00", "cod_tp_rtdxc10", "cod_tp_rtdxg00", "cod_tp_rtdxg10",
        "imp_tp_rtfxc00", "imp_tp_rtfxc10", "cod_model", "fec_dtesys", "cod_tp_nomcur", "cod_divpago", "cod_divrecibo",
        "xti_compraventa", "fec_ultfixpago", "fec_ultfixrecibo", "imp_mvpago", "imp_mvrecibo", "imp_mvcontrasala",
        "imp_mvcontraeur", "imp_plpago", "imp_plrecibo", "imp_plcontrasala", "imp_plcontraeur", "cod_aplicacionfront",
        "ind_adj", "des_ficorig", "xti_murex", "cod_region"});

    BeanWrapperFieldSetMapper<CounterPartCatalogo> fieldSetMapper = new BeanWrapperFieldSetMapper<>(){
      @Override
      public CounterPartCatalogo mapFieldSet(FieldSet fielSet) {
        CounterPartCatalogo curlPlCatalogo = new CounterPartCatalogo();
        curlPlCatalogo.setValue(fielSet.readString("cod_counterpart"));
        return curlPlCatalogo;
      }
    };

    lineMapper.setLineTokenizer(tokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    reader.setLineMapper(lineMapper);
    return reader;

  }



  @Bean
  public Job job1(JobBuilderFactory jobBuilderFactory, Step jsonstep, JobCompletionNotificationListener listener) {
    return jobBuilderFactory.get("job1")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .start(jsonstep)
            .build();
  }
/*  @Bean
  public Job job1(JobBuilderFactory jobBuilderFactory, Step processNumoperfrontStep, Step processCurStep,
                  Step processCounterStep, Step processAriadnaStep ,Step processFenergoWorkFlowStep,Step jsonstep, JobCompletionNotificationListener listener) {

    return jobBuilderFactory.get("job1")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        //.start(processAriadnaStep)
        .start(jsonstep)
        //.next(processCounterStep)
        //.next(processCurStep)
        //.next(processNumoperfrontStep)
        .build();
  }*/


  /**
   *
   * @param stepBuilderFactory
   * @param numoperfrontReader
   * @param numoperfrontProcessor
   * @param numoperfrontWriter
   * @return
   */
  @Bean
  public Step processNumoperfrontStep(StepBuilderFactory stepBuilderFactory, ItemReader<NumoperfrontCatalogo> numoperfrontReader,
                                      NumoperfrontProcessor numoperfrontProcessor, UniqueNumoperfrontWriter numoperfrontWriter) {
    return stepBuilderFactory.get("processStep")
        .<NumoperfrontCatalogo, NumoperfrontCatalogo>chunk(10000)
        .reader(numoperfrontReader)
        .processor(numoperfrontProcessor)
        .writer(numoperfrontWriter)
        .faultTolerant()
        .skipLimit(100000)
        .skipPolicy(new DuplicateRecordSkipPolicy()) //los registros duplicados serán omitidos durante el proceso de escritura, y el batch continuará procesando los demás registros.
        .taskExecutor(taskExecutor()) // Usar TaskExecutor para paralelismo
        .throttleLimit(20) // Limitar el número de hilos concurrentes
        .build();
  }

  @Bean
  public Step jsonstep(StepBuilderFactory stepBuilderFactory,
                                         JsonItemReader<MyObject> jsonItemReader,
                                         WorkflowProcessor workflowProcessor,
                                         WorkFlowWriter workflowWriter) {
    return stepBuilderFactory.get("processFenergoWorkflow")
            .<MyObject, MyObject>chunk(10)
            .reader(jsonItemReaderPrueba())
            .processor(jsonItemProcessor())
            .writer(jsonItemWriter())
            .build();
  }

/*  @Bean
  public Step processWorkflowStep(StepBuilderFactory stepBuilderFactory,
                                  ItemReader<FenergoWrapper> jsonItemReader,
                                  ItemProcessor<FenergoWrapper, List<Workflow>> workflowProcessor,
                                  ItemProcessor<List<Workflow>, Workflow> workflowFlattener,
                                  ItemWriter<Workflow> workflowWriter) {

    return stepBuilderFactory.get("processWorkflowStep")
            .<FenergoWrapper, Workflow>chunk(10)
            .reader(jsonItemReader)
            .processor(new CompositeItemProcessor<>(workflowProcessor, workflowFlattener))
            .writer(workflowWriter)
            .build();
  }*/


  @Bean
  public Step processAriadnaStep(StepBuilderFactory stepBuilderFactory, ItemReader<Ariadna> ariadnaReader,
                                 ItemProcessor <Ariadna,Ariadna> processorAriadna, UniqueAriadnaWriter ariadnaWriter) {
    return stepBuilderFactory.get("processAriadnaStep")
            .<Ariadna, Ariadna>chunk(10)
            .reader(ariadnaReader)
            .processor(processorAriadna)
            .writer(ariadnaWriter)
            .faultTolerant()
            .skipLimit(100000)
            .skipPolicy(new DuplicateRecordSkipPolicy()) //los registros duplicados serán omitidos durante el proceso de escritura, y el batch continuará procesando los demás registros.
            .taskExecutor(taskExecutor()) // Usar TaskExecutor para paralelismo
            .throttleLimit(20) // Limitar el número de hilos concurrentes
            .build();
  }

  @Bean
  public Step processCurStep(StepBuilderFactory stepBuilderFactory, ItemReader<CurlPlCatalogo> curPlReader,
                             CurlPlProcessor curProcessor, UniqueCurlPlWriter curWriter) {
    return stepBuilderFactory.get("processStep")
        .<CurlPlCatalogo, CurlPlCatalogo>chunk(10000)
        .reader(curPlReader)
        .processor(curProcessor)
        .writer(curWriter)
        .faultTolerant()
        .skipLimit(100000)
        .skipPolicy(new DuplicateRecordSkipPolicy()) //los registros duplicados serán omitidos durante el proceso de escritura, y el batch continuará procesando los demás registros.
        .taskExecutor(taskExecutor()) // Usar TaskExecutor para paralelismo
        .throttleLimit(20) // Limitar el número de hilos concurrentes
        .build();
  }

  @Bean
  public Step processCounterStep(StepBuilderFactory stepBuilderFactory, ItemReader<CounterPartCatalogo> counterReader,
                                 CounterPartProcessor counterProcessor, UniqueCounterPartWriter counterWriter) {
    return stepBuilderFactory.get("processStep")
        .<CounterPartCatalogo, CounterPartCatalogo>chunk(2000)
        .reader(counterReader)
        .processor(counterProcessor)
        .writer(counterWriter)
        .faultTolerant()
        .skipLimit(100)
        .skipPolicy(new DuplicateRecordSkipPolicy()) //los registros duplicados serán omitidos durante el proceso de escritura, y el batch continuará procesando los demás registros.
        .taskExecutor(taskExecutor()) // Usar TaskExecutor para paralelismo
        .throttleLimit(20) // Limitar el número de hilos concurrentes
        .build();
  }

  @Bean
  public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(10); // Número mínimo de hilos que se mantendrán en el pool
    taskExecutor.setMaxPoolSize(20); // Número máximo de hilos permitidos en el pool
    taskExecutor.setQueueCapacity(50); // Capacidad de la cola para tareas pendientes
    return taskExecutor;
  }

  @Bean
  MurexItemProcessor processor() {
    return new MurexItemProcessor();
  }


}
