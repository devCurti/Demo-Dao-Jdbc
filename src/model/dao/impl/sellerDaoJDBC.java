package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO seller "
					+ "(Name,Email,BirthDate,BaseSalary,DepartmentId) VALUES "
					+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}else {
				throw new DbException("Unexpected error: No lines affected!");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(null);
		}
		
	}

	@Override
	public void update(seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(null);
		}
		
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			st.setInt(1, id);
			
			int rows = st.executeUpdate();
			
			if(rows == 0) {
				throw new DbException("None affected rows!");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
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
		dep.setName(rs.getString("department.Name"));
		return dep;
	}

	@Override
	public List<seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<seller> sellerList = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name"
					+ " FROM seller INNER JOIN department"
					+ " ON seller.DepartmentId = department.Id"
					+ " ORDER BY seller.Name");
			rs = st.executeQuery();
			
			if(rs.next()) {
				do {
					department depa = instantiateDepartment(rs);
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

}
