package com.yzl.it.repository;

import com.yzl.it.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 更新注释
@Repository//20234069杨宗霖
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    
    // 按企业名称查询
    List<JobPosting> findByCompanyNameContaining(String companyName);
    
    // 按岗位名称查询
    List<JobPosting> findByJobTitleContaining(String jobTitle);
    
    // 按状态查询
    List<JobPosting> findByStatus(String status);
    
    // 按企业名称和岗位名称组合查询
    List<JobPosting> findByCompanyNameContainingAndJobTitleContaining(String companyName, String jobTitle);
}