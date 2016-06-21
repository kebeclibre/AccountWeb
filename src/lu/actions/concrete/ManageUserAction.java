package lu.actions.concrete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lu.actions.ActionAbstract;
import lu.actions.ActionResult;

public class ManageUserAction extends ActionAbstract {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
	
		result.setTarget("userForm");
		
		
		
		return result;
	}
	
}
