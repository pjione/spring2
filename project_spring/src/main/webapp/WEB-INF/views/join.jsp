<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
        * { box-sizing:border-box; }

        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }

        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        label {
            width:300px;
            height:30px;
            margin-top :4px;
        }

        #submit {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }

        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }

        .msg{
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        .sns-chk {
            margin-top : 5px;
        }
    </style>
    <title>Register</title>
</head>
<body>
<form action="<c:url value="/join"/>" method="post" onsubmit="return formCheck(this)">
    <div class="title">Register</div>
    <div id="msg" class = "msg">
        <c:if test="${not empty msg}">
            <i class="fa fa-exclamation-circle"> ${msg}</i>
        </c:if>
        <c:if test="${not empty error}">
            <p>${error}</p>
        </c:if>
    </div>
    <label for="">아이디</label>
    <input class="input-field" type="text" name="id" placeholder="영소문자로 시작하는 5~10자 조합">
    <label for="">비밀번호</label>
    <input class="input-field" type="password" name="pwd" placeholder="4~15자리로 해주세요.">
    <label for="">비밀번호 확인</label>
    <input class="input-field" type="password" name="pwdChk" placeholder="4~15자리로 해주세요.">
    <label for="">이름</label>
    <input class="input-field" type="text" name="name" placeholder="홍길동">
    <label for="">이메일</label>
    <input class="input-field" type="text" name="email" placeholder="example@spring.co.kr">
    <label for="">생일</label>
    <input class="input-field" type="date" name="birth">
    <div class="sns-chk">
        <label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
        <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
        <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
    </div>
    <input type = "submit" id = "submit" value="회원가입">
</form>
<script>

    function formCheck(frm) {
        document.getElementById("msg").innerHTML = '';
        let msg ='';
        var idReg = /^[a-z0-9]{5,10}$/g;
        if(!idReg.test(frm.id.value)) {
            setMessage('id는 영소문자로 시작하는 5~10자로 해주세요.(특수문자 불가)', frm.id);
            return false;
        } else if(frm.pwd.value != frm.pwdChk.value){
            setMessage('비밀번호 불일치', frm.pwdChk);
            return false;
        } else if(frm.pwd.value.length<4 || frm.pwd.value.length>15){
            setMessage('pwd의 길이는 4~15자리로 해주세요.', frm.pwd);
            return false;
        } else if(frm.name.value.trim() == ''){
            setMessage('이름을 입력해주세요.', frm.name);
            return false;
        } else{
            return true;
        }
    }

    function setMessage(msg, element){
        document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;

        if(element) {
            element.select();
        }
    }
</script>
</body>
</html>