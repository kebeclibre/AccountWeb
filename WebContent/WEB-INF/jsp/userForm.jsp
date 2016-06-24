<jsp:include page="includes/header.jsp"/>



<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">
  
  	<form action="manageUser.html" method="POST">
  	<p>Username : <span class="alreadyFilled">${user.getUsername()}</span>  <input  name="username" /></p>
  	<p>Password : <input  name="password" type="password" /></p>
  	<p><input  type="submit"  value="Envoyer" /></p>
  	</form>


	<div>
	<c:if test="${user.prendreAccounts()!=null && user.prendreAccounts().size() > 0 }">

		<h1>Available Accounts for user : ${user.getUsername()}</h1>
		<table>
		<tr><td>Account Number</td><td>Account Balance</td><td>Account Credit Line</td><td>Management</td>
		</tr>
		<c:forEach items="${user.prendreAccounts()}" var="account">
		<tr>
			<td> ${account.getAccountNumber()} </td>
			<td> ${account.getAccountBalance()} </td>
			<td> ${account.getAccountCreditLine()} </td>
			<td><a href="manageAccount.html?aid=${account.getAccountId()}">Manage</a>	</td>
			<!--<td> <input type="radio" name="accountId" value="${account.getAccountId()}"/> 	</td>-->
			
		</tr>
		</c:forEach>
		</table>

	</c:if>
	<c:if test="${user!=null}" >
		<div id="deleteOption">
		<p>Carefull with this 	<a href="deleteUser.html?uid=${user.getUserId()}">Delete current user</a></p>
		</div>
	
  	</c:if>
  	</div>
  </div>
  <div class="col-md-4">

  
  
  </div>
</div>


<jsp:include page="includes/footer.jsp"/>