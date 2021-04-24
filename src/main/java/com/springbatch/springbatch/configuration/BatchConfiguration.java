package com.springbatch.springbatch.configuration;

import com.springbatch.springbatch.domain.ArquivoEntrada;
import com.springbatch.springbatch.domain.MyFlatFileWriter;
import com.springbatch.springbatch.processor.ArquivoProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Environment env;

    private Resource outputResource = new FileSystemResource("data/outputData.csv");
    private Resource inputResource = new FileSystemResource("data/202004EN.txt");
    @Bean
    public FlatFileItemReader<ArquivoEntrada> arquivoEntradaFlatFileItemReader(){
        FlatFileItemReader<ArquivoEntrada> reader = new FlatFileItemReader<>();
        //Pular a linha do cabeçalho
        reader.setLinesToSkip(1);
        reader.setResource(inputResource);
        reader.setStrict(true);
        DefaultLineMapper<ArquivoEntrada> arquivoEntradaDefaultLineMapper = new DefaultLineMapper<>();

        FixedLengthTokenizer tokenizer = fixedLengthTokenizer();
        //DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        //tokenizer.setNames("numero");

        arquivoEntradaDefaultLineMapper.setLineTokenizer(tokenizer);
       // arquivoEntradaDefaultLineMapper.setFieldSetMapper(new ArquivoEntradaFieldSetMapper());
        arquivoEntradaDefaultLineMapper.afterPropertiesSet();
        reader.setLineMapper(arquivoEntradaDefaultLineMapper);

        return reader;
    }

    @Bean
    public FixedLengthTokenizer fixedLengthTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        tokenizer.setNames("CODIGO", "DESCRICAO", "TEXTO", "TEXTO");
        tokenizer.setColumns(new Range(1, 13),
                new Range(14, 60),
                new Range(69, 104),
                new Range(104, 219));

        return tokenizer;
    }

    @Bean(name = "writerBean")
    public ItemWriter<ArquivoEntrada> arquivoEntradaItemWriter(){
        FlatFileItemWriter<ArquivoEntrada> writer = new FlatFileItemWriter<ArquivoEntrada>();
        writer.setResource(outputResource);

        DelimitedLineAggregator<ArquivoEntrada> lineAggregator = new DelimitedLineAggregator<ArquivoEntrada>();
        lineAggregator.setDelimiter(";");

        writer.setHeaderCallback(new MyFlatFileWriter());
        BeanWrapperFieldExtractor<ArquivoEntrada> fieldExtractor = new BeanWrapperFieldExtractor<ArquivoEntrada>();
        fieldExtractor.setNames(new String[]{"numero","parOuImpar","multiplo17","resto17"});
        lineAggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(lineAggregator);
        return writer;
    }

    @Bean
    public ArquivoProcessor processor(){
        //Classe que irá montar o Arquivo de saída
        return new ArquivoProcessor();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<ArquivoEntrada, ArquivoEntrada>chunk(10)
                .reader(arquivoEntradaFlatFileItemReader())
                .processor(processor())
               .writer(arquivoEntradaItemWriter())
                .build();
    }

    @Bean
    public Job readTxtFilesJob() {
        return jobBuilderFactory
                .get("readTxtFilesJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

/*    @Bean
    public JdbcBatchItemWriter<Person> writer(@Qualifier("localdbSource") final DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO tablename (column_name,column_name) VALUES (:filed1, :filed1)")
                .dataSource(dataSource)
                .build();
    }*/

/*    @Bean
    public DataSource localdbSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl("spring.datasource.url");
        dataSource.setUsername("spring.datasource.username");
        dataSource.setPassword("spring.datasource.password");
        return dataSource;
    }*/

    class ArquivoEntradaLineMapper  implements LineMapper<ArquivoEntrada> {

        @Override
        public ArquivoEntrada mapLine(String s, int rowNum) throws Exception {
            ArquivoEntrada arquivoEntrada = new ArquivoEntrada();
            return arquivoEntrada;
        }
    }
}