package com.juaracoding.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaracoding.entity.User;
import com.juaracoding.repository.UserRepository;

@Service
public class ModelUser implements ModelUserInterface{

	@Autowired
	UserRepository uRepo;
	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return this.uRepo.findAll();
	}

	@Override
	public String addUser(User user) {
		// TODO Auto-generated method stub
		this.uRepo.save(user);
		return "Berhasil menyimpan";
	}

	@Override
	public String updateUser(User user) {
		// TODO Auto-generated method stub
		this.uRepo.save(user);
		return "Berhasil update";
	}

	@Override
	public User getByIdUser(long id) {
		// TODO Auto-generated method stub
		return this.uRepo.findById(id).get();
	}

	@Override
	public String deleteUser(long id) {
		// TODO Auto-generated method stub
		this.uRepo.deleteById(id);
		return "Berhasil menghapus";
	}

	@Override
	public int validasiUser(String username, String password) {
		// TODO Auto-generated method stub
		return this.uRepo.validateUser(username, password);
	}

}
