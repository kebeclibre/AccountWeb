package lu.actions;

public class ActionResult {
	private boolean isRedirect = false;
	private String target;
	
	public void makeRedirect() {
		isRedirect=true;
	}
	
	public boolean isRedirect() {
		return isRedirect;
	}
	
	public void setTarget(String targ) {
		this.target = targ;
	}
	
	public String getTarget() {
		return this.target;
	}
	
	
}
