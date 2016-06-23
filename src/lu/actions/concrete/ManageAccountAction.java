package lu.actions.concrete;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.entities.Account;
import ejb.account.entities.User;
import ejb.account.session.AccountSessionRemote;
import ejb.account.session.UserSessionRemote;
import lu.actions.ActionAbstract;
import lu.actions.ActionResult;
import lu.utils.GetLookUp;

public class ManageAccountAction extends ActionAbstract {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("user");
		String aid = req.getParameter("aid");
		
		if (null==u) {
			result.setTarget("manageUser");
			result.makeRedirect();
			System.out.println("in Null");
			return result;				
		}
		
				
		if (null != aid) {
			Integer acc = Integer.parseInt(aid);
			AccountSessionRemote sessionAccount = (AccountSessionRemote) GetLookUp.getSessionBean("AccountSession");
			UserSessionRemote sessionUser = (UserSessionRemote) GetLookUp.getSessionBean("UserSession");
			
			
			Account a = sessionAccount.getAccountById(acc);
			req.setAttribute("acc",a);
			List<User> listU = sessionUser.getAll();
			req.setAttribute("listU", listU);	
		}

		result.setTarget("accountForm");
		return result;
	}

}
