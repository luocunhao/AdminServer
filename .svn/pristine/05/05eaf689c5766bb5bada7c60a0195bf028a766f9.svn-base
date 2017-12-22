package com.pulan.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.HuaShuDao;
import com.pulan.Service.HuaShuService;
import com.pulan.model.VerbalTrick;
@Service
public class HuaShuServiceImpl implements HuaShuService{
	@Autowired
    private HuaShuDao huaShuDao;
	@Override
	public int insertVerbal(VerbalTrick verbalTrick) {
		// TODO Auto-generated method stub
		return huaShuDao.insertVerbal(verbalTrick);
	}
	@Override
	public int deleteVerbal(int id) {
		// TODO Auto-generated method stub
		return huaShuDao.deleteVerbal(id);
	}
	@Override
	public int updateVerbal(VerbalTrick verbalTrick) {
		// TODO Auto-generated method stub
		return huaShuDao.updateVerbal(verbalTrick);
	}
	@Override
	public List<VerbalTrick> getVerbalByCategoryAndType(String category, String type, int fromIndex, int pageSize) {
		// TODO Auto-generated method stub
		return huaShuDao.getVerbalByCategoryAndType(category, type, fromIndex, pageSize);
	}
	@Override
	public List<VerbalTrick> getAllVerbal() {
		// TODO Auto-generated method stub
		return huaShuDao.getAllVerbal();
	}
	@Override
	public List<String> getTypes() {
		// TODO Auto-generated method stub
		return huaShuDao.getTypes();
	}
	@Override
	public List<String> getCategoryType(String type) {
		// TODO Auto-generated method stub
		return huaShuDao.getCategoryType(type);
	}
	@Override
	public int getDataSize() {
		// TODO Auto-generated method stub
		return huaShuDao.getDataSize();
	}
	@Override
	public int getDataSizepage(String type, String category) {
		// TODO Auto-generated method stub
		return huaShuDao.getDataSizepage(type, category);
	}

}
