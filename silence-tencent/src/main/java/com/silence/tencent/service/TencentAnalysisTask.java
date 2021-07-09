package com.silence.tencent.service;

import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionRequest;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionResponse;

import java.io.File;

/**
 *  腾讯一句话识别
 *  https://cloud.tencent.com/document/product/1093/35646
 */
public interface TencentAnalysisTask {

    SentenceRecognitionResponse sentenceRecognition(SentenceRecognitionRequest abstractModel) throws Exception;

    String sentenceRecognition(File mp3File) throws Exception;
}
