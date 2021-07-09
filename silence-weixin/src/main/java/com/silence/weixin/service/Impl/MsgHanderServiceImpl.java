package com.silence.weixin.service.Impl;

import com.silence.common.utils.file.FileUtils;
import com.silence.system.domain.WechatMessage;
import com.silence.system.service.IWechatMessageService;
import com.silence.tencent.service.impl.TencentAnalysisTaskImpl;
import com.silence.weixin.service.IMsgHandlerService;
import com.silence.weixin.utils.WeChatUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Slf4j
@Service
public class MsgHanderServiceImpl implements IMsgHandlerService {

    @Autowired
    private IWechatMessageService wechatMessageService;
    @Autowired
    private TencentAnalysisTaskImpl tencentAnalysisTaskImpl;
    @Autowired
    @Lazy
    private WeChatUtils weChatUtils;

    @Override
    public String msgHandlerService(WxMpXmlMessage wxMessage, WxMpService weixinService) throws WxErrorException {
        String content="";
        switch (wxMessage.getMsgType()){
            case WxConsts.XmlMsgType.TEXT:
                log.info("接收到一条"+WxConsts.XmlMsgType.TEXT+"类型请求！");
                WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser());
                WechatMessage wechatMessage=new WechatMessage();
                wechatMessage.setMsgId(wxMessage.getMsgId());
                wechatMessage.setContent(wxMessage.getContent());
                wechatMessage.setFromUserName(userWxInfo.getNickname());
                wechatMessage.setOpenId(userWxInfo.getOpenId());
                wechatMessage.setMsgType(wxMessage.getMsgType());
                wechatMessage.setCreateBy(userWxInfo.getNickname());
                wechatMessage.setCreateTime(new Date());
                wechatMessage.setToUserName(wxMessage.getToUser());
                wechatMessageService.insertWechatMessage(wechatMessage);
                content=wxMessage.getContent();
                break;
            case WxConsts.XmlMsgType.IMAGE:
                log.info("接收到一条"+WxConsts.XmlMsgType.IMAGE+"类型请求！");
                break;
            case WxConsts.XmlMsgType.VOICE:
                log.info("接收到一条"+WxConsts.XmlMsgType.VOICE+"类型请求！");
                try {
                    File file = weChatUtils.DownloadMaterial(wxMessage.getMediaId());
                    File mp3File= FileUtils.changeToMp3(file);
                    return tencentAnalysisTaskImpl.sentenceRecognition(mp3File);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case WxConsts.XmlMsgType.SHORTVIDEO:
                log.info("接收到一条"+WxConsts.XmlMsgType.SHORTVIDEO+"类型请求！");
                break;
            case WxConsts.XmlMsgType.VIDEO:
                log.info("接收到一条"+WxConsts.XmlMsgType.VIDEO+"类型请求！");
                break;
            case WxConsts.XmlMsgType.NEWS:
                log.info("接收到一条"+WxConsts.XmlMsgType.NEWS+"类型请求！");
                break;
            case WxConsts.XmlMsgType.MUSIC:
                log.info("接收到一条"+WxConsts.XmlMsgType.MUSIC+"类型请求！");
                break;
            case WxConsts.XmlMsgType.LOCATION:
                log.info("接收到一条"+WxConsts.XmlMsgType.LOCATION+"类型请求！");
                break;
            case WxConsts.XmlMsgType.LINK:
                log.info("接收到一条"+WxConsts.XmlMsgType.LINK+"类型请求！");
                break;
            case WxConsts.XmlMsgType.EVENT:
                log.info("接收到一条"+WxConsts.XmlMsgType.EVENT+"类型请求！");
                break;
            case WxConsts.XmlMsgType.DEVICE_TEXT:
                log.info("接收到一条"+WxConsts.XmlMsgType.DEVICE_TEXT+"类型请求！");
                break;
            case WxConsts.XmlMsgType.DEVICE_EVENT:
                log.info("接收到一条"+WxConsts.XmlMsgType.DEVICE_EVENT+"类型请求！");
                break;
            case WxConsts.XmlMsgType.DEVICE_STATUS:
                log.info("接收到一条"+WxConsts.XmlMsgType.DEVICE_STATUS+"类型请求！");
                break;
            case WxConsts.XmlMsgType.HARDWARE:
                log.info("接收到一条"+WxConsts.XmlMsgType.HARDWARE+"类型请求！");
                break;
            case WxConsts.XmlMsgType.TRANSFER_CUSTOMER_SERVICE:
                log.info("接收到一条"+WxConsts.XmlMsgType.TRANSFER_CUSTOMER_SERVICE+"类型请求！");
                break;
            default:
                content="暂不支持"+wxMessage.getMsgType()+"类型，敬请期待";
                break;
        }
        return content;
    }

}
