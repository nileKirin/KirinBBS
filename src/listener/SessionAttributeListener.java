package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    public SessionAttributeListener() {
    }

    public void attributeAdded(HttpSessionBindingEvent event)  {
    	//System.out.println("セッション属性が追加されたました");
    }
    public void attributeRemoved(HttpSessionBindingEvent event)  {

    	//System.out.println("セッション属性が削除されました");//消してからの処理
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  {
    }

}
