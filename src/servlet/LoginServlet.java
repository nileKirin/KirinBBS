package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.GetAccountLogic;
import model.Account;
import model.Login;
import tools.ParameterChecker;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	String forwardPath;
	String errorMsg;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		errorMsg = "";
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		forwardPath = account != null ? "MainServlet":"/WEB-INF/jsp/login.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		errorMsg = "";
		Account account = null;
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		Login login = new Login(userId, pass);

		errorMsg = ParameterChecker.isNull(userId,pass) ? "データを正しく入力して下さい" : "";
		if(errorMsg.length() == 0 || errorMsg.isEmpty()) {
			GetAccountLogic bo = new GetAccountLogic();
			account = bo.excecute(login);
			if(account != null) {
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
			}else{
				errorMsg ="ログインに失敗しました、ログイン情報を確認してください";
			}
		}


		//リクエストスコープは、login.jspに返した時のinputのValue用
		request.setAttribute("login", login);

		request.setAttribute("errorMsg", errorMsg);
		forwardPath = account != null ? "/WEB-INF/jsp/loginResult.jsp" : "/WEB-INF/jsp/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);

	}
}
