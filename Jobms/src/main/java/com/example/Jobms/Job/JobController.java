package com.example.Jobms.Job;

import com.example.Jobms.Job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }


    @GetMapping("/jobs")
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        return new ResponseEntity(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJobs(@RequestBody Job job){
        jobService.createJobs(job);
        return new ResponseEntity("job is added successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{Id}")
    public ResponseEntity<Job> findSingleJob(@PathVariable Long Id){
        Job job = jobService.findSingleJob(Id);
        if( job != null) return new ResponseEntity(job, HttpStatus.OK);
//        return new Job(0L,"Job Application you are looking for is not found.","","","","");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{Id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long Id){
        String job = jobService.deleteJob(Id);
        if(job != "") return new ResponseEntity("Job deleted Successfully.", HttpStatus.OK);
        return new ResponseEntity<>("Job Id that you are looking for is not found", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/jobs/{Id}")
    public ResponseEntity<Boolean> updateJob(@PathVariable Long Id, @RequestBody Job updatedJob){
        boolean job = jobService.updateJob(Id, updatedJob);
        if(job) return new ResponseEntity("Job updated Successfully.", HttpStatus.OK);
        return new ResponseEntity("Job Id that you are looking for is not found.", HttpStatus.NOT_FOUND);
    }
}

