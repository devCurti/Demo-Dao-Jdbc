package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.sellerDao;
import model.entities.department;
import model.entities.seller;

public class sellerDaoJDBC implements sellerDao {

	private Connection conn;
	
	public sellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name"
					+ " FROM seller INNER JOIN department"
					+ " ON seller.DepartmentId = department.Id"
					+ " WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				department dep = instantiateDepartment(rs);
				seller obj = instatianteSeller(rs, dep);
				return obj;
				
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	public List<seller> findByDepartmentId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<seller> sellerList = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name"
					+ " FROM seller INNER JOIN department"
					+ " ON seller.DepartmentId = department.Id"
					+ " WHERE DepartmentId = ?"
					+ " ORDER BY seller.Name");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				department depa = instantiateDepartment(rs);
				do {
					seller obj = instatianteSeller(rs,depa);
					sellerList.add(obj);
					} while(rs.next());
				return sellerList;	
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private seller instatianteSeller(ResultSet rs, department dep) throws SQLException {
		seller obj = new seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private department instantiateDepartment(ResultSet rs) throws SQLException {
		department dep = new department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
