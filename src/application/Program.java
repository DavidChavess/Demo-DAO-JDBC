package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		SellerDao sld = DaoFactory.createSellerDao();
		
		Seller seller = sld.findById(3);
		System.out.println("===Test01 findById===");
		System.out.print(seller);
		
		
		System.out.println("\n===Test02 findByDepartment===");
		Department department = new Department(2, null);
		List<Seller> departments = sld.findByDepartment(department);
		departments.forEach(System.out::println);
		
		
		System.out.println("\n\n ===Test03 findAll===");
		List<Seller> listAll = sld.findAll();
		listAll.forEach(System.out::println);
		
		

		
		

	}

}
