package com.apt.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;


public class DBGifReader4 extends HttpServlet {

	Connection con;

	public static final String SQL = "SELECT AP_PIC1 FROM `APARTMENT` WHERE AP_NO = ?";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif,image/png,image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		try {

			PreparedStatement pstmt = con.prepareStatement(SQL);


			Integer ap_no = new Integer(req.getParameter("ap_no"));

			pstmt.setInt(1, ap_no);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("AP_PIC1"));

				if (in.available() != 0) {
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					InputStream input = getServletContext().getResourceAsStream("/img/no_apt.jpg");
					byte[] b = new byte[input.available()];
					input.read(b);
					out.write(b);
					in.close();
				}
			} else {

//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/img/no_apt.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/img/no_apt.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOMII");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}