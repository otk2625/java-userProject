package com.testProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.testProject.config.DB;
import com.testProject.dto.JoinReqDto;
import com.testProject.dto.UserDto;

public class UserDao {

	public UserDto login(String username, String password) {
		String sql = "SELECT id, username, email,role FROM user WHERE username = ? AND password = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs =  pstmt.executeQuery();

			// Persistence API - 스프링프레임워크에 있음
			if(rs.next()) {
				UserDto user = UserDto.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.role(rs.getString("role"))
						.build();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}

		return null;
	}

	public int join(JoinReqDto dto) {
		
		String sql = "INSERT INTO user(username, password, email) VALUES(?,?,?) ";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			try {
				conn.close();
				pstmt.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		return -1;
	}

	public List<UserDto> findAll() {
		List<UserDto> userList = new ArrayList<>();
		
		String sql = "SELECT id,username,password,email,role "
				+ "FROM user ";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				UserDto dto = new UserDto().builder()
	            		.id(rs.getInt("id"))
	            		.username(rs.getString("username"))
	            		.password(rs.getString("password"))
	            		.email(rs.getString("email"))
	            		.role(rs.getString("role"))
	            		.build();
	            
				userList.add(dto);
	        }
			
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public int deleteUser(int id) {
		String sql = "DELETE FROM user WHERE id=? ";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			try {
				conn.close();
				pstmt.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		return -1;
	}

}
