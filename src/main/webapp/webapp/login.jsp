<%
    /**
     * 通过java 和 js 都能获取 cookie来操作
     */
    String name = "";
    String password = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                //把cookie对应值付给account
                name = cookie.getValue();
            } else if (cookie.getName().equals("pwd")) {
                //把cookie对应值付给pwd
                password = cookie.getValue();
            }
        }
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <jsp:include page="/resources/v2/webui.jsp">
        <jsp:param name="UIS" value="FORM_DGRID_LAYER_BTN_LAYOUT_PAGE"/>
    </jsp:include>
<%--    <script type="text/javascript" src="../../resources/static/lib/jquery.min.js"></script>--%>
<%--    <script type="text/javascript" src="/resources/static/plugg/DatePicker/WdatePicker.js"></script>--%>
<%--    <link rel="stylesheet" href="/resources/static/tdh/btn/css/btn.css"/>--%>
<%--    <script type="text/javascript" src="/resources/static/tdh/btn/js/tdh.btn.js"></script>--%>
<%--    <link rel="stylesheet" href="/resources/static/tdh/form/css/form.css"/>--%>
<%--    <script type="text/javascript" src="/resources/static/tdh/form/js/tdh.form.js"></script>--%>
<%--    <script type="text/javascript" src="/resources/static/tdh/form/js/tdh.form.placeholder.js"></script>--%>
<%--    <link rel="stylesheet" href="/resources/static/tdh/btn/css/btn.css" />--%>
<%--    <script type="text/javascript" src="/resources/static/lib/jquery.min.js" ></script>--%>
<%--    <script type="text/javascript" src="/resources/static/tdh/btn/js/tdh.btn.js" ></script>--%>
    <style>
        html {
            width: 100%;
            height: 100%;
            overflow: auto;
            overflow-x: hidden;
        }
    </style>
</head>
<body background="../img/background.jpg"
      style="background-repeat:no-repeat;background-size:100% 100%;background-attachment: fixed;">
<div style="font-size: 50px;" class="hz">用户登录</div>
<%--<div class="dcenter">--%>
<%--    <div style=" padding: 10px 10px 0px 40px;">--%>
<%--        <div>--%>
<%--            账号<input id="zh" name="zh"/> <input type="checkbox" id="jzzh"/>记住账号--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            密码<input id="pwd" name="pwd" type="password"/> <input type="checkbox" id="jzmm"/>记住密码--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div align="center" style="padding: 20px;">--%>
<%--        <button id="login" onclick="login()"> 登录</button>&nbsp;&nbsp;&nbsp;&nbsp;<button id="reset"--%>
<%--                                                                                         onclick="resetCilck()"> 重置--%>
<%--    </button>--%>
<%--    </div>--%>
<%--</div>--%>
<div>
    <table class="tdh_form dd">
        <tr>
            <td class="tdTitle"><i class="required">*</i>用户账号</td>
            <td class="tdCont" colspan="10"><input class="inputText" type="text" placeholder="请输入" value="" id="zh"
                                                   name="zh"/></td>
            <td class="tdCont" colspan="5">
                <label><input name="fxk1" class="inputCheck" type="checkbox" id="jzzh"/>记住账号</label>
            </td>
        </tr>
        <tr>
            <td class="tdTitle"><i class="required">*</i>用户密码</td>
            <td class="tdCont" colspan="10"><input class="inputText" type="password" placeholder="请输入" value="" id="pwd"
                                                   name="pwd"/></td>
            <td class="tdCont" colspan="5">
                <label><input name="fxk1" class="inputCheck" type="checkbox" id="jzmm" />记住密码</label>
            </td>
        </tr>
        <tr>
            <td colspan="2"></td>
            <td colspan="5">
                <a class="tdh_btn tdh_btn_blue"  id="login" onclick="login()">登录</a>
            </td>
            <td colspan="10">
                <a class="tdh_btn tdh_btn_yellow" id="reset" onclick="resetCilck()">重置</a>
            </td>
        </tr>
    </table>
</div>

</body>
<script src="../js/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("#zh").val("<%=name%>")
        $("#pwd").val("<%=password%>")
        var name = "<%=name%>";
        var password = "<%=password%>";
        console.log(name)
        console.log(password)
        if (name != "" || password != "") {
            //这里需要注意 无法设置 后期设置
            $("#jzzh").prop("checked", true)
            $("#jzmm").prop("checked", true)
        }
    })

    function resetCilck() {
        $("#zh").val("");
        $("#pwd").val("");
    }

    function login() {
        if ($("#jzzh").prop("checked")) {
            document.cookie = "account=" + $("#zh").val();
        }
        if ($("#jzmm").prop("checked")) {
            document.cookie = "pwd=" + $("#pwd").val();
        }
        if ($("#zh").val() == "" || $("#pwd").val() == "") {
            alert("账号或密码未填")
        } else {
            window.location.href = "loginservelt?zh=" + $("#zh").val() + "&pwd=" + $("#pwd").val();
        }
    }
</script>
<style>
    .dcenter {
        width: 400px;
        height: 100px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        border: 3px solid #000;
    }

    .hz {
        position: absolute;
        top: 40%;
        left: 50%;
        transform: translate(-50%, -50%);
        font-family: "Times New Roman", Times, serif;
    }

    .dd {
        width: 600px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        border: 1px solid #000;
        box-shadow: 10px 10px 10px rgba(0, 0, 0, .5);
        /*考虑浏览器兼容性*/
        -moz-box-shadow: 10px 10px 10px rgba(0, 0, 0, .5);
        -webkit-box-shadow: 10px 10px 10px rgba(0, 0, 0, .5);
    }
</style>
</html>
