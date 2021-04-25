package com.springbatch.springbatch.step;

import com.springbatch.springbatch.domain.ArquivoEntrada;
import com.springbatch.springbatch.listener.LoggingStepStartStopListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class LeituraArquivoLarguraFixaStepConfig {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

   @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public Step leituraArquivoLarguraFixaStep(TaskExecutor taskExecutor, ItemReader<ArquivoEntrada> leituraArquivoLarguraFixaStep,
                                              ItemWriter<ArquivoEntrada> leituraArquivoLarguraFixaWriter){
        return stepBuilderFactory
                .get("leituraArquivoLarguraFixaStep")
                .<ArquivoEntrada, ArquivoEntrada>chunk(10000)
                .reader(leituraArquivoLarguraFixaStep)
                .writer(leituraArquivoLarguraFixaWriter)
                .listener(new LoggingStepStartStopListener())
                .taskExecutor(taskExecutor)
                .throttleLimit(10)
                .build();

    }
}
