package com.pulan.ServiceImpl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulan.Dao.AdvertismentDao;
import com.pulan.Service.AdvertismentService;
import com.pulan.model.Advertisment;
@Service
public class AdvertismentServiceImpl implements AdvertismentService{
	@Autowired
	private AdvertismentDao advertismentDao;
	@Override
	public int insertAd(Advertisment ad) {
		// TODO Auto-generated method stub
		return advertismentDao.insertAd(ad);
	}
	@Override
	public List<Advertisment> getAllFile() {
		// TODO Auto-generated method stub
		return advertismentDao.getAllFile();
	}
	@Override
	public List<Advertisment> GetFileByType(String type) {
		// TODO Auto-generated method stub
		return advertismentDao.GetFileByType(type);
	}
	@Override
	public int deleteAd(String media_id,String filePath) {
		// TODO Auto-generated method stub
		Advertisment ad = advertismentDao.getFilenameById(media_id);
		String file = ad.getMedia_link();
		File f = new File(file);
		f.delete();
		return advertismentDao.deleteAd(media_id);
	}
	@Override
	public int updateAd(Advertisment ad) {
		// TODO Auto-generated method stub
		return advertismentDao.updateAd(ad);
	}
	@Override
	public Advertisment getFilenameById(String media_id) {
		// TODO Auto-generated method stub
		return advertismentDao.getFilenameById(media_id);
	}
}
