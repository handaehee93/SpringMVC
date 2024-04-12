
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
 <title>Title</title>
</head>
<body>
  <p>WEB-INF 아래 new-form.jsp, 컨트롤러를 통해서만 접근 가능</p>
  <hr>
  <form action="save" method="post">
       username: <input type="text" name="username" />
       age: <input type="text" name="age" />
       <button type="submit">전송</button>
  </form>
</body>
</html>