package com.ftn.OnlineFitness.dao;

import java.util.List;

import com.ftn.OnlineFitness.model.Admin;


public interface AdminDAO {
	
	public Admin findOne(int id);

	public List<Admin> findAll();
	
	public int save(Admin admin);

	public int update(Admin admin);

	public int delete(int id);

}
