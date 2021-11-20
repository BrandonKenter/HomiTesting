package com.payment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bal.model.BalService;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.payment.model.PaymentService;
import com.payment.model.PaymentVO;


@WebServlet("/PaymentServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PaymentServlet() {
        super();

    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text; charset=ISO-8859-1");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action").trim();

		if ("insert_credit_card".equals(action)) { 

			try {
				int mb_no = new Integer(req.getParameter("mb_no"));
				String card_no = req.getParameter("card_no").trim();
				String card_name = req.getParameter("card_name").trim();
				String exp_mon = req.getParameter("exp_mon").trim();
				String exp_year = req.getParameter("exp_year").trim();
				String csc = req.getParameter("csc").trim();
				PaymentService paySvc = new PaymentService();
				PaymentVO payvo = paySvc.addCard(mb_no, card_no, card_name, exp_mon, exp_year, csc);
				JSONObject json = new JSONObject();
				json.put("status", "success");
				json.put("payno", payvo.getPay_no());
				out.print(json.toString());
				return;
			} catch (Exception e) {
				e.printStackTrace(System.err);
				out.print("fail");
			}
			finally {
				out.flush();
				out.close();
			}
		}

		if ("delete_credit_card".equals(action)) {
			try {
				int pay_no = new Integer(req.getParameter("pay_no"));
				PaymentService paySvc = new PaymentService();
				boolean result = paySvc.deleteCard(pay_no);
				if(result) {
					out.print("success");
				} else {
					out.print("failure");
				}
				return;
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			} finally {
				out.flush();
				out.close();
			}
		}
		
		if ("getall_crdt_by_mbid".equals(action)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/back-end/payment/paymentInfo.jsp");
			try {
				int mb_id = new Integer(req.getParameter("mb_id"));
				PaymentService paySvc = new PaymentService();
				req.setAttribute("creditCars", paySvc.getAllCard(mb_id));
				req.setAttribute("msg", "remove successfully");
				dispatcher.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("msg", "remove fail");
				dispatcher.forward(req, res);
			}
			  finally {
				out.flush();
				out.close();
			}
		}
		
		if ("payFromTenant".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);
			try {
				int mb_no = new Integer(req.getParameter("member_no"));
				
				if (req.getParameter("price") == null || req.getParameter("price").length()==0 ) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/bal/displayBalForTenant.jsp");
					failureView.forward(req, res);
					return;
				}
				Float price = new Float(req.getParameter("price").trim());
				if  (price <= 0) {
					errorMsgs.add("error");
				}
				
				if (req.getParameter("pay_no") == null || req.getParameter("pay_no").length()==0) {
					System.out.println("11");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/bal/displayBalForTenant.jsp");
					failureView.forward(req, res);
					return;
				}
				String type = req.getParameter("type").trim();
				if (type == null || type.trim().length() == 0) {
					errorMsgs.add("error");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/bal/displayBalForTenant.jsp");
					failureView.forward(req, res);
					return;
				}
				MemService memSvc = new MemService();
				float curBal = memSvc.getOneMem(mb_no).getBalance();
				memSvc.updateBalance(mb_no, curBal - price);
				MemVO memVO = memSvc.getOneMem(mb_no);
				
				BalService balSvc = new BalService();
				balSvc.addBal(mb_no, -1, type, price);
				
				req.setAttribute("memVO", memVO);
				String url = "/front-end/bal/displayBalForTenant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // after adding, forward to
																				// listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		if ("addPrice".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);
			try {
				int land_no = new Integer(req.getParameter("member_no"));
				
				String str = req.getParameter("price");

				if (str == null || str.length()==0 || !PaymentServlet.isNum(str)) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/IIPay/Pay.jsp");
					failureView.forward(req, res);
					return;
				}
				Float price = new Float(req.getParameter("price").trim());
				if  (price <= 0) {
					errorMsgs.add("error");
				}
				
				if (req.getParameter("llRadio") == null || req.getParameter("llRadio").length()==0) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/IIPay/Pay.jsp");
					failureView.forward(req, res);
					return;
				}
				String llRadio = req.getParameter("llRadio").trim();
				if (llRadio == null || llRadio.trim().length() == 0) {
					errorMsgs.add("error");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/IIPay/Pay.jsp");
					failureView.forward(req, res);
					return;
				}
				Integer tenant_no = new Integer(req.getParameter("tenant_name").trim());
				MemService memSvc = new MemService();
				float curBal = memSvc.getOneMem(tenant_no).getBalance();
				
				if("0".equals(llRadio)) {
					memSvc.updateBalance(tenant_no, curBal + price);					
				}
				if("1".equals(llRadio)) {
					memSvc.updateBalance(tenant_no, curBal - price);					
				}

				
				BalService balSvc = new BalService();
				balSvc.addBal(tenant_no, land_no, llRadio, price);

				String url = "/front-end/IIPay/Pay.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // after adding, forward to
				// listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	
	}
	public static boolean isNum (String str){
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }

}
