package model.dao;

import java.util.List;

import model.entities.department;

public interface departmentDao {
	
	void insert(department obj);
	void update(department obj);
	void deleteById(Integer id);
	department findById(Integer id);
	List<department> finaAll();
}
