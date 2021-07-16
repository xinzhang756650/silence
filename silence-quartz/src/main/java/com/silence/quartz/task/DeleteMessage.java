package com.silence.quartz.task;

import com.silence.common.utils.StringUtils;
import com.silence.system.domain.WechatMessage;
import com.silence.system.service.IWechatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("deleteMessage")
public class DeleteMessage {

    @Autowired
    private IWechatMessageService wechatMessageService;

    public void deleteMessage(String limit){
        List<WechatMessage> resultList = wechatMessageService.selectWechatMessageList(new WechatMessage());
        if(resultList!=null && resultList.size()>=Integer.valueOf(limit)){
            String ids="";
            for (int i = resultList.size()-1-Integer.valueOf(limit); i >= 0; i--) {
                ids=resultList.get(i).getMsgId()+",";
            }
            if(StringUtils.isNotEmpty(ids)){
                wechatMessageService.deleteWechatMessageByIds(ids);
            }
        }
    }

}
