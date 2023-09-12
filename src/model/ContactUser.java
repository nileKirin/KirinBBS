package model;

//このmodelパッケージ内で「このクラスのみDTOではない」

//ContactServlet.jspで使用しており
//ContactResult.jspにリクエストスコープを綺麗に渡すために作成

public class ContactUser{
	private String name;
	private String email;
	private String contents;
	private String textArea;

	public ContactUser() {}
	public ContactUser(String name, String email, String contents,String textArea) {
		this.name = name;
		this.email = email;
		this.contents = contents;
		this.textArea = textArea;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getContents() {
		return contents;
	}
	public String getTextArea() {
		return textArea;
	}

}
