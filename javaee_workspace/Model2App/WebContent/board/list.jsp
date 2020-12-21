<%@page import="common.board.Pager"%>
<%@page import="com.model2.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//포워딩을 통해 넘겨받은 request 객체에 담겨진 데이터 꺼내기
	List<Board> boardList = (List)request.getAttribute("boardList");
	//out.print("게시물 수는 " + boardList.size());

	Pager pager = new Pager();
	pager.init(request, boardList);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

a{
	text-decoration: none;
}
</style>
</head>
<body>

	<table>
		<tr>
			<th>No</th>
			<th>title</th>
			<th>writer</th>
			<th>regdate</th>
			<th>hit</th>
		</tr>
		<%
			int num = pager.getNum();
			int curPos = pager.getCurPos();
		%>
		<%for(int i=0; i<pager.getPageSize(); i++){ %>
		<%if(num < 1) break; %>
		<%Board board = boardList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/board/detail.do?board_id=<%=board.getBoard_id()%>"><%=board.getTitle() %></a>[<%=board.getCnt()%>]</td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getContent() %></td>
			<td><%=board.getHit() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5" style="text-align:center">
				<a href="list.do?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
				
				<%for(int i=pager.getFirstPage(); i<pager.getLastPage(); i++){ %>
				<%if(i > pager.getTotalPage()) break; %>
				<a href="list.do?currentPage=<%=i%>">[<%=i %>]</a>
				<%} %>
				
				<a href="list.do?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<button onClick="location.href='regist_form.jsp'">글 등록</button>
			</td>
		</tr>
	</table>

</body>
</html>
