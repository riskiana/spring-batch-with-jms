package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.jsr.step.batchlet.BatchletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SendEmailStep sendEmailStep;

    @Bean
    public Step step() {
        return stepBuilderFactory.get("myStep")
                .tasklet(new BatchletAdapter(sendEmailStep))
                .build();
    }

    @Bean
    public Job job(Step step) throws Exception {
        return jobBuilderFactory.get("myJob")
                .start(step)
                .build();
    }

}