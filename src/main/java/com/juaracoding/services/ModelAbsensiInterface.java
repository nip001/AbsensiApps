package com.juaracoding.services;

import java.util.List;

import com.juaracoding.entity.Absensi;


public interface ModelAbsensiInterface {

	public List<Absensi> getAllAbsensi();
	public List<Absensi> getAllAbsensiByUsername(String username);
	public Absensi getCheckIn(String tanggal);
	public String addAbsensi(Absensi absensi);
	public String updateAbsensi(Absensi absensi);
	public Absensi getByIdAbsensi(long id);
	public String deleteAbsensi(long id);
}
