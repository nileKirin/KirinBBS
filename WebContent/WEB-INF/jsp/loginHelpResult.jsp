<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class = "loginResult">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2>ログインヘルプ画面</h2>
            <p><c:out value ="${account.email}"/>宛てにログイン情報を入れたメールを送信いたしました！</p>
            <p><a href="WelcomeServlet" class="btn">トップメニューに戻る</a></p>
        </div>
    </body>
</html>