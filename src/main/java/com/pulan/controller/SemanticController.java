package com.pulan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pulan.Service.SemanticService;
import com.pulan.Service.SemanticSlotService;
import com.pulan.model.Semantic;
import com.pulan.model.SemanticSlot;
@Controller 
@RequestMapping(value = "/message/semantic")
public class SemanticController {

	@Autowired
	private SemanticService semanticServiceImpl;
	@Autowired
	private SemanticSlotService semanticSlotServiceImpl;
	
	@RequestMapping(value = "/")
	public String toSemantic(ModelMap mm) {
		mm.put("allData", semanticServiceImpl.getAllSemantic());
		return "pages/semantic";
	}
	@ResponseBody
	@RequestMapping(value = "/selectSemantic")
	public List<Semantic> getAllSemantic() {
		return semanticServiceImpl.getAllSemantic();
	}

	@ResponseBody
	@RequestMapping(value = "/insertSemantic")
	public int insertSemantic(@RequestBody Semantic semantic) {
		return semanticServiceImpl.insertSemantic(semantic);
	}

	@ResponseBody
	@RequestMapping(value = "/updateSemantic")
	public int updateSemantic(@RequestBody Semantic semantic) {
		return semanticServiceImpl.updateSemantic(semantic);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteSemantic")
	public int deleteSemantic(@RequestBody Semantic semantic) {
		semanticSlotServiceImpl.deleteSemanticslotByTemlateCode(semantic.getTemplate_code());
		return semanticServiceImpl.deleteSemantic(semantic.getTemplate_id());
	}

	@ResponseBody
	@RequestMapping(value = "/selectSemanticSlot")
	public JSONObject getAllSemanticSlot(@RequestBody String msgData) {
		JSONObject ret = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		String template_id = msgObj.getString("template_id");
		List<SemanticSlot> list = semanticSlotServiceImpl.getSemanticslot(template_id);
		ret.put("resp", list);
		return ret;
	}

	// 有id 则修改 无Id 则插入 同时更新semantic表
	@ResponseBody
	@RequestMapping(value = "/insertSemanticSlot")
	public int insertSemanticSlot(@RequestBody String msgBody) {
		int ret = 0;
		JSONArray msgObj = JSON.parseArray(msgBody);
		List<SemanticSlot> semanticslots = new ArrayList<SemanticSlot>();
		for(int i=0;i<msgObj.size();i++){
			JSONObject jo = msgObj.getJSONObject(i);
			String slot_id = jo.getString("slot_id");
			String template_id = jo.getString("template_code");
			String slot_name = jo.getString("slot_name");
			String slot_code = jo.getString("slot_code");
			String slot_value = jo.getString("slot_value");
			String required = jo.getString("required");
			String default_value = jo.getString("default_value");
			String prompt = jo.getString("prompt");
			String word_class = jo.getString("word_class");
			int slot_order = jo.getInteger("slot_order");
			int try_count = jo.getInteger("try_count");
			semanticslots.add(new SemanticSlot(slot_id,template_id,slot_name,slot_code,
					slot_value,required,default_value,prompt,word_class,slot_order,try_count));
		}
		// 删除
		// 数据库中的id
		List<SemanticSlot> list = semanticSlotServiceImpl.getSemanticslot(semanticslots.get(0).getTemplate_id());
		// 取差集
		list.removeAll(semanticslots);
		for (SemanticSlot semanticSlot : list) {
			semanticSlotServiceImpl.deleteSemanticslot(semanticSlot.getSlot_id());
		}
		// 更新semantic表
		JSONObject jo = msgObj.getJSONObject(0);
		String temId = jo.getString("temId");
		String template_code = jo.getString("template_code");
		String template_name = jo.getString("template_name");
		String creator_name = jo.getString("creator_name");
		String create_time = jo.getString("create_time");
		String action_url = jo.getString("action_url");
		String action_success = jo.getString("action_success");
		String action_failure = jo.getString("action_failure");
		String last_response = jo.getString("last_response");
		Semantic semantic = new Semantic(temId,template_code,template_name,
				creator_name,create_time,action_url,action_success,action_failure,last_response);
		semanticServiceImpl.updateSemantic(semantic);
		for (SemanticSlot semanticSlot : semanticslots) {
			// 有id更新semanticslot表
			if (semanticSlot.getSlot_id()!=null&&!("0").equals(semanticSlot.getSlot_id())) {
				ret += semanticSlotServiceImpl.updateSemanticslot(semanticSlot);
			} else {
				// 无id插入semanticslot表
				ret += semanticSlotServiceImpl.insertSemanticslot(semanticSlot);
			}
		}
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "/updateSemanticSlot")
	public int updateSemanticSlot(@RequestBody SemanticSlot semanticslot) {
		return semanticSlotServiceImpl.updateSemanticslot(semanticslot);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteSemanticSlot")
	public int deleteSemanticSlot(@RequestBody SemanticSlot semanticslot) {
		return semanticSlotServiceImpl.deleteSemanticslot(semanticslot.getSlot_id());
	}
}
