package com.tfb.aibank.chl.general.qu004.model;

// @formatter:off
import com.ibm.tw.ibmb.base.model.ClientRequest;import java.util.Locale; /**
 * @(#)NGNQU005Output.java
 * 
 * <p>Description:所有服務 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, MartyP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNQU004Input  {

    private Locale locale;

    private ClientRequest clientRequest;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ClientRequest getClientRequest() {
        return clientRequest;
    }

    public void setClientRequest(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }
}
