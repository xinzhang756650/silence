package com.silence.weixin.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTransferKefuMessage;

public interface IMsgHandlerService {

    public String msgHandlerService(WxMpXmlMessage wxMessage, WxMpService weixinService) throws WxErrorException;

}
