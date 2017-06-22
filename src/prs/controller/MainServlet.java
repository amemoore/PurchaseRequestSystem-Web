package prs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prs.business.User;
import prs.db.DAOFactory;
import prs.user.db.UserDAO;

/**
 * Servlet implementation class signin
 */
@WebServlet({"/MainServlet", "/pickSignIn", "/pickRegister"})
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/index.html";	
		String gettingSignIn = request.getParameter("signin");
		
			
			if (gettingSignIn==null){
				url="/register.jsp";
			}
			else if  (gettingSignIn.equals("signingin")){
				url="/signIn.jsp";
			}
			
		getServletContext().getRequestDispatcher(url).forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
