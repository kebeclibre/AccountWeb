package lu.actions.concrete;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.entities.User;
import ejb.account.entities.Userstoaccount;
import ejb.account.session.UserSessionRemote;
import ejb.account.session.UsersToAccountRemote;
import lu.actions.ActionAbstract;
import lu.actions.ActionResult;
import lu.utils.GetLookUp;

public class DeleteUserAction extends ActionAbstract {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		
		int uid = Integer.parseInt(req.getParameter("uid"));
		
		UserSessionRemote sessionUser = (UserSessionRemote) GetLookUp.getSessionBean("UserSession");
		UsersToAccountRemote sessionRel = (UsersToAccountRemote) GetLookUp.getSessionBean("UsersToAccount");
		
		User u = sessionUser.getUserById(uid);
		List<Userstoaccount> accountsRel = u.getUserstoaccounts();
		
		for (Userstoaccount uta : accountsRel) {
			sessionRel.removeRel(uta);
		}
		
		sessionUser.removeUser(u);
		
		
		req.getSession().invalidate();
		result.makeRedirect();
		result.setTarget("index");
		
		return result;
	}

}
