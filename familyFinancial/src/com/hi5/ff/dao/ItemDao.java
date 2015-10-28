package com.hi5.ff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hi5.ff.dao.ItemDao;
import com.hi5.ff.util.FFConstants;
import com.hi5.ff.util.SQLUtil;
import com.hi5.ff.entity.CategoryExpenditure;
import com.hi5.ff.entity.DayExpenditure;
import com.hi5.ff.entity.Item;
import com.hi5.ff.entity.User;

public class ItemDao {

	public Item getItem(int itemId) {

		Item item = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from item where item_id=?";
		Object[] args = { itemId };
		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			if (rs.next()) {
				item = new Item();
				item.setItemId(rs.getInt("item_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemPrice(rs.getDouble("item_price"));
				item.setItemRemark(rs.getString("item_remark"));
				item.setUserId(rs.getInt("user_id"));
				item.setItemAddDate(rs.getDate("item_add_date"));
				item.setCategoryId(rs.getInt("category_id"));
			}

		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return item;

	}

	public List<Item> getAllItems() {

		List<Item> itemList = new ArrayList<Item>();
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from item order by item_id";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
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

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(stmt);
			SQLUtil.closeConnection(conn);
		}

		return itemList;

	}

	public List<Item> getAllUserItems(int userid) {

		List<Item> itemList = new ArrayList<Item>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM ITEM WHERE USER_ID = ? ORDER BY ITEM_ID";
		Object[] args = { userid };

		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			while (rs.next()) {
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

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return itemList;

	}

	public List<Item> getAllItemsByMonth(int month) {

		List<Item> itemList = new ArrayList<Item>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM ITEM WHERE MONTH(ITEM_ADD_DATE) = ? ORDER BY ITEM_ID";
		Object[] args = { month };

		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);

			while (rs.next()) {
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

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return itemList;

	}

	public List<Item> getAllUserItemsByMonth(int userid, int month) {

		List<Item> itemList = new ArrayList<Item>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM ITEM WHERE USER_ID = ? AND MONTH(ITEM_ADD_DATE) = ? ORDER BY ITEM_ID";
		Object[] args = { userid, month };

		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);

			while (rs.next()) {
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

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return itemList;

	}

	public List<DayExpenditure> getAllUserItemsByDurationForReporting(int userid, String startDate, String endDate) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DayExpenditure> itemList = new ArrayList<DayExpenditure>();

		try {
			if(userid == FFConstants.EVERYBODY) {
				String sql = "SELECT ITEM_ADD_DATE, SUM(ITEM_PRICE) FROM ITEM WHERE ITEM_ADD_DATE >= ? AND ITEM_ADD_DATE <= ? GROUP BY DATE(ITEM_ADD_DATE) ORDER BY ITEM_ADD_DATE";
				Object[] args = { startDate, endDate };
				pstmt = conn.prepareStatement(sql);
				rs = SQLUtil.executePreparedQuery(pstmt, args);
			}

			else {
				String sql = "SELECT ITEM_ADD_DATE, SUM(ITEM_PRICE) FROM ITEM WHERE USER_ID = ? AND ITEM_ADD_DATE >= ? AND ITEM_ADD_DATE <= ? GROUP BY DATE(ITEM_ADD_DATE) ORDER BY ITEM_ADD_DATE";
				Object[] args = { userid, startDate, endDate };
				pstmt = conn.prepareStatement(sql);
				rs = SQLUtil.executePreparedQuery(pstmt, args);
			}

			while (rs.next()) {
				DayExpenditure expenditure = new DayExpenditure();
				expenditure.setDate(rs.getDate("ITEM_ADD_DATE"));
				expenditure.setAmount(rs.getDouble("SUM(ITEM_PRICE)"));
				itemList.add(expenditure);
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return itemList;

	}

public List<Item> getAllUserItemsByDuration(int userid, String startDate, String endDate) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Item> itemList = new ArrayList<Item>();

		String sql = "SELECT * FROM ITEM WHERE USER_ID = ? AND ITEM_ADD_DATE >= ? AND ITEM_ADD_DATE <= ? ORDER BY ITEM_ADD_DATE";
		Object[] args = { userid, startDate, endDate };

		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);


			while (rs.next()) {
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

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return itemList;

	}

	public List<Item> getAllItemsByMonthAndCategory(int month, int category) {

		List<Item> itemList = new ArrayList<Item>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM ITEM WHERE MONTH(ITEM_ADD_DATE) = ? AND CATEGORY_ID = ? ORDER BY ITEM_ID";
		Object[] args = { month, category };

		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);

			while (rs.next()) {
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

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return itemList;

	}

	public double getExpenditureByMonth(int month) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double expenditure = 0.00;

		String sql = "SELECT SUM(ITEM_PRICE) FROM ITEM WHERE MONTH(ITEM_ADD_DATE) = ?";
		Object[] args = { month };

		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);

			while (rs.next()) {
				expenditure = rs.getDouble("SUM(ITEM_PRICE)");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return expenditure;

	}

	public double getUserExpenditureByMonth(int userid, int month) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double expenditure = 0.00;

		String sql = "SELECT SUM(ITEM_PRICE) FROM ITEM WHERE USER_ID = ? AND MONTH(ITEM_ADD_DATE) = ?";
		Object[] args = { userid, month };

		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);

			while (rs.next()) {
				expenditure = rs.getDouble("SUM(ITEM_PRICE)");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return expenditure;

	}

	public double getUserExpenditureByCategoryAndMonth(int userid, int category, int month) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double expenditure = 0.00;

		String sql = "SELECT SUM(ITEM_PRICE) FROM ITEM WHERE USER_ID = ? AND CATEGORY_ID = ? AND MONTH(ITEM_ADD_DATE) = ?";
		Object[] args = { userid, category, month };

		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);

			while (rs.next()) {
				expenditure = rs.getDouble("SUM(ITEM_PRICE)");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return expenditure;

	}

public List<CategoryExpenditure> getUserExpenditureByCategoryAndDurationForReporting(int userid, String startDate, String endDate) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CategoryExpenditure> itemList = new ArrayList<CategoryExpenditure>();

		try {
			if(userid == FFConstants.EVERYBODY) {
				String sql = "SELECT CATEGORY_ID, SUM(ITEM_PRICE) FROM ITEM WHERE ITEM_ADD_DATE >= STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s') AND ITEM_ADD_DATE <= STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s')  GROUP BY CATEGORY_ID ORDER BY CATEGORY_ID";
				startDate = startDate + " 00:00:01";
				endDate = endDate + " 23:59:59";
				Object[] args = { startDate, endDate };
				pstmt = conn.prepareStatement(sql);
				rs = SQLUtil.executePreparedQuery(pstmt, args);
			}

			else {
				String sql = "SELECT CATEGORY_ID, SUM(ITEM_PRICE) FROM ITEM WHERE USER_ID = ? AND ITEM_ADD_DATE >= ? AND ITEM_ADD_DATE <= ? GROUP BY CATEGORY_ID ORDER BY CATEGORY_ID";
				startDate = startDate + " 00:00:01";
				endDate = endDate + " 23:59:59";
				Object[] args = { userid, startDate, endDate };
				pstmt = conn.prepareStatement(sql);
				rs = SQLUtil.executePreparedQuery(pstmt, args);
			}

			while (rs.next()) {
				CategoryExpenditure item = new CategoryExpenditure();
				item.setCategoryID(rs.getInt("CATEGORY_ID"));
				item.setExpenditure(rs.getDouble("SUM(ITEM_PRICE)"));
				itemList.add(item);
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
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
		Object[] args = { newItem.getItemName(), newItem.getItemPrice(), newItem.getItemRemark(), newItem.getCategoryId(), newItem.getUserId() };

		try {
			pstmt = conn.prepareStatement(sql);
			int r = SQLUtil.executeUpd(pstmt, args);

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		}
		finally {
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
		Object[] args = { existItem.getItemName(), existItem.getItemPrice(), existItem.getItemRemark(), existItem.getCategoryId(), existItem.getUserId(), existItem.getItemId() };

		try {
			pstmt = conn.prepareStatement(sql);
			int r = SQLUtil.executeUpd(pstmt, args);
			if (r != 1) {
				success = false;
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		}
		finally {
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
		Object[] args = { (Integer) itemId };

		try {
			pstmt = conn.prepareStatement(sql);
			int r = SQLUtil.executeUpd(pstmt, args);
			if (r != 1) {
				success = false;
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		}
		finally {
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
		try {
			Object[] args = { (Integer) userId };
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			while (rs.next()) {
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
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}
		return itemList;

	}

	public List<String> getUserExpenditureDates(int userId, String startDate, String endDate) {

		List<String> dateList = new ArrayList<String>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select distinct DATE_FORMAT(item_add_date,'%Y-%m-%d') dateStr from item where user_id=? AND ITEM_ADD_DATE >= STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s')  AND ITEM_ADD_DATE <= STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s')  ";
		try {
			Object[] args = { (Integer) userId, startDate+" 00:00:01", endDate+" 23:59:59" };
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			while (rs.next()) {
				dateList.add(rs.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}
		return dateList;

	}

	public List<CategoryExpenditure> getUserExpenditureForBarChart(int userid, String startDate, String endDate) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CategoryExpenditure> itemList = new ArrayList<CategoryExpenditure>();

		try {
			if(userid == FFConstants.EVERYBODY) {
				String sql = "select x.CATEGORY_id, sum(x.ITEM_PRICE) from " +
							"(" +
							"select CATEGORY_id, item_price " +
							"FROM hi5.ITEM i " +
							//"WHERE USER_ID = 1 " +
							"AND ITEM_ADD_DATE >= STR_TO_DATE('2015-10-24 00:00:01', '%Y-%m-%d %H:%i:%s') " +
							"and ITEM_ADD_DATE <= STR_TO_DATE('2015-10-24 23:59:59', '%Y-%m-%d %H:%i:%s') " +
							"Union " +
							"select CATEGORY_id, 0.0 item_price from category " +
							") x " +
							"group by x.category_id";

				startDate = startDate + " 00:00:01";
				endDate = endDate + " 23:59:59";
				Object[] args = { startDate, endDate };
				pstmt = conn.prepareStatement(sql);
				rs = SQLUtil.executePreparedQuery(pstmt, args);
			}

			else {
				String sql = "select x.CATEGORY_id, sum(x.ITEM_PRICE) from " +
						"(" +
						"select CATEGORY_id, item_price " +
						"FROM hi5.ITEM i " +
						"WHERE USER_ID = ? " +
						"AND ITEM_ADD_DATE >= STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s') " +
						"and ITEM_ADD_DATE <= STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s') " +
						"Union " +
						"select CATEGORY_id, 0.0 item_price from category " +
						") x " +
						"group by x.category_id";
				startDate = startDate + " 00:00:01";
				endDate = endDate + " 23:59:59";
				Object[] args = { userid, startDate, endDate };
				pstmt = conn.prepareStatement(sql);
				rs = SQLUtil.executePreparedQuery(pstmt, args);
			}

			while (rs.next()) {
				CategoryExpenditure item = new CategoryExpenditure();
				item.setCategoryID(rs.getInt(1));
				item.setExpenditure(rs.getDouble(2));
				itemList.add(item);
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return itemList;

	}

}
