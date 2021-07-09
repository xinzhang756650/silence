package com.silence.weixin.handler;

import com.silence.generator.domain.Fans;
import com.silence.generator.service.IFansService;
import com.silence.weixin.builder.TextBuilder;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @desc:  取消关注事件
 * @author: cao_wencao
 * @date: 2019-09-02 17:17
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

    @Autowired
    private IFansService fansService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        this.logger.info("取消关注用户 OPENID: " + openId);
        // TODO 可以更新本地数据库为取消关注状态
        Fans fans = new Fans();
        fans.setOpenId(openId);
        List<Fans> resultList = fansService.selectFansList(fans);
        if (resultList != null && !resultList.isEmpty()) {
            resultList.get(0).setIsSubscribe("0");
            fansService.updateFans(resultList.get(0));
            try {
                return new TextBuilder().build("尊敬的" + resultList.get(0).getNickName() + "，江湖再见！", wxMessage, wxMpService);
            } catch (Exception e) {
                this.logger.error(e.getMessage(), e);
                return null;
            }
        }else{
            return null;
        }
    }
}