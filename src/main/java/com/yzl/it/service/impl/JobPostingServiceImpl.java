package com.yzl.it.service.impl;

import com.yzl.it.model.JobPosting;
import com.yzl.it.repository.JobPostingRepository;
import com.yzl.it.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostingServiceImpl implements JobPostingService {
    
    private final JobPostingRepository jobPostingRepository;
    
    @Autowired
    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }
    
    @Override
    public JobPosting addJobPosting(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }
    
    @Override
    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }
    
    @Override
    public Optional<JobPosting> getJobPostingById(Long id) {
        return jobPostingRepository.findById(id);
    }
    
    @Override
    public JobPosting updateJobPosting(Long id, JobPosting jobPosting) {
        Optional<JobPosting> existingJob = jobPostingRepository.findById(id);
        if (existingJob.isPresent()) {
            JobPosting updatedJob = existingJob.get();
            updatedJob.setCompanyName(jobPosting.getCompanyName());
            updatedJob.setJobTitle(jobPosting.getJobTitle());
            updatedJob.setSalary(jobPosting.getSalary());
            updatedJob.setRequirements(jobPosting.getRequirements());
            updatedJob.setLocation(jobPosting.getLocation());
            updatedJob.setDescription(jobPosting.getDescription());
            updatedJob.setApplicationDate(jobPosting.getApplicationDate());
            updatedJob.setStatus(jobPosting.getStatus());
            updatedJob.setNotes(jobPosting.getNotes());
            return jobPostingRepository.save(updatedJob);
        }
        throw new RuntimeException("Job posting not found with id: " + id);
    }
    
    @Override
    public void deleteJobPosting(Long id) {
        if (jobPostingRepository.existsById(id)) {
            jobPostingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Job posting not found with id: " + id);
        }
    }
    
    @Override
    public List<JobPosting> searchByCompanyName(String companyName) {
        return jobPostingRepository.findByCompanyNameContaining(companyName);
    }
    
    @Override
    public List<JobPosting> searchByJobTitle(String jobTitle) {
        return jobPostingRepository.findByJobTitleContaining(jobTitle);
    }
    
    @Override
    public List<JobPosting> filterByStatus(String status) {
        return jobPostingRepository.findByStatus(status);
    }
    
    @Override
    public List<JobPosting> searchByCompanyAndJobTitle(String companyName, String jobTitle) {
        return jobPostingRepository.findByCompanyNameContainingAndJobTitleContaining(companyName, jobTitle);
    }
}