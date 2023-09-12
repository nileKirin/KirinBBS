<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class = "loginHelp">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2>ログインヘルプ画面</h2>
            <div class="text">
                <p>メールにてログインID、パスワードに関する<br>お客様情報を送信いたします</p>
                <p>登録したメールアドレスを入力してください</p>
            </div>
            <form action="LoginHelpServlet" class ="baseForm" method ="post">
                <dl class ="clearfix">
                    <dt>メールアドレス</dt>
                    <dd><input type="email" name = "email"></dd>
                </dl>
                <div class="btn">
                    <input type="submit" value="送信する">
                </div>
            </form>
            <p>${errorMsg}</p>
            <p><a href="WelcomeServlet">トップメニュー画面に戻る</a></p>
        </div>
    </body>
</html>