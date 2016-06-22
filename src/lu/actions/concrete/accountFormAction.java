package lu.actions.concrete;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.entities.Account;
import ejb.account.entities.User;
import ejb.account.entities.Userstoaccount;
import ejb.account.session.AccountSessionRemote;
import ejb.account.session.UserSessionRemote;
import lu.actions.ActionAbstract;
import lu.actions.ActionResult;


public class accountFormAction extends ActionAbstract {


	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("user");
	
		String aidForm = req.getParameter("aidForm");
		String numero = req.getParameter("numero");
		String balance = req.getParameter("balance");
		String allowedCredit =  req.getParameter("allowedCredit");
		
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
		
		
		
			if (null == u) {
				result.makeRedirect();
				result.setTarget("userForm");
				return result;
			}
		
		if (aidForm.equals("false")) {
			if (null==numero || null==balance || null==allowedCredit){
				result.makeRedirect();
			} else {
				Account newAccount = new Account();
				Userstoaccount assoc = new Userstoaccount();
				newAccount.setAccountBalance(Double.parseDouble(balance));
				newAccount.setAccountNumber(numero);
				newAccount.setAccountCreditLine(Double.parseDouble(allowedCredit));
				assoc.setAccount(newAccount);
				assoc.setUser(u);
				u.prendreAccounts().add(newAccount);
				userSession.persistRelation(assoc);
				result.setTarget("userForm");
				return result;
				
				
			}
		}
		
			
			result.setTarget("accountForm");
			
			return result;
			
	}


}
