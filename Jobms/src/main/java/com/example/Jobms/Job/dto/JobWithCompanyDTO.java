package com.example.Jobms.Job.dto;

import com.example.Jobms.Job.Job;
import com.example.Jobms.Job.external.Company;

public class JobWithCompanyDTO {

    private Job job;
    private Company company;


    public JobWithCompanyDTO(Job job, Company company) {
        this.job = job;
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
