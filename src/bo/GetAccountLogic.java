package bo;

import dao.AccountDAO;
import model.Account;
import model.Login;
//ログイン情報(Login login)から、紐づくアカウント情報をAccount型で返す。
public class GetAccountLogic {
	public Account excecute(Login login) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.findByLogin(login);
		return account;
	}
}
