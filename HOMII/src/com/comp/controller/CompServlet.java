package com.comp.controller;

import java.io.IOException;
import java.io.InputStream;
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
import com.mem.model.MemService;
import com.mem.model.MemVO;


@WebServlet("/CompServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CompServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public CompServlet() {

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
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
				String apNameReg = "^[(a-zA-Z0-9_)(\\s\\S)]{2,20}$"; // Regular Expression
				if (apName == null || apName.trim().length() == 0) {
					errorMsgs.add("Please enter member name");
				} else if (!apName.trim().matches(apNameReg)) {
					errorMsgs.add("member name can be only accepted in English and Digital number with length 2 - 20");
				}

				String landName = req.getParameter("landName");
				String landNameReg = "^[(a-zA-Z0-9_)(\\s\\S)]{2,20}$"; // Regular Expression
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

				String caseTitle = req.getParameter("caseTitle");
				if (caseTitle == null || caseTitle.trim().length() == 0) {
					errorMsgs.add("Please enter address");
				} 

				String description = req.getParameter("description");
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("Please enter address");
				} 

				String pubType = req.getParameter("pubType");
				
				Part part1 = req.getPart("photo");
				in = part1.getInputStream();
				byte[] photo = new byte[in.available()];
				in.read(photo);
				in.close();
				Part part2 = req.getPart("video");
				in = part2.getInputStream();
				byte[] video = new byte[in.available()];
				in.read(video);
				in.close();
				
				CompVO compVO = new CompVO();
				compVO.setMember_no(mb_no);
				compVO.setAp_name(apName);
				compVO.setAp_address(apAddress);
				compVO.setLand_name(landName);
				compVO.setCase_title(caseTitle);
				compVO.setDescription(description);
				compVO.setPubtype(pubType);
				compVO.setComp_pic(photo);
				compVO.setComp_vid(video);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("compVO", compVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/comp/addComp.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.Start to add member information
				 ***************************************/
				CompService compSvc = new CompService();
				
				compVO = compSvc.addComp(mb_no, apName, apAddress, landName, caseTitle, description, pubType, photo, video);

				String url = "/front-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 

				successView.forward(req, res);

				/***************************
				 * other potential errors
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/comp/addComp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("view_comPic".equals(action)) {
			res.setContentType("image/gif, image/jpeg, image/png, image/jpg");
			Integer complaint_no = new Integer(req.getParameter("complaint_no"));

			CompService compSvc = new CompService();
			CompVO compVO = compSvc.getOnePic(complaint_no);
			byte[] comp_pic = compVO.getComp_pic();
			if (comp_pic != null && comp_pic.length != 0) {
				res.getOutputStream().write(comp_pic);
			} else {
				in = req.getServletContext().getResourceAsStream("/img/no image.png");
				byte[] pic = new byte[in.available()];
				in.read(pic);
				res.getOutputStream().write(pic);
				in.close();
			}
		}
		
		if ("response".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);
			try {
				res.setContentType("image/gif, image/jpeg, image/png, image/jpg");
				Integer complaint_no = new Integer(req.getParameter("complaint_no").trim());
				String restype = req.getParameter("restype");
				String response = req.getParameter("response");
	
				
				CompVO compVO = new CompVO();
				compVO.setComplaint_no(complaint_no);
				compVO.setStatus(restype);
				compVO.setResponse(response);
			
	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("compVO", compVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/comp/listOneComp.jsp");
					failureView.forward(req, res);
					return;
				}
				CompService compSvc = new CompService();
				compSvc.updateResponse(complaint_no, restype, response);
				String url = "/front-end/comp/listAllCompForLand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
	
				successView.forward(req, res);
				

			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/comp/listOneComp.jsp");
				failureView.forward(req, res);
			}
		
		}
	}
}
