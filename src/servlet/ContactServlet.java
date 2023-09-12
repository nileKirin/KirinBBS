package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContactUser;
import tools.EmailSend;
import tools.ParameterChecker;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet{
	String forwardPath;
	String errorMsg;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		errorMsg = "";
		forwardPath = "/WEB-INF/jsp/contact.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		errorMsg = "";
		forwardPath = "/WEB-INF/jsp/contact.jsp";

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String remail = request.getParameter("remail");
		String contents = request.getParameter("contents");
		String textArea = request.getParameter("textarea");

		ParameterChecker pChecker = new ParameterChecker();
		errorMsg = pChecker.contactFormCheck(name, email, remail, contents, textArea);
		if (errorMsg.isEmpty()) {
			//情報に問題がなければ
			//リクエストスコープにデータ保存
			ContactUser contactUser = new ContactUser(name, email, contents, textArea);
			request.setAttribute("contactUser", contactUser);

			//メール送信処理
			EmailSend eSend = new EmailSend();
			eSend.contact(name, remail, contents, textArea);
			//フォワードパスの変更
			forwardPath = "/WEB-INF/jsp/contactResult.jsp";
		}
		request.setAttribute("errorMsg", errorMsg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}
