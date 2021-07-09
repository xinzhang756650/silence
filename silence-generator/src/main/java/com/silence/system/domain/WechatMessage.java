package com.silence.system.domain;

import com.silence.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * @desc: 微信消息表 wechat_message
 * @author: silence
 * @date: 2021-07-06
 */
@Data
@ToString
public class WechatMessage extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	

	private Long msgId;  //消息编号

	private String openId;  //OpendId

	private String content;  //消息内容

	private String toUserName;  //去向

	private String fromUserName;  //来自

	private String msgType;  //消息类型

																																																						
}
