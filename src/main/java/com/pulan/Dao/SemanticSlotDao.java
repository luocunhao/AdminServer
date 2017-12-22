package com.pulan.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pulan.model.SemanticSlot;


public interface SemanticSlotDao {
	public int insertSemanticslot(SemanticSlot semanticslot);
	public int deleteSemanticslot(@Param("slot_id") String id);
	public int updateSemanticslot(SemanticSlot semanticslot);
	public List<SemanticSlot> getSemanticslot(@Param("template_id") String template_id);
	public int deleteSemanticslotByTemlateCode(@Param("template_code") String template_code);
}
