<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.comp.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.reg.model.*"%>
<%@ page import="com.apt.model.*"%>

<jsp:useBean id="compSvc" scope="page" class="com.comp.model.CompService" />
<jsp:useBean id="regSvc" scope="page" class="com.reg.model.RegService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="compDAO" scope="page" class="com.comp.model.CompDAO" />	
<jsp:useBean id="memVO" scope="session" class="com.mem.model.MemVO" />
<%
	memVO = memSvc.getOneMem(memVO.getMember_no());
	pageContext.setAttribute("memVO", memVO);

	RegVO regVO = regSvc.getOneRegister(memVO.getMember_no());
	pageContext.setAttribute("regVO", regVO);
	AptService aptSvc = new AptService();
	AptVO aptVO = aptSvc.getOneAptByApName(regVO.getAp_name());
	pageContext.setAttribute("aptVO", aptVO);

%>
<html>
<head>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/chatbox.css" type="text/css" />
<title>AllCaseForTenant</title>

<style>
   body {  
/*      width: 1200px;   */


	font-family: "Helvetica Neue", Helvetica, Roboto, Arial, "Lucida Grande", "PingFang TC", "Apple LiGothic Medium", sans-serif;
	  background-image: url("<%=request.getContextPath()%>/img/complaint form.jpg");
  background-size:100%;
    background-attachment:fixed;
 	        }  
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
.row{
	margin-left:20px !important;
	margin-right:20px !important;
}
  table {
	width: 100%;
	background-color: white;
	margin-top: 0px;
	margin-bottom: 5px;
	
  }
  table, th, td {
/*     border: 1px solid #CCCCFF; */
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .forum-body{

  	padding:0;
}
.forum-nav{
/* 	background-color:#126E7D; */
}
.search-form{
	float:right;
}

.forum-body #forum>thead>tr>th{
	color:white;
	text-align:center;
}
.forum-body #forum>thead>tr>th,
.forum-body #forum>tbody>tr>td{
	font-size:small;
}
ul.nav-pills > li.active > a.toggle{
	background-color: #51A1B4;
}
table#forum.table>tbody>tr>td{
	padding-top:3px !important;
	padding-bottom:3px! important;
	color:#6c6760;
}
table#forum.table>tbody>tr:hover
{
	background-color:#FFFFDD !important;
 	transform: translateY(-5px); 
     box-shadow: 0 5px 5px rgba(0, 0, 0, 0.15); 
/* 	transition: box-shadow .25s; */
/*     box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 3px 10px 0 rgba(0, 0, 0, 0.19); */
}
table#forum.table>tbody>tr
{
 	box-shadow: 0 0 3px rgba(0, 0, 0, 0.2); 
     transition: 0.2s; 
/*  	transition: box-shadow .25s; */
/*     box-shadow: 0 0px 0px 0 rgba(0, 0, 0, 0.16), 0 1px 5px 0 rgba(0, 0, 0, 0.12); */
}
table#forum.table>tbody>tr>td>a
{
	font-weight:bold;
	color:black;
}
 table#forum.table>tbody>tr>td>a.notJQellipsis
 { 
 	font-weight:bold; 
 } 
table#forum.table>tbody>tr>td>a.JQellipsis
{
	font-weight:200;
	color:#6c6760;
	font-size:small;
}
#forum > tbody + tbody{
	border-top: 1px dotted #ddd;
}
.forum-nav>ul.nav.nav-pills>li>a.toggle{
	font-weight:700;
}
.content-header{
  font-family: 'Roboto', sans-serif;
  color:#fcc500;
  font-size: 45px;
}

.section-content{
  text-align: center; 

}
#contact{
  margin:35px 50px;
  font-family: 'Teko', sans-serif;
  width: 87%;
  backdrop-filter: blur(5px);
  position:relative;
  border-radius:50px;
  backdrop-filter: blur(5px);
  background-color:#80808021;

}

#contact button{
	width:180px;

}
.contact-section{
  padding-top: 40px;
}
.contact-section .col-md-6{
  width: 50%;
}

.form-line{
  border-right: 1px solid #B29999;
}

.form-group{
  margin: 10px 30px;
}
label{
  font-size: 1em;
  line-height: 1em;
  font-weight: bolder;
}
.form-control{
  color: #080808;
}
textarea.form-control {
    height: 135px;
   /* margin-top: px;*/
}


.form-group label{
	display: block;
	float: left;
	overflow: hidden;
	width: 190px;
	height: 29px;
	padding-right: 10px;
	text-align: justify;
	line-height: 2;
}

.form-group span {
    display: block;
    overflow: hidden;
    padding: 0 4px 0 6px
}
.form-group .form-control input {
    width: 50%
}
.balance{
	margin:38px 0px;
	border-radius:50px;
	backdrop-filter: blur(5px);
	background-color:#80808021;
}
.balance h1, div, a{
	text-align:center;
}
.submit{
  font-size: 1.1em;
  width: 200px;
  background-color: transparent;
  color: #fff;

}

.w-100{
	height: 250px;
	width: 80% !important;
	margin: 20px 44px;
	overflow:hidden;
}
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
<body onload="connect();" onunload="disconnect();">
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
	                         <li><a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/front-end/IIPay/Pay.jsp">Payment System</a></li>
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
									id="${memVO.member_no}" alt=" width="60px;" height="60px" 
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

<div class="container-fluid forum-body" style="min-height:450px;">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">Fix error as below:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${memVO != null}">
<!-- chatbox -->
<div id="chat-circle" class="btn btn-raised">
        <div id="chat-overlay"></div>
        <i class="fas fa-sms"></i>
</div>
<div class="chat-box">
    <div class="chat-box-header">
      <i class="far fa-comment" style="margin-right:5px"></i><span id="contactName"></span>
      <span class="chat-box-toggle"><i class="fas fa-minus"></i></span>
    </div>
    <div class="chat-box-body">
      <div class="chat-box-overlay">   
      </div>
      <div class="chat-logs">
       
      </div><!--chat-log -->
    </div>
    <div class="chat-input">      
      <input type="text" id="chat-input" placeholder="Send a message..." onkeydown="if (event.keyCode == 13) sendMessage();"/>
      <button class="chat-submit" id="chat-submit"><i class="fas fa-paper-plane"></i></button>
    </div>
  </div>
<!-- chatbox -->
</c:if>
<div class="row">
	<div class="col-md-4">
		<div class="balance" >
			<div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			      <img src="${pageContext.request.contextPath}/apt/apt.do?action=view_aptPic1&ap_name=${aptVO.ap_name}" class="d-block w-100" alt="...">
			    </div>
			    <div class="carousel-item">
				  <img src="${pageContext.request.contextPath}/apt/apt.do?action=view_aptPic2&ap_name=${aptVO.ap_name}" class="d-block w-100" alt="...">
			    </div>
			    <div class="carousel-item">
			      <img src="${pageContext.request.contextPath}/apt/apt.do?action=view_aptPic3&ap_name=${aptVO.ap_name}" class="d-block w-100" alt="...">
			    </div>
			  </div>
			</div>

			<div style="padding:35px 0px">
			<div class="form-group">
		    	<label for="apName">Apartment Name</label>
		    	<span><input type="text" class="form-control" value="<%= (regVO==null)? "" : regVO.getAp_name()%>" id="apName"  disabled="disabled"></span>
		  	</div>
			<div class="form-group">
		    	<label for="apAddress">Apartment Address</label>
		    	<span><textarea class="form-control" cols="2" rows="2" id="apAddress"  disabled="disabled">${regVO.ap_address}</textarea></span>
		  	</div>
			<div class="form-group">
		    	<label for="landName">LandLord Name</label>
		    	<span><input type="text" class="form-control" value="<%= (regVO==null)? "" : regVO.getLand_name()%>" id="landName"  disabled="disabled"></span>
		  	</div>
			<div class="form-group">
		    	<label for="status">Status</label>
		    	 <c:choose>
                   <c:when test="${regVO.status == 0}">
		    			 <span>Pending</span>
                   </c:when>
                   <c:when test="${regVO.status == 1}">
                         <span>Approved</span>
                   </c:when>
                   <c:when test="${regVO.status == 2}">
                         <span>Rejected</span>
                   </c:when>
                   <c:otherwise>
                   		 <span>Pending</span>
                   </c:otherwise>
	            </c:choose>  
		  	</div>
		  	</div>
		</div>
	</div>
	<div class="col-md-8">
		<div style="text-align:center; margin:40px 0px auto;"><h3 class="shadow p-3 mb-1 rounded" style="display:inline-block;">Your Neighbors</h3></div>
		<section id="contact">
			<div id="statusOutput" class="statusOutput" style="display: none;"></div>
				<div id="row" style="display:flex; justify-content:center;"></div>
		</section>

<div style="text-align:center;"><h3 class="shadow p-3 mb-1 rounded" style="display:inline-block;">Cases in your community</h3></div>
<table id="forum" class="table table-hover">
	 <thead style="background-color:#126E7D">
		<tr>		
			<th>Tenant</th>
			<th>Case Title</th>
			<th>Status</th>
			<th>Priority</th>			
			<th>Date</th>
		</tr>
	</thead>
	<c:forEach var="compVO" items="${compSvc.getAllCompByMemNo(memVO.member_no)}">		
		<tbody>
			<tr>
				<td>${memSvc.getOneMem(compVO.member_no).mb_name}</td>

				<td style=" font-size:large;">
					<a class="notJQellipsis" href="<%=request.getContextPath()%>/front-end/comp/displayOneCompForTenate.jsp?complaint_no=${compVO.complaint_no}">${compVO.case_title}</a>
				</td>		
			    <c:choose>
                   <c:when test="${compVO.status==0}">
                         <td>Pending</td>
                   </c:when>
                   <c:when test="${compVO.status==1}">
                         <td>Processing</td>
                   </c:when>
                   <c:when test="${compVO.status==2}">
                         <td>Solved</td>
                   </c:when>
                   <c:otherwise>
                   		 <td>Invalid Status</td>
                   </c:otherwise>
	            </c:choose>  
	            <td>${compVO.priority}</td>
				<td><fmt:formatDate value="${compVO.crt_dt}" pattern="yyyy-MM-dd HH:mm"/></td>

			</tr>
		</tbody>			
	</c:forEach>
</table>
	</div>
</div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
$(function(){
    var len = 50;
    $(".JQellipsis").each(function(i){
        if($(this).text().length>len){
            $(this).attr("title",$(this).text());
            var text=$(this).text().substring(0,len-1)+"<a href=" + $(this).attr('href') + ">......see more...<a>";
            
            $(this).html(text);
        }
    });
});

$("#forum>tbody>tr>td").mouseover(function(){

		$(this).find(".notJQellipsis").css("color", "#126E7D");
});

$("#forum>tbody>tr>td").mouseleave(function(){
	$(this).find(".notJQellipsis").css("color", "black");
});

function loginFirst(){
	Swal.fire('Please Login').then((result)=>{
		window.location.href = "<%=request.getContextPath()%>/front-end/mem/MemLogin.jsp";
	});
}


</script>
	
<script>
	<c:if test="${memVO != null}">
		  var INDEX = 0; 
		  var statusOutput = document.getElementById("statusOutput");
		  var friendNO;
		  
		  var memberImg = "<%=request.getContextPath()%>/MemServlet?action=view_memPic&member_no=${memVO.member_no}";
		  var friendImg = "<%=request.getContextPath()%>/MemServlet?action=view_memPic&member_no=${memVO.member_no}";
		  <%-- <c:forEach var="regVO1" items="${regSvc.getAllRegisterByApName(regVO.ap_name)}">
		  $("#mem_${regVO1.member_no}").click(function() {
			  console.log(event.target);
			  friendNO = event.target.value;
			  console.log(friendNO);
		  });

		  var friendImg_${regVO1.member_no} = "<%=request.getContextPath()%>/MemServlet?action=view_memPic&member_no=${regVO1.member_no}";
		  if (friendNO === "${regVO1.member_no}"){
			  friendImg = friendImg_${regVO1.member_no};
		  }
		  </c:forEach> --%>
		  $("#chat-submit").click(sendMessage);
		  
		  function sendMsg() {
		    var msg = $("#chat-input").val(); 
		    if(msg.trim() == ''){
		      return false;
		    }
		    generate_message(msg, 'member');
		  }
		  function generate_message(msg, type) {
		    INDEX++;
		    let img;

		    type === "member" ? img = memberImg : img = friendImg
			    var str="";

			    str += "<div id='cm-msg-"+INDEX+"' class=\"chat-msg "+type+"\">";
			    str += "          <span class=\"msg-avatar\">";
			    str += "            <img src=\"" + img + "\">";
			    str += "          <\/span>";
			    str += "          <div class=\"cm-msg-text\">";
			    str += msg;
			    str += "          <\/div>";
			    str += "        <\/div>";
			    $(".chat-logs").append(str);
			    $("#cm-msg-"+INDEX).hide().fadeIn(300);
			    $("#chat-input").val(''); 
			    $(".chat-logs").stop().animate({ scrollTop: $(".chat-logs")[0].scrollHeight}, 1000);  

		  }  

		 //WebSocket
        var MyPoint = "/FriendWS/${memVO.member_no}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		var empID;
		var empName;
		var webSocket = null;
		var self = '${memVO.member_no}';
		var statusOutput = document.getElementById("statusOutput");
		var contactName = document.getElementById("contactName");
	
		function connect() {
			// create a websocket
			webSocket = new WebSocket(endPointURL);
	
			webSocket.onopen = function(event) {
				console.log("Connect Success!");
				document.getElementById('chat-input').disabled = false;
			}
	
			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
				if ("open" === jsonObj.type) {
					refreshFriendList(jsonObj);
				} else if ("history" === jsonObj.type) {
					var messages = JSON.parse(jsonObj.message);
					if(messages.length == 0){
						msg = messages;
					}
					for (var i = 0; i < messages.length; i++) {
						var historyData = JSON.parse(messages[i]);
						var msg = historyData.message;
						var mem = historyData.sender;

						let type = '';
						if (mem === self){
							type='member';
						}
						else{
							type='emp';
						}
						generate_message(msg, type)
					}
<%-- 					let fno = statusOutput.textContent;
					  let formData = new FormData();
					  formData.append("member_no", fno);

		              $.ajax({
		                  url: "<%=request.getContextPath()%>/MemServlet?action=view_memPic",
		                  data: formData,
		                  type: "POST",
		                  processData: false, 
		                  contentType: false,
		                  success: function (data) {
		                          $(".friendImage").attr('src', 'data:image/png;base64,' + data);
		                  },
		              }); --%>
					
				} else if ("chat" === jsonObj.type) {
					let msg = jsonObj.message;
					let memberID = jsonObj.sender;
					let type = '';
					if (memberID === self){
						type='member';
					}
					else{
						type='emp';
					}
					generate_message(msg, type)
				} 
			};
	
			webSocket.onclose = function(event) {
				console.log("Disconnected!");
			};
		}
		
		function sendMessage() {
			var inputMessage = document.getElementById("chat-input");
			var memberID = "${memVO.member_no}";
			var memberName = "${memVO.mb_name}";
			var friend = statusOutput.textContent;
			var message = inputMessage.value.trim();
			if (message === "") {
				inputMessage.focus();
				return;
			} else {
				var jsonObj = {
					"type" : "chat",
					"sender" : memberID,
					"receiver" : friend,
					"message" : message,
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}
		
		function disconnect() {
			if (webSocket != null) webSocket.close();
		}
		function refreshFriendList(jsonObj) {
			var friends = jsonObj.users;
			var row = document.getElementById("row");
			row.innerHTML = '';
			let friendsName_back = [];
			let friendsId_back = [];
			<c:forEach var="regVO1" items="${regSvc.getAllRegisterByApName(regVO.ap_name)}">
				if ("${regVO1.member_no}" == self){
					row.innerHTML +='<div class="row mem-block" id=mem_${regVO1.member_no} class="column" name="friendName" style="display:none;"> ' 
					+ '<div style="margin: auto 0;"><img src="<%=request.getContextPath()%>/img/offline2.png" alt="" width="25px" height="25px" ></div>'
					+ '<div><img class="rounded-circle" width="45px" height="40px" src="${pageContext.request.contextPath}/MemServlet?action=view_memPic&member_no=${memVO.member_no}"/></div>'
					+ '<button id=membtn_${regVO1.member_no} style="background:transparent; border:unset;"value="${regVO1.member_no}">${memSvc.getOneMem(regVO1.member_no).mb_name}</button>' 
					'</div>';
					friendsId_back.push("${regVO1.member_no}");
					friendsName_back.push("${memSvc.getOneMem(regVO1.member_no).mb_name}");
				}
				else{
					row.innerHTML +='<div class="row mem-block" id=mem_${regVO1.member_no} class="column" name="friendName" style="display:inline;"> ' 
					+ '<div style="margin: auto 0;"><img src="<%=request.getContextPath()%>/img/offline2.png" alt="" width="15px" height="15px" ></div>'
					+ '<div><img class="rounded-circle" width="45px" height="40px" src="${pageContext.request.contextPath}/MemServlet?action=view_memPic&member_no=${memVO.member_no}"/></div>'
					+ '<button id=membtn_${regVO1.member_no} style="background:transparent; border:unset;"value="${regVO1.member_no}">${memSvc.getOneMem(regVO1.member_no).mb_name}</button>' 
					'</div>';
					friendsId_back.push("${regVO1.member_no}");
					friendsName_back.push("${memSvc.getOneMem(regVO1.member_no).mb_name}");
				}


			
			for (var i = 0; i < friends.length; i++) {
				for (var j = 0 ; j < friendsId_back.length ; j++){
					if(friends[i] == friendsId_back[j]){
						$("#mem_" + friendsId_back[j]).empty();
						var h2 = '<button style="background:transparent; border:unset;"value='+friendsId_back[j]+'>' +  friendsName_back[j] + '</h2>'
						var image = '<div style="margin: auto 0;"><img src="<%=request.getContextPath()%>/img/online2.png" alt="" width="15px" height="15px"></div>';
						var memPic = '<div><img class="rounded-circle" width="45px" height="40px" src="${pageContext.request.contextPath}/MemServlet?action=view_memPic&member_no='+ friendsId_back[j] +'"/></div>';
						$("#mem_" + friendsId_back[j]).append(image);
						$("#mem_" + friendsId_back[j]).append(memPic);
						$("#mem_" + friendsId_back[j]).append(h2);
						
					}
				}
			}

			</c:forEach> 
			
			
			/*addListener start*/
			 <c:forEach var="regVO1" items="${regSvc.getAllRegisterByApName(regVO.ap_name)}">
			  var switcher_${regVO1.member_no} = 1;
			  $("#mem_${regVO1.member_no}").click(function() {
				  if (switcher_${regVO1.member_no} == 1){
				    $("#chat-circle").toggle(500);
				    $(".chat-box").toggle(500);	
				    var friend = event.target.value;
				    var friendName = "${memSvc.getOneMem(regVO1.member_no).mb_name}";
				    contactName.innerHTML = friendName; 
					updateFriendName(friend);
					var jsonObj = {
							"type" : "history",
							"sender" : self,
							"receiver" : friend,
							"message" : ""
						};
					webSocket.send(JSON.stringify(jsonObj));

					switcher_${regVO1.member_no} = 2;

				  }
				  else{
				    $("#chat-circle").toggle(500);
				    $(".chat-box").toggle(500);	
				    $(".chat-logs").empty();
				    switcher_${regVO1.member_no} = 1;
				  }
			  })
			  </c:forEach>	 
			  /*addListener end*/
			  
			  
			  $(".chat-box-toggle").click(function() {
			    $("#chat-circle").toggle(500);
			    $(".chat-box").toggle(500);
				  <c:forEach var="regVO1" items="${regSvc.getAllRegisterByApName(regVO.ap_name)}">
				  switcher_${regVO1.member_no} = 1;
				  </c:forEach>
				  console.log("change")
				  $(".chat-logs").empty();
			  })
			  


		}

		function updateFriendName(name) {
			statusOutput.innerHTML = name;
		}
	</c:if>
	</script>	
	
</html>