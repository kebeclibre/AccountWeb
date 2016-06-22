package lu.actions.concrete;

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

public class ManageAccountAction extends ActionAbstract {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("user");
		String aid = req.getParameter("aid");
		
		if (null==req.getSession().getAttribute("user")) {
			result.setTarget("userForm");
			result.makeRedirect();
			return result;
			
		}
				
		if (null != aid) {
			Integer acc = Integer.parseInt(aid);
			AccountSessionRemote sessionAccount = null; 
			InitialContext ctx = null;
			try {
				ctx = new InitialContext();
				sessionAccount = (AccountSessionRemote) ctx.lookup("java:app/AccountEJB/AccountSession!ejb.account.session.AccountSessionRemote");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Account a = sessionAccount.getAccountById(acc);
			req.setAttribute("acc",a);
			
		}

		
		
		
		result.setTarget("accountForm");
		
		return result;
	}

}
