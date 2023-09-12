package bo;

import dao.AccountDAO;
import model.Account;

public class getAccountFromEmailLogic {
	public Account excecute(String email) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.emailGetAccount(email);
		return account;
	}
}
