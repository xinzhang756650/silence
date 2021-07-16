package com.silence.tencent.service.impl;

import com.silence.common.utils.encryption.EncryptUtil;
import com.silence.tencent.config.TencentConfiguration;
import com.silence.tencent.service.TencentAnalysisTask;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionRequest;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionResponse;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class TencentAnalysisTaskImpl implements TencentAnalysisTask {

    @Autowired
    private TencentConfiguration tencentConfiguration;

    @Override
    public SentenceRecognitionResponse sentenceRecognition(SentenceRecognitionRequest req) throws Exception {
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint(tencentConfiguration.getRealmName());

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            AsrClient client = new AsrClient(tencentConfiguration.getInstances(), "", clientProfile);

//            SentenceRecognitionRequest req = new SentenceRecognitionRequest();
            req.setProjectId(1232563L);
            req.setSubServiceType(2L);
            req.setEngSerViceType("16k_zh");
            req.setSourceType(1L);
            req.setVoiceFormat("mp3");
            req.setUsrAudioKey("12312313");
//            String path="D:/Users/xinzh/Music/mu/yan.mp3";
//            String str= EncryptUtil.encodeBase64File(path);
//            req.setData(str);
            SentenceRecognitionResponse resp = client.SentenceRecognition(req);
            System.out.println("--------------"+SentenceRecognitionResponse.toJsonString(resp));
            return resp;
    }


    @Override
    public String sentenceRecognition(File mp3File) throws Exception {
        SentenceRecognitionRequest srq=new SentenceRecognitionRequest();
        srq.setData(EncryptUtil.encodeBase64File(mp3File));
        SentenceRecognitionResponse srp=sentenceRecognition(srq);
        return srp.getResult();
    }
}
