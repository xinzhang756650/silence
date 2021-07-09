package com.silence.system.service;

import com.silence.system.domain.WechatMessage;
import java.util.List;

/**
 * 微信消息 服务层
 * 
 * @author silence
 * @date 2021-07-06
 */
public interface IWechatMessageService 
{
	/**
     * 查询微信消息信息
     * 
     * @param msgId 微信消息ID
     * @return 微信消息信息
     */
	public WechatMessage selectWechatMessageById(Integer msgId);
	
	/**
     * 查询微信消息列表
     * 
     * @param wechatMessage 微信消息信息
     * @return 微信消息集合
     */
	public List<WechatMessage> selectWechatMessageList(WechatMessage wechatMessage);
	
	/**
     * 新增微信消息
     * 
     * @param wechatMessage 微信消息信息
     * @return 结果
     */
	public int insertWechatMessage(WechatMessage wechatMessage);
	
	/**
     * 修改微信消息
     * 
     * @param wechatMessage 微信消息信息
     * @return 结果
     */
	public int updateWechatMessage(WechatMessage wechatMessage);
		
	/**
     * 删除微信消息信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatMessageByIds(String ids);
	
}
