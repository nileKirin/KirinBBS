<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class = "loginResult">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2>ログインに成功しました！</h2>
            <p>${account.userId}さん ようこそ！</p>
            <p><a href="MainServlet" class="btn">メイン画面に行く</a></p>
        </div>
    </body>
</html>