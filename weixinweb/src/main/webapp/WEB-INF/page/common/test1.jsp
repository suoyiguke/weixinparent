<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath }/addUser.action"
      enctype="multipart/form-data">
    schoolId<input type="text" name="schoolId"/>
    nickname <input type="text" name="nickname"/>
    sex <input type="text" name="sex"/>
    autograph <input type="text" name="autograph"/>
    sessionId<input type="text" name="sessionId"/>

    <input type="file"  multiple="multiple" name="uploadFile"/>  
    <input type="submit"/>    

</form>

</body>
</html>
