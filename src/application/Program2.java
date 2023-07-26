package application;

import java.util.List;
import java.util.Scanner;

import model.dao.daoFactory;
import model.dao.departmentDao;
import model.entities.department;

public class Program2 {

	public static void main(String[] args) {
		departmentDao depDao = daoFactory.createDepartmentDao();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-- TEST 1: Find all departments --");
		List<department> depList = depDao.findAll();
		depList.stream().forEach((x) -> System.out.println(x));
		
		System.out.println("-- TEST 1: Insert a department --");
		System.out.println("Type a department name: ");
		String depName = sc.nextLine();
		department depObj = new department(depName);
		depDao.insert(depObj);
		System.out.println("New department inserted!");
		

	}

}