<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class="main">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2>掲示板一覧画面</h2>
            <h3><c:out value = "${account.userId}"/>さんようこそ</h3>

               <ul class = "event clearfix">
                <li><a href="MainServlet">更新</a></li>
                <li><a href="WelcomeServlet">トップメニュー</a></li>
                <li><a href="LogoutServlet">ログアウト</a></li>
               </ul>
            <div class="mainForm">
        	<%-- --  (投稿用のform)    --%>
                <form action="MainServlet" method="post" >
                    <textarea name=content rows="5"></textarea>
                    <button>投稿</button>
                    <p>${errorMsg}</p>
                </form>
                <%--  /(投稿用のform) --%>
            </div>
            <div class="post-list">
	            <c:forEach var ="mutter" items ="${mutterList}">
		            <dl class="post">
		            	<dt>投稿者 : ${mutter.userId}</dt>
		                <dd class = "clearfix">
		                    <time>登校時間 : ${mutter.time}</time>
		                    <p class = "content"><span style ="font-weight:bold">投稿内容 </span><br>${mutter.content}</p>
		                    <c:if test="${account.userId.equals(mutter.userId)}"><p class = "delete"><a href ="MainServlet?id=<c:out value = "${mutter.id}"/>">削除</a></p></c:if>
		                </dd>
		            </dl>
	            </c:forEach>
            </div>
			<jsp:include page="/WEB-INF/jsp/include/navi.jsp"/>
        </div>
    </body>
</html>