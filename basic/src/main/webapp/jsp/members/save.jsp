// 서블릿에서 파라미터 가져오는 것과 같은 로직이 먼저 실행이 되고, 마지막에 html일 반환했던 것 처럼
// jsp 에서도 먼저 java코드를 상단에 입력하고 필요한 클래스들을 import 한다.

<%@ page import="mvc.basic.domain.member.MemberRepository" %>
<%@ page import="mvc.basic.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

// jsp도 결국 서블릿으로 변환 되어 사용이 되기 때문에  request, response 그냥 사용 가능 하다.
<%
 MemberRepository memberRepository = MemberRepository.getInstance();
 System.out.println("save.jsp");
 String username = request.getParameter("username");
 int age = Integer.parseInt(request.getParameter("age"));
 Member member = new Member(username, age);
 System.out.println("member = " + member);
 memberRepository.save(member);
%>
<html>
<head>
 <meta charset="UTF-8">
</head>
<body>
  성공
  <ul>
   <li>id=<%=member.getId()%></li>
   <li>username=<%=member.getUsername()%></li>
   <li>age=<%=member.getAge()%></li>
  </ul>
  <a href="/index.html">메인</a>
</body>
</html>