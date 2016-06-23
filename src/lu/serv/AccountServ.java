package lu.serv;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import lu.dispatch.Dispatch;


@WebServlet(name = "AccountWeb", urlPatterns= {"*.html"})
public class AccountServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccountServ() {
        super();

    }

    public void service(HttpServletRequest req, HttpServletResponse resp) {
    	Dispatch.delegate(req, resp);
    }
	

}
