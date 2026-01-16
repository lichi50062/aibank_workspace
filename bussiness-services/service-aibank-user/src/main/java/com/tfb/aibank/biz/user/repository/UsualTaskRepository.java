package com.tfb.aibank.biz.user.repository;

import com.tfb.aibank.biz.user.repository.entities.UsualTaskEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.UsualTaskEntityPk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UsualTaskRepository extends JpaRepository<UsualTaskEntity, UsualTaskEntityPk>, JpaSpecificationExecutor<UsualTaskEntity> {

    List<UsualTaskEntity> findByCompanyKeyAndUserKeyOrderByTaskSortAsc(Integer companyKey, Integer userKey);

}