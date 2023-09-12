package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionExpirationListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event)  {
        // セッションが作成されたときの処理（HttpSession）属性ではなくセッションIDを作ったとき
    	//System.out.println("セッションが作成されました");
    }

    public void sessionDestroyed(HttpSessionEvent event)  {
    	//System.out.println("セッションが破棄されました");
        // セッションがタイムアウトで破棄される直前の処理


    }

}
