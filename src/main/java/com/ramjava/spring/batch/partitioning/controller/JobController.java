package com.ramjava.spring.batch.partitioning.controller;

import com.ramjava.spring.batch.partitioning.entity.Customer;
import com.ramjava.spring.batch.partitioning.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    // Trigger the job
    @Autowired
    private JobLauncher jobLauncher;
    // Object of Job
    @Autowired
    private Job job;
    @Autowired
    CustomerRepository repository;

    @PostMapping("/importCustomers")
    public void importCsvToDbJob() {
        // Pass Job object as a paramater
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        // Trigger the job
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
}
