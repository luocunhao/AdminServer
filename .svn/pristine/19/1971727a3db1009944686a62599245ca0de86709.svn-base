package com.pulan.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pulan.model.Message;
import com.pulan.model.MessageParam;

public interface MessageDao {
	public List<Message> getAllMessage(@Param("fromIndex") int fromIndex,@Param("pageSize") int pageSize);

	public int getDataSize();
	
	public int deleteMessage(@Param("message_uuid") String message_uuid);
	
	public int insertMessage(Message message);
	
	public int updateMessage(Message message);
	
	public List<MessageParam> getAllParamByMessage(@Param("message_uuid") String message_uuid);
	
	public int deleteMessageParam(@Param("message_uuid") String message_uuid);

	public List<MessageParam> getMessageByUUID(@Param("message_uuid") String message_uuid);

	public int deleteMessageParamByUUID(@Param("message_param_uuid") String message_param_uuid);

	public int updateMessageParam(MessageParam messageparam);

	public int insertMessageParam(MessageParam messageparam);
}
