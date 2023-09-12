package test;

import java.util.ArrayList;
import java.util.List;

import dao.MutterDAO;
import model.Mutter;

public class MutterDAOTest {

	public static void main(String[] args) {
		//データベース MUTTER 　DAOテスト用

		create();//データ挿入（実行時database注意）
		//findallMutter();//テーブルすべてコンソールに出力
		//delete(35);//deleteId(int)をもとにテーブルから該当する行を削除(テスト時はテーブルのIDをしようしてください、 ID情報が削除される為注意)
	}
	public static void create() {
		Mutter mutter = new Mutter("kirin","8/28テスト");
		MutterDAO dao = new MutterDAO();
		dao.create(mutter);
		System.out.println("Mutterテーブルにユーザとコメントを新しく作成しました");
	}

	public static void delete(int deleteId) {
		MutterDAO dao = new MutterDAO();
		dao.delete(deleteId);
		System.out.println("そのIDをもとに削除成功しました");
	}

	public  static void findallMutter() {
		List<Mutter> mutterList = new ArrayList<>();
		MutterDAO dao = new MutterDAO();
		mutterList= dao.findAll();
		if(mutterList!=null) {
			for(Mutter mutter : mutterList)
			{
				String userId = mutter.getUserId();
				String content = mutter.getContent();
				String time = mutter.getTime();
				System.out.println("ログインID : ["+userId+"] "+
									"投稿内容 : ["+content+"]  "+
									"投稿時間 : ["+time+"]");
			}
		}
	}
}
