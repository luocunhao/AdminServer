package com.pulan.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pulan.model.VerbalTrick;

public interface HuaShuDao {
	public int insertVerbal(VerbalTrick verbalTrick);
	public int deleteVerbal(int id);
	public int updateVerbal(VerbalTrick verbalTrick);
	public List<VerbalTrick> getVerbalByCategoryAndType(@Param("category") String category,@Param("type") String type,@Param("fromIndex") int fromIndex,@Param("pageSize") int pageSize);
	public List<VerbalTrick> getAllVerbal();
	public List<String> getTypes();
	public List<String> getCategoryType(@Param("type") String type);
	public int getDataSize();
	public int getDataSizepage(@Param("type") String type, @Param("category") String category);
}
