package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.Account;
import tools.EmailSend;

public class EmailSendTest {
	public static void main(String[] args)
	throws IOException, FileNotFoundException {

        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		EmailSend eSend = new EmailSend();

		//コンタクトフォーム　ユーザーからKirin宛にメールを送信
		eSend.contact("テストメール", "yuta.ookawa1221@gmail.com", "EmailSendTest", "EmailSendTest.javaからのメール\nテスト時間"+sdFormat.format(cl.getTime()));
		//ログインヘルプ   キリンからユーザー宛にメールを送信
		Account account = new Account("キリン","kirin1234","yuta.ookawa1221@gmail.com");
		eSend.loginHelp(account);

	}
}