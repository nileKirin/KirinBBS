package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Mutter;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();

		//セッションにアカウント情報がなければリダイレクト
		Account account = (Account)session.getAttribute("account");
		if(account==null) {
			response.sendRedirect("welcome.jsp");
		}

		@SuppressWarnings("unchecked")//下記の35行目のコードの警告を無視させる
		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		List<Mutter> userMutterList = mutterListFromUser(mutterList, account.getUserId());
		application.setAttribute("userMutterList", userMutterList);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
		dispatcher.forward(request, response);

	}

	//投稿一覧を加工してユーザー投稿一覧にして返すメソッド
	public List<Mutter> mutterListFromUser(List<Mutter> mutterList,String user){
		List<Mutter> userMutterList = new ArrayList<>();
		for(Mutter m : mutterList){
			String userId = m.getUserId();
			String content = m.getContent();
			String time = m.getTime();
			if(user.equals(userId)) {
				Mutter mutter = new Mutter(userId, content,time);
				userMutterList.add(mutter);
			}
		}
		if(userMutterList == null || userMutterList.size() == 0){
			return null;
		}
		return userMutterList;
	}


}
