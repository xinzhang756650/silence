package com.silence.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.silence.common.annotation.Log;
import com.silence.common.enums.BusinessType;
import com.silence.system.domain.WechatMessage;
import com.silence.system.service.IWechatMessageService;
import com.silence.common.core.controller.BaseController;
import com.silence.common.core.page.TableDataInfo;
import com.silence.common.core.domain.AjaxResult;
import com.silence.common.utils.poi.ExcelUtil;

/**
 * 微信消息 信息操作处理
 * 
 * @author silence
 * @date 2021-07-06
 */
@Controller
@RequestMapping("/system/wechatMessage")
public class WechatMessageController extends BaseController
{
    private String prefix = "system/wechatMessage";
	
	@Autowired
	private IWechatMessageService wechatMessageService;
	
	@RequiresPermissions("system:wechatMessage:view")
	@GetMapping()
	public String wechatMessage()
	{
	    return prefix + "/wechatMessage";
	}
	
	/**
	 * 查询微信消息列表
	 */
	@RequiresPermissions("system:wechatMessage:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatMessage wechatMessage)
	{
		startPage();
        List<WechatMessage> list = wechatMessageService.selectWechatMessageList(wechatMessage);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出微信消息列表
	 */
	@RequiresPermissions("system:wechatMessage:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatMessage wechatMessage)
    {
    	List<WechatMessage> list = wechatMessageService.selectWechatMessageList(wechatMessage);
        ExcelUtil<WechatMessage> util = new ExcelUtil<WechatMessage>(WechatMessage.class);
        return util.exportExcel(list, "wechatMessage");
    }
	
	/**
	 * 新增微信消息
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存微信消息
	 */
	@RequiresPermissions("system:wechatMessage:add")
	@Log(title = "微信消息", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatMessage wechatMessage)
	{		
		return toAjax(wechatMessageService.insertWechatMessage(wechatMessage));
	}

	/**
	 * 修改微信消息
	 */
	@GetMapping("/edit/{msgId}")
	public String edit(@PathVariable("msgId") Integer msgId, ModelMap mmap)
	{
		WechatMessage wechatMessage = wechatMessageService.selectWechatMessageById(msgId);
		mmap.put("wechatMessage", wechatMessage);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存微信消息
	 */
	@RequiresPermissions("system:wechatMessage:edit")
	@Log(title = "微信消息", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatMessage wechatMessage)
	{		
		return toAjax(wechatMessageService.updateWechatMessage(wechatMessage));
	}
	
	/**
	 * 删除微信消息
	 */
	@RequiresPermissions("system:wechatMessage:remove")
	@Log(title = "微信消息", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(wechatMessageService.deleteWechatMessageByIds(ids));
	}
	
}
