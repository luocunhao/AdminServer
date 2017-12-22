package com.pulan.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.pulan.model.User;
import com.pulan.model.WkUser;

public interface UserDao {
	public User findByUserName(@Param("username") String username);
	public List<String> getAllPermissionByUserid(@Param("userid") String userid);
	//获取所有用户
	public List<User> getAllUser(@Param("fromIndex") int fromIndex,@Param("pageSize")int pageSize);
	public int addUser(User user);
	public int getDataSize();
	public List<String> getAllRoleByUserId(@Param("userid") String userid);
	public int insertUser(User user);
	public int deleteUser(@Param("userid") String id);
	public User getUserById(@Param("userid") String id);
	public int update(User user);
	public List<String> getAllRole();
	public int insertUserRole(@Param("userid") String id,@Param("roleid") String role_id);
	public String getRoleIdByRole(@Param("role") String role);
	public int deleteRole(@Param("user_id")String user_id);
	public int updateUhead(String mail_name,String pic);
	public String getEmailByname(@Param("name") String name);
	public User getUserByFaceid(@Param("faceid") String faceid);
	public int updateFaceId(User user);
	public List<String> getAllMailName();
}
