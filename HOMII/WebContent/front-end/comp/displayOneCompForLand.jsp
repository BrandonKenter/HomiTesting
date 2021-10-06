<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.comp.model.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	if(memVO == null){
		memVO = new MemVO();
		memVO.setMember_no(99);
	}
	pageContext.setAttribute("memVO", memVO);
	String test = request.getParameter("complaint_no");
	Integer complaint_no = new Integer(test.trim());
	CompService compSvc = new CompService();
	CompVO compVO = compSvc.getOneComplaint(complaint_no);
	pageContext.setAttribute("compVO", compVO);
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" />

<title>Insert title here</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
/*Contact sectiom*/

body {
    
  background-image: url("<%=request.getContextPath()%>/img/complaint form.jpg");
  background-size:100%;
  background-attachment:fixed;
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
    
  font-family: 'Teko', sans-serif;
  padding-top: 30px;
  width: 100%;
  width: 100vw;
  height: 47vw;

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
  margin-top: 10px;
}
label{
  font-size: 1em;
  line-height: 1em;
  font-weight: bolder;
}
.form-control{
  font-size: 1.3em;
  color: #080808;
}
textarea.form-control {
    height: 135px;
   /* margin-top: px;*/
}

.submit{
  font-size: 1.1em;
  width: 150px;
  background-color: transparent;
  color: #fff;

}

.form-group label{
	display: block;
	float: left;
	overflow: hidden;
	width: 160px;
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
    width: 100%
}

.askPubType{
	text-align:center;
	margin-top:60px;
}
img {
	max-width:300px;
	myimg:expression(onload=function(){
	this.style.width=(this.offsetWidth > 300)?"300px":"auto"});
}

.restype{
	backdrop-filter: blur(5px);
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
<div class="errorMsgs">
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">There some errors, please check it:</font>
		<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
		</ul>
	</c:if>
</div>

<div class="successMsgs">
	<c:if test="${not empty successMsgs}">
		<ul>
		<c:forEach var="message" items="${successMsgs}">
			<li style="color:green">${message}</li>
		</c:forEach>
		</ul>
	</c:if>
</div>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/comp/comp.do" name="form2" enctype="multipart/form-data" data-toggle="validator">  
	<section id="contact">
			<div class="section-content">
				<h1 class="section-header">Complaint case</h1>
			</div>
			<div class="contact-section">
			<div class="container">
				<div class="row">
				<form>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
					    	<label for="username">Tenant Name</label>
					    	<span><input type="text" class="form-control" value="<%=memVO.getMb_name()%>" id="username"  disabled="disabled"></span>
			  			</div>
<%-- 			  			<div class="form-group">
			  				<label for="apName">Apartment Name</label>
					    	<span><input type="text" class="form-control" name="apName" value="<%=compVO.getAp_name()%>" id="apName" disabled="disabled"></span>
				  		</div> --%>
				  		<div class="form-group">
					    	<label for="apAddress">Apartment Address</label>
					    	<span><input type="text" class="form-control" name="apAddress" value="<%=compVO.getAp_address()%>" id="apAddress" disabled="disabled"></span>
					  	</div>
			  			<div class="form-group">
			  				<label for="title">Case title</label>
					    	<span><input type="text" class="form-control" name="caseTitle" value="<%=compVO.getCase_title()%>" id="title" disabled="disabled"></span>
				  		</div>
			  			<div class="form-group">
			  				<label for ="description"> Description</label>
			  			 	<span><textarea  class="form-control" name="description"  id="description" disabled="disabled">${compVO.description}</textarea></span>
			  			</div>
			 
			  		</div>
			  		<div class="col-md-6">
			  			<div class="form-group photodiv">
			  				<label for="photo">Photo</label>
					    	<span><img src="${pageContext.request.contextPath}/comp/comp.do?action=view_comPic&complaint_no=${compVO.complaint_no}" alt=""></span>
				  		</div>
			  			<div class="form-group">
			  				<label for="video">Video</label>
					    	<span></span>
				  		</div>
					</div>
					</div>
					<br>
					<hr style="height:2px;border-width:0;color:gray;background-color:gray">
					
			  		<div class="row">
			  			<div class="col-md-6 restype">
				  			<div class="form-group">
				  				<label for ="response"> Response Type</label>
								<input type="radio" name="restype" value="0" ${(compVO.status == "0") ? "checked" : "" }> Pending 
								<input type="radio" name="restype" value="1" ${(compVO.status == "1") ? "checked" : "" }> Processing 
								<input type="radio" name="restype" value="2" ${(compVO.status == "2") ? "checked" : "" }> Solved
							</div>
			  			</div>
			  			<div class="col-md-6">
			  				<div class="form-group">
			  					<label for ="response"> Response</label>
			  			 		<span><textarea  class="form-control" name="response" id="response" placeholder="Enter your response ">${compVO.response}</textarea></span>
			  				</div>
			  			</div>
			  			<div align="center">
			  				<input type="hidden" name="complaint_no" value="${compVO.complaint_no}">
				  			<input type="hidden" name="action" value="response">
				  			<input type="submit" class="btn btn-primary submit" style="width:190px;" value="Send your response">
			  			</div>
			  		</div>
				</form>
				</div>
			</div>
			</div>
		</section>
</FORM>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
for (var i = 0; i < images.length; i++) { var image = images[i], width = String(image.currentStyle.width); if (width.indexOf('%') == -1) { continue; } image.origWidth = image.offsetWidth; image.origHeight = image.offsetHeight; imgCache.push(image); c.ieAlpha(image); image.style.width = width; }
</script>
</body>
</html>