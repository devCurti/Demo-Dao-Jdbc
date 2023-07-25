package model.dao;

import java.util.List;

import model.entities.seller;

public interface sellerDao {
	void insert(seller obj);
	void update(seller obj);
	void deleteById(Integer id);
	seller findById(Integer id);
	List<seller> findByDepartmentId(Integer id);
	List<seller> findAll();
}
