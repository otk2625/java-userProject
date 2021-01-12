<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<input type="hidden" name="cmd" value="search" /> 

<div class="container">
  <h2>유저 리스트</h2>
	<br>          
  <table class="table">
  
    <thead>
      <tr>
        <th>Username</th>
        <th>Email</th>
        <th>role</th>
        <th>Delete</th>
      </tr>
    </thead>
    
    <tbody>
     
       <c:forEach items="${users}" var="user" varStatus="status">
		 <tr>
        <td>${user.username }</td>
        <td>${user.email }</td>
         <td>${user.role }</td>
        <td>
      
         
         <c:choose>
         	<c:when test="${sessionScope.principal != null and sessionScope.principal.id == user.id  and user.role == 'user' }">
         	<i class="material-icons" onclick="deleteById(${user.id})" style="cursor: pointer">delete</i>
         	</c:when>
         	
         	<c:when test="${sessionScope.principal != null  and sessionScope.principal.role == 'admin' and 
         	sessionScope.principal.id != user.id }">
         		<i class="material-icons" onclick="deleteByAdmin(${user.id})" style="cursor: pointer">delete</i>
         	</c:when>
         	
         	<c:otherwise>
         		
         	</c:otherwise>
         	
         </c:choose>
         
         </td>
      </tr>
	</c:forEach>
     
    </tbody>
  </table>
</div>

<script>

	function deleteById(userid){
		
		$.ajax({
			type: "post",
			url: "/testProject/user?cmd=delete&id="+userid,
			dataType: "json"
		}).done(function(result){
			console.log(result);
			if(result == 'ok'){
				alert("삭제 완료");
				location.href="/testProject/user?cmd=logout";
			}else{
				alert("삭제에 실패하였습니다.");
			}
		});
		
	}
	
function deleteByAdmin(userid){
		
		$.ajax({
			type: "post",
			url: "/testProject/user?cmd=delete&id="+userid,
			dataType: "json"
		}).done(function(result){
			console.log(result);
			if(result == 'ok'){
				alert("삭제 완료");
				location.reload();
			}else{
				alert("삭제에 실패하였습니다.");
			}
		});
		
	}
	
</script>
</body>
</html>