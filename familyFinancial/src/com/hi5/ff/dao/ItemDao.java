package com.hi5.ff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hi5.ff.dao.ItemDao;
import com.hi5.ff.util.SQLUtil;
import com.hi5.ff.entity.Item;
import com.hi5.ff.entity.User;

public class ItemDao {

	public Item getItem(int itemId){

		Item item = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from item where item_id=?";
		Object[] args = {itemId};
		try{
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			if(rs.next()){
				item= new Item();
				item.setItemId(rs.getInt("item_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemPrice(rs.getDouble("item_price"));
				item.setItemRemark(rs.getString("item_remark"));
				item.setUserId(rs.getInt("user_id"));
				item.setItemAddDate(rs.getDate("item_add_date"));
				item.setCategoryId(rs.getInt("category_id"));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}
		return item;


	}

	public List<Item> getAllItems(){

		List<Item> itemList = new ArrayList<Item>();
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from item order by item_id";
		try{
			stmt = conn.createStatement();
			rs =stmt.executeQuery(sql);
			while(rs.next()){
				Item item = new Item();
				item.setItemId(rs.getInt("item_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemPrice(rs.getDouble("item_price"));
				item.setItemRemark(rs.getString("item_remark"));
				item.setUserId(rs.getInt("user_id"));
				item.setItemAddDate(rs.getDate("item_add_date"));
				item.setCategoryId(rs.getInt("category_id"));

				itemList.add(item);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(stmt);
			SQLUtil.closeConnection(conn);
		}
		return itemList;


	}

	public boolean addItem(Item newItem) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "insert into item (item_name, item_price, item_remark, item_add_date, category_id, user_id) values (?,?,?,now(),?,?)";
		Object[] args= {newItem.getItemName(), newItem.getItemPrice(), newItem.getItemRemark(), newItem.getCategoryId(), newItem.getUserId()};

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

	public boolean editItem(Item existItem) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "update item set item_name=?, item_price=?, item_remark=?, item_add_date=now(), category_id=?, user_id=? where item_id=?";
		Object[] args= {existItem.getItemName(), existItem.getItemPrice(), existItem.getItemRemark(), existItem.getCategoryId(), existItem.getUserId(), existItem.getItemId()};

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

	public boolean removeItem(int itemId) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "delete from item where item_id=?";
		Object[] args= {(Integer) itemId};

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

	public List<Item> getAllItemsByUser(int userId) {

		List<Item> itemList = new ArrayList<Item>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from item where user_id=? order by item_id";
		try{
			Object[] args= {(Integer) userId};
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			while(rs.next()){
				Item item = new Item();
				item.setItemId(rs.getInt("item_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemPrice(rs.getDouble("item_price"));
				item.setItemRemark(rs.getString("item_remark"));
				item.setUserId(rs.getInt("user_id"));
				item.setItemAddDate(rs.getDate("item_add_date"));
				item.setCategoryId(rs.getInt("category_id"));

				itemList.add(item);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}
		return itemList;


	}

}
