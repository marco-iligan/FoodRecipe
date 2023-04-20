package com.foodrecipe.repository;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public interface IRepository<T> {
	List<T> getAll();
	T findById(Long id);
	int create(T model);
	int update(T model);
	void remove(Long id);
	DefaultTableModel populateTable();
}
