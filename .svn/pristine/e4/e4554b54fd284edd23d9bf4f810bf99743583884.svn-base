package com.pulan.Service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.pulan.model.User;
import com.pulan.model.WkUser;
@Service
public interface UserService {
	public User findByUserName(String username);
	public List<String> getAllPermissionByUserid(String userid);
	//获取所有用户
	public List<User> getAllUser(int fromIndex,int pageSize);
	//新增用户
	public int addUser(User user);
	public int getDataSize();
	public int insertUser(User user);
	public int deleteUser(String id);
	public User getUserById(String id);
	public int update(User user);
	public List<String> getAllRole();
	public List<String> getAllRoleByUserId(String id);
	//插入user_role表
	public int insertUserRole(String id,String role_id);
	//根据role名查role_id
	public String getRoleIdByRole(String role);
	public int deleteRole(String user_id);
	public int updateUhead(String mail_name,String pic);
	public String getEmailByname(String name);
	public User getUserByFaceid(String faceid);
	public int updateFaceId(User user);
	public List<String> getAllMailName();
}
