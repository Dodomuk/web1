<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<head>
<style type="text/css">
	.valid_info{
		display:block;
		color:red;
</style>
</head>
<body>
	ID : <input type="text" name="id" id="id"><br>
	PW : <input type="password" name="pw" id="pw">
	<span class="valid_info"></span>
	<button onclick="login()">로그인</button>

	<script type="text/javascript">
		let login = () => {
			const url = '/member/loginimpl';
			let paramObj = new Object();
			paramObj.id = id.value;
			paramObj.pw = pw.value;
			
			let headerObj = new Headers();
			headerObj.append("content-type","application/x-www-form-urlencoded");
			fetch(url,{ //fetch : 서버에서 원하는 정보를 가져오는 것. 
				method:"post",
				headers:headerObj,
				//body:urlEncodeForm(paramObj)
				body:"data="+ JSON.stringify(paramObj)
			})
			.then(response => {
				//response.ok : 상태코드 200~299사이라면 ok = true				
				if(response.ok){
					return response.text();	
				}
				//200번대 코드가 아니라면 에러를 발생시켜서 catch블록으로 이동
				throw new AsyncPageError(response.text());
			}).then((text) => {
				if(text == 'fail'){
					document.querySelector('.valid_info').innerHTML 
					= '아이디나 비밀번호를 확인하세요';
				}else{
					<%-- 로그인에 성공하면 index페이지로 브라우저가 재요청 --%>
					location.href="/index/index";
				}
			}).catch(error => {
				error.alertMessage();
			});
		}
		
		
		
	</script>
	
	
	
	
	
	
	
	
	
	
</body>
</html>



