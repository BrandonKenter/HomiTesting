package com.bal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bal.model.BalService;
import com.bal.model.BalVO;


/**
 * Servlet implementation class BalServlet
 */
@WebServlet("/BalServlet")
public class BalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
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
				String type = req.getParameter("type");
				Float price = new Float(req.getParameter("price"));
				Integer member_no = new Integer(req.getParameter("member_no"));
				Integer land_no = new Integer(req.getParameter("land_no"));
				
				BalVO balVO = new BalVO();
				balVO.setMember_no(member_no);
				balVO.setType(type);
				balVO.setLand_no(land_no);
				balVO.setPrice(price);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("balVO", balVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/bal/addBal.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.Start to add member information
				 ***************************************/
				BalService balSvc = new BalService();
				balVO = balSvc.addBal(member_no, land_no, type, price);

				/***************************
				 * 3.Added, prepare to forward(Send the Success view)
				 ***********/

				String url = "/front-end/bal/listAllBalForLand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // after adding, forward to
																				// listAllEmp.jsp
				successView.forward(req, res);

				/***************************
				 * other potential errors
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/bal/addBal.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
