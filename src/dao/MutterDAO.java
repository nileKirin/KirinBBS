package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {

	//データベース接続情報
	public final String JDBC_URL = "jdbc:h2:tcp://localhost/~/KirinBBS";
	public final String DB_USER = "sa";
	public final String DB_PASS = "123";

	//一覧を取得(SELECT)投稿一覧を取得表示用 (MainServlet,UserServlet)
	public List<Mutter> findAll(){
		List<Mutter> mutterList = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "SELECT ID,USER_ID,CONTENT,TIME FROM MUTTER";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String userId = rs.getString("USER_ID");
				String content = rs.getString("CONTENT");
				Timestamp timeStamp = rs.getTimestamp("TIME");

				//TimeStamp型で取得したデータをフォーマットに入れて文字列で渡す
				SimpleDateFormat sdf = new SimpleDateFormat("M月d日H時m分s秒");
				String time = sdf.format(timeStamp);

				Mutter mutter = new Mutter(id,userId, content,time);
				mutterList.add(mutter);
			}

		}catch (SQLException e) {
			System.out.println("MutterDAO.findAll()エラー");
			e.fillInStackTrace();
			return null;
		}
		return mutterList;
	}


	//投稿を追加(INSERT) ユーザのつぶやきをMutterデーブルに登録(MainServlet)
	public void create(Mutter mutter) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "INSERT INTO MUTTER (USER_ID, CONTENT) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mutter.getUserId());
			pStmt.setString(2, mutter.getContent());
			//SQL実行
			int line =  pStmt.executeUpdate();
			if(line < 0) {
				System.out.println("MutterDAO.create() 投稿の追加に失敗しました");
			}
		} catch (SQLException e) {
			System.out.println("MutterDAO.create()エラー発生");
			e.printStackTrace();
		}
	}


	//投稿を削除(DELETE) ユーザの次ぐ焼きIdから該当データを削除(MainServlet)
	public void delete(int deleteId) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "DELETE MUTTER WHERE ID = ?";
			PreparedStatement pStmt =conn.prepareStatement(sql);
			pStmt.setInt(1, deleteId);
			int line = pStmt.executeUpdate();
			if(line < 0) {
				System.out.println("MutterDAO.delete() 投稿の削除に失敗しました");
			}
		} catch (SQLException e) {
			System.out.println("MutterDAO.delete()エラー発生");
			e.printStackTrace();
		}
	}

}
