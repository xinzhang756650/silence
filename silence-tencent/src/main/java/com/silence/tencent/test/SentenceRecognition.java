package com.silence.tencent.test;


import com.silence.common.utils.encryption.EncryptUtil;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionRequest;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

public class SentenceRecognition {
    public static void main(String[] args) {
        try {
            Credential cred = new Credential("AKIDoRovlJbdNq53k9H7gxxg7G8j36rLZWCb", "91cvyjDPKyLRGbfSxHK7cqnUw71e04sP");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("asr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            AsrClient client = new AsrClient(cred, "", clientProfile);

            SentenceRecognitionRequest req = new SentenceRecognitionRequest();
            req.setProjectId(1232563L);
            req.setSubServiceType(2L);
            req.setEngSerViceType("16k_zh");
            req.setSourceType(1L);
            req.setVoiceFormat("mp3");
            req.setUsrAudioKey("12312313");
            String path="D:/Users/xinzh/Music/mu/yan.mp3";
            String str= EncryptUtil.encodeBase64File(path);
            req.setData(str);
            SentenceRecognitionResponse resp = client.SentenceRecognition(req);

            System.out.println(SentenceRecognitionResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}