<!--  jsp는 아래의 코드가 반드시 필요하다.-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
 <title>Title</title>
</head>
<body>
  <form action="/jsp/members/save.jsp" method="post">
       username: <input type="text" name="username" />
       age: <input type="text" name="age" />
       <button type="submit">전송</button>
  </form>
</body>
</html>