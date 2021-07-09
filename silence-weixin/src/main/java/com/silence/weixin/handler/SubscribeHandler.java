package com.silence.weixin.handler;

import com.silence.common.utils.DateUtils;
import com.silence.generator.domain.Fans;
import com.silence.generator.service.IFansService;
import com.silence.weixin.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @desc:  关注事件
 * @author: cao_wencao
 * @date: 2019-09-02 17:17
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    private IFansService fansService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());
/*
2021-07-06 15:02:38.863  INFO 11464 [io-8888-exec-22] c.s.weixin.handler.SubscribeHandler      [27] : 新关注用户 OPENID: oJYpN572xaTAueq3m6bQJSspXacY
2021-07-06 15:02:38.866  INFO 11464 [pool-2-thread-5] com.silence.weixin.handler.LogHandler    [23] :
接收到请求消息，内容：{
  "allFieldsMap": {
    "CreateTime": "1625554957",
    "EventKey": "",
    "Event": "subscribe",
    "ToUserName": "gh_a5fe73e46d6d",
    "FromUserName": "oJYpN572xaTAueq3m6bQJSspXacY",
    "MsgType": "event"
  },
  "toUser": "gh_a5fe73e46d6d",
  "fromUser": "oJYpN572xaTAueq3m6bQJSspXacY",
  "createTime": 1625554957,
  "msgType": "event",
  "event": "subscribe",
  "eventKey": "",
  "scanCodeInfo": {},
  "sendPicsInfo": {
    "picList": []
  },
  "sendLocationInfo": {},
  "hardWare": {}
}


【请求地址】: https://api.weixin.qq.com/cgi-bin/user/info?access_token=46_inGDWta_OD1n88I2KWVbhxQUmKi3Acx00ZiuJ6G2dcw66GVe-fnqabTjN2xpUpD-yHiHIkKNvr1TOHC4oK13ttn91bQeMEKp0080UHMG4IfrhxP3knEQtF52N0vxzeCKuBL7CYiDRZIvR4DNSCBcAAABLK
【请求参数】：openid=oJYpN572xaTAueq3m6bQJSspXacY&lang=zh_CN
【响应数据】：
{
	"subscribe": 1,
	"openid": "oJYpN572xaTAueq3m6bQJSspXacY",
	"nickname": "遥祝",
	"sex": 1,
	"language": "zh_CN",
	"city": "阜阳",
	"province": "安徽",
	"country": "中国",
	"headimgurl": "http:\/\/thirdwx.qlogo.cn\/mmopen\/GxnNTRibdiceIYdrnOEOjNib4uicocWwY6NDI16DHU3MX6aQURVsmWkuBPpHVSk70x0mMcU4GotVZHsf6xia6ASM15SyrhYZmyaNN\/132",
	"subscribe_time": 1625554957,
	"remark": "",
	"groupid": 0,
	"tagid_list": [],
	"subscribe_scene": "ADD_SCENE_QR_CODE",
	"qr_scene": 0,
	"qr_scene_str": ""
}

 */

        String message="";
        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
                    .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                // TODO 可以添加关注用户到本地数据库
                Fans fans=new Fans();
                fans.setOpenId(userWxInfo.getOpenId());
                List<Fans> resultList=fansService.selectFansList(fans);
                if(resultList!=null && !resultList.isEmpty()){
                    resultList.get(0).setIsSubscribe("1");
                    fansService.updateFans(resultList.get(0));
                    message="欢迎回来！";
                }else{
                    fans.setId(UUID.randomUUID().toString());
                    fans.setIsSubscribe(userWxInfo.getSubscribe()==true?"1":"0");
                    fans.setCity(userWxInfo.getCity());
                    fans.setSubscribe(new Date());
                    fans.setCountry(userWxInfo.getCountry());
                    fans.setHeadimgurl(userWxInfo.getHeadImgUrl());
                    fans.setNickName(userWxInfo.getNickname());
                    fans.setPhone("");
                    fans.setProvince(userWxInfo.getProvince());
                    fans.setRealName(userWxInfo.getNickname());
                    fans.setSex(userWxInfo.getSex()==0?"女":"男");
                    fans.setUnionid(userWxInfo.getUnionId());
                    fansService.insertFans(fans);
                    message="感谢您的关注！";
                }
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }


        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build(message, wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}

