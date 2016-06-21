package lu.actions.concrete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.entities.User;
import lu.actions.ActionAbstract;
import lu.actions.ActionResult;

public class ManageAccountAction extends ActionAbstract {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("user");
		
		if (null==req.getSession().getAttribute("user")) {
			result.setTarget("userForm");
			result.makeRedirect();
			return result;
			
		}
		
		result.setTarget("accountForm");
		
		return result;
	}

}
