package application;

import java.util.List;

import model.dao.daoFactory;
import model.dao.sellerDao;
import model.entities.seller;

public class Program {
	public static void main(String args[]) {
		
		sellerDao SellerDao = daoFactory.createSellerDao();
		
		System.out.println("-- TEST 1: Test find by Id --");
		seller Seller = SellerDao.findById(3);
		System.out.println(Seller);
		
		System.out.println("-- TEST 2: Test find by DepartmentId --");
		List<seller> sellerList = SellerDao.findByDepartmentId(1);
		System.out.println(sellerList);
	}
}
