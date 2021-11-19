package com.comp.model;

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

import com.mem.model.MemVO;



public class CompDAO implements CompDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/homii?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO complaint (member_no, ap_name, ap_address, land_name, case_title, description, pubtype, comp_pic, comp_vid, priority) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM complaint where land_name = ?";
	private static final String GET_ALL_STMT_BY_MEMNO = "SELECT * FROM complaint where member_no = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM complaint where complaint_no = ?";
	private static final String GET_ONE_PIC = "SELECT comp_pic FROM complaint WHERE complaint_no=?";
	private static final String UPDATERES = "UPDATE complaint set status=?, response=? where complaint_no = ?";
	
	public int insert(CompVO compVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int num = 0;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.createStatement();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, compVO.getMember_no());
			pstmt.setString(2, compVO.getAp_name());
			pstmt.setString(3, compVO.getAp_address());
			pstmt.setString(4, compVO.getLand_name());
			pstmt.setString(5, compVO.getCase_title());
			pstmt.setString(6, compVO.getDescription());
			pstmt.setString(7, compVO.getPubtype());
			pstmt.setBytes(8, compVO.getComp_pic());
			pstmt.setBytes(9, compVO.getComp_vid());
			pstmt.setString(10, compVO.getPriority());

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
	public List<CompVO> getAllByLandName(String land_name) {
		List<CompVO> list = new ArrayList<CompVO>();
		CompVO compVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, land_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				compVO = new CompVO();
				compVO.setComplaint_no(rs.getInt("complaint_no"));
				compVO.setMember_no(rs.getInt("member_no"));
				compVO.setAp_name(rs.getString("ap_name"));
				compVO.setAp_address(rs.getString("ap_address"));
				compVO.setLand_name(rs.getString("land_name"));
				compVO.setCase_title(rs.getString("case_title"));
				compVO.setDescription(rs.getString("description"));
				compVO.setPubtype(rs.getString("pubtype"));
				compVO.setComp_pic(rs.getBytes("comp_pic"));
				compVO.setComp_vid(rs.getBytes("comp_vid"));
				compVO.setCrt_dt(rs.getDate("crt_dt"));
				compVO.setStatus(rs.getString("status"));
				compVO.setResponse(rs.getString("response"));
				compVO.setPriority(rs.getString("priority"));
		
				list.add(compVO); // Store the row in the list
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
	public List<CompVO> getAllByMemNo(Integer member_no) {
		List<CompVO> list = new ArrayList<CompVO>();
		CompVO compVO = null;
		
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
				compVO = new CompVO();
				compVO.setComplaint_no(rs.getInt("complaint_no"));
				compVO.setMember_no(rs.getInt("member_no"));
				compVO.setAp_name(rs.getString("ap_name"));
				compVO.setAp_address(rs.getString("ap_address"));
				compVO.setLand_name(rs.getString("land_name"));
				compVO.setCase_title(rs.getString("case_title"));
				compVO.setDescription(rs.getString("description"));
				compVO.setPubtype(rs.getString("pubtype"));
				compVO.setComp_pic(rs.getBytes("comp_pic"));
				compVO.setComp_vid(rs.getBytes("comp_vid"));
				compVO.setCrt_dt(rs.getDate("crt_dt"));
				compVO.setStatus(rs.getString("status"));
				compVO.setResponse(rs.getString("response"));
				compVO.setPriority(rs.getString("priority"));
				
				list.add(compVO); // Store the row in the list
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
	public CompVO findByPrimaryKey(Integer complaint_no) {
		
		CompVO compVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, complaint_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				compVO = new CompVO();
				compVO.setComplaint_no(rs.getInt("complaint_no"));
				compVO.setMember_no(rs.getInt("member_no"));
				compVO.setAp_name(rs.getString("ap_name"));
				compVO.setAp_address(rs.getString("ap_address"));
				compVO.setLand_name(rs.getString("land_name"));
				compVO.setCase_title(rs.getString("case_title"));
				compVO.setDescription(rs.getString("description"));
				compVO.setPubtype(rs.getString("pubtype"));
				compVO.setComp_pic(rs.getBytes("comp_pic"));
				compVO.setComp_vid(rs.getBytes("comp_vid"));
				compVO.setCrt_dt(rs.getDate("crt_dt"));
				compVO.setStatus(rs.getString("status"));
				compVO.setResponse(rs.getString("response"));
				compVO.setPriority(rs.getString("priority"));
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
		return compVO;
	}
	
	public CompVO getOnePic(Integer complaint_no) {
		CompVO compVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_PIC);

			pstmt.setInt(1, complaint_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				compVO = new CompVO();
				compVO.setComp_pic(rs.getBytes("comp_pic"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return compVO;
	}
	public int updateRes(CompVO compVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int num = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATERES);
			
			pstmt.setString(1, compVO.getStatus());
			pstmt.setString(2, compVO.getResponse());
			pstmt.setInt(3, compVO.getComplaint_no());



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
}
