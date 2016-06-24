package lu.dispatch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lu.actions.Action;
import lu.actions.ActionResult;
import lu.actions.concrete.IndexAction;
import lu.actions.concrete.ManageAccountAction;
import lu.actions.concrete.ManageUserAction;
import lu.actions.concrete.QuitAction;
import lu.actions.concrete.UpdateAccountAction;
import lu.actions.concrete.accountFormAction;
import lu.actions.concrete.DeleteUserAction;
import lu.actions.concrete.DeleteUserFromAccountAction;
import lu.actions.concrete.UserFormAction;


public class Dispatch {
	private static Map<String,Action> paths;
	
	
	static {
		paths=new HashMap<>();
		paths.put("GET/index.html",new IndexAction());
		paths.put("GET/manageAccount.html",new ManageAccountAction());
		paths.put("POST/manageAccount.html",new accountFormAction());
		paths.put("POST/manageUser.html",new UserFormAction());
		paths.put("GET/manageUser.html",new ManageUserAction());
		paths.put("POST/updateAccount.html",new UpdateAccountAction());
		paths.put("GET/quit.html",new QuitAction());
		paths.put("GET/deleteUserFromAccount.html", new DeleteUserFromAccountAction());
		paths.put("GET/deleteUser.html", new DeleteUserAction());
		

	}
	
	public static void delegate(HttpServletRequest req, HttpServletResponse resp) {
			String reqType = req.getMethod();
			String url = req.getServletPath();
			System.out.println(reqType+url);
			ActionResult whereTo = paths.get(reqType+url).execute(req, resp);
			
			if (null == whereTo) {
				whereTo = new ActionResult();
				whereTo.setTarget("index");
				whereTo.makeRedirect();
			}
			
			if (whereTo.isRedirect()) {
				try {
					resp.sendRedirect(whereTo.getTarget()+".html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			} else {
				try {
					req.getRequestDispatcher("/WEB-INF/jsp/"+whereTo.getTarget()+".jsp").forward(req, resp);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
			
		
	}
}
