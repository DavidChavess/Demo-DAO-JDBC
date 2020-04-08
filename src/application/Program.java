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
		
		
		System.out.println("\n\n ===Test05 Update===");
		seller = sellerDao.findById(1);
		seller.setName("Bruce Waine");
		seller.setBaseSalary(4000.0);
		sellerDao.update(seller);
		System.out.println("Update Completed !");
		
		System.out.println("\n\n ===Test06 Delete===");

		for(int i = 10; i < 16; i++) {
			sellerDao.deleteById(i);
		}
	
		System.out.println("Deleted duplicates");

	}

}
