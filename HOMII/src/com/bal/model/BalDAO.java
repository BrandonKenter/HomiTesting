package com.bal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BalDAO implements BalDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOMII");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_STMT_BY_MEMNO = "SELECT * FROM balance where member_no = ?";
	private static final String GET_ALL_STMT_BY_LAND = "SELECT * FROM balance where land_no = ?";
	private static final String INSERT_STMT = "INSERT INTO balance (member_no, land_no, type, price, crt_dt) VALUES (?, ?, ?, ?, default)";
	
	public void insert(BalVO balVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, balVO.getMember_no());
			pstmt.setInt(2, balVO.getLand_no());
			pstmt.setString(3, balVO.getType());
			pstmt.setFloat(4, balVO.getPrice());

			pstmt.executeUpdate();

			

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	

	public List<BalVO> getAllByMemNo(Integer member_no) {
		List<BalVO> list = new ArrayList<BalVO>();
		BalVO balVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_MEMNO);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				balVO = new BalVO();
				balVO.setBal_no(rs.getInt("bal_no"));
				balVO.setMember_no(rs.getInt("member_no"));
				balVO.setLand_no(rs.getInt("land_no"));
				balVO.setType(rs.getString("type"));
				balVO.setPrice(rs.getFloat("price"));
				balVO.setCrt_dt(rs.getDate("crt_dt"));
				
				list.add(balVO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	public List<BalVO> getAllByLandNo(Integer member_no) {
		List<BalVO> list = new ArrayList<BalVO>();
		BalVO balVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_LAND);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				balVO = new BalVO();
				balVO.setBal_no(rs.getInt("bal_no"));
				balVO.setMember_no(rs.getInt("member_no"));
				balVO.setLand_no(rs.getInt("land_no"));
				balVO.setType(rs.getString("type"));
				balVO.setPrice(rs.getFloat("price"));
				balVO.setCrt_dt(rs.getDate("crt_dt"));
				
				list.add(balVO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
