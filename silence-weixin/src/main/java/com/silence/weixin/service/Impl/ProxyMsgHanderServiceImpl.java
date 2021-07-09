package com.silence.weixin.service.Impl;

import com.silence.tencent.service.impl.OwnthinkRobotAnswerImpl;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代理MsgHanderServiceImpl.msgHandlerService
 */
@Slf4j
@Service
public class ProxyMsgHanderServiceImpl extends MsgHanderServiceImpl{

    @Autowired
    private OwnthinkRobotAnswerImpl ownthinkRobotAnswer;

    @Override
    public String msgHandlerService(WxMpXmlMessage wxMessage, WxMpService weixinService) throws WxErrorException {
        String message = super.msgHandlerService(wxMessage, weixinService);
        if(!message.contains("敬请期待")){
            log.info("收到信息："+message);
            String result=ownthinkRobotAnswer.ownthinkRobotAnswer(message);
            log.info("回复消息："+result);
            return result;
        }else{
            return message;
        }
    }
}
