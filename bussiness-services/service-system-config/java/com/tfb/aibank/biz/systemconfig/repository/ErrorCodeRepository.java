package com.tfb.aibank.biz.systemconfig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.systemconfig.repository.entities.ErrorCodeEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.pk.ErrorCodeEntityPk;

// @formatter:off
/**
 * @(#)ErrorCodeRepository.java
 * 
 * <p>Description:錯誤代碼 repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Transactional(readOnly = true)
public interface ErrorCodeRepository extends JpaRepository<ErrorCodeEntity, ErrorCodeEntityPk> {

    /**
     * 依 category 及 locale
     * 
     * @param category
     * @param locale
     * @return
     */
    @Query("select e from ErrorCodeEntity e where e.systemId in :systemId and e.locale in :locale")
    List<ErrorCodeEntity> findBySystemIdAndLocale(@Param("systemId") String[] systemId, @Param("locale") String[] locale);

}
