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
		
		System.out.println("-- TEST 2: Insert a department --");
		System.out.println("Type a department name: ");
		String depName = sc.nextLine();
		department depObj = new department(depName);
		depDao.insert(depObj);
		System.out.println("New department inserted!");
		
		
		System.out.println("-- TEST 3: Find department by id --");
		System.out.println("Type a department id: ");
		int depId = sc.nextInt();
		depObj = depDao.findById(depId);
		System.out.println(depObj);
		
		System.out.println("-- TEST 4: Delete department by id --");
		System.out.println("Type a department id: ");
		depId = sc.nextInt();
		depDao.deleteById(depId);
		
		System.out.println("-- TEST 5: Update department --");
		depObj.setName("Building");
		depDao.update(depObj);
		
		
		sc.close();

	}

}
