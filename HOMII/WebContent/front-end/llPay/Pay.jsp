<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Badger Bank">
    <meta name="author" content="Matthew Maurice">
    <!-- <link rel="stylesheet" href="styles.css"> -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    
    <style>
@import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
/* header */
nav{
	background-color: #4a3b3b;
}
.cd-header-buttons{
	margin-bottom:0rem !important;
	padding:0px 20px;
}

.cd-header-buttons img{
	margin-right:15px;
	border-radius:50%;
}
</style>
    
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/index.jsp"><h2 class="text-white headfont">HöMI</h2></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mob-navbar" aria-label="Toggle">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mob-navbar">
                <ul class="navbar-nav mb-2 mb-lg-0 mx-auto">
                    <li class="nav-item">
	                    <c:choose>
	                         <c:when test="${memVO==null || memVO.member_no==99 }">
	                               <li><a class="nav-link active" aria-current="page" href="#" onclick="loginFirst()">Member Center</a></li>
	                         </c:when>
	                         <c:otherwise>
	                         	<li><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/front-end/mem/memberInfo.jsp">Member Center</a></li>
	                         </c:otherwise>
	                    </c:choose>    	
                    </li>
		    <li class="nav-item">
	                    <c:choose>
	                         <c:when test="${memVO==null || memVO.member_no==99 }">
	                               <li><a class="nav-link" aria-current="page" href="#" onclick="loginFirst()">My Archive</a></li>
	                         </c:when>
	                         <c:otherwise>
	                         	<li><a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/front-end/archive/myArchive.jsp">My Archive</a></li>
	                         </c:otherwise>
	                    </c:choose>    	
                    </li>
                    <li class="nav-item">
                    	<c:choose>
	                         <c:when test="${memVO==null || memVO.member_no==99 }">
	                               <li><a class="nav-link" aria-current="page" href="#" onclick="loginFirst()">My rent</a></li>
	                         </c:when>
	                         <c:otherwise>
	                         	<c:choose>
	                         		<c:when test="${memVO.membership == 0}">
	  	                       			<li><a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/front-end/comp/listAllCompForTenant.jsp">My rent</a></li>
	                         		</c:when>
	                         	</c:choose>
	                         	<c:choose>
	                         		<c:when test="${memVO.membership == 1}">
	  	                       			<li><a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/front-end/comp/listAllCompForLand.jsp">My property</a></li>
	                         		</c:when>
	                         	</c:choose>
	                         </c:otherwise>
	                    </c:choose> 
                    </li>
                    <li class="nav-item">
	                    <c:choose>
	                         <c:when test="${memVO.membership == 1 }">
	                         <li><a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/front-end/mem/memberInfo.jsp">Payment System</a></li>
	                         </c:when>
	                         <c:otherwise>
	                         </c:otherwise>
	                    </c:choose>    	
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Our Services</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:choose>
                         <c:when test="${memVO==null || memVO.member_no==99 }">
                         	<li><a class="dropdown-item" href="#" onclick="loginFirst()" >Service1</a></li>
                         	<li><a class="dropdown-item" href="#" onclick="loginFirst()" >Service2</a></li>
                         	<li><a class="dropdown-item" href="#" onclick="loginFirst()" >Service3</a></li>
                         	<li><hr class="dropdown-divider" /></li>
                         	<li><a class="dropdown-item" href="#" onclick="loginFirst()" >Service4</a></li>
                         </c:when>
                         <c:otherwise>
                         	<li><a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/xxx">Service1</a></li>
                         	<li><a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/xxx">Service2</a></li>
                         	<li><a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/xxx">Service3</a></li>
                         	<li><hr class="dropdown-divider" /></li>
                         	<li><a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/xxx">Service4</a></li>
                         </c:otherwise>
                        </c:choose>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact Us</a>
                    </li>
                </ul>
                <c:choose>
                     <c:when test="${memVO==null || memVO.member_no==99 }">
                        <a class="nav-link text-white" aria-current="page" href="#" onclick="loginFirst()">Create case</a>
                     </c:when>
                     <c:otherwise>
                     	<a class="nav-link text-white" aria-current="page" href="<%=request.getContextPath()%>/front-end/comp/addComp.jsp">Create case</a>
                     </c:otherwise>
                </c:choose> 
                <a class="nav-link text-white" href="#">|</a>

                <ul class="cd-header-buttons">
					<c:choose>
						<c:when test="${memVO.member_no == 99}">
							<a id="login-btn"  href="<%=request.getContextPath()%>/front-end/mem/MemLogin.jsp">Login</a>
						</c:when>
						<c:otherwise>
								<img src="${pageContext.request.contextPath}/mem/DBGifReader4.do?member_no=${memVO.member_no}" 
									id="${memVO.member_no}" alt=" width="50px;" height="50px" 
									class="clickable" />
								<a class="text-white" id="welcome"> ${memVO.mb_name} &nbsp</a>
								<a id="logout-btn" href="<%=request.getContextPath()%>/front-end/mem/MemLogout.jsp"> Logout </a>
						</c:otherwise>
					</c:choose>
				</ul> <!-- cd-header-buttons -->
                   <%--  <a class="nav-link text-white" href="<%=request.getContextPath()%>/front-end/mem/MemLogin.jsp">Log in / Sign up</a> --%>
<!--                     <a class="nav-link " href="#">Sign up</a> -->

            </div>
        </div>
    </nav>

	<br>
            <br>
            <h2 style="text-align:center">Welcome to your payment portal!</h2>  
            
            <br>
            <p style="text-align:center">Select Tenant</p>
            <div class="dropdown" style="text-align:center">
              <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Tenant
              </button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
              </div>
            </div>
            <br>
            <h3 style="text-align:center">Current Balance</h2>
            <p id="balance" style="text-align:center">38.92</p>


            <!--Pick Action-->
            <div style="text-align:center" onchange="landlordTypeSelect()">
              <div class="form-check form-check-inline" >
                <input class="form-check-input" type="radio" name="llRadio" id="llPenaltyRadio" formaction="javascript:landlordPenaltySelect();">
                <label class="form-check-label" for="flexRadioDefault1">
                  Penalty
                </label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="llRadio" id="llPaymentRadio" checked formaction="javascript:landlordPaymentSelect();">
                <label class="form-check-label" for="flexRadioDefault2">
                  Payment
                </label>
              </div>
            </div>
          
            <!--
          <p>Enter Credit Card Info:</p>
          <input style="margin-bottom: 10px" id="CreditCardInfo" type="text"></input>
            -->

          <!-- Payment -->
          <div id = "llPaymentForm" style="text-align:center">
            <p>Enter Amount:</p>
            <input style="margin-bottom: 10px" id="amountPaid" type="text"></input>

            <form>
              <button type="submit" formaction='javascript:landlordAddPayment();' style="margin-bottom: 10px" class = "btn">Add to Balance</button>
            </form>
          </div>

          <!-- Penalty -->
          <div id = "llPenaltyForm" style="text-align:center; visibility:hidden">
            <p>Enter Amount:</p>
            <input style="margin-bottom: 10px" id="penaltyAmount" type="text"></input>
            <form>
              <button type="submit" formaction='javascript:landlordAddPenalty();' style="margin-bottom: 10px" class = "btn">Add Penalty</button>
            </form>
          </div>
	
	<script>
	
		function landlordAddPayment() {
	
		    var curBal = document.getElementById("balance").innerHTML;
	
		    var paymentAmnt = document.getElementById("amountPaid").value;
	
		    // Displays errors if input is not a number, or is empty
			if (isNaN(paymentAmnt) || paymentAmnt.length == 0){
				console.log("Invalid Value");	
			}
			// Displays the new balance
			else {
				document.getElementById("balance").innerHTML = curBal - paymentAmnt;
			}   
		}
		
		function landlordAddPenalty() {

		    var curBal = document.getElementById("balance").innerHTML;

		    var penaltyAmnt = document.getElementById("penaltyAmount").value;

		    // Displays errors if input is not a number, or is empty
			if (isNaN(penaltyAmnt) || penaltyAmnt.length == 0){
				console.log("Invalid Value");	
			}
			// Displays the new balance
			else {
				newBal = parseFloat(curBal)+parseFloat(penaltyAmnt);
		        console.log(newBal);

		        document.getElementById("balance").innerHTML = newBal;
			}
		}
	
		function landlordTypeSelect() {
		    //console.log("PaymentOutter");
		    if ($("#llPaymentRadio").is(":checked")) {
		        //console.log("Payment");
		        document.getElementById("llPaymentForm").style.visibility = "visible";
		        document.getElementById("llPenaltyForm").style.visibility = "hidden";
		    }
		    if ($("#llPenaltyRadio").is(":checked")) {
		        //console.log("Payment");
		        document.getElementById("llPenaltyForm").style.visibility = "visible";
		        document.getElementById("llPaymentForm").style.visibility = "hidden";
		    }
		    // document.getElementById("llPaymentForm").style.visibility = "visible";
		    // document.getElementById("llPenaltyForm").style.visibility = "hidden";
		}
	
	
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</body>
</html>
