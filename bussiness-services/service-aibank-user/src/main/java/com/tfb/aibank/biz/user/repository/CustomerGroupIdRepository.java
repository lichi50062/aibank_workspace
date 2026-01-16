package com.tfb.aibank.biz.user.repository;

import com.tfb.aibank.biz.user.repository.entities.CustomerGroupIdEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.CustomerGroupIdEntityPk;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerGroupIdRepository extends JpaRepository<CustomerGroupIdEntity, CustomerGroupIdEntityPk> {


}