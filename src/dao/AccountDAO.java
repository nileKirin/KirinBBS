package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Login;

public class AccountDAO {

	//データベース接続情報
	public final String JDBC_URL = "jdbc:h2:tcp://localhost/~/KirinBBS";
	public final String DB_USER = "sa";
	public final String DB_PASS = "123";

	//ログイン(Login login)からAccountテーブルに該当データがあれば、そのAccount型を返す
	public Account findByLogin(Login login) {
		Account account = null;
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//SQL準備
			String sql = "SELECT USER_ID,PASS,AGE,EMAIL FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pStm = conn.prepareStatement(sql);
			pStm.setString(1, login.getUserId());
			pStm.setString(2, login.getPass());

			//SQL実行　結果を取得
			ResultSet rs = pStm.executeQuery();

			//SQLの内容(login.userId と　login.pass )と
			//一致したユーザが存在する場合　そのユーザのAccountインスタンス作成
			if(rs.next()) {
			//結果表（配列）データ取得
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				int age = rs.getInt("AGE");
				String email = rs.getString("EMAIL");
				account = new Account(userId, pass, age, email);
			}
		} catch (SQLException e) {
			System.out.println("AccountDAO.findByLogin()エラー発生");
			e.printStackTrace();
			return null;
		}
		//見つかったユーザーかNULLを返す
		return account;
	}

	//アカウント新規作成(INSERT)のメソッド(CreateAccountServlet.java)
	public void createAccount(Account account) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "INSERT INTO ACCOUNT(USER_ID,PASS,AGE,EMAIL) VALUES(?,?,?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getUserId());
			pStmt.setString(2, account.getPass());
			pStmt.setInt(3, account.getAge());
			pStmt.setString(4, account.getEmail());
			//SQL実行
			int line =  pStmt.executeUpdate();
			if(line < 0) {
				System.out.println("AccountDAO.createAccount() account作成に失敗しました");
			}


		} catch (SQLException e) {
			System.out.println("AccountDAO.createAccount()エラー発生");
			e.printStackTrace();
		}
	}

	//アカウント同名(USER,email)チェック用 （SELECT）
	//入力された新規accountと同じデータがDBにあるかをチェック。(CreateAccountServlet.java)
    public String checkUserExists(Account account) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String userIdCheckSql = "SELECT COUNT(*) FROM ACCOUNT WHERE USER_ID = ?";
            String emailCheckSql = "SELECT COUNT(*) FROM ACCOUNT WHERE EMAIL = ?";

            try (PreparedStatement userIdCheckStm = conn.prepareStatement(userIdCheckSql);
                 PreparedStatement emailCheckStm = conn.prepareStatement(emailCheckSql)) {
                userIdCheckStm.setString(1, account.getUserId());
                emailCheckStm.setString(1, account.getEmail());

                int userIdCount = getCount(userIdCheckStm);
                int emailCount = getCount(emailCheckStm);

                if (userIdCount > 0 && emailCount > 0) {
                    return "ログインIDとメールアドレスはどちらも使用されています!";
                } else if (userIdCount > 0) {
                    return "そのログインIDは既に存在します!";
                } else if (emailCount > 0) {
                    return "そのメールアドレスは既に存在します!";
                }
            }
        } catch (SQLException e) {
            System.out.println("AccountDAO.checkUserExists()エラー発生");
            e.printStackTrace();
            return "エラーが発生しました";
        }
        return ""; // チェックに問題ない場合は文字列""を返す
    }
    //pStmtが通らなければ例外をスロー
    private int getCount(PreparedStatement pStmt) throws SQLException {
        try (ResultSet rs = pStmt.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

	//アカウントデータを（List<Account>ですべて取得するメソッド
	public List<Account> getAccountList(){
		List<Account> accountList = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "SELECT ID, USER_ID, PASS, AGE, EMAIL FROM ACCOUNT";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("ID");
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				int age = rs.getInt("AGE");
				String email = rs.getString("EMAIL");
				Account account = new Account(id,userId, pass, age, email);
				accountList.add(account);
			}
		} catch (SQLException e) {
			System.out.println("AccountDAO.getAccountList()エラー発生");
			e.printStackTrace();
			return null;
		}

		return accountList;
	}


	//文字列（email）の情報を渡されるとデータ照会、該当するユーザーのAccountを返す（loginHelp.jsp）
	public Account emailGetAccount(String email) {
		Account account = null;
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "SELECT USER_ID,PASS FROM ACCOUNT WHERE EMAIL = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				account = new Account(userId, pass, email);
			}
		} catch (SQLException e) {
			System.out.println("AccountDAO().emailGetAccountでエラー発生");
			e.printStackTrace();
			return null;
		}
		return account;
	}



	//ユーザー情報(文字列）を渡されるとテーブルにあるかを真偽で返す
	public boolean isAccount(String userId){
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "SELECT USER_ID FROM ACCOUNT";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			if(rs.next()) {
				String getUserId = rs.getString("USER_ID");
				return userId.equals(getUserId);
			}
		} catch (SQLException e) {
			System.out.println("AccountDAO.isAccount()エラー発生");
			e.printStackTrace();
			return false;
		}

		return false;
	}

}
