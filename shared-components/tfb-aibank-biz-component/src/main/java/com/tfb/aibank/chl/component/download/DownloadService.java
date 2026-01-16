package com.tfb.aibank.chl.component.download;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.ibmb.base.model.ClientRequest;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;

@Service
public class DownloadService {

    private static final String DOWNLOAD_ID_FORMAT = "DOWNLOAD:%s";
    
    @Autowired
    @Qualifier("stringRedisTemplate")
    private RedisTemplate<String, String> template;
    
    public String prepareDownload(AbstractAIBankBaseTask<?, ?> task, String downloadResourcePath) {

        ClientRequest req = task.getRequest();
        req.setResource(downloadResourcePath);
        Map<String, String> rqData = new HashMap<>();
        rqData.put("downloadId", getDownloadId());
        req.setRqData(rqData);
        String downloadReqJson = GsonHelper.newInstance().toJson(req);
        String downloadKey = DigestUtils.sha256Hex(downloadReqJson);
        // 依 task timeout 時間設定保留
        template.opsForValue().set(downloadKey, downloadReqJson, Duration.of(task.getTimeout(), ChronoUnit.MINUTES));
        
        return downloadKey;
    }
    
    public void clearDownload(String downloadKey) {
        template.delete(downloadKey);
    }

    private String getDownloadId() {
        return String.format(DOWNLOAD_ID_FORMAT, UUID.randomUUID().toString());
    }
}
