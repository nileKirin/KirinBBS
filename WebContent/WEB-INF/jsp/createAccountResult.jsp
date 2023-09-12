<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class = "createAccountResult">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2>アカウント内容確認画面</h2>
            <div class="baseForm">
	            <dl class ="clearfix">
	                   <dt>ログインId ：</dt>
	                   <dd>${newAccount.userId}</dd>
	               </dl>
	               <dl class ="clearfix">
	                   <dt>パスワード:</dt>
	                   <dd>${newAccount.pass}</dd>
	               </dl>
	               <dl class ="clearfix">
	                   <dt>年齢</dt>
	                   <dd>${newAccount.age}</dd>
	               </dl>
	               <dl class ="clearfix">
	                   <dt>メールアドレス</dt>
	                   <dd>${newAccount.email}</dd>
	               </dl>
	           </div>
            <p><a href="CreateAccountResulServlet" class="btn">この内容でアカウントを作成する</a></p>
        </div>

    </body>
</html>