package lu.actions.concrete;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.account.entities.Account;
import ejb.account.entities.User;
import ejb.account.entities.Userstoaccount;
import ejb.account.session.AccountSessionRemote;
import ejb.account.session.UserSessionRemote;
import ejb.account.session.UsersToAccountRemote;
import lu.actions.ActionAbstract;
import lu.actions.ActionResult;
import lu.utils.GetLookUp;

public class UpdateAccountAction extends ActionAbstract {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
		int aid = Integer.parseInt( req.getParameter("aidForm"));
		String numero = req.getParameter("numero");
		String balance = req.getParameter("balance");
		String allowedCredit =  req.getParameter("allowedCredit");		
		String addUser =  req.getParameter("addUser");
		
		AccountSessionRemote accSession = (AccountSessionRemote) GetLookUp.getSessionBean("AccountSession");
		Account account = accSession.getAccountById(aid);
		UserSessionRemote UserSess = (UserSessionRemote) GetLookUp.getSessionBean("UserSession");
		UsersToAccountRemote RelSess = (UsersToAccountRemote) GetLookUp.getSessionBean("UsersToAccount");
		
		if (null!=numero && !numero.equals("")) {
			account.setAccountNumber(numero);
		}
		
		if (null!=balance && !balance.equals("")) {
			account.setAccountBalance(Double.parseDouble(balance));;
		}
		
		if (null!=allowedCredit && !allowedCredit.equals("")) {
			account.setAccountCreditLine(Double.parseDouble(allowedCredit));;
		}
		
		if (null!=addUser && !addUser.equals("false")) {
			int uid = Integer.parseInt(addUser);
			User newUsr= UserSess.getUserById(uid);
			Userstoaccount rel = new Userstoaccount();
			rel.setAccount(account);
			rel.setUser(newUsr);
			RelSess.createRel(rel);			
		}
		
		accSession.updateAccount(account);
		
		List<User> listU = UserSess.getAll();
		req.setAttribute("listU", listU);
		
		Account a = accSession.getAccountById(aid);
		req.setAttribute("acc",a);
		
		req.setAttribute("acc", account);
		result.setTarget("accountForm");
		return result;
	}

}
