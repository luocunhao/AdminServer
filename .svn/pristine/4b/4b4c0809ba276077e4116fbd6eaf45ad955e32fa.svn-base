package com.pulan.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.UserDao;
import com.pulan.Service.UserService;
import com.pulan.model.User;
import com.pulan.model.WkUser;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
	private UserDao userDao;
	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}
	@Override
	public List<String> getAllPermissionByUserid(String userid) {
		// TODO Auto-generated method stub
		return userDao.getAllPermissionByUserid(userid);
	}
	@Override
	public List<User> getAllUser(int fromIndex,int pageSize) {
		// TODO Auto-generated method stub
		List<User> list = userDao.getAllUser(fromIndex,pageSize);
		for(User user:list){
			String role = userDao.getAllRoleByUserId(user.getId()).toString();
			user.setRole(role);
		}
		return list;
	}
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}
	@Override
	public int getDataSize() {
		// TODO Auto-generated method stub
		return userDao.getDataSize();
	}
	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insertUser(user);
	}
	@Override
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(id);
	}
	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}
	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		String[] roles = user.getRole().split(",");
		String userid = user.getId();
		//删除用户所有角色 重新添加
		userDao.deleteRole(userid);
		//获取用户的所有角色
		for(String role:roles){
			String roleid = userDao.getRoleIdByRole(role);
			if(!"".equals(roleid)&&roleid!=null){
			userDao.insertUserRole(userid,roleid);
			}
		}
		return userDao.update(user);
	}
	@Override
	public List<String> getAllRole() {
		// TODO Auto-generated method stub
		return userDao.getAllRole();
	}
	@Override
	public List<String> getAllRoleByUserId(String id) {
		// TODO Auto-generated method stub
		return userDao.getAllRoleByUserId(id);
	}
	@Override
	public int insertUserRole(String id, String role_id) {
		// TODO Auto-generated method stub
		return userDao.insertUserRole(id,role_id);
	}
	@Override
	public String getRoleIdByRole(String role) {
		// TODO Auto-generated method stub
		return userDao.getRoleIdByRole(role);
	}
	@Override
	public int deleteRole(String user_id) {
		// TODO Auto-generated method stub
		return userDao.deleteRole(user_id);
	}
	@Override
	public int updateUhead(String mail_name,String pic) {
		// TODO Auto-generated method stub
		return userDao.updateUhead(mail_name,pic);
	}
	@Override
	public String getEmailByname(String name) {
		// TODO Auto-generated method stub
		return userDao.getEmailByname(name);
	}
	@Override
	public User getUserByFaceid(String faceid) {
		// TODO Auto-generated method stub
		return userDao.getUserByFaceid(faceid);
	}
	@Override
	public int updateFaceId(User user) {
		// TODO Auto-generated method stub
		return userDao.updateFaceId(user);
	}
	@Override
	public List<String> getAllMailName() {
		// TODO Auto-generated method stub
		return userDao.getAllMailName();
	}
    
}
