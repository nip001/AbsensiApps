package com.juaracoding.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaracoding.entity.Absensi;
import com.juaracoding.repository.AbsensiRepository;

@Service
public class ModelAbsensi implements ModelAbsensiInterface{

	@Autowired
	AbsensiRepository aRepo;

	@Override
	public List<Absensi> getAllAbsensi() {
		// TODO Auto-generated method stub
		return this.aRepo.findAll();
	}

	@Override
	public String addAbsensi(Absensi absensi) {
		// TODO Auto-generated method stub
		this.aRepo.save(absensi);
		return "Berhasil menyimpan";
	}

	@Override
	public String updateAbsensi(Absensi absensi) {
		// TODO Auto-generated method stub
		this.aRepo.save(absensi);
		return "Berhasil memperbarui";
	}

	@Override
	public Absensi getByIdAbsensi(long id) {
		// TODO Auto-generated method stub
		return this.aRepo.findById(id).get();
	}

	@Override
	public String deleteAbsensi(long id) {
		// TODO Auto-generated method stub
		this.aRepo.deleteById(id);
		return "Berhasil menyimpan";
	}

	@Override
	public List<Absensi> getAllAbsensiByUsername(String username) {
		// TODO Auto-generated method stub
		return this.aRepo.findByUsername(username);
	}

	@Override
	public Absensi getCheckIn(String tanggal) {
		// TODO Auto-generated method stub
		return this.aRepo.checkCheckin(tanggal);
	}
}
