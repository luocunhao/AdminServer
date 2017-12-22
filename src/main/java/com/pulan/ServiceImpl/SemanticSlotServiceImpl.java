package com.pulan.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.SemanticSlotDao;
import com.pulan.Service.SemanticSlotService;
import com.pulan.model.SemanticSlot;
@Service
public class SemanticSlotServiceImpl implements SemanticSlotService{
	@Autowired
	private SemanticSlotDao semanticSlotDao;
	@Override
	public List<SemanticSlot> getSemanticslot(String semanticcode){
		return semanticSlotDao.getSemanticslot(semanticcode);
	}
	@Override
	public int insertSemanticslot(SemanticSlot semanticslot) {
		// TODO Auto-generated method stub
		return semanticSlotDao.insertSemanticslot(semanticslot);
	}
	@Override
	public int deleteSemanticslot(String slot_id) {
		// TODO Auto-generated method stub
		return semanticSlotDao.deleteSemanticslot(slot_id);
	}
	@Override
	public int updateSemanticslot(SemanticSlot semanticslot) {
		// TODO Auto-generated method stub
		return semanticSlotDao.updateSemanticslot(semanticslot);
	}
	@Override
	public int deleteSemanticslotByTemlateCode(String template_code) {
		// TODO Auto-generated method stub
		return semanticSlotDao.deleteSemanticslotByTemlateCode(template_code);
	}
}
