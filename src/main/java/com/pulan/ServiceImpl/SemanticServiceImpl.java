package com.pulan.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.SemanticDao;
import com.pulan.Service.SemanticService;
import com.pulan.model.Semantic;
@Service
public class SemanticServiceImpl implements SemanticService{
    @Autowired
	private SemanticDao semanticDao;
	@Override
	public List<Semantic> getAllSemantic() {
		// TODO Auto-generated method stub
		return semanticDao.getAllSemantic();
	}
	@Override
	public int insertSemantic(Semantic semantic) {
		// TODO Auto-generated method stub
		return semanticDao.insertSemantic(semantic);
	}
	@Override
	public int deleteSemantic(String id) {
		// TODO Auto-generated method stub
		return semanticDao.deleteSemantic(id);
	}
	@Override
	public int updateSemantic(Semantic semantic) {
		// TODO Auto-generated method stub
		return semanticDao.updateSemantic(semantic);
	}

}
