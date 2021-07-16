package com.silence.tencent.service.impl;

import com.silence.common.utils.http.HttpUtils;
import com.silence.tencent.service.OwnthinkRobotAnswer;
import org.springframework.stereotype.Service;

@Service
public class OwnthinkRobotAnswerImpl implements OwnthinkRobotAnswer {

    @Override
    public String ownthinkRobotAnswer(String message) {
        String url = "https://api.ownthink.com/bot";
        String appid = "appid=498ff1799cd14d0769e1d2601b88c256";
        String userid = "userid=ySm1xEui";
        String spoken = "spoken="+message;
        return HttpUtils.sendGet(url,appid,userid,spoken);
    }

}
