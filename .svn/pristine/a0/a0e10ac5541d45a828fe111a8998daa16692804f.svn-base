package com.pulan.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.MessageDao;
import com.pulan.Service.MessageService;
import com.pulan.model.Message;
import com.pulan.model.MessageParam;
@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageDao messageDao;
	@Override
	public List<Message> getAllMessage(int fromIndex, int pageSize) {
		// TODO Auto-generated method stub
		return messageDao.getAllMessage(fromIndex, pageSize);
	}
	@Override
	public int getDataSize() {
		// TODO Auto-generated method stub
		return messageDao.getDataSize();
	}
	@Override
	public int deleteMessage(String message_uuid) {
		// TODO Auto-generated method stub
		return messageDao.deleteMessage(message_uuid);
	}
	@Override
	public int insertMessage(Message message) {
		// TODO Auto-generated method stub
		return messageDao.insertMessage(message);
	}
	@Override
	public int updateMessage(Message message) {
		// TODO Auto-generated method stub
		return messageDao.updateMessage(message);
	}
	@Override
	public List<MessageParam> getAllParamByMessage(String message_uuid) {
		// TODO Auto-generated method stub
		return messageDao.getAllParamByMessage(message_uuid);
	}
	@Override
	public int deleteMessageParam(String message_uuid) {
		// TODO Auto-generated method stub
		return messageDao.deleteMessageParam(message_uuid);
	}
	@Override
	public List<MessageParam> getMessageByUUID(String message_uuid) {
		// TODO Auto-generated method stub
		return messageDao.getMessageByUUID(message_uuid);
	}
	@Override
	public int deleteMessageParamByUUID(String message_param_uuid) {
		// TODO Auto-generated method stub
		return messageDao.deleteMessageParamByUUID(message_param_uuid);
	}
	@Override
	public int updateMessageParam(MessageParam messageparam) {
		// TODO Auto-generated method stub
		return messageDao.updateMessageParam(messageparam);
	}
	@Override
	public int insertMessageParam(MessageParam messageparam) {
		// TODO Auto-generated method stub
		return messageDao.insertMessageParam(messageparam);
	}

}
