package lu.utils;

import javax.ejb.SessionBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class GetLookUp {
	private static InitialContext ctx;
	
	private static void setInitialContext() {
		if (null==ctx) {
			try {
				ctx=new InitialContext();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static Object getSessionBean(String ressource) {
		if (null==ctx) {
			setInitialContext();
		}
		Object sb = null;
		
		try {
			sb = ctx.lookup("java:app/AccountEJB/"+ressource+"!ejb.account.session."+ressource+"Remote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb;
	}
		
	
	
}
