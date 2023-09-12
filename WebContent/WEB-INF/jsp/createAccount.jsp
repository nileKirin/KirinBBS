<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class = "createAccount">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2>アカウント作成画面</h2>
            <form action="CreateAccountServlet" method="post" class ="baseForm">
                <dl class ="clearfix">
                    <dt>ログインId</dt>
                    <dd>
                    	<input type="text" name = "userId" placeholder="例）ナイル川キリン" value="${accountReqest.userId}">
                    	<p>4文字以上の文字列を入力して下さい</p>
                    </dd>
                </dl>
                <dl class ="clearfix">
                    <dt>パスワード</dt>
                    <dd>
                    	<input type="password" name="pass"placeholder="例）kirin1234" value="${accountReqest.pass}">
                    	<p>6文字以上の半角英数を入力して下さい</p>
                    </dd>
                </dl>
                <dl class ="clearfix">
                    <dt>パスワード<span>(再度入力してください)</span></dt>
                    <dd><input type="password" name="repass"></dd>
                </dl>
                <dl class ="clearfix">
                    <dt>年齢</dt>
                    <dd>
                    	<input type="text" placeholder="例)20" name="age" value ="">
                    	<p>2桁の整数を入力して下さい</p>
                    </dd>
                </dl>
                <dl class ="clearfix">
                    <dt>メールアドレス</dt>
                    <dd><input type="email" placeholder="xxxxx.com" name="email" value="${accountReqest.email}"></dd>
                </dl>
                <dl class ="clearfix">
                    <dt>メールアドレス<span>(再度入力してください)</span></dt>
                    <dd><input type="email" placeholder="もう一度メールアドレスを入力してください" name="remail"></dd>
                </dl>
                <div class="btn">
                    <input type="submit" value="アカウント確認画面へ">
                </div>
            </form>
            <p><a href="WelcomeServlet">トップメニュー画面に戻る</a></p>
            <p>${errorMsg}</p>
        </div>

    </body>
</html>