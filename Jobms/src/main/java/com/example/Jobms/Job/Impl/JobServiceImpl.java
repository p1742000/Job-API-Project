package com.example.Jobms.Job.Impl;

import com.example.Jobms.Job.JobService;
import com.example.Jobms.Job.JobRepository;
import com.example.Jobms.Job.Job;
import com.example.Jobms.Job.dto.JobWithCompanyDTO;
import com.example.Jobms.Job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();
    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    //    variable to have unique IDs and it's validation
//    private Long nextId = 1L;
    @Override
    public List<JobWithCompanyDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        for (Job job: jobs
             ) {
            Company company = restTemplate.getForObject("http://127.0.0.1:8081/companies" + job.getCompanyId(), Company.class);
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO(job,company);
            jobWithCompanyDTOs.add(jobWithCompanyDTO);
        }
        return jobWithCompanyDTOs;
    }

    @Override
    public void createJobs(Job job) {
//        job.setId(nextId++);
//        jobs.add(job);
        jobRepository.save(job);
    }

    public Job findSingleJob(Long Id){
//        for (Job job: jobs
//             ) {
//           if(job.getId().equals(Id)) return job;
//        }
//        return null;
       return jobRepository.findById(Id).orElse(null);
    }

    @Override
    public String deleteJob(Long Id) {
//        for (Job job: jobs
//             ) {
//            if(job.getId().equals(Id)) {
//                jobs.remove(job);
//                return "Successfully deleted Job.";
//            }
//        }
//        return "";
        try{
            jobRepository.deleteById(Id);
            return "Successfully deleted Job.";
        }catch (Exception e){
            return "";
        }

    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
//        for (Job job:jobs
//             ) {
//            if(job.getId().equals(id)){
//                jobs.set(jobs.indexOf(job), updatedJob);
//                return true;
//            }
//        }
//        return false;
    }
}
