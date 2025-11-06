package com.yzl.it.controller;

import com.yzl.it.model.JobPosting;
import com.yzl.it.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobPostingController {
    
    private final JobPostingService jobPostingService;
    
    @Autowired
    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }
    
    // 添加新岗位
    @PostMapping
    public ResponseEntity<JobPosting> addJobPosting(@RequestBody JobPosting jobPosting) {
        JobPosting savedJob = jobPostingService.addJobPosting(jobPosting);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }
    
    // 获取所有岗位
    @GetMapping
    public ResponseEntity<List<JobPosting>> getAllJobPostings() {
        List<JobPosting> jobs = jobPostingService.getAllJobPostings();
        return ResponseEntity.ok(jobs);
    }
    
    // 根据ID获取岗位
    @GetMapping("/{id}")
    public ResponseEntity<JobPosting> getJobPostingById(@PathVariable Long id) {
        return jobPostingService.getJobPostingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // 更新岗位信息
    @PutMapping("/{id}")
    public ResponseEntity<JobPosting> updateJobPosting(@PathVariable Long id, @RequestBody JobPosting jobPosting) {
        try {
            JobPosting updatedJob = jobPostingService.updateJobPosting(id, jobPosting);
            return ResponseEntity.ok(updatedJob);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // 删除岗位
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        try {
            jobPostingService.deleteJobPosting(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // 搜索接口
    @GetMapping("/search")
    public ResponseEntity<List<JobPosting>> searchJobPostings(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) String status) {
        
        List<JobPosting> results;
        
        if (status != null) {
            results = jobPostingService.filterByStatus(status);
        } else if (companyName != null && jobTitle != null) {
            results = jobPostingService.searchByCompanyAndJobTitle(companyName, jobTitle);
        } else if (companyName != null) {
            results = jobPostingService.searchByCompanyName(companyName);
        } else if (jobTitle != null) {
            results = jobPostingService.searchByJobTitle(jobTitle);
        } else {
            results = jobPostingService.getAllJobPostings();
        }
        
        return ResponseEntity.ok(results);
    }
}