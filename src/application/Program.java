package application;

import model.dao.daoFactory;
import model.dao.sellerDao;
import model.entities.seller;

public class Program {
	public static void main(String args[]) {
		
		sellerDao SellerDao = daoFactory.createSellerDao();
		
		System.out.println("-- TEST 1: Test find by Id --");
		seller Seller = SellerDao.findById(3);
		
		System.out.println(Seller);
	}
}
