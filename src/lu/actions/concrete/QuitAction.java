package lu.actions.concrete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lu.actions.ActionAbstract;
import lu.actions.ActionResult;

public class QuitAction extends ActionAbstract {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {		
		result.makeRedirect();
		req.getSession().invalidate();
		result.setTarget("index");
		
		return result;
	}

}
