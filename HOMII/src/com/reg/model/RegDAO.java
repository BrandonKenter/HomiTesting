package com.reg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.comp.model.CompVO;


public class RegDAO implements RegDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/homii?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO register (member_no, ap_name, ap_address, land_name, start_dt, end_dt) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ONE_STMT_BY_MEMNO = "SELECT * FROM register where member_no = ?";
	private static final String GET_ONE_STMT_BY_REGNO = "SELECT * FROM register where reg_no = ?";
	private static final String GET_ALL_STMT_BY_MEMNO = "SELECT * FROM register where member_no = ?";
	private static final String GET_ALL_STMT_BY_LANDNAME = "SELECT * FROM register where land_name = ?";
	private static final String GET_ALL_STMT_BY_APNAME = "SELECT * FROM register where ap_name = ?";
	private static final String UPDATERES = "UPDATE register set ap_name=?, ap_address=?, start_dt=?, end_dt=?, status=? where reg_no = ?";
	
	public int insert(RegVO regVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int num = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, regVO.getMember_no());
			pstmt.setString(2, regVO.getAp_name());
			pstmt.setString(3, regVO.getAp_address());
			pstmt.setString(4, regVO.getLand_name());
			pstmt.setDate(5, regVO.getStart_dt());
			pstmt.setDate(6, regVO.getEnd_dt());

			num = pstmt.executeUpdate();

			

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return num;
	}
	@Override
	public RegVO findByPrimaryKey(Integer member_no) {
		RegVO regVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_MEMNO);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				regVO = new RegVO();
				regVO.setReg_no(rs.getInt("reg_no"));
				regVO.setMember_no(rs.getInt("member_no"));
				regVO.setAp_name(rs.getString("ap_name"));
				regVO.setAp_address(rs.getString("ap_address"));
				regVO.setLand_name(rs.getString("land_name"));
				regVO.setStatus(rs.getString("status"));
				regVO.setStart_dt(rs.getDate("start_dt"));
				regVO.setEnd_dt(rs.getDate("end_dt"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return regVO;
	}
	@Override
	public RegVO findByPrimaryKeyByRegNo(Integer reg_no) {
		
		RegVO regVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_REGNO);
			pstmt.setInt(1, reg_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				regVO = new RegVO();
				regVO.setReg_no(rs.getInt("reg_no"));
				regVO.setMember_no(rs.getInt("member_no"));
				regVO.setAp_name(rs.getString("ap_name"));
				regVO.setAp_address(rs.getString("ap_address"));
				regVO.setLand_name(rs.getString("land_name"));
				regVO.setStatus(rs.getString("status"));
				regVO.setStart_dt(rs.getDate("start_dt"));
				regVO.setEnd_dt(rs.getDate("end_dt"));
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return regVO;
	}
	
	@Override
	public List<RegVO> getAllByMemNo(Integer member_no) {
		List<RegVO> list = new ArrayList<RegVO>();
		RegVO regVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_MEMNO);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				regVO = new RegVO();
				regVO.setMember_no(rs.getInt("member_no"));
				regVO.setAp_name(rs.getString("ap_name"));
				regVO.setAp_address(rs.getString("ap_address"));
				regVO.setLand_name(rs.getString("land_name"));
				regVO.setStatus(rs.getString("status"));
				regVO.setStart_dt(rs.getDate("start_dt"));
				regVO.setEnd_dt(rs.getDate("End_dt"));
				
				
				list.add(regVO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	@Override
	public List<RegVO> getAllByLandName(String land_name) {
		List<RegVO> list = new ArrayList<RegVO>();
		RegVO regVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_LANDNAME);
			pstmt.setString(1, land_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				regVO = new RegVO();
				regVO.setReg_no(rs.getInt("reg_no"));
				regVO.setMember_no(rs.getInt("member_no"));
				regVO.setAp_name(rs.getString("ap_name"));
				regVO.setAp_address(rs.getString("ap_address"));
				regVO.setLand_name(rs.getString("land_name"));
				regVO.setStatus(rs.getString("status"));
				regVO.setStart_dt(rs.getDate("start_dt"));

		
				list.add(regVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	@Override
	public List<RegVO> getAllByApName(String ap_name) {
		List<RegVO> list = new ArrayList<RegVO>();
		RegVO regVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_APNAME);
			pstmt.setString(1, ap_name);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				regVO = new RegVO();
				regVO.setReg_no(rs.getInt("reg_no"));
				regVO.setMember_no(rs.getInt("member_no"));
				regVO.setAp_name(rs.getString("ap_name"));
				regVO.setAp_address(rs.getString("ap_address"));
				regVO.setLand_name(rs.getString("land_name"));
				regVO.setStatus(rs.getString("status"));
				regVO.setStart_dt(rs.getDate("start_dt"));
				
				
				list.add(regVO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	@Override
	public List<RegVO> getAllByLandNameWithApproval(String land_name) {
		List<RegVO> list = new ArrayList<RegVO>();
		RegVO regVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_LANDNAME);
			pstmt.setString(1, land_name);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				regVO = new RegVO();
				regVO.setReg_no(rs.getInt("reg_no"));
				regVO.setMember_no(rs.getInt("member_no"));
				regVO.setAp_name(rs.getString("ap_name"));
				regVO.setAp_address(rs.getString("ap_address"));
				regVO.setLand_name(rs.getString("land_name"));
				regVO.setStatus(rs.getString("status"));
				regVO.setStart_dt(rs.getDate("start_dt"));
				
				if(regVO.getStatus().equals("1")) {
					list.add(regVO); // Store the row in the list
				}

			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public int updateRes(RegVO regVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int num = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATERES);
			
			pstmt.setString(1, regVO.getAp_name());
			pstmt.setString(2, regVO.getAp_address());
			pstmt.setDate(3, regVO.getStart_dt());
			pstmt.setDate(4, regVO.getEnd_dt());
			pstmt.setString(5, regVO.getStatus());
			pstmt.setInt(6, regVO.getReg_no());
			
			num = pstmt.executeUpdate();

			

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return num ;
	}
}
