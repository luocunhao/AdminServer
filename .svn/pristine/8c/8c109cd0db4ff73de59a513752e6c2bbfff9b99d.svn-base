package com.pulan.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pulan.model.VerbalTrick;
@Service
public interface HuaShuService {
	public int insertVerbal(VerbalTrick verbalTrick);
	public int deleteVerbal(int id);
	public int updateVerbal(VerbalTrick verbalTrick);
	public List<VerbalTrick> getVerbalByCategoryAndType(String category,String type,int fromIndex,int pageSize);
	public List<VerbalTrick> getAllVerbal();
	public List<String> getTypes();
	public List<String> getCategoryType(String type);
	public int getDataSize();
	public int getDataSizepage(String type,String category);
}
