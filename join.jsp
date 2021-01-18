<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<body>
   <h1>회원 가입 양식</h1>
     <form action="${context}/member/joinimpl" method="post" id="frm_join">
        <table>
           <tr>
              <td>ID : </td>
              <td>
                   <input type="text" name="id" size="10" required/>
                 <button type="button" onclick="idCheck()">아이디 확인하기</button><br>
                 <span id="confirm"></span>
              </td>
           </tr>
           <tr>
              <td>PASSWORD : </td>
              <td>
                   <input type="password" name="pw" id="pw" required/>
                   <span id="pw_confirm"></span>
              </td>
           </tr>
           <tr>
              <td>휴대폰번호 : </td>
              <td>
                   <input type="tel" name="tell" required/>
              </td>
           </tr>
           <tr>
              <td>email : </td>
              <td>
                   <input type="email" name="email" required/>
              </td>
           </tr>
           <tr>
              <td>
                 <input type="submit" value="가입" />
                 <input type="reset" value="취소" />
              </td>
          </tr>
      </table>
   </form>
   <script type="text/javascript">
   document.querySelector('#frm_join').addEventListener('submit',(e) => {
      /* 요소의 아이디로 엘리먼트 객체 호출 가능(웹표준이 아님)  */   
      let password = pw.value;
      
      let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;
     
      if(!(regExp.test(password))){
         //form의 데이터 전송을 막음
         e.preventDefault();
         pw_confirm.innerHTML = '비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상이어야 합니다.';
         pw_confirm.style.color = 'red';
         pw_confirm.style.fontSize = '0.5vw';
         pw.value = '';
         pw.focus();
      }
   });
   
   

   </script>
   
</body>
</html>