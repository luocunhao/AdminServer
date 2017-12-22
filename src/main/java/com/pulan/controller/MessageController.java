package com.pulan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pulan.Dao.MessageDao;
import com.pulan.Service.MessageService;
import com.pulan.model.Message;
import com.pulan.model.MessageParam;
import com.pulan.model.Semantic;
import com.pulan.model.SemanticSlot;
import com.pulan.model.VerbalTrick;
import com.pulan.utils.Pager;

@Controller // (直接返回数据而不是视图)
@RequestMapping(value = "/message/msg")
public class MessageController {
	@Autowired
	private MessageService messageServiceImpl;
	@RequestMapping(value = "/")
	public String getAllMessage(ModelMap mm, HttpServletRequest request) {
		JSONObject ret = new JSONObject();
		// 当前默认为第一页
		int currentPage = 0;
		int totalRecord = messageServiceImpl.getDataSize();
		mm.put("totalRecord", totalRecord);
		String pageNum = request.getParameter("pageNum");
		int pageSize = Pager.DEFAULT_PAGESIZE;
		// 如果前台传入pageNum参数，则设置pageNum为当前第几页
		if (pageNum != null && !"".equals(pageNum.trim())) {
			currentPage = Integer.parseInt(pageNum);
		} else {
			currentPage = Pager.DEFAULT_PAGENUM;
		}
		Pager pager = new Pager(currentPage, pageSize, totalRecord);
		int fromIndex = pager.getPageSize() * (pager.getCurrentPage() - 1);
		mm.put("allData", messageServiceImpl.getAllMessage(fromIndex, pageSize));
		mm.put("page", pager);
		mm.put("pageNum", pageNum);
		return "pages/messageTip";
	}
	@ResponseBody
	@RequestMapping(value = "/select")
	public JSONObject getAllMessageByFpage(HttpServletRequest request) {
		JSONObject ret = new JSONObject();
		// 当前默认为第一页
		int currentPage = 0;
		int totalRecord = messageServiceImpl.getDataSize();
		ret.put("totalRecord", totalRecord);
		String pageNum = request.getParameter("pageNum");
		int pageSize = Pager.DEFAULT_PAGESIZE;
		// 如果前台传入pageNum参数，则设置pageNum为当前第几页
		if (pageNum != null && !"".equals(pageNum.trim())) {
			currentPage = Integer.parseInt(pageNum);
		} else {
			currentPage = Pager.DEFAULT_PAGENUM;
		}
		Pager pager = new Pager(currentPage, pageSize, totalRecord);
		int fromIndex = pager.getPageSize() * (pager.getCurrentPage() - 1);
		ret.put("allData", messageServiceImpl.getAllMessage(fromIndex, pageSize));
		ret.put("page", pager);
		ret.put("pageNum", pageNum);
		return ret;
	}
	@ResponseBody
	@RequestMapping(value = "/select_page")
	public JSONObject getAllMessageByPage(HttpServletRequest request) {
		JSONObject ret = new JSONObject();
		// 当前默认为第一页
		int currentPage = 0;
		int totalRecord = messageServiceImpl.getDataSize();
		ret.put("totalRecord", totalRecord);
		String pageNum = request.getParameter("pageNum");
		int pageSize = Pager.DEFAULT_PAGESIZE;
		// 如果前台传入pageNum参数，则设置pageNum为当前第几页
		if (pageNum != null && !"".equals(pageNum.trim())) {
			currentPage = Integer.parseInt(pageNum);
		} else {
			currentPage = Pager.DEFAULT_PAGENUM;
		}
		Pager pager = new Pager(currentPage, pageSize, totalRecord);
		int fromIndex = pager.getPageSize() * (pager.getCurrentPage() - 1);
		ret.put("allData", messageServiceImpl.getAllMessage(fromIndex, pageSize));
		ret.put("page", pager);
		ret.put("pageNum", pageNum);
		return ret;
	}
	@ResponseBody
	@RequestMapping(value = "deleteMessage",method = RequestMethod.POST)
	public JSONObject deleteMessage(@RequestBody String msgData){
		JSONObject result = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		String message_uuid = msgObj.getString("message_uuid");
		int ret = messageServiceImpl.deleteMessage(message_uuid);
		messageServiceImpl.deleteMessageParam(message_uuid);
		if (ret == 0) {
			result.put("resp", "删除失败");
		} else {
			result.put("resp", "删除成功");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "updateMessage", method = RequestMethod.POST)
	public JSONObject updateVerbal(@RequestBody Message message) {
		JSONObject result = new JSONObject();
		int ret = messageServiceImpl.updateMessage(message);
		if (ret == 0) {
			result.put("resp", "修改失败");
		} else {
			result.put("resp", "修改成功");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "insertMessage", method = RequestMethod.POST)
	public JSONObject insertVerbal(@RequestBody Message message) {
		JSONObject result = new JSONObject();
		int ret = messageServiceImpl.insertMessage(message);
		if (ret == 0) {
			result.put("resp", "新增失败");
		} else {
			result.put("resp", "新增成功");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "/selectMessageParam")
	public JSONObject selectMessageParam(@RequestBody String msgData) {
		JSONObject ret = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		String message_uuid = msgObj.getString("message_uuid");
		List<MessageParam> list = messageServiceImpl.getAllParamByMessage(message_uuid);
		ret.put("resp", list);
		return ret;
	}
	// 有id 则修改 无Id 则插入 同时更新message表
		@ResponseBody
		@RequestMapping(value = "/insertMessageParam")
		public int insertMessageParam(@RequestBody String msgBody) {
			int ret = 0;
			JSONArray msgObj = JSON.parseArray(msgBody);
			List<MessageParam> messageparams = new ArrayList<MessageParam>();
			for(int i=0;i<msgObj.size();i++){
				JSONObject jo = msgObj.getJSONObject(i);
				String message_uuid = jo.getString("message_uuid");
				String message_param_uuid = jo.getString("message_param_uuid");
				String message_param_name = jo.getString("message_param_name");
				String message_param_index = jo.getString("message_param_index");
				String message_param_value = jo.getString("message_param_value");
				messageparams.add(new MessageParam(message_param_uuid,message_param_name,message_param_index,message_param_value,
						message_uuid));
			}
			// 删除
			// 数据库中的id
			if(messageparams.size()>0){
			List<MessageParam> list = messageServiceImpl.getMessageByUUID(messageparams.get(0).getMessage_uuid());
			// 取差集
			list.removeAll(messageparams);
			for (MessageParam messageparam : list) {
				messageServiceImpl.deleteMessageParamByUUID(messageparam.getMessage_param_uuid());
			}
			}
			// 更新message表
			JSONObject jo = msgObj.getJSONObject(0);
			String message_uuid = jo.getString("message_uuid");
			String message_name = jo.getString("message_name");
			String message_type = jo.getString("message_type");
			String data_source = jo.getString("data_source");
			String data_sql = jo.getString("data_sql");
			String schedule_type = jo.getString("schedule_type");
			String message_template = jo.getString("message_template");
			String expire_time = jo.getString("expire_time");
			Message message = new Message(message_uuid,message_name,message_type,
					data_source,data_sql,message_template,schedule_type,expire_time);
			messageServiceImpl.updateMessage(message);
			for (MessageParam messageparam : messageparams) {
				// 有id更新semanticslot表
				if (messageparam.getMessage_param_uuid()!=null&&!("0").equals(messageparam.getMessage_param_uuid())) {
					ret += messageServiceImpl.updateMessageParam(messageparam);
				} else {
					// 无id插入semanticslot表
					ret += messageServiceImpl.insertMessageParam(messageparam);
				}
			}
			return ret;
		}

}
