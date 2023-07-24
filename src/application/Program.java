package application;

import java.util.Date;

import model.entities.department;
import model.entities.seller;

public class Program {
	public static void main(String args[]) {
		
		department obj = new department(1,"Books");
		seller seller = new seller(21,"bob", "bob@gmail.com", new Date(), 3000, obj);
		
		System.out.println(obj);
		System.out.println(seller);
	}
}
