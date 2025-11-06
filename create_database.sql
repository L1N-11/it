-- 创建数据库
CREATE DATABASE IF NOT EXISTS it_job_tracker DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用创建的数据库
USE it_job_tracker;

-- 创建岗位信息表
CREATE TABLE IF NOT EXISTS job_posting (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '岗位ID',
    company_name VARCHAR(255) NOT NULL COMMENT '企业名称',
    job_title VARCHAR(255) NOT NULL COMMENT '岗位名称',
    salary VARCHAR(100) COMMENT '薪资',
    requirements TEXT COMMENT '具体要求',
    location VARCHAR(255) COMMENT '工作地点',
    description TEXT COMMENT '岗位描述',
    application_date DATE COMMENT '申请日期',
    status VARCHAR(50) DEFAULT '待处理' COMMENT '申请状态',
    notes TEXT COMMENT '备注信息',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建索引以优化查询性能
CREATE INDEX idx_company_name ON job_posting(company_name);
CREATE INDEX idx_job_title ON job_posting(job_title);
CREATE INDEX idx_status ON job_posting(status);
CREATE INDEX idx_application_date ON job_posting(application_date);

-- 插入示例数据
INSERT INTO job_posting (company_name, job_title, salary, requirements, location, description, application_date, status, notes)
VALUES 
('阿里巴巴', 'Java开发工程师', '25k-40k', '3年以上Java开发经验，熟悉Spring Boot框架', '杭州', '负责电商平台后端开发', '2024-10-01', '面试中', '技术一面已通过'),
('腾讯', '前端开发工程师', '20k-35k', '精通React/Vue框架，熟悉前端工程化', '深圳', '负责产品界面开发', '2024-10-05', '已投递', '等待HR联系'),
('字节跳动', '全栈开发工程师', '30k-50k', '熟悉前后端技术栈，有微服务开发经验', '北京', '负责内部系统开发', '2024-10-10', '待处理', '准备简历优化'),
('美团', '数据分析师', '20k-30k', '熟悉SQL和Python，有数据分析经验', '北京', '负责业务数据分析', '2024-10-15', '已拒绝', '薪资不符合预期');

-- 显示创建的表结构
SHOW TABLES;
DESCRIBE job_posting;

-- 显示插入的数据
SELECT * FROM job_posting;