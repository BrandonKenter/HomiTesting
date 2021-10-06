package com.mem.controller;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mem.model.MemDAO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.mem.model.SendEmail;

import imageUtil.ImageUtil;

import java.util.*;



@WebServlet("/MemServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemServlet() {

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
				String mb_name = req.getParameter("mb_name");
				String mb_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$"; // Regular Expression
				if (mb_name == null || mb_name.trim().length() == 0) {
					errorMsgs.add("Please enter member name");
				} else if (!mb_name.trim().matches(mb_nameReg)) {
					errorMsgs.add("member name can be only accepted in English and Digital number with length 2 - 10");
				}

				String mb_email = req.getParameter("mb_email");
				String mb_emailReg = "^[(a-zA-Z0-9_)]{2,20}[@][(a-zA-Z0-9)]{3,10}[.][(a-zA-Z)]{1,5}$";
				if (mb_email == null || mb_email.trim().length() == 0) {
					errorMsgs.add("Please enter email");
				} else if (!mb_email.trim().matches(mb_emailReg)) {
					errorMsgs.add("member email can be only accepted in English and Digital number with length 2 - 10");
				}

				String mb_pwd = req.getParameter("mb_pwd");
				String mb_pwdReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (mb_pwd == null || mb_pwd.trim().length() == 0) {
					errorMsgs.add("Please enter password");
				} else if (!mb_pwd.trim().matches(mb_pwdReg)) {
					errorMsgs.add("password can be only accepted in English and Digital number with length 2 - 20");
				}

				String mb_phone = req.getParameter("mb_phone");
				String mb_phoneReg = "^[(0-9)]{2,15}$";
				if (mb_phone == null || mb_phone.trim().length() == 0) {
					errorMsgs.add("Please enter phone");
				} else if (!mb_phone.trim().matches(mb_phoneReg)) {
					errorMsgs.add("phone can be only accepted in Digital number with length 2 - 15");
				}

				String mb_address = req.getParameter("mb_address");
				String mb_addressReg = "^[(a-zA-Z0-9_)(\\s)]{1,1000}$";
				if (mb_address == null || mb_address.trim().length() == 0) {
					errorMsgs.add("Please enter address");
				} else if (!mb_address.trim().matches(mb_addressReg)) {
					errorMsgs.add("address can be only accepted in English and Digital number with length 2 - 30");
				}

				String membership = req.getParameter("membership");
				
				Part part = req.getPart("mb_pic");
				in = part.getInputStream();
				byte[] mb_pic = new byte[in.available()];
				in.read(mb_pic);
				in.close();

				MemVO memVO = new MemVO();
				memVO.setMb_name(mb_name);
				memVO.setMb_email(mb_email);
				memVO.setMb_pwd(mb_pwd);
				memVO.setMb_phone(mb_phone);
				memVO.setMb_address(mb_address);
				memVO.setMembership(membership);


				memVO.setMb_pic(mb_pic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/MemLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.Start to add member information
				 ***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.emailCheck(mb_email);
				memVO = memSvc.addMem(mb_name, mb_email, mb_pwd, mb_pic, mb_phone, mb_address, membership);
				if (memVO != null) {
					SendEmail mailService = new SendEmail();
					mailService.sendMail(mb_email);
//					System.out.println(mb_email);
				}

				/***************************
				 * 3.Added, prepare to forward(Send the Success view)
				 ***********/
				successMsgs.add(
						"Account is registered successfully. Please receive confirm mail to activate your account");
				String url = "/front-end/mem/MemLogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // after adding, forward to
																				// listAllEmp.jsp
				successView.forward(req, res);

				/***************************
				 * other potential errors
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/MemLogin.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String str = req.getParameter("member_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("Please enter member no");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				Integer member_no = null;
				try {
					member_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("The format is incorrect.");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(member_no);
				if (memVO == null) {
					errorMsgs.add("No data");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("memVO", memVO);
				String url = "/back-end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer member_no = new Integer(req.getParameter("member_no"));

				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(member_no);

				req.setAttribute("memVO", memVO);
				String url = "/front-end/mem/memberInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("Cannot retreat the data" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer member_no = new Integer(req.getParameter("member_no").trim());

				String mb_name = req.getParameter("mb_name");
				String mb_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$"; // Regular Expression
				if (mb_name == null || mb_name.trim().length() == 0) {
					errorMsgs.add("Please enter member name");
				} else if (!mb_name.trim().matches(mb_nameReg)) {
					errorMsgs.add("member name can be only accepted in English and Digital number with length 2 - 10");
				}

				String mb_email = req.getParameter("mb_email");
				String mb_emailReg = "^[(a-zA-Z0-9_)]{2,20}[@][(a-zA-Z0-9)]{3,10}[.][(a-zA-Z)]{1,5}$";
				if (mb_email == null || mb_email.trim().length() == 0) {
					errorMsgs.add("Please enter email");
				} else if (!mb_email.trim().matches(mb_emailReg)) {
					errorMsgs.add("member email can be only accepted in English and Digital number with length 2 - 10");
				}

				String mb_pwd = req.getParameter("mb_pwd");
				String mb_pwdReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (mb_pwd == null || mb_pwd.trim().length() == 0) {
					errorMsgs.add("Please enter password");
				} else if (!mb_pwd.trim().matches(mb_pwdReg)) {
					errorMsgs.add("password can be only accepted in English and Digital number with length 2 - 20");
				}

				String mb_phone = req.getParameter("mb_phone");
				String mb_phoneReg = "^[(0-9)]{2,15}$";
				if (mb_phone == null || mb_phone.trim().length() == 0) {
					errorMsgs.add("Please enter phone");
				} else if (!mb_phone.trim().matches(mb_phoneReg)) {
					errorMsgs.add("phone can be only accepted in Digital number with length 2 - 15");
				}

				String mb_address = req.getParameter("mb_address");
				String mb_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (mb_address == null || mb_address.trim().length() == 0) {
					errorMsgs.add("Please enter address");
				} else if (!mb_address.trim().matches(mb_addressReg)) {
					errorMsgs.add("phone can be only accepted in English and Digital number with length 2 - 30");
				}


				String status = req.getParameter("status");

				java.sql.Date crt_dt = null;
				try {
					crt_dt = java.sql.Date.valueOf(req.getParameter("crt_dt").trim());
				} catch (IllegalArgumentException e) {
					crt_dt = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("Please enter date");
				}

				Part part = req.getPart("mb_pic");
				System.out.println(part.getContentType());
				in = part.getInputStream();
				byte[] mb_pic = new byte[in.available()];
				in.read(mb_pic);
				in.close();

				MemVO memVO = new MemVO();
				memVO.setMember_no(member_no);
				memVO.setMb_name(mb_name);
				memVO.setMb_email(mb_email);
				memVO.setMb_pwd(mb_pwd);
				memVO.setMb_pic(mb_pic);
				memVO.setMb_phone(mb_phone);
				memVO.setStatus(status);
				memVO.setCrt_dt(crt_dt);
				memVO.setMb_address(mb_address);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/update_mem.jsp");
					failureView.forward(req, res);
					return;
				}

				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(member_no, mb_name, mb_email, mb_pwd, mb_pic, mb_phone, mb_address, status,
						crt_dt);

				req.setAttribute("memVO", memVO);
				String url = "/back-end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/update_mem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update_member".equals(action)) {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text; charset=utf-8");
			PrintWriter out = res.getWriter();
			try {
				String mb_name = req.getParameter("mb_name").trim();
				String mb_phone = req.getParameter("mb_phone").trim();
				String mb_address = req.getParameter("mb_address").trim();
				int mb_no = new Integer(req.getParameter("mb_no").trim());
				Part part = req.getPart("mb_pic");
				MemService memberSvc = new MemService();
				if (part.getContentType() != null && part.getContentType().indexOf("image") >= 0) {
					in = req.getPart("mb_pic").getInputStream();
					byte[] mb_pic = new byte[in.available()];
					in.read(mb_pic);
					memberSvc.updateMemPic(mb_no, mb_pic);
				}

				memberSvc.frontUpdateMem(mb_no, mb_name, mb_phone, mb_address);
				if (req.getSession().getAttribute("member") != null) {
					MemVO member = memberSvc.getOneMem(mb_no);
					req.getSession().setAttribute("member", member);
				}
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer member_no = new Integer(req.getParameter("member_no"));

				MemService memSvc = new MemService();
				memSvc.deleteMem(member_no);

				String url = "/back-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("view_memPic".equals(action)) {
			res.setContentType("image/gif, image/jpeg, image/png, image/jpg");
			Integer member_no = new Integer(req.getParameter("member_no"));

			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOnePic(member_no);
			byte[] mb_pic = memVO.getMb_pic();
			if (mb_pic != null && mb_pic.length != 0) {
				/* mb_pic = ImageUtil.shrink(mb_pic, 800); */
				res.getOutputStream().write(mb_pic);
			} else {
				in = req.getServletContext().getResourceAsStream("/img/user_icon.png");
				byte[] pic = new byte[in.available()];
				in.read(pic);
				res.getOutputStream().write(pic);
				in.close();
			}
		}

		if ("email_confirm".equals(action)) {
			PrintWriter out = res.getWriter();
			try {
				String email = req.getParameter("email").trim();
				MemService memberSvc = new MemService();
				List<MemVO> members = memberSvc.getAll();
				for (MemVO member : members) {
					if (email.equals(member.getMb_email())) {
						res.setContentType("text; charset=utf-8");
						out.print("used");
						return;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}

		if ("update_picture".equals(action)) {
			PrintWriter out = res.getWriter();
			try {
				int mb_no = new Integer(req.getParameter("mb_no").trim());
				in = req.getPart("mb_pic").getInputStream();
				byte[] mb_pic = new byte[in.available()];
				in.read(mb_pic);
				MemService memberSvc = new MemService();
				memberSvc.updateMemPic(mb_no, mb_pic);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}
		if ("update_password".equals(action)) {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text; charset=utf-8");
			PrintWriter out = res.getWriter();
			int mb_no = new Integer(req.getParameter("mb_no").trim());
			MemService memberSvc = new MemService();
			MemVO memvo = memberSvc.getOneMem(mb_no);
			String pwd = memvo.getMb_pwd();
			String old_mb_pwd = req.getParameter("old_mb_pwd");
			if (!old_mb_pwd.equals(pwd)) {
				out.print("pwd_incorrect");
				return;
			}
			try {
				String mb_pwd = req.getParameter("confirm_new_mb_pwd").trim();
				mb_no = new Integer(req.getParameter("mb_no").trim());
				memberSvc = new MemService();
				memberSvc.updatePwd(mb_no, mb_pwd);
				MemVO newmember = memberSvc.getOneMem(mb_no);
				req.getSession().setAttribute("member", newmember);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
			return;
		}

		if ("login_check".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String mb_email = req.getParameter("mb_email");
				String mb_emailReg = "^[(a-zA-Z0-9_)]{2,20}[@][(a-zA-Z0-9)]{3,10}[.][(a-zA-Z)]{1,5}$";
				if (mb_email == null || mb_email.trim().length() == 0) {
					errorMsgs.add("Pleae enter email");
				} else if (!mb_email.trim().matches(mb_emailReg)) {
					errorMsgs.add("email can be only accepted in English and Digital number with length 2 - 10");
				}

				String mb_pwd = req.getParameter("mb_pwd");
				String mb_pwdReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (mb_pwd == null || mb_pwd.trim().length() == 0) {
					errorMsgs.add("Please enter password");
				} else if (!mb_pwd.trim().matches(mb_pwdReg)) {
					errorMsgs.add("password can be only accepted in English and Digital number with length 2 - 20");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				MemService memSvc = new MemService();
				MemVO memVO = memSvc.loginCheck(mb_email, mb_pwd);
				if (memVO == null) {
					errorMsgs.add("Account or password is wrong.");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/MemLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				String checkstatus = memVO.getStatus();
				if (checkstatus.contentEquals("0")) {
					errorMsgs.add("Account is not activated.");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/MemLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				HttpSession session = req.getSession();
				session.setAttribute("memVO", memVO);
				String url = "/front-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("Cannot retreat data:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/MemLogin.jsp");
				failureView.forward(req, res);
			}
		}

		/*
		 * if ("listRelationships_ByMemberno_A".equals(action) ||
		 * "listRelationships_ByMemberno_B".equals(action)) {
		 * 
		 * List<String> errorMsgs = new LinkedList<String>();
		 * req.setAttribute("errorMsgs", errorMsgs);
		 * 
		 * try {
		 *//***************************
			 * 1.接收請求參數
			 ****************************************/
		/*
		 * Integer member_no = new Integer(req.getParameter("member_no"));
		 * 
		 *//***************************
			 * 2.開始查詢資料
			 ****************************************/
		/*
		 * MemService memSvc = new MemService(); Set<RelationshipVO> set =
		 * memSvc.getRelationshipsByMemberno(member_no);
		 * 
		 *//***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 ************/
		/*
		 * req.setAttribute("listRelationships_ByMemno", set); //
		 * 資料庫取出的list物件,存入request
		 * 
		 * String url = null; if ("listRelationships_ByMemberno_A".equals(action)) url =
		 * "/front-end/relationship/select_page.jsp"; // 成功轉交
		 * dept/listEmps_ByDeptno.jsp else if
		 * ("listRelationships_ByMemberno_B".equals(action)) url =
		 * "/front-end/mem/listRelationships_ByMemno.jsp"; // 成功轉交
		 * dept/listAllDept.jsp
		 * 
		 * RequestDispatcher successView = req.getRequestDispatcher(url);
		 * successView.forward(req, res);
		 * 
		 *//***************************
			 * 其他可能的錯誤處理
			 ***********************************//*
													 * } catch (Exception e) { throw new ServletException(e); } }
													 */

		if ("forgot_password".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);

			try {
				String mb_email = req.getParameter("mb_email");
				String mb_emailReg = "^[(a-zA-Z0-9_)]{2,20}[@][(a-zA-Z0-9)]{3,10}[.][(a-zA-Z)]{1,5}$";
				if (mb_email == null || mb_email.trim().length() == 0) {
					errorMsgs.add("Please enter email");
				} else if (!mb_email.trim().matches(mb_emailReg)) {
					errorMsgs.add("");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/MemLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getPassword(mb_email);
				if (memVO == null) {
					errorMsgs.add("Email is incorrect.");
				} else {
					SendEmail mailService = new SendEmail();
					String randomPwd = mailService.sendPassword(mb_email);
					memSvc.updateRandomPws(mb_email, randomPwd);
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/MemLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				successMsgs.add("New password has sent to your email. Please receive it and log in with new password.");
				String url = "/front-end/mem/MemLogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/MemLogin.jsp");
				failureView.forward(req, res);
			}
		}

		/*
		 * if ("listMems_ByCompositeQuery".equals(action)) { List<String> errorMsgs =
		 * new LinkedList<String>(); // Store this set in the request scope, in case we
		 * need to // send the ErrorPage view. req.setAttribute("errorMsgs", errorMsgs);
		 * 
		 * try {
		 * 
		 *//***************************
			 * 1.將輸入資料轉為Map
			 **********************************/
		/*
		 * // 採用Map<String,String[]> getParameterMap()的方法 // 注意:an immutable
		 * java.util.Map // Map<String, String[]> map = req.getParameterMap();
		 * HttpSession session = req.getSession(); Map<String, String[]> map =
		 * (Map<String, String[]>) session.getAttribute("map"); // 以下的 if
		 * 區塊只對第一次執行時有效 if (req.getParameter("whichPage") == null) {
		 * Map<String, String[]> map1 = new HashMap<String,
		 * String[]>(req.getParameterMap()); session.setAttribute("map", map1); map =
		 * map1; }
		 *//***************************
			 * 2.開始複合查詢
			 ***************************************/
		/*
		 * MemService memSvc = new MemService(); List<MemVO> list = memSvc.getAll(map);
		 *//***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 ************/
		/*
		 * req.setAttribute("listMems_ByCompositeQuery", list); //
		 * 資料庫取出的list物件,存入request RequestDispatcher successView = req
		 * .getRequestDispatcher("/front-end/mem/listMems_ByCompositeQuery.jsp"); //
		 * 成功轉交listMems_ByCompositeQuery.jsp successView.forward(req, res);
		 * 
		 *//***************************
			 * 其他可能的錯誤處理
			 **********************************//*
												 * } catch (Exception e) { errorMsgs.add(e.getMessage());
												 * RequestDispatcher failureView =
												 * req.getRequestDispatcher("/front-end/mem/addMem.jsp");
												 * failureView.forward(req, res); } }
												 */

		if ("update_for_Ajax".contentEquals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			PrintWriter out = res.getWriter();

			try {
				String mb_status = req.getParameter("mb_status");

				int member_no = new Integer(req.getParameter("member_no"));

				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(member_no);
				MemService memSvc1 = new MemService();
				memSvc1.updateMem(member_no, memVO.getMb_name(), memVO.getMb_email(), memVO.getMb_pwd(),
						memVO.getMb_pic(), memVO.getMb_phone(), 
						memVO.getMb_address(), mb_status, memVO.getCrt_dt());

				out.print("success");

			} catch (Exception e) {
				out.print("fail");
				errorMsgs.add("");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			} finally {
				out.flush();
				out.close();
			}

		}
	}
}