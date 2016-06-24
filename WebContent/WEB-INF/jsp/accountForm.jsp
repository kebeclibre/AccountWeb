<jsp:include page="includes/header.jsp"/>



<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">
  
	<p><c:out value="User :  ${user.getUsername()}">Create Account Alstublieft</c:out></p>
	<c:choose>
		<c:when test="${acc!=null}">
		<form action="updateAccount.html" method="POST">	
		</c:when>
		<c:when test="${acc==null}">
		<form action="manageAccount.html" method="POST">
		</c:when>
	</c:choose>
   <p>Numéro : <span class="alreadyFilled">${acc.getAccountNumber()}</span> <input type="text" name="numero" /></p>
  <p>Solde Initial : <span class="alreadyFilled">${acc.getAccountBalance()}</span> <input type="text" name="balance"/></p>
  <p>Credit Autorisé : <span class="alreadyFilled">${acc.getAccountCreditLine()}</span> <input type="text" name="allowedCredit"/></p>
  <input type="hidden" name="aidForm" value="${acc!=null?acc.getAccountId():false}" />
   <p><input type="submit" value="Envoyer"/></p>
  <div>
	<c:if test="${acc!=null}">
	<p>Do you want to add a user to this account ?</p>
		<select name="addUser">
		<option value="false" selected>Don't Add Any User</option>
		<c:forEach items="${listU}" var="usr" >
			<option value="${usr.getUserId()}">${usr.getUsername()}</option>				
		</c:forEach>
		</select>
 
		<div><p>Account managed by: </p>
		<c:forEach items="${acc.associatedUsers()}" var="AccUser">
			<p>${AccUser.getUsername()}
			<span class="unsubscribe"><a href="deleteUserFromAccount.html?combined=${acc.getAccountId()}-${AccUser.getUserId()}">Remove user from account</a></span></p>
		</c:forEach>
		
		</div>
		
	</c:if>
  </div>
  

  </form>

  
  </div>
  <div class="col-md-4">
  
  </div>
</div>




<jsp:include page="includes/footer.jsp"/>