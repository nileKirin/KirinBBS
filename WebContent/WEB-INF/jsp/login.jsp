<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class = "login">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2>ログイン画面</h2>
            <form action="LoginServlet" method ="post" class ="baseForm">
                <dl class ="clearfix">
                    <dt>ログインId</dt>
                    <dd><input type="text" name = "userId" value ="${login.userId}"  placeholder="例）ニックネームまたはメールアドレスを入力してください"></dd>
                </dl>
                <dl class ="clearfix">
                    <dt>パスワード</dt>
                    <dd><input type="password" name="pass"></dd>
                </dl>
                <div class="btn">
                    <input type="submit" value="ログインする">
                </div>
            </form>
            <p><a href=WelcomeServlet>トップメニュー画面に戻る</a></p>
            <p><a href="LoginHelpServlet">パスワードを忘れてしまった方はこちらへ</a></p>
            <p>${errorMsg}</p>
        </div>
    </body>
</html>