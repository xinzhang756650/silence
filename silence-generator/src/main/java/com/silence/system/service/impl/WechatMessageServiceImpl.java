package com.silence.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.silence.system.mapper.WechatMessageMapper;
import com.silence.system.domain.WechatMessage;
import com.silence.system.service.IWechatMessageService;
import com.silence.common.core.text.Convert;

/**
 * 微信消息 服务层实现
 * 
 * @author silence
 * @date 2021-07-06
 */
@Service
public class WechatMessageServiceImpl implements IWechatMessageService 
{
	@Autowired
	private WechatMessageMapper wechatMessageMapper;

	/**
     * 查询微信消息信息
     * 
     * @param msgId 微信消息ID
     * @return 微信消息信息
     */
    @Override
	public WechatMessage selectWechatMessageById(Integer msgId)
	{
	    return wechatMessageMapper.selectWechatMessageById(msgId);
	}
	
	/**
     * 查询微信消息列表
     * 
     * @param wechatMessage 微信消息信息
     * @return 微信消息集合
     */
	@Override
	public List<WechatMessage> selectWechatMessageList(WechatMessage wechatMessage)
	{
	    return wechatMessageMapper.selectWechatMessageList(wechatMessage);
	}
	
    /**
     * 新增微信消息
     * 
     * @param wechatMessage 微信消息信息
     * @return 结果
     */
	@Override
	public int insertWechatMessage(WechatMessage wechatMessage)
	{
	    return wechatMessageMapper.insertWechatMessage(wechatMessage);
	}
	
	/**
     * 修改微信消息
     * 
     * @param wechatMessage 微信消息信息
     * @return 结果
     */
	@Override
	public int updateWechatMessage(WechatMessage wechatMessage)
	{
	    return wechatMessageMapper.updateWechatMessage(wechatMessage);
	}

	/**
     * 删除微信消息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatMessageByIds(String ids)
	{
		return wechatMessageMapper.deleteWechatMessageByIds(Convert.toStrArray(ids));
	}
	
}
