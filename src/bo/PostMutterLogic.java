package bo;

import dao.MutterDAO;
import model.Mutter;
//Mutterに新しいデータを挿入
public class PostMutterLogic {
 public void excecute(Mutter mutter) {
	 MutterDAO dao = new MutterDAO();
	 dao.create(mutter);
 }
}
