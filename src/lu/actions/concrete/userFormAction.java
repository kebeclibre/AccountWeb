package lu.actions.concrete;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.entities.User;
import ejb.account.session.AccountSessionRemote;
import ejb.account.session.UserSessionRemote;
import lu.actions.ActionAbstract;
import lu.actions.ActionResult;
import lu.utils.GetLookUp;


public class UserFormAction extends ActionAbstract {
 
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserSessionRemote sessionUser = (UserSessionRemote) GetLookUp.getSessionBean("UserSession");
		Object objectUser = req.getSession().getAttribute("user");
		
		if (null == objectUser) {			
			if (null == username || username.equals("") || null == password || password.equals("") ) {
				result.setTarget("manageUser");
				result.makeRedirect();
			
				return result;
			}
			
			User gotUser = sessionUser.getUserWithCredentials(username, password);
		
			if (null == gotUser) {
				gotUser = new User();
				gotUser.setPassword(password);
				gotUser.setUsername(username);
				sessionUser.create(gotUser);
			
			}
			req.getSession().setAttribute("user", gotUser);
		} else {
			User usr = (User) objectUser;
			
			if (!username.equals("")) {
				usr.setUsername(username);
			}
			
			if (!password.equals("")) {
				usr.setPassword(password);
			}
			
			sessionUser.updateUser(usr);
		}
		
		result.setTarget("userForm");
		return result;
		
	}

}
