package bo;

import java.util.List;

import dao.MutterDAO;
import model.Mutter;

public class GetMuuterListLogic {
	public List<Mutter> excecute() {
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findAll();
		return mutterList;
	}
}
