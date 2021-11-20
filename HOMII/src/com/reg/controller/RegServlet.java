package com.reg.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.comp.model.CompService;
import com.comp.model.CompVO;
import com.reg.model.RegService;
import com.reg.model.RegVO;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=ISO-8859-1");
		InputStream in = null;
		
		if ("insert".equals(action)) { // request from addEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);

			try {
				/***********************
				 * 1.catch requested parameter, do error handling
				 *************************/
				Integer mb_no;

				String memNo = req.getParameter("memNo");
				mb_no = new Integer(memNo.trim());

				String apName = req.getParameter("apName");
				String apNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\s\\S)]{2,20}$"; // Regular Expression
				if (apName == null || apName.trim().length() == 0) {
					errorMsgs.add("Please enter member name");
				} else if (!apName.trim().matches(apNameReg)) {
					errorMsgs.add("member name can be only accepted in English and Digital number with length 2 - 20");
				}

				String landName = req.getParameter("landName");
				String landNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\s\\S)]{2,20}$"; // Regular Expression
				if (landName == null || landName.trim().length() == 0) {
					errorMsgs.add("Please enter member name");
				} else if (!landName.trim().matches(landNameReg)) {
					errorMsgs.add("member name can be only accepted in English and Digital number with length 2 - 20");
				}


				String apAddress = req.getParameter("apAddress");
				String apAddressReg = "^[(a-zA-Z0-9_)(\\s\\S)]{1,100}$";
				if (apAddress == null || apAddress.trim().length() == 0) {
					errorMsgs.add("Please enter address");
				} else if (!apAddress.trim().matches(apAddressReg)) {
					errorMsgs.add("address can be only accepted in English and Digital number with length 1 - 100");
				}

				Date start_dt;
				try{
					start_dt = java.sql.Date.valueOf(req.getParameter("start_dt").trim());
				}catch (IllegalArgumentException e) {
					start_dt= new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("Date format is incorrect!");
				}
				Date end_dt;
				try{
					end_dt = java.sql.Date.valueOf(req.getParameter("end_dt").trim());
				}catch (IllegalArgumentException e) {
					end_dt= new java.sql.Date(System.currentTimeMillis());
				}
				RegVO regVO = new RegVO();
				regVO.setMember_no(mb_no);
				regVO.setAp_name(apName);
				regVO.setAp_address(apAddress);
				regVO.setLand_name(landName);
				regVO.setStart_dt(start_dt);
				regVO.setEnd_dt(end_dt);
	

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("regVO", regVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/reg/addReg.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************
				 * 2.Start to add member information
				 ***************************************/
				RegService regSvc = new RegService();
				
				regVO = regSvc.addReg(mb_no, apName, apAddress, landName, start_dt, end_dt);

				String url = "/front-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 

				successView.forward(req, res);

				/***************************
				 * other potential errors
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/reg/addReg.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("feedback".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);
			try {
				res.setContentType("image/gif, image/jpeg, image/png, image/jpg");
				Integer reg_no = new Integer(req.getParameter("regNo"));
				System.out.println(reg_no);
				String ap_name = req.getParameter("apName");
				String ap_address = req.getParameter("apAddress");
				Date start_dt;
				try{
					start_dt = java.sql.Date.valueOf(req.getParameter("start_dt").trim());
				}catch (IllegalArgumentException e) {
					start_dt= new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("Date format is incorrect!");
				}
				Date end_dt;
				try{
					end_dt = java.sql.Date.valueOf(req.getParameter("end_dt").trim());
				}catch (IllegalArgumentException e) {
					end_dt= new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("Date format is incorrect!");
				}
				String restype = req.getParameter("restype");
	
				
				RegVO regVO = new RegVO();
				regVO.setReg_no(reg_no);
				regVO.setAp_name(ap_name);
				regVO.setAp_address(ap_address);
				regVO.setStart_dt(start_dt);
				regVO.setEnd_dt(end_dt);
				regVO.setStatus(restype);
				System.out.println("222");
	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("regVO", regVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/reg/displayOneRegForLand.jsp");
					failureView.forward(req, res);
					return;
				}
				RegService regSvc = new RegService();
				regSvc.updateResponse(reg_no, ap_name, ap_address, start_dt, end_dt, restype);
				String url = "/front-end/comp/listAllCompForLand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
	
				successView.forward(req, res);
				

			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/reg/displayOneRegForLand.jsp");
				failureView.forward(req, res);
			}
		
		}
	}

}
