package com.tfb.aibank.biz.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tfb.aibank.biz.user.repository.entities.IpLocationBEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.IpLocationEntityPk;

@Repository
public interface IpLocationBRepository extends JpaRepository<IpLocationBEntity, IpLocationEntityPk> {
    
    @Query("SELECT i FROM IpLocationBEntity i WHERE :ipNumber BETWEEN i.ipFrom AND i.ipTo")
    Optional<IpLocationBEntity> findByIpNumber(@Param("ipNumber") Long ipNumber);

}
