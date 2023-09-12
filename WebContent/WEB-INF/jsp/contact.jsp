<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class ="contact">
        <div class ="wrapper">
            <h1>キリン掲示板</h1>
            <h2>お問い合わせ画面</h2>
            <form action="ContactServlet" method ="post"  class ="baseForm" >
                <dl class ="clearfix">
                    <dt>お名前</dt>
                    <dd><input type="text" name = "name" placeholder="例）山田 太郎"></dd>
                </dl>
                <dl class ="clearfix">
                    <dt>メールアドレス</dt>
                    <dd><input type="email" placeholder="xxxxx.com" name="email"></dd>
                </dl>
                <dl class ="clearfix">
                    <dt>メールアドレス<span>(再度入力してください)</span></dt>
                    <dd><input type="email" placeholder="もう一度メールアドレスを入力してください" name="remail"></dd>
                </dl>
                <dl class ="clearfix">
                    <dt>お問い合わせ種類</dt>
                    <dd>
                        <select name = "contents">
                            <option value="アカウントに関して" >アカウントに関して</option>
                            <option value="この掲示板に関して">この掲示板に関して</option>
                            <option value="管理人に関して">管理人に関して</option>
                            <option value="特に意味はないけど送信したくなった人用">特に意味はないけど送信して見たくなった人用</option>
                        </select>
                    </dd>
                </dl>
                <dl class ="clearfix">
                    <dt>お問い合わせ内容</dt>
                    <dd><textarea name="textarea" rows="5"></textarea></dd>
                </dl>
                <div class="btn">
                    <input type="submit" value="送信">
                </div>
            </form>
            <p>${errorMsg}</p>
            <p><a href="WelcomeServlet">トップメニュー画面に戻る</a></p>
        </div>
    </body>
</html>