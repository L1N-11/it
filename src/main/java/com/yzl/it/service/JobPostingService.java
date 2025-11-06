package com.yzl.it.service;

import com.yzl.it.model.JobPosting;

import java.util.List;
import java.util.Optional;

public interface JobPostingService {
    
    // 添加新岗位
    JobPosting addJobPosting(JobPosting jobPosting);
    
    // 获取所有岗位
    List<JobPosting> getAllJobPostings();
    
    // 根据ID获取岗位
    Optional<JobPosting> getJobPostingById(Long id);
    
    // 更新岗位信息
    JobPosting updateJobPosting(Long id, JobPosting jobPosting);
    
    // 删除岗位
    void deleteJobPosting(Long id);
    
    // 按企业名称搜索
    List<JobPosting> searchByCompanyName(String companyName);
    
    // 按岗位名称搜索
    List<JobPosting> searchByJobTitle(String jobTitle);
    
    // 按状态筛选
    List<JobPosting> filterByStatus(String status);
    
    // 组合搜索
    List<JobPosting> searchByCompanyAndJobTitle(String companyName, String jobTitle);
}