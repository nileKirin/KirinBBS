package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.CreateAccountLogic;
import model.Account;

@WebServlet("/CreateAccountResulServlet")
public class CreateAccountResulServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		//セッションからデータ獲得、SQL(INSERT)実行
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("newAccount");
		CreateAccountLogic createAccountLogic = new CreateAccountLogic();
		createAccountLogic.excecute(account);

		//セッション破棄
		session.removeAttribute("newAccount");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createAccountSuccess.jsp");
		dispatcher.forward(request, response);

	}

}
