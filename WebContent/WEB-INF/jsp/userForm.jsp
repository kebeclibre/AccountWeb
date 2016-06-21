<jsp:include page="includes/header.jsp"/>



<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">
  
  	<form action="manageUser.html" method="POST">
  	<p>Username : <span class="alreadyFilled">${user.getUsername()}</span><input  name="username" /></p>
  	<p>Password : <input  name="password" type="password" /></p>
  	<p><input  type="submit"  value="Envoyer" /></p>
  	
  	
  	
  	
  	
  	</form>

  
  </div>
  <div class="col-md-4">

  
  
  </div>
</div>


<jsp:include page="includes/footer.jsp"/>