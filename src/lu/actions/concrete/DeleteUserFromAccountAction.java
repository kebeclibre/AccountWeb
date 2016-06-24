package lu.actions.concrete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.session.UserSessionRemote;
import ejb.account.session.UsersToAccount;
import ejb.account.session.UsersToAccountRemote;
import lu.actions.ActionAbstract;
import lu.actions.ActionResult;
import lu.utils.GetLookUp;

public class DeleteUserFromAccountAction extends ActionAbstract {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String combined = req.getParameter("combined");
		String[] combinedExpl = combined.split("-");
		UserSessionRemote sessionUser = (UserSessionRemote) GetLookUp.getSessionBean("UserSession");
		int userId = Integer.parseInt(combinedExpl[1]);
		int accountId = Integer.parseInt(combinedExpl[0]);
		
		UsersToAccountRemote utaSess = (UsersToAccountRemote) GetLookUp.getSessionBean("UsersToAccount");
		
		utaSess.removeRelByCombinedId(userId, accountId);
		
		req.getSession().setAttribute("user",sessionUser.getUserById(userId) );
		
		result.setTarget("userForm");
		
		return result;
	}
	
}
