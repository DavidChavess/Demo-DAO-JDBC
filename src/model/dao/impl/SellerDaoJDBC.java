package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn = null;
	
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		
		PreparedStatement st = null;
		try {		
			st = conn.prepareStatement(
					"INSERT INTO seller " 
					+ " (Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (? ,?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
					
					st.setString(1, obj.getName());
					st.setString(2, obj.getEmail());
					st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
					st.setDouble(4, obj.getBaseSalary());
					st.setInt(5, obj.getDepartment().getId());	
					
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					obj.setId(rs.getInt(1));
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Unexpected error! No affected rows");
			}
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					 "SELECT s.*, d.name as 'DepName' FROM seller s "
					+ "INNER JOIN department d on d.id = s.departmentId "
					+ "WHERE s.id = ? ");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dp = instantiateDepartment(rs);
				Seller s = instantiateSeller(rs, dp);
				return s;
			}
			
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
		Seller s = new Seller();
		s.setId(rs.getInt("Id"));
		s.setName(rs.getString("Name"));
		s.setEmail(rs.getString("Email"));
		s.setBirthDate(rs.getDate("BirthDate"));
		s.setDepartment(dp);
		
		return s;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dp = new Department();
		dp.setId(rs.getInt("DepartmentId"));
		dp.setName(rs.getString("DepName"));
		return dp;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT s.*, d.name as DepName "
					+ "FROM seller s INNER JOIN department d on d.id = s.departmentId "
					+ "ORDER BY s.name");
					
			rs = st.executeQuery();

			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				list.add(instantiateSeller(rs, dep));
			}
			return list;		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findByDepartment(Department dp) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT s.*, d.name as DepName "
					+ "FROM seller s INNER JOIN department d on d.id = s.departmentId "
					+ "WHERE s.departmentId = ? "
					+ "ORDER BY s.name");
			
			st.setInt(1, dp.getId());
						
			rs = st.executeQuery();

			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				list.add(instantiateSeller(rs, dep));
			}
			return list;		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
