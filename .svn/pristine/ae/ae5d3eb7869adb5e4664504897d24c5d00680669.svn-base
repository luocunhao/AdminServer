package com.pulan.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pulan.model.Advertisment;

public interface AdvertismentDao {
	public int insertAd(Advertisment ad);

	public List<Advertisment> getAllFile();

	public List<Advertisment> GetFileByType(@Param("type") String type);

	public int deleteAd(@Param("media_id") String media_id);
	public int updateAd(Advertisment ad);

	public Advertisment getFilenameById(@Param("media_id") String media_id);
}
