package com.pulan.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.WkUserDao;
import com.pulan.Service.WkUserService;
import com.pulan.model.WkUser;
@Service
public class WkUserServiceImpl implements WkUserService{
	@Autowired
	private WkUserDao wkUserDao;
	@Override
	public int insertUser(WkUser wkUser) {
		// TODO Auto-generated method stub
		return wkUserDao.insertUser(wkUser);
	}
	@Override
	public boolean getVip(String username) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if("1".equals(wkUserDao.getVip(username))){
			flag = true;
		}
		return flag;
	}
	@Override
	public WkUser getUser(String faceId) {
		// TODO Auto-generated method stub
		return wkUserDao.getUser(faceId);
	}

}
