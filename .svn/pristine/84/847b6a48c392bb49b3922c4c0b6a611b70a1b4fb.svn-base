package com.pulan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pulan.Service.AdvertismentService;
import com.pulan.Service.WkUserService;
import com.pulan.model.Advertisment;
import com.pulan.model.VerbalTrick;
import com.pulan.model.WkUser;
import com.pulan.utils.FileUtil;

@Controller // (直接返回数据而不是视图)
@Configuration
@RequestMapping(value = "/message")
public class AdController {
	@Value("${web.upload-path}")
	private String path;
	@Autowired
	private AdvertismentService advertismentServiceImpl;
	@RequestMapping("/uploadFile")
	public String touploadFile(ModelMap mm){
		mm.put("allData", advertismentServiceImpl.getAllFile());
        return "pages/uploadFile";
	}
	@RequestMapping("/uploadAd")
	public String uploadMedia(@RequestParam("file") MultipartFile file,
            HttpServletRequest request){
		String subject = request.getParameter("subject");
		String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
//        String filePath = request.getSession().getServletContext().getRealPath("uploadFile/");
        try {
            FileUtil.uploadFile(file.getBytes(), path, fileName);
            advertismentServiceImpl.insertAd(new Advertisment(fileName,contentType,path+fileName,subject));
        } catch (Exception e) {
            // TODO: handle exception
      System.out.println(e.getMessage());
        }
        //返回json
        return "index";
	}
	@ResponseBody
	@RequestMapping("/getFile")
	public JSONObject getFile(@RequestBody String msgData){
		JSONObject result = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		List<Advertisment> list = advertismentServiceImpl.getAllFile();
		result.put("resp", list);
        //返回json
        return result;
	}
	@ResponseBody
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	public JSONObject deleteVerbal(@RequestBody String msgData,HttpServletRequest request) {
	    String filePath = request.getSession().getServletContext().getRealPath("uploadFile/");
		JSONObject result = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		String media_id = msgObj.getString("media_id");
		int ret = advertismentServiceImpl.deleteAd(media_id,filePath);
		if (ret == 0) {
			result.put("resp", "删除失败");
		} else {
			result.put("resp", "删除成功");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "updateFile", method = RequestMethod.POST)
	public JSONObject updateFile(@RequestBody Advertisment ad) {
		JSONObject result = new JSONObject();
		int ret = advertismentServiceImpl.updateAd(ad);
		if (ret == 0) {
			result.put("resp", "修改失败");
		} else {
			result.put("resp", "修改成功");
		}
		return result;
	}
}
