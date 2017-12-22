package com.pulan.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pulan.model.Message;
import com.pulan.model.MessageParam;
import com.pulan.model.SemanticSlot;
@Service
public interface MessageService {
	public List<Message> getAllMessage(int fromIndex,int pageSize);
	public int getDataSize();
	public int deleteMessage(String message_uuid);
	
	public int insertMessage(Message message);
	
	public int updateMessage(Message message);
	
	public List<MessageParam> getAllParamByMessage(String message_uuid);
	public int deleteMessageParam(String message_uuid);
	public List<MessageParam> getMessageByUUID(String message_uuid);
	public int deleteMessageParamByUUID(String message_param_uuid);
	public int updateMessageParam(MessageParam messageparam);
	public int insertMessageParam(MessageParam messageparam);
}
