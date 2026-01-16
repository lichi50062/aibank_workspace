package com.tfb.aibank.biz.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.component.e2ee.E2EEUtils;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.security.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.security.repository.CertificateActLogDataRepository;
import com.tfb.aibank.biz.security.repository.DeviceModelRepository;
import com.tfb.aibank.biz.security.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.security.repository.MbGestureProfileRepository;
import com.tfb.aibank.biz.security.repository.MotpDeviceInfoRepository;
import com.tfb.aibank.biz.security.repository.MotpMidDataRepository;
import com.tfb.aibank.biz.security.repository.TrustDeviceRepository;
import com.tfb.aibank.biz.security.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.security.repository.aib.PushSubscriptionRepository;
import com.tfb.aibank.biz.security.services.devicebinding.DeviceBindingService;
import com.tfb.aibank.biz.security.services.fido.FidoService;
import com.tfb.aibank.biz.security.services.fido.helper.FidoServiceHelper;
import com.tfb.aibank.biz.security.services.fido2.Fido2Service;
import com.tfb.aibank.biz.security.services.mid.MidService;
import com.tfb.aibank.biz.security.services.motp.MotpAuthService;
import com.tfb.aibank.biz.security.services.motp.MotpBindService;
import com.tfb.aibank.biz.security.services.motp.MotpLogService;
import com.tfb.aibank.biz.security.services.motp.MotpProxyService;
import com.tfb.aibank.biz.security.services.motp.helper.MotpLogDataHelper;
import com.tfb.aibank.biz.security.services.motp.helper.MotpServiceHelper;
import com.tfb.aibank.biz.security.services.otp.OtpBindService;
import com.tfb.aibank.biz.security.services.serviceImpl.E2eeService;
import com.tfb.aibank.biz.security.services.twcaapi.TwcaApiServise;

//@formatter:off
/**
* @(#)ServiceFactory.java
* 
* <p>Description:ServiceFactory</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/09, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on

@Service
public class ServiceFactory {

    @Autowired
    private E2EEUtils e2eeUtils;

    @Autowired
    private IdentityService identityService;

    @Autowired
    FidoServiceHelper fidoServiceHelper;

    @Autowired
    MbDeviceInfoRepository mbDeviceInfoRepository;

    @Autowired
    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    @Autowired
    MbGestureProfileRepository mbGestureProfileRepository;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private MotpMidDataRepository motpMidDataRepository;

    @Autowired
    private MotpDeviceInfoRepository motpDeviceInfoRepository;

    @Autowired
    private MotpProxyService motpProxyService;

    @Autowired
    private MotpServiceHelper motpServiceHelper;

    @Autowired
    private EsbChannelGateway esbChannelGateway;

    @Autowired
    private DeviceModelRepository deviceModelRepository;

    @Autowired
    private MotpLogDataHelper motpLogDataHelper;

    @Autowired
    private CertificateActLogDataRepository certificateActLogDataRepository;

    @Autowired
    private PushSubscriptionRepository pushSubscriptionRepository;

    @Autowired
    private TrustDeviceRepository trustDeviceRepository;

    public E2eeService getE2eeService() {
        return new E2eeService(e2eeUtils);
    }

    public FidoService getFidoService() {
        return new FidoService(identityService, fidoServiceHelper, mbDeviceInfoRepository, mbGestureProfileRepository, mbDevicePushInfoRepository, trustDeviceRepository);
    }

    public DeviceBindingService getDeviceBindingService() {
        return new DeviceBindingService(identityService, mbDeviceInfoRepository, mbGestureProfileRepository, mbDevicePushInfoRepository, trustDeviceRepository);
    }

    public MidService getMidService() {
        return new MidService(identityService, systemParamCacheManager, motpMidDataRepository);
    }

    public MotpBindService getMotpBindService() {
        return new MotpBindService(identityService, motpProxyService, motpDeviceInfoRepository, motpMidDataRepository, mbDeviceInfoRepository, deviceModelRepository, motpLogDataHelper);
    }

    public MotpAuthService getMotpAuthService() {
        return new MotpAuthService(identityService, motpServiceHelper);
    }

    public OtpBindService getOtpBindService() {
        return new OtpBindService(esbChannelGateway);
    }

    public MotpLogService getMotpLogService() {
        return new MotpLogService(motpLogDataHelper);
    }

    public TwcaApiServise createTwcaApiService() {
        return new TwcaApiServise(identityService, systemParamCacheManager, certificateActLogDataRepository);
    }

    public Fido2Service getFido2Service() {
        return new Fido2Service(identityService, mbDeviceInfoRepository, mbDevicePushInfoRepository, pushSubscriptionRepository, systemParamCacheManager);
    }
}
