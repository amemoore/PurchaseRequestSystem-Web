package prs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import prs.business.User;
import prs.db.DAOFactory;
import prs.user.db.UserDAO;

@WebServlet({"/SignInRegister", "/Registering"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		String url = "/index.html";	
		UserDAO usersDAO = DAOFactory.getUserDAO();
		User u;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String isManager = request.getParameter("isManager");
		boolean manager=false;	
			if(isManager!=null)
				manager=true;
		User realUser = usersDAO.getUser(username);	
		
		if (username.equalsIgnoreCase(realUser.getUserName())){
			message =  "Welcome " + username;
			url="/createRequest.jsp";
			}
		else if (username.isEmpty()||username==null||password.isEmpty()||password==null||firstName.isEmpty()||firstName==null
				||lastName.isEmpty()||lastName==null||phone.isEmpty()||phone==null||email.isEmpty()||email==null){
			message = "Please fill out all text boxes.";
			url="/register.jsp";
		}
		else {
			u = new User(username, password, firstName, lastName, phone, email, manager);
			usersDAO.addUser(u);
			message =  "Welcome " + username;
			url="/createRequest.jsp";
		}	
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}

