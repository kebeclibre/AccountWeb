package lu.actions.concrete;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.entities.User;
import ejb.account.session.UserSessionRemote;
import lu.actions.ActionAbstract;
import lu.actions.ActionResult;


public class userFormAction extends ActionAbstract {
 
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		InitialContext ctx = null;
		
		UserSessionRemote userSession = null; 
		try {
			ctx = new InitialContext();
			userSession = (UserSessionRemote) ctx.lookup("java:app/AccountEJB/UserSession!ejb.account.session.UserSessionRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if (null == username || null == password) {
			result.setTarget("manageUser");
			result.makeRedirect();
			
			return result;
		}
		
		User gotUser = userSession.getUserWithCredentials(username, password);
		
		if (null == gotUser) {
			gotUser = new User();
			gotUser.setPassword(password);
			gotUser.setUsername(username);
			userSession.create(gotUser);
			
		}
		
	
		req.getSession().setAttribute("user", gotUser);
		result.setTarget("userForm");
		
		return result;
	}

}
