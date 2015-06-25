package com.userprofile.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author analian
 *
 */
@Controller
public class FileUploadController {

	 @RequestMapping(value="/uploadPhoto", method=RequestMethod.GET)
	    public @ResponseBody String provideUploadInfo() {
	        return "Upload File.";
	    }
	 
	 @RequestMapping(value="/uploadPhoto", method=RequestMethod.POST)
	    public @ResponseBody void handleFileUpload(@RequestParam("file") MultipartFile file){
	        if (!file.isEmpty()) {
	            try {	
	            	System.out.println();
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
	                stream.write(bytes);
	                stream.close();
	            } catch (Exception e) {
	                System.err.println(e.getMessage());
	            }
	        } else {
	           System.err.println();
	        }
	    }
}
