package bo;

import dao.AccountDAO;
import model.Account;
//createAccount.jsp でアカウント作成時に USERID,Emailを
//DB照会 真偽に応じてエラーメッセージを返す

public class CheckUserExistsLogic {
	public String excecute(Account account) {
		AccountDAO dao = new AccountDAO();
		return dao.checkUserExists(account);
	}
}
