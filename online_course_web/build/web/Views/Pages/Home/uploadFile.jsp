<%-- 
    Document   : uploadFile
    Created on : Jan 10, 2021, 11:51:07 PM
    Author     : A556U
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <div>
		<h1>Upload file in servlet</h1>
		<form action="<%=request.getContextPath()%>/uploadFile" method="post" enctype="multipart/form-data" >
			<label>Upload file : </label>
			<input type="file" value="Upload file" name="hinhanh"  /> <br />
			<input type="submit" value="Thực hiện" name="thuchien"  />
                        <input type="hidden"  name ="CSRFToken" value="<c:out value='${CSRFToken}'/>">
		</form>
	</div>
    </body>
</html>
