package application;

import java.awt.image.DataBufferShort;
import java.util.List;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		System.out.println("===Test 01 Find All ===");
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		List<Department> list = departmentDao.findAll();
		list.forEach(System.out::println);
		
		System.out.println("===Test 02 FindById ===");
		Department dp = departmentDao.findById(2);
		System.out.println(dp);
		
		try {		
			System.out.println("===Test 03 DeleteById ===");
			departmentDao.deleteById(6);
			System.out.println("Deleted Completed");
		}catch (DbException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("===Test 04 insert ===");
		Department newDp = new Department(null, "Music");
		departmentDao.insert(newDp);
		System.out.println("Insertd Completed! Id = " + newDp.getId());
		

		System.out.println("===Test 05 Update ===");
		dp = departmentDao.findById(1);
		dp.setName("Devs");
		departmentDao.update(dp);
		System.out.println("Update Completed!");
		
		
		
		
	}

}
