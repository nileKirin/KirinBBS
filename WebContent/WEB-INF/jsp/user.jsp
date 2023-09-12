<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class="main">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2><c:out value = "${account.userId}"/>Myページ</h2>
            <p>あなたの投稿一覧です</p>
            <div class="post-list">
	            <c:forEach var ="mutter" items ="${userMutterList}">
		            <dl class="post">
		            	<dt>投稿者 : ${mutter.userId}</dt>
		                <dd>
		                    <time>投稿時間 : ${mutter.time}</time>
		                    <p>投稿内容 : ${mutter.content}</p>
		                </dd>
		            </dl>
	            </c:forEach>
            </div>
			<jsp:include page="/WEB-INF/jsp/include/navi.jsp"/>
        </div>
    </body>
</html>