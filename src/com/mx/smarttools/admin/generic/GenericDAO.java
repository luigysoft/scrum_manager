package com.mx.smarttools.admin.generic;

import java.util.List;

public interface GenericDAO<T> {

	public boolean add(T t);
	public boolean update(int id, T t);
	public boolean delete(int id);
	public boolean delete(T t);
	public T getById(int id);
	public List<T> getAll();
}
