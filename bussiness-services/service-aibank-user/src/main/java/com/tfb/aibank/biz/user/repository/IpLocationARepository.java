package com.tfb.aibank.biz.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tfb.aibank.biz.user.repository.entities.IpLocationAEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.IpLocationEntityPk;

@Repository
public interface IpLocationARepository extends JpaRepository<IpLocationAEntity, IpLocationEntityPk> {

    
   @Query("SELECT i FROM IpLocationAEntity i WHERE :ipNumber BETWEEN i.ipFrom AND i.ipTo")
   Optional<IpLocationAEntity> findByIpNumber(@Param("ipNumber") Long ipNumber);

}
