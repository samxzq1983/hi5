package com.hi5.ff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hi5.ff.dao.ProfileDao;
import com.hi5.ff.util.SQLUtil;
import com.hi5.ff.entity.Profile;
import com.hi5.ff.entity.User;

public class ProfileDao {

	public Profile getProfile(int profileId) {

		Profile profile = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from profile where profile_id=?";
		Object[] args = { (Integer) profileId };
		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			if (rs.next()) {
				profile = new Profile();
				profile.setProfileDesc(rs.getString("profile_desc"));
				profile.setProfileId(rs.getInt("profile_id"));
				profile.setProfileName(rs.getString("profile_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}
		return profile;

	}

	public List<Profile> getAllProfiles() {
		List<Profile> profileList = new ArrayList<Profile>();
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from profile";
		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Profile profile = new Profile();
				profile.setProfileDesc(rs.getString("profile_desc"));
				profile.setProfileId(rs.getInt("profile_id"));
				profile.setProfileName(rs.getString("profile_name"));

				profileList.add(profile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(stmt);
			SQLUtil.closeConnection(conn);
		}
		return profileList;

	}

	public String getProfileNameById(int profileId) {
		// TODO Auto-generated method stub
		String name = "";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from profile where profile_id=?";
		try{
			pstmt = conn.prepareStatement(sql);
			Object[] args = {(Integer) profileId};

			rs = SQLUtil.executePreparedQuery(pstmt, args);
			while(rs.next()){
				name = rs.getString("profile_name");
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

	public boolean removeProfile(int profileId) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "delete from profile where profile_id=?";
		Object[] args= {(Integer) profileId};

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

	public boolean editProfile(Profile existProfile) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "update profile set profile_name=?, profile_desc=? where profile_id=?";
		Object[] args= {existProfile.getProfileName(), existProfile.getProfileDesc(), existProfile.getProfileId()};

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

	public boolean addProfile(Profile newProfile) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "insert into profile (profile_name, profile_desc) values (?,?)";
		Object[] args= {newProfile.getProfileName(), newProfile.getProfileDesc()};

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
