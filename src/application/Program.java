package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.daoFactory;
import model.dao.sellerDao;
import model.entities.department;
import model.entities.seller;

public class Program {
	public static void main(String args[]) {
		
		sellerDao SellerDao = daoFactory.createSellerDao();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-- TEST 1: Test find by Id --");
		seller Seller = SellerDao.findById(3);
		System.out.println(Seller);
		
		System.out.println("-- TEST 2: Test find by DepartmentId --");
		List<seller> sellerList = SellerDao.findByDepartmentId(1);
		for(seller obj : sellerList) {
			System.out.println(obj);
		}
		
		System.out.println("-- TEST 3: Test find by findAll --");
		sellerList = SellerDao.findAll();
		for(seller obj : sellerList) {
			System.out.println(obj);		
		}
		
		System.out.println("-- TEST 4: Test insert --");
		department dep = new department(2, "Electronics");
		seller newSeller = new seller("Greg", "greg@gmail.com", new Date(), 4000.0, dep);
		SellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		
		System.out.println("-- TEST 5: Test update --");
		Seller.setName("Martha");
		SellerDao.update(Seller);
		System.out.println("Update completed");
		
		System.out.println("-- TEST 6: Test delete --");
		System.out.println("Enter a id to be deleted: ");
		int id = sc.nextInt();
		SellerDao.deleteById(id);
		System.out.println("Delete complete!");
		
		
		sc.close();
		
		
		
		
	}
}
