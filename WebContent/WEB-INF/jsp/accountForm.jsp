<jsp:include page="includes/header.jsp"/>



<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">
  
	<p><c:out value="User :  ${user.getUsername()}">Create Account Alstublieft</c:out></p>
  <form action="manageAccount.html" method="POST">
   <p>Num�ro : <span class="alreadyFilled">${user.getAccount().getAccountNumber()}</span> <input type="text" name="numero"/></p>
  <p>Solde Initial : <span class="alreadyFilled">${user.getAccount().getAccountBalance()}</span> <input type="text" name="balance"/></p>
  <p>Credit Autoris� : <span class="alreadyFilled">${user.getAccount().getAccountCreditLine()}</span> <input type="text" name="allowedCredit"/></p>
  <p><input type="submit" value="Envoyer"/></p>
  </form>

  
  </div>
  <div class="col-md-4">
  
  </div>
</div>




<jsp:include page="includes/footer.jsp"/>