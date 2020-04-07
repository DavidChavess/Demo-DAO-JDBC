package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		SellerDao sld = DaoFactory.createSellerDao();
		
		Seller seller = sld.findById(3);
		
		System.out.print(seller);

	}

}
