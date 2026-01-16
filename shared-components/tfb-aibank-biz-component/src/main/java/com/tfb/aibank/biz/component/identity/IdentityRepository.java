/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.component.identity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

// @formatter:off
/**
 * @(#)CompanyRepository.java
 * 
 * <p>Description:識別資料 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class IdentityRepository {

    private static final IBLog logger = IBLog.getLog(IdentityRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public IdentityRepository() {
        // default constructor
    }

    public IdentityData query(String cIxd, String dup, String uxd, Integer companyKind) {
        cIxd = ValidateParamUtils.validParam(cIxd);
        dup = ValidateParamUtils.validParam(dup);
        uxd = ValidateParamUtils.validParam(uxd);
        logger.warn("為追查原因，添加 LOG。 ");
        logger.warn("cIxd=" + cIxd);
        logger.warn("dup=" + dup);
        //logger.warn("uxd=" + uxd);
        String sql = "SELECT C.COMPANY_KEY, C.STATUS AS COMPANY_STATUS, U.USER_KEY, U.STATUS AS USER_STATUS FROM COMPANY C JOIN USER_PROFILE U ON C.COMPANY_KEY = U.COMPANY_KEY WHERE C.COMPANY_UID = ? AND C.UID_DUP = ? AND C.COMPANY_KIND = ? AND U.USER_UUID = ? AND C.STATUS = 1 AND U.STATUS = 1";
        List<IdentityData> list = jdbcTemplate.query(sql, new ConvertRowMapper(), new Object[] { cIxd, dup, companyKind, uxd });

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("查無符合紀錄。 SQL = {}", sql);
            return null;
        }
        return list.get(0);
    }

    /**
     * 僅查詢 CompanyKey
     * 
     * @param custId
     * @param dup
     * @return
     */
    public IdentityData queryForCompany(String cIxd, String dup) {
        String sql = "SELECT COMPANY_KEY, STATUS AS COMPANY_STATUS FROM COMPANY WHERE COMPANY_UID = ? AND UID_DUP = ? AND STATUS = 1";
        Object[] params = { ValidateParamUtils.validParam(cIxd), ValidateParamUtils.validParam(dup) };
        List<IdentityData> list = jdbcTemplate.query(sql, new ConvertRowMapperForCompany(), params);

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("查無符合紀錄。 SQL = {}", sql);
            return new IdentityData();
        }
        return list.get(0);
    }

    public List<IdentityData> queryMultiUser(String custIxd, String dup) {
        String sql = "SELECT C.COMPANY_KEY, C.STATUS AS COMPANY_STATUS, U.USER_KEY, U.STATUS AS USER_STATUS FROM COMPANY C JOIN USER_PROFILE U ON C.COMPANY_KEY = U.COMPANY_KEY WHERE C.COMPANY_UID = ? AND C.UID_DUP = ? AND C.COMPANY_KIND <> 3 AND C.COMPANY_KIND <> 4";
        List<IdentityData> list = jdbcTemplate.query(sql, new ConvertRowMapper(), new Object[] { ValidateParamUtils.validParam(custIxd), ValidateParamUtils.validParam(dup) });
        if (CollectionUtils.isEmpty(list)) {
            logger.warn("查無符合紀錄。 SQL = {}", sql);
            return new ArrayList<>();
        }
        return list;
    }

    class ConvertRowMapper implements RowMapper<IdentityData> {
        @Override
        public IdentityData mapRow(ResultSet rs, int rowNum) throws SQLException {
            IdentityData data = new IdentityData();
            data.setCompanyKey(rs.getInt("COMPANY_KEY"));
            data.setCompanyStatus(rs.getInt("COMPANY_STATUS"));
            data.setUserKey(rs.getInt("USER_KEY"));
            data.setUserStatus(rs.getInt("USER_STATUS"));
            return data;
        }
    }

    class ConvertRowMapperForCompany implements RowMapper<IdentityData> {
        @Override
        public IdentityData mapRow(ResultSet rs, int rowNum) throws SQLException {
            IdentityData data = new IdentityData();
            data.setCompanyKey(rs.getInt("COMPANY_KEY"));
            data.setCompanyStatus(rs.getInt("COMPANY_STATUS"));
            return data;
        }
    }

}
