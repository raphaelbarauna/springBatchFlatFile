package com.springbatch.springbatch.configuration;

import com.springbatch.springbatch.domain.ArquivoEntrada;
import com.springbatch.springbatch.domain.ControleArquivo;
import com.springbatch.springbatch.repository.ControleArquivoRepository;
import com.springbatch.springbatch.repository.custom.JdbcTemplateBulkOperations;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@Log4j2
public class CustomJobLauncher {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    ApplicationContext context;

    @Autowired
    JdbcTemplateBulkOperations bulkOperations;

    @Autowired
    ControleArquivoRepository controleArquivoRepository;

    @Scheduled(cron = "*/10 * * * * *")
    public void myScheduler() {

        try {

            File[] files = new File("data/").listFiles();
            List<ControleArquivo> controleArquivos = new ArrayList<>();

            for (File file : files) {
                if (controleArquivoRepository.findByName(file.getName()).isPresent()) {

                    log.info("Arquivo " + file.getName() + " no diretorio" + file.getPath() + "já cadastrado na tabela de controle.");

                } else {

//                    FlatFileItemReader<ArquivoEntrada> bean = (FlatFileItemReader<ArquivoEntrada>) context.getBean("readerBean");
//
//                    bean.setResource(new FileSystemResource(file.getPath()));
                    JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                            .toJobParameters();
                    JobExecution jobExecution = jobLauncher.run(job, jobParameters);
                    System.out.println("Job's Status:::" + jobExecution.getStatus());

                    ControleArquivo controleArquivo = new ControleArquivo(null, file.getName(), file.getPath(), new Date());
                    controleArquivoRepository.save(controleArquivo);
                }
            }
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobParametersInvalidException | JobRestartException e) {
            e.printStackTrace();
        }
    }


//    @Scheduled(cron = "*/10 * * * * *")
//    public void myScheduler() {
//
//        try {
//            ArquivoEntrada arquivoEntrada = new ArquivoEntrada();
//            arquivoEntrada.setCodigo("Teste1");
//            arquivoEntrada.setDescricao("Teste1");
//            arquivoEntrada.setTexto01("teste1");
//            arquivoEntrada.setTexto02("teste1");
//            arquivoEntrada.setTexto03("teste1");
//            arquivoEntrada.setTexto04("teste1");
//
//            ArquivoEntrada arquivoEntrada2 = new ArquivoEntrada();
//            arquivoEntrada.setCodigo("Teste2");
//            arquivoEntrada.setDescricao("Teste2");
//            arquivoEntrada.setTexto01("teste2");
//            arquivoEntrada.setTexto02("teste2");
//            arquivoEntrada.setTexto03("teste2");
//            arquivoEntrada.setTexto04("teste2");
//
//            List<ArquivoEntrada> entidades = new ArrayList<>();
//            entidades.add(arquivoEntrada);
//            entidades.add(arquivoEntrada);
//
//            bulkOperations.bulkPersist(entidades);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}