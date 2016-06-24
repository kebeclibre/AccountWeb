package lu.actions.concrete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.entities.User;
import ejb.account.session.AccountSessionRemote;
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
		User currUser = (User) req.getSession().getAttribute("user");
		
		int userId = Integer.parseInt(combinedExpl[1]);
		int accountId = Integer.parseInt(combinedExpl[0]);
		
		UsersToAccountRemote utaSess = (UsersToAccountRemote) GetLookUp.getSessionBean("UsersToAccount");
		AccountSessionRemote accSession = (AccountSessionRemote) GetLookUp.getSessionBean("AccountSession");
		
		utaSess.removeRelByCombinedId(userId, accountId);
		
		currUser = sessionUser.getUserById(currUser.getUserId());
		req.getSession().setAttribute("user",currUser);
		req.setAttribute("acc", accSession.getAccountById(accountId) );
		
		result.setTarget("accountForm");
		
		return result;
	}
	
}
