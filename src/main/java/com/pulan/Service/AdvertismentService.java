package com.pulan.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pulan.model.Advertisment;

@Service
public interface AdvertismentService {
	public int insertAd(Advertisment ad);
	public List<Advertisment> getAllFile();
	public List<Advertisment> GetFileByType(String type);
	public int deleteAd(String media_id,String filePath);
	public int updateAd(Advertisment ad);
	public Advertisment getFilenameById(String media_id);
}
