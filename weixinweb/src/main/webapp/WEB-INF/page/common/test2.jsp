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

<form method="post" action="${pageContext.request.contextPath }/addTopic.action"
      enctype="multipart/form-data">
    forumId<input type="text" name="forumId"/>
    title<input type="text" name="title"/>
    content<input type="text" name="content"/>
    sessionId<input type="text" name="sessionId"/>

    <input type="file"  multiple="multiple" name="file"/>  
    <input type="file"  multiple="multiple" name="file"/>
    <input type="submit"/>    

</form>
</body>
</html>
