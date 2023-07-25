package model.dao;

import model.dao.impl.sellerDaoJDBC;

public class daoFactory {
	public static sellerDao createSellerDao() {
		return new sellerDaoJDBC();	
	}
}
