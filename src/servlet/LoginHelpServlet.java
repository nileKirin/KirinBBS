package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.getAccountFromEmailLogic;
import model.Account;
import tools.EmailSend;
import tools.ParameterChecker;

@WebServlet("/LoginHelpServlet")
public class LoginHelpServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginHelp.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String forwardPath;
		String errorMsg = "";

		String email = request.getParameter("email");
		ParameterChecker pChecker = new ParameterChecker();
		boolean isMail = pChecker.isMail(email);

		//Email送信処理
		if(!(email.length()==0 || email.isEmpty()) && isMail){
			getAccountFromEmailLogic getAccountFromEmailLogic = new getAccountFromEmailLogic();
			Account account = getAccountFromEmailLogic.excecute(email);
			if(account.getEmail().equals(email)){
				EmailSend eSend = new EmailSend();
				eSend.loginHelp(account);
				request.setAttribute("account", account);
			}else {
				errorMsg = "該当するメールアドレスは登録されていません";
			}
		}else if(email.length()==0 || email.isEmpty()){
			errorMsg = "文字を入力して下さい";
		}else {
			errorMsg = "メールアドレスを正しく入力されていないかもしれません";
		}

		request.setAttribute("errorMsg", errorMsg);
		forwardPath =  errorMsg.length()==0 || errorMsg.isEmpty() ? "/WEB-INF/jsp/loginHelpResult.jsp":"/WEB-INF/jsp/loginHelp.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
