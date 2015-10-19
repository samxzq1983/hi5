package com.hi5.ff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hi5.ff.dao.CategoryDao;
import com.hi5.ff.util.SQLUtil;
import com.hi5.ff.entity.Category;
import com.hi5.ff.entity.Profile;
import com.hi5.ff.entity.User;

public class CategoryDao {

	public Category getCategory(int categoryId) {

		Category category = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from category where category_id=?";
		Object[] args = { (Integer) categoryId };
		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			if (rs.next()) {
				category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}
		return category;

	}

	public List<Category> getAllCategories() {
		List<Category> categoryList = new ArrayList<Category>();
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from category";
		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));

				categoryList.add(category);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(stmt);
			SQLUtil.closeConnection(conn);
		}
		return categoryList;

	}

	public String getCategoryNameById(int categoryId) {
		// TODO Auto-generated method stub
		String name = "";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from category where category_id=?";
		try{
			pstmt = conn.prepareStatement(sql);
			Object[] args = {(Integer) categoryId};

			rs = SQLUtil.executePreparedQuery(pstmt, args);
			while(rs.next()){
				name = rs.getString("category_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}
		return name;
	}

	public boolean removeCategory(int categoryId) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "delete from category where category_id=?";
		Object[] args= {(Integer) categoryId};

		try {
			pstmt = conn.prepareStatement(sql);
			int r = SQLUtil.executeUpd(pstmt, args);
			if(r!=1){
				success = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success =false;
		}finally{
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return success;



	}

	public boolean editCategory(Category existCategory) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "update category set category_name=? where category_id=?";
		Object[] args= {existCategory.getCategoryName(), existCategory.getCategoryId()};

		try {
			pstmt = conn.prepareStatement(sql);
			int r = SQLUtil.executeUpd(pstmt, args);
			if(r!=1){
				success = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success =false;
		}finally{
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return success;



	}

	public boolean addCategory(Category newCategory) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "insert into category (category_name) values (?)";
		Object[] args= {newCategory.getCategoryName()};

		try {
			pstmt = conn.prepareStatement(sql);
			int r = SQLUtil.executeUpd(pstmt, args);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success =false;
		}finally{
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return success;



	}

}
