package prs.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import prs.business.LineItem;
import prs.business.Product;
import prs.business.Request;
import prs.business.User;
import prs.business.Vendor;
import prs.db.DAOFactory;
import prs.lineitem.db.LineItemDAO;
import prs.product.db.ProductDAO;
import prs.product.db.ProductDB;
import prs.request.db.RequestDAO;
import prs.user.db.UserDAO;
import prs.vendor.db.VendorDAO;
import prs.vendor.db.VendorDB;

@WebServlet({"/CreateRequest"})
public class CreateRequest extends HttpServlet {
	
	private static ProductDAO productsDAO = null;
	private static VendorDAO vendorsDAO = null;
	private static RequestDAO requestsDAO = null;
	
	private static final long serialVersionUID = 1L;
       
    public CreateRequest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
		session = request.getSession();
		String message = "";
		String url = "/index.html";
		String action = request.getParameter("action");
		System.out.println(action);
		
		if (action==null)
			url="/index.html";	
		
		else if (action.equalsIgnoreCase("CreateRequest")) {
			String description = request.getParameter("description");
			String justification = request.getParameter("justification");
			String dateNeeded = request.getParameter("dateNeeded");
			String username = request.getParameter("username");
			String deliveryMode = request.getParameter("deliveryMode");
			String isDocAttached = request.getParameter("isDocAttached");
				boolean docAttached = false;
					if(isDocAttached!=null)
						docAttached=true;
			String status = request.getParameter("status");
			String total = request.getParameter("total");
			String submittedDate = request.getParameter("submittedDate");
				
				//If any form fiels are empty, returns to createRequest.jsp.
				if (description.isEmpty()||description==null||justification.isEmpty()||justification==null
						||dateNeeded.isEmpty()||username.isEmpty()||username==null||deliveryMode.isEmpty()
						||deliveryMode==null||status.isEmpty()||status==null
						||total==null||total.isEmpty()||submittedDate.isEmpty()){
					message = "Please fill out all text boxes.";
					url="/createRequest.jsp";
				}
				//Creating Request and sending to the vendors.jsp page
				else {
					User u;
					UserDAO usersDAO = DAOFactory.getUserDAO();
					u = usersDAO.getUser(username);
					int userID = u.getuID();
					//Making java.sql.date dates and parsing double from string
					java.sql.Date javaSqlDateNeeded = java.sql.Date.valueOf(dateNeeded);
					Double dtotal = Double.parseDouble(total);
					java.sql.Date javaSqlDateSubmitted = java.sql.Date.valueOf(submittedDate);
					//create request object and set it to a session
					Request r = new Request(description, justification, javaSqlDateNeeded , userID, 
							deliveryMode, docAttached, status, dtotal, javaSqlDateSubmitted);
					session.setAttribute("request", r);
					
					vendorsDAO = DAOFactory.getVendorDAO();
					ArrayList<prs.business.Vendor> allVendors = vendorsDAO.getAllVendors();
					System.out.println(allVendors.size() + "Printing from servlet");
					request.setAttribute("vendors", allVendors);
					url="/vendor.jsp";
				}
		}
		//Setting Vendor session and getting products for the specific Vendor		
		else if (action.equalsIgnoreCase("SelectVendor")) {   
			String vendorID = request.getParameter("vId");
			int vendorIDInt = Integer.parseInt(vendorID);
			Vendor v = vendorsDAO.getVendorByVendorId(vendorIDInt);
			session.setAttribute("vendor", v);
			
			productsDAO = DAOFactory.getProductDAO();
			ArrayList<prs.business.Product> products = productsDAO.getProductsForVendor(vendorIDInt);
			request.setAttribute("products", products);
			url = "/product.jsp";
		}
		//Selecting product, performing transaction, sending to confirmation page
		else if (action.equalsIgnoreCase("SelectProduct")){
			String productID = request.getParameter("pId");
			int productIDInt = Integer.parseInt(productID);
			Product p = productsDAO.getProductByProductNo(productIDInt);
			
			Request r = (Request) session.getAttribute("request");
			request.setAttribute("request", r);
			
			Vendor v = (Vendor) session.getAttribute("vendor");
			requestsDAO = DAOFactory.getRequestDAO();
				requestsDAO.addRequest(r);
				int rId = requestsDAO.getRequestId();
				
				System.out.println(rId);
				
				LineItem li = new LineItem(rId, productIDInt, 1);
				
				if (r.getTotal() < 50.0){
					requestsDAO.approveRequestUnderFifty(rId);
					message = "Purchase Request No." + rId + " for " + p.getName() + " was created." +  "\r" 
							+  "\r" + "Your purchase request has been approved. ";
					}
				else if (r.getTotal() > 49.99){
					message = "Request No." + rId + " for " + p.getName() + " was created." + "\n" 
							+ "\n" + "Please wait for manager approval. ";
				}
			url = "/completion.jsp";
		}
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}			
}
