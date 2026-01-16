package com.tfb.aibank.biz.pushlistener.repository.entities.pk;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

//@formatter:off
/**
 * @(#)OfferNotificationInfoEntityPk.java
 *
 * <p>OfferNotificationInfoEntityPk Pk</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class OfferNotificationInfoEntityPk implements Serializable {
    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    public Integer offerMasterKey;

    public String templateId;

    public OfferNotificationInfoEntityPk() {
    }

    public OfferNotificationInfoEntityPk(String str) {
        fromString(str);
    }

    /**
     * @return the offerMasterKey
     */
    public Integer getOfferMasterKey() {
        return offerMasterKey;
    }

    /**
     * @param offerMasterKey
     *            the offerMasterKey to set
     */
    public void setOfferMasterKey(Integer offerMasterKey) {
        this.offerMasterKey = offerMasterKey;
    }

    /**
     * @return the templateId
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId
     *            the templateId to set
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return offerMasterKey + "::" + templateId;
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((offerMasterKey == null) ? 0 : offerMasterKey.hashCode());
        rs = rs * 37 + ((templateId == null) ? 0 : templateId.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        OfferNotificationInfoEntityPk other = (OfferNotificationInfoEntityPk) obj;
        return ((offerMasterKey == null && other.offerMasterKey == null) || (offerMasterKey != null && offerMasterKey.equals(other.offerMasterKey))) && ((templateId == null && other.templateId == null) || (templateId != null && templateId.equals(other.templateId)));
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            offerMasterKey = null;
        else
            offerMasterKey = NumberUtils.toInt(str);
        str = toke.nextToken();
        if ("null".equals(str))
            templateId = null;
        else
            templateId = str;
    }

    protected static class Tokenizer {
        private final String str;

        private int last;

        public Tokenizer(String str) {
            this.str = str;
        }

        public String nextToken() {
            int next = str.indexOf("::", last);
            String part;
            if (next == -1) {
                part = str.substring(last);
                last = str.length();
            }
            else {
                part = str.substring(last, next);
                last = next + 2;
            }
            return part;
        }
    }
}
