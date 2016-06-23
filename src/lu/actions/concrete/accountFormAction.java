package lu.actions.concrete;

import java.util.List;

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
import lu.utils.GetLookUp;


public class accountFormAction extends ActionAbstract {


	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("user");
	
		String aidForm = req.getParameter("aidForm");
		String numero = req.getParameter("numero");
		String balance = req.getParameter("balance");
		String allowedCredit =  req.getParameter("allowedCredit");
		
		InitialContext ctx = null;
		
		AccountSessionRemote sessionAccount = (AccountSessionRemote) GetLookUp.getSessionBean("AccountSession");
		UserSessionRemote sessionUser = (UserSessionRemote) GetLookUp.getSessionBean("UserSession"); 

// IF there is no user in HTTP Session, redirect to login page
		if (null == u) {
			result.makeRedirect();
			result.setTarget("userForm");
			return result;
		}

// If there is no account to manage (which Id would be aid): we treat the form to create a new account and add it to the user u
// If form is empty, we can't treat it, we redirect to the form
		if (aidForm.equals("false")) {
			if (null==numero || null==balance || null==allowedCredit){
				result.setTarget("manageAccount.html");
				result.makeRedirect();
				return result;
			} else {
				
				// PREPARATION OF OUR OBJECT
				Account newAccount = new Account();
				Userstoaccount assoc = new Userstoaccount();
				newAccount.setAccountBalance(Double.parseDouble(balance));
				newAccount.setAccountNumber(numero);
				newAccount.setAccountCreditLine(Double.parseDouble(allowedCredit));
				
				// PERSIST OUR OBJECT
				assoc.setAccount(newAccount);
				assoc.setUser(u);
				Userstoaccount recup = sessionUser.persistRelation(assoc);
				
				
				// UPDATE VIEW AND RETURN			
				User newUsr=sessionUser.getUserById(recup.getUser().getUserId());
				req.getSession().setAttribute("user", newUsr);
				result.setTarget("userForm");
		
				return result;	
			}
		}
		
// otherwise, it means there is an account to manage, we just display the view
			result.setTarget("accountForm");
			return result;
			
	}


}
