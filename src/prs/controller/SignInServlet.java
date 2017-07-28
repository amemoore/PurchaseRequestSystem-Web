package prs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prs.business.User;
import prs.db.DAOFactory;
import prs.user.db.UserDAO;

@WebServlet({"/SignInServlet", "/SigningIn"})
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Back in the servlet - in the do Post");
			UserDAO usersDAO = DAOFactory.getUserDAO();
			User realUser;
			String username = request.getParameter("username");
			String message = "";
			String url = "/index.html";
			realUser = usersDAO.getUser(username);
			
			
			if (username.isEmpty()){
				message = "Please enter a username in the box." ;
				url="/signIn.jsp";
			}
			
			else if (!username.isEmpty()){
			
				if (username.equalsIgnoreCase(realUser.getUserName())){
					message = "Welcome " + username;
					url="/createRequest.jsp";
				}
			
				else if (!username.equalsIgnoreCase(realUser.getUserName())){
					message = "Username is not active.  Please register." ;
					url="/register.jsp";
				}
			}
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}
