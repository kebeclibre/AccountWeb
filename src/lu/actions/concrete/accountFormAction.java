package lu.actions.concrete;

import javax.ejb.EJB;
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


public class accountFormAction extends ActionAbstract {


	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("user");
		
		InitialContext ctx = null;
		
		AccountSessionRemote sessionAccount = null; 
		UserSessionRemote userSession = null; 
		try {
			ctx = new InitialContext();
			sessionAccount = (AccountSessionRemote) ctx.lookup("java:app/AccountEJB/AccountSession!ejb.account.session.AccountSessionRemote");
			userSession = (UserSessionRemote) ctx.lookup("java:app/AccountEJB/UserSession!ejb.account.session.UserSessionRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String numero = req.getParameter("numero");
		double  balance = Double.parseDouble(req.getParameter("balance"));
		double allowedCredit = Double.parseDouble(req.getParameter("allowedCredit"));
		
			if (null == u) {
				result.makeRedirect();
				result.setTarget("userForm");
				return result;
			}
		
			if (null == u.getAccount()) {
				Account a = new Account();
				a.setAccountBalance(balance);
				a.setAccountNumber(numero);
				a.setAccountCreditLine(allowedCredit);
				u.setAccount(a);
				userSession.updateUser(u);
			}
			
			result.setTarget("accountForm");
			
			return result;
			
	}


}
