package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.CheckUserExistsLogic;
import model.Account;
import tools.ParameterChecker;
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet{
	String forwardPath;
	String errorMsg;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		forwardPath = "/WEB-INF/jsp/createAccount.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		forwardPath ="/WEB-INF/jsp/createAccount.jsp";
		errorMsg ="";
		int age = 0;

		// リクエストパラメータの取得とチェック
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String rePass = request.getParameter("repass");
		String ageStr =  request.getParameter("age");
		String email = request.getParameter("email");
		String remail = request.getParameter("remail");
		ParameterChecker pChecker = new ParameterChecker();
		errorMsg  = pChecker.createAccountFormatCheck(userId, pass, rePass, ageStr, email, remail);

        //// アカウントの存在チェック
		Account ceeckAccount = new Account(userId,pass,email);
		CheckUserExistsLogic checkUserExistsLogic = new CheckUserExistsLogic();
		errorMsg += checkUserExistsLogic.excecute(ceeckAccount);


        // エラーメッセージがなければ → セッションデータ保存 フォワードパスを変更
	    try {
	        if (errorMsg.isEmpty()) {
	            age = Integer.parseInt(ageStr);

	            HttpSession session = request.getSession();
	            Account newAccount = new Account(userId, pass, age, email);

	            session.setAttribute("newAccount", newAccount);
	            forwardPath = "/WEB-INF/jsp/createAccountResult.jsp";
	        }
	    } catch (NumberFormatException e) {
	        // 数値変換の例外チェック
	        errorMsg = "年齢が無効な値です!";
	    }
		//JSPのフォームにデータを渡す為のインスタンス
		Account accountReqest = new Account(userId, pass, age, email);
		request.setAttribute("accountReqest", accountReqest);

		request.setAttribute("errorMsg", errorMsg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}
