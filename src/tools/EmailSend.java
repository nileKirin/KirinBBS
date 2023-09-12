package tools;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Account;

public class EmailSend {

	public void contact(String name, String email, String contents, String textArea)
	throws IOException, FileNotFoundException {

		String mailFormat =
				name+"様からの問い合わせ" + StaticTools.LINE_SEPARATOR +
				"メールアドレス : "+ email + StaticTools.LINE_SEPARATOR +
				contents+"に関して" + StaticTools.LINE_SEPARATOR +
				"問い合わせ内容 : "+ textArea;
		Properties prop = new Properties();

		//絶対パス// ポリテクUSB用
		String absolutePath = "F:/usbWorkSpace/KirinBBS/WebContent/setting/mail.properties";
		prop.load(new FileReader(absolutePath));

/*
		  // UTF－8 にエンコードして  プロパティファイルの読み込む
		Properties prop = new Properties();
		try (InputStream input = classLoader.getResourceAsStream("setting/mail.properties")) {
		    InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
		    prop.load(reader);
		}
*/
		//送信元のGmailアドレス
		final String USERNAME = prop.getProperty("mailaddress");
		//Gmailアカウントのアプリパスワード
		final String PASSWORD = prop.getProperty("password");
		//SMTPサーバーへの認証とメールセッションの作成
		//メールセッション = メールの送信に関するパラメータや設定を保持
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});

		try {
			Message message = new MimeMessage(session);
			// 送信元の設定
			message.setFrom(new InternetAddress(USERNAME));
			// 送信先の設定
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(USERNAME));
			// 件名の設定
			message.setSubject("KirinBBSの問い合わせ:"+contents+"に関して");
			// 本文の設定
			message.setText(mailFormat);
			//メール送信
			Transport.send(message);
			System.out.println("Email(cantact)：送信成功");

		}catch(MessagingException e){
			System.out.println("Email(cantact)：送信失敗 " + e);
		}

	}

	public void loginHelp(Account account) throws IOException {
		String ln = StaticTools.LINE_SEPARATOR;
		String mailFormat =
				"きりんBBSからのメールになります" + ln +
				"メールアドレス["+account.getEmail()+"]で登録されたアカウント情報はこちらです"+ ln +
				"ログインID : "+account.getUserId()+ln+
				"パスワード : "+ account.getPass();
		Properties prop = new Properties();

		//絶対パス
		String absolutePath = "F:/usbWorkSpace/KirinBBS/WebContent/setting/mail.properties";
		prop.load(new FileReader(absolutePath));

		final String USERNAME = prop.getProperty("mailaddress");
		final String PASSWORD = prop.getProperty("password");

		//SMTPサーバーへの認証とメールセッションの作成
		//メールセッション = メールの送信に関するパラメータや設定を保持
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(account.getEmail()));
			message.setSubject("KirinBBSからログイン情報に関して");
			message.setText(mailFormat);
			//メール送信
			Transport.send(message);
			System.out.println("Emaill(loginHelp):送信成功");

		}catch(MessagingException e){
			System.out.println("Email(loginHelp):送信失敗 " + e);
		}
	}


}
