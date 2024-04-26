package com.example.Jobms.Job;


import com.example.Jobms.Job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {

    List<JobWithCompanyDTO> findAll();
    void createJobs(Job job);

    public Job findSingleJob(Long Id);

    String deleteJob(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
