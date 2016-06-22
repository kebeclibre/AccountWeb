package lu.actions.concrete;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.entities.Account;
import ejb.account.entities.User;
import ejb.account.session.AccountSessionRemote;
import lu.actions.ActionAbstract;
import lu.actions.ActionResult;

public class ManageAccountAction extends ActionAbstract {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("user");
		String aid = req.getParameter("aid");
		System.out.println(result.isRedirect()+result.getTarget());
		
		if (null==u) {
			result.setTarget("manageUser");
			result.makeRedirect();
			System.out.println("in Null");
			return result;
					
		}
		
		System.out.println(result.isRedirect()+result.getTarget());
				
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
		
		
		

		
		System.out.println(result.isRedirect()+result.getTarget());
		
		result.setTarget("accountForm");
		System.out.println(result.isRedirect()+result.getTarget());
		
		return result;
	}

}
