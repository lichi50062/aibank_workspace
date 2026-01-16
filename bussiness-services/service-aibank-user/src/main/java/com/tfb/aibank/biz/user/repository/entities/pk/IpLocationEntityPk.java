package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;
import java.util.Objects;

/**
 * @(#)IpLocationEntityPk.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/20, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class IpLocationEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long ipFrom;
    private Long ipTo;
    
    public IpLocationEntityPk() {}
    
    public IpLocationEntityPk(Long ipFrom, Long ipTo) {
        this.ipFrom = ipFrom;
        this.ipTo = ipTo;
    }
    
    // Getters and Setters
    public Long getIpFrom() { return ipFrom; }
    public void setIpFrom(Long ipFrom) { this.ipFrom = ipFrom; }
    
    public Long getIpTo() { return ipTo; }
    public void setIpTo(Long ipTo) { this.ipTo = ipTo; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpLocationEntityPk that = (IpLocationEntityPk) o;
        return Objects.equals(ipFrom, that.ipFrom) && Objects.equals(ipTo, that.ipTo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(ipFrom, ipTo);
    }

}
