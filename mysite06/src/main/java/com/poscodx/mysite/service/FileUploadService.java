package com.poscodx.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.mysite.exception.FileUploadServiceException;

@Service
@PropertySource("classpath:web/fileupload/fileupload.properties")
public class FileUploadService {
	@Autowired
	Environment env;

	public String restore(MultipartFile file) {
		String url = null;

		try {
			File uploadDirectory = new File(env.getProperty("fileupload.uploadLocation"));
			if (!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
			}

			if (file.isEmpty()) {
				return url;
			}

			String originalFilename = file.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			String saveFilename = generateSaveFilename(extName);

			byte[] data = file.getBytes();
			FileOutputStream os = new FileOutputStream(
					env.getProperty("fileupload.uploadLocation") + "/" + saveFilename);
			os.write(data);
			os.close();

			url = env.getProperty("fileupload.resourceUrl") + "/" + saveFilename;

		} catch (IOException e) {
			throw new FileUploadServiceException(e.toString());
		}

		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH + 1);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += "." + extName;

		return filename;
	}

}
