package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department dp = new Department(1, "Books");
		
		Seller s = new Seller(21, "Bob", "bobBrown@gmail.com", new Date(), 3000.0, dp);
		
		SellerDao sld = DaoFactory.createSellerDao();
		
		System.out.print(s);

	}

}
