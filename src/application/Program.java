package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(3);
		System.out.println("===Test01 findById===");
		System.out.print(seller);
		
		
		System.out.println("\n===Test02 findByDepartment===");
		Department department = new Department(2, null);
		List<Seller> departments = sellerDao.findByDepartment(department);
		departments.forEach(System.out::println);
		
		
		System.out.println("\n\n ===Test03 findAll===");
		List<Seller> listAll = sellerDao.findAll();
		listAll.forEach(System.out::println);
		
		
	
		
		System.out.println("\n\n ===Test04 Insert===");
		Seller insertSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(insertSeller);
		System.out.println("INSERTED! NEW ID "+ insertSeller.getId());

	}

}
