package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.GetMuuterListLogic;
import bo.PostMutterLogic;
import dao.MutterDAO;
import model.Account;
import model.Mutter;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	String forwardPath;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		//リクエストパラメータでdeleteIdを取得したら行う＞データ消去
		String deleteId = request.getParameter("id");
		if(!(deleteId == null || deleteId.isEmpty()))
		{
			int id = Integer.parseInt(deleteId);
			MutterDAO dao = new MutterDAO();
			dao.delete(id);
		}

		GetMuuterListLogic getMuuterListLogic = new GetMuuterListLogic();
		List<Mutter>mutterList = getMuuterListLogic.excecute();

		ServletContext application = this.getServletContext();
		application.setAttribute("mutterList",mutterList);

		HttpSession session = request.getSession();
		Account account	= (Account)session.getAttribute("account");
		forwardPath = account  != null ? "/WEB-INF/jsp/main.jsp" : "/welcome.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account	= (Account)session.getAttribute("account");

		//リクエストパラメータの取得
		String content = request.getParameter("content");
		Mutter mutter = new Mutter(account.getUserId(), content);

		//投稿処理 Mutterテーブルにデータ追加
		if(!(mutter == null || mutter.getContent().isEmpty())){
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.excecute(mutter);
		}else {
			String errorMsg = "文字を入力してください";
			request.setAttribute("errorMsg", errorMsg);

		}

		GetMuuterListLogic getMuuterListLogic = new GetMuuterListLogic();
		List<Mutter>mutterList = getMuuterListLogic.excecute();

		ServletContext application = this.getServletContext();
		application.setAttribute("mutterList",mutterList);


		forwardPath = account  != null ? "/WEB-INF/jsp/main.jsp" : "/welcome.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
