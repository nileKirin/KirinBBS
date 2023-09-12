<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<html lang="ja">
    <body class="welcome">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2>メニュー画面</h2>
            <p>この掲示板はJSP勉強用にキリンが作成した掲示板です</p>
            <ul class="clearfix">
                <li><a href="LoginServlet"> ログイン画面へ</a></li>
                <li><a href="CreateAccountServlet">ユーザー登録</a></li>
                <li><a href="ContactServlet">お問い合わせ</a></li>
            </ul>
        </div>
    </body>
</html>