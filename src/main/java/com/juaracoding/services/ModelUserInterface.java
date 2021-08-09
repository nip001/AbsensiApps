package com.juaracoding.services;

import java.util.List;

import com.juaracoding.entity.User;


public interface ModelUserInterface {

	public List<User> getAllUser();
	public String addUser(User user);
	public String updateUser(User user);
	public User getByIdUser(long id);
	public String deleteUser(long id);
	public int validasiUser(String username, String password);
}
