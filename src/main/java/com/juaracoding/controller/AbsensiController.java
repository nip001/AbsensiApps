package com.juaracoding.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.juaracoding.entity.Absensi;
import com.juaracoding.services.ModelAbsensi;
import com.juaracoding.utility.FileUtility;


@RestController
@RequestMapping("/absensi")
public class AbsensiController {

	@Autowired
	ModelAbsensi aModel;

	@GetMapping("/")
	public ResponseEntity<List<Absensi>> getAll(){
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.aModel.getAllAbsensi());
	}
	
	@GetMapping("/absensi")
	public ResponseEntity<List<Absensi>> getAbsensiByUsername(@RequestParam String username){
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.aModel.getAllAbsensiByUsername(username));
	}
	

	@PostMapping("/absen")
	public ResponseEntity<String> addAbsen(@RequestParam(value = "file") MultipartFile images, @ModelAttribute(value="data") String dataJSON) throws IOException{
		Date tanggal = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");  
		String strDate = formatter.format(tanggal);  
//		System.out.println("Date Format with E, dd MMM yyyy HH:mm:ss z : "+strDate);  
		Absensi temp = this.aModel.getCheckIn(strDate);

		String filename = StringUtils.cleanPath(images.getOriginalFilename());
		String uploadDir="user-photos/";
		FileUtility.saveFile(uploadDir, filename, images);
		Absensi absensi= new Gson().fromJson(dataJSON, Absensi.class);
		
		if(temp == null) {
			formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			strDate = formatter.format(tanggal);
			
			absensi.setDataFoto(filename) ;
			absensi.setTanggalMasuk(strDate);
			

		}else if(temp.getTanggalKeluar() == null) {

			formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			strDate = formatter.format(tanggal);
			
			absensi.setDataFoto(filename) ;
			absensi.setTanggalMasuk(temp.getTanggalMasuk());
			absensi.setTanggalKeluar(strDate);
			absensi.setId(temp.getId());
		}

		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.aModel.addAbsensi(absensi));
		
	}
	
}
