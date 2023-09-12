package bo;

import dao.AccountDAO;
import model.Account;

//（createAccount.jsp）渡されたAccount型をもとにSQL(INCERT 実行)
public class CreateAccountLogic {
	public void excecute(Account account) {
		AccountDAO dao = new AccountDAO();
		dao.createAccount(account);

	}
}
