<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"/>
    <body class = "createAccountResult">
        <div class="wrapper">
            <h1>キリン掲示板</h1>
            <h2>問い合わせ内容画面</h2>
            <div class="baseForm">
	            <dl class ="clearfix">
	                   <dt>お名前 ： </dt>
	                   <dd>${contactUser.name}</dd>
	               </dl>
	               <dl class ="clearfix">
	                   <dt>メールアドレス : </dt>
	                   <dd>${contactUser.email}</dd>
	               </dl>
	               <dl class ="clearfix">
	                   <dt>問い合わせ種類 : </dt>
	                   <dd>${contactUser.contents}</dd>
	               </dl>
	               <dl class ="clearfix">
	                   <dt>お問い合わせ内容 : </dt>
	                   <dd>${contactUser.textArea}</dd>
	               </dl>
	           </div>
	           <p>この内容で管理人にメールを送信いたしました。</p>
            <p><a href="WelcomeServlet" class="btn">トップメニューに戻る</a></p>
        </div>

    </body>
</html>