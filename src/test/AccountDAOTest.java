package test;

import java.util.ArrayList;
import java.util.List;

import dao.AccountDAO;
import model.Account;
import model.Login;

public class AccountDAOTest {
	public static void main(String[] args) {
		//データベース Account 　DAOテスト用

		testFindByLogin1();//データ照会(該当ユーザがいる場合)
		//testFindByLogin2();//データ照会(該当ユーザがいない場合)
		//testGetAccountList();////テーブルすべてコンソールに出力
		//testCheckUserExists();//登録データはDBに存在するかTrueならエラーエラーメッセージを返す。
		//testCreateAccount();  //新しくデータ挿入　（テスト後データベース注意）
		testEmailGetAccount("yuta.ookawa1221@gmail.com");//String(email)からDBのデータ照会該当データを返す
		//testIsAccount("kirin");

	}
	public static void testFindByLogin1() {
		//Login login = new Login("kirin","' ' or  'a' = 'a'"); //インジェクションテスト
		Login login = new Login("kirin","1234");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		if(result != null &&
				result.getUserId().equals("kirin") &&
				result.getPass().equals("1234") &&
				result.getAge()==27 &&
				result.getEmail().equals("kirin@gmail.com")){
			System.out.println("testFindByLogin1:該当ユーザーがいます（テスト成功）");
		}else {
			System.out.println("testFindByLogin1:（テスト失敗）");
		}
	}
	public static void testFindByLogin2() {
		Login login = new Login("nirin","1234");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		if(result == null){
			System.out.println("testFindByLogin2:該当ユーザはいません（テスト成功）");
		}else {
			System.out.println("testFindByLogin2:（テスト失敗）");
		}
	}

	public static void testGetAccountList() {
		List<Account> accountList = new ArrayList<>();
		AccountDAO dao = new AccountDAO();
		accountList = dao.getAccountList();
		for(Account account : accountList){
			System.out.print(account.getId()+"  ");
			System.out.print(account.getUserId()+"  ");
			System.out.print(account.getPass()+"  ");
			System.out.print(account.getAge()+"  ");
			System.out.println(account.getEmail());
		}
	}

	public static void testCheckUserExists() {
		Account account = new Account("kirin","1234",27,"kirin@gmail.com");
		String errorMsg= "";
		AccountDAO dao = new AccountDAO();
		errorMsg = dao.checkUserExists(account);
		if(!errorMsg.isEmpty() || errorMsg.length() == 0) {
			System.out.println(errorMsg + "Accountは存在するものを使用しているため（テスト成功）");
		}else {
			System.out.println(errorMsg + "Accountが存在しているものを使用している→エラーはない（テスト失敗）");
		}
	}

	public static void testCreateAccount() {
		AccountDAO dao = new AccountDAO();
		Account account = new Account("test", "123456789", 999, "test@gmail.com");
		dao.createAccount(account);
		System.out.println("testCreateAccount : Accountデータベースにデータを作成しました(テスト成功)");
		System.out.print(account.getId()+"  ");
		System.out.print(account.getUserId()+"  ");
		System.out.print(account.getPass()+"  ");
		System.out.print(account.getAge()+"  ");
		System.out.println(account.getEmail());

	}

	public static void testEmailGetAccount(String email) {
		AccountDAO dao = new AccountDAO();
		Account account2 = dao.emailGetAccount(email);
		System.out.println("ログインID : "+account2.getUserId()+"\n pass : "+account2.getPass()+account2.getEmail()+"成功！");
	}

	public static void testIsAccount(String userId) {
		AccountDAO dao = new AccountDAO();
		boolean isAccount =  dao.isAccount(userId);
		if(isAccount){
			System.out.println("Accountテーブルに該当するUSER_IDはありました! テスト成功");
		}else {
			System.out.println("Accountテーブルに該当するUSER_IDはありませんでした! DBを確認してください テスト成功？");
		}
	}
}
