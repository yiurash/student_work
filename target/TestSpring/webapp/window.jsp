<%--
  Created by IntelliJ IDEA.
  User: W
  Date: 2022/1/5
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="static com.exam.mapper.CodeToSome.MAP_BM" %>
<%@ page import="static com.exam.service.SelectUserService.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<%
    List<String> useridlist = selectUserId();
    String label = (String) request.getAttribute("label");
    if (label == null) {
        label = "";
    }
%>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/dcalendar.picker.js"></script>
    <link rel="stylesheet" href="../css/dcalendar.picker.css">
    <script type="text/javascript" src="/resources/static/plugg/DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="/resources/static/tdh/form/css/form.css"/>
    <script type="text/javascript" src="/resources/static/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/static/tdh/form/js/tdh.form.js"></script>
    <script type="text/javascript" src="/resources/static/tdh/layout/js/tdh.divresize.js"></script>
    <jsp:include page="">
        <jsp:param name="UIS" value="FORM_DGRID_LAYER_BTN_LAYOUT_PAGE"/>
    </jsp:include>
</head>
<body>
<table class="tdh_form">
    <colgroup>
        <col width="11%"/>
        <col width="22%"/>
    </colgroup>
    <tr>
        <td class="tdTitle" id="yhid"><i class="required">*</i>用户ID</td>
        <td class="tdCont"><input class="inputText" type="text" id="yhidk" class="k"
                                  name="yhid" placeholder="ID必填"/></td>
        <td class="tdTitle" id="yhxm"><i class="required">*</i>用户姓名</td>
        <td class="tdCont"><input class="inputText" type="text" id="yhxmk" class="k"
                                  name="yhxm"
                                  placeholder="姓名必填"/></td>
    </tr>
    <tr>
        <td class="tdTitle" id="yhkl"><i class="required">*</i>用户口令</td>
        <td class="tdCont"><input class="inputText" type="text" id="yhklk"
                                  name="yhkl"
                                  class="k" placeholder="密码必填"/></td>
        <td class="tdTitle" id="cfkl"><i class="required">*</i>重复口令</td>
        <td class="tdCont"><input class="inputText" type="text" id="cfklk"
                                  name="cfkl"
                                  class="k" placeholder="再次输入密码"/></td>
    </tr>
    <tr>
        <td class="tdTitle" id="yhbm">用户部门</td>
        <td class="tdCont" id="yhbmk" class="k" name="yhbm">
            <select class="inputSel">
                <option value="" selected="selected" style="text-align: center;"></option>
                <%
                    for (String bm : MAP_BM.values()
                    ) {
                %>
                <option value="<%=bm%>"><%=bm%>
                </option>
                <%
                    }
                %>
            </select>
        </td>
        <td class="tdTitle" id="yhxb">用户性别</td>
        <td class="tdCont">
            <select class="inputSel">
                <option value=""></option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </td>
    </tr>
    <tr>
        <td class="tdTitle" >出生日期</td>
        <td class="tdCont"><input class="Wdate inputText" type="text" onClick="WdatePicker()" readonly="" name="csrq"/></td>
        <td class="tdTitle"><i class="required">*</i>排序号</td>
        <td class="tdCont"><input class="inputText" type="text" id="pxhk" name="pxh"/></td>
    </tr>
    <tr>
        <td class="tdTitle" >是否禁用</td>
        <td class="tdCont" colspan="5">
            <label><input name="fxk1" class="inputCheck" type="checkbox" checked="false" id="choose"/></label>
        </td>
    </tr>
    <tr>
        <td colspan="4" align="center">
            <button id="save" type="submit" onclick="save()">保存</button>
            <button id="cancel" onclick="cancel()">返回</button>
        </td>
    </tr>
</table>


<table cellspacing="0" border="1px" cellpadding="10px" align="center">
<%--    <colgroup>--%>
<%--        <col width="150"/>--%>
<%--        <col width="150"/>--%>
<%--        <col width="150"/>--%>
<%--        <col width="150"/>--%>
<%--    </colgroup>--%>
<%--    <tr>--%>
<%--        <td colspan="4" align="center">用户信息</td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <div>--%>
<%--            <td align="right" id="yhid">--%>
<%--                <span style="color: #FF0000;">*</span>用户ID--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <input type="text" style="width: 140px; height: 30px;float: left;  resize: none;" id="yhidk" class="k"--%>
<%--                       name="yhid"--%>
<%--                       placeholder="姓名必填"/>--%>
<%--            </td>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <td align="right" id="yhxm">--%>
<%--                <span style="color: #FF0000;">*</span>用户姓名--%>
<%--            </td>--%>
<%--            <td align="left">--%>
<%--                <input type="text" style="width: 140px; height: 30px;float: left;  resize: none;" id="yhxmk" class="k"--%>
<%--                       name="yhxm"--%>
<%--                       placeholder="姓名必填"/>--%>
<%--            </td>--%>
<%--        </div>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <div>--%>
<%--            <td align="right" id="yhkl">--%>
<%--                <span style="color: #FF0000;">*</span>用户口令--%>
<%--            </td>--%>
<%--            <td align="left">--%>
<%--                <input type="password" style="width: 140px; height: 30px;float: left;  resize: none;" id="yhklk"--%>
<%--                       name="yhkl"--%>
<%--                       class="k" placeholder="密码必填"/>--%>
<%--            </td>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <td align="right" id="cfkl">--%>
<%--                <span style="color: #FF0000;">*</span>重复口令--%>
<%--            </td>--%>
<%--            <td align="left">--%>
<%--                <input type="password" style="width: 140px; height: 30px;float: left;  resize: none;" id="cfklk"--%>
<%--                       name="cfkl"--%>
<%--                       class="k" placeholder="再次输入密码"/>--%>
<%--            </td>--%>
<%--        </div>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <div>--%>
<%--            <td align="right" id="yhbm">--%>
<%--                <span style="color: #FF0000;">*</span>用户部门--%>
<%--            </td>--%>
<%--            <td align="left">--%>
<%--                <select style="width: 100px; " id="yhbmk" class="k" name="yhbm">--%>
<%--                    <option value="" selected="selected" style="text-align: center;"></option>--%>
<%--                    <%--%>
<%--                        for (String bm : MAP_BM.values()--%>
<%--                        ) {--%>
<%--                    %>--%>
<%--                    <option value="<%=bm%>"><%=bm%>--%>
<%--                    </option>--%>
<%--                    <%--%>
<%--                        }--%>
<%--                    %>--%>
<%--                </select>--%>
<%--            </td>--%>
<%--        </div>--%>
<%--        <td align="right">用户性别</td>--%>
<%--        <td align="left">--%>
<%--            <select style="width: 100px; " id="yhxb" name="yhxb">--%>
<%--                <option value="" selected="selected" style="text-align: center;"></option>--%>
<%--                <option value="男">男</option>--%>
<%--                <option value="女">女</option>--%>
<%--            </select></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td align="right">出生日期</td>--%>
<%--        <td align="left">--%>
<%--            <input id='mydatepicker' type='text' disabled="true" name="csrq"/>--%>
<%--        </td>--%>

<%--        <td align="right" id="pxh">排序号</td>--%>
<%--        <td align="left">--%>
<%--            <input type="text" style="width: 140px; height: 30px;float: left;  resize: none;" id="pxhk" name="pxh"/>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td align="right">是否禁用</td>--%>
<%--        <td align="left" colspan="4">--%>
<%--            <input name="sfjy" type="checkbox" checked="false" id="choose"/>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td colspan="4" align="center">--%>
<%--            <button id="save" type="submit" onclick="save()">保存</button>--%>
<%--            <button id="cancel" onclick="cancel()">返回</button>--%>
<%--        </td>--%>
<%--    </tr>--%>
</table>
</body>
<script>
    var TEXT = "请检查 ：";
    var Message = "";

    /**
     * 保存是对应的文字提示和如果没写则相应红色提示
     */
    function save() {
        $(".k").each(function () {
            var Str = $(this).attr("id")
            if ($(this).val().trim() == "") {
                $('#' + Str.slice(0, Str.length - 1)).css("background-color", "red");
                TEXT += $('#' + Str.slice(0, Str.length - 1)).text();
            } else {
                $('#' + Str.slice(0, Str.length - 1)).css("background-color", "white");
                Message += $(this).attr("id") + "=" + encodeURIComponent($(this).val()).trim();
            }
        })
        if ($("#yhklk").val() == $("#cfklk").val() && $("#yhklk").val() != "" && $("#cfklk").val() != "") {
            $("td#yhkl").css("background-color", "white");
            $("td#cfkl").css("background-color", "white");
        } else {
            TEXT += "  *口令不一致 ";
            $("td#yhkl").css("background-color", "red");
            $("td#cfkl").css("background-color", "red");
        }
        if (check_validate1($("#pxhk").val()) && $("#pxhk").val() != "") {
            TEXT += "排序号输入错误 ";
            $("td#pxh").css("background-color", "red");
        } else {
            $("td#pxh").css("background-color", "white");
        }
        if ($("#yhidk").val().indexOf("admin") != -1) {
            TEXT += "用户ID不能包含admin";
        }
        if (type == "IN" && "<%=useridlist%>".indexOf($("#yhidk").val()) != -1) {
            TEXT += "用户id已经存在";
        }
        if (TEXT == "请检查 ：") {
            alert(Message);
            window.location.href = "operationservelt?type=" + type + "&yhid=" + $("#yhidk").val() + "&yhxm=" +
                $("#yhxmk").val() + "&yhkl=" + $("#yhklk").val() + "&yhbm=" + $("#yhbmk").val() + "&yhxb=" + $("#yhxb").val()
                + "&pxh=" + $("#pxhk").val() + "&csrq=" + $("#mydatepicker").val() + "&sfjy=" + ($("#choose").prop("checked") == true ? 1 : 0);
            //  window.close();
        } else {
            alert(TEXT);

        }
        TEXT = "请检查 ：";
        Message = "";
    };

    //点击关闭
    function cancel() {
        window.close();
    }

    /**
     * @param {Object} value
     * 检查数字
     */
    function check_validate1(value) {
        var reg = /^[0-9]*$/;
        if (!reg.test(value)) {
            return true;
        }
        return false;
    }

    var type = "";
    var message = [];
    var afterUrl = decodeURI(window.location.search.substring(1));//(问号以后的字符串)
    $(document).ready(function () {
        $('#mydatepicker').dcalendarpicker({format: 'yyyy-mm-dd'});
        $(".k").each(function () {
            if ($(this).val() == "") {
                $(this).parents("div").css("background-color", "red")
            }
        })
        //直接打卡默认输入状态
        if (afterUrl == "") {
            type = "IN"
        } else {
            type = getValueFromUrl("message");
        }
        /**
         * 添加，更新成功或失败对应操作
         */
        if ("<%=label%>" != "") {
            if ("<%=label%>" == "保存成功" || "<%=label%>" == "更新成功") {
                opener.window.location.href = opener.window.location.href;
                window.close();
            }
            if ("<%=label%>" == "保存失败" || "<%=label%>" == "更新失败") {
                type = "IN";
            }
        }
        //初始设置窗口是否可选中
        $("#yhidk").attr("readOnly", set("1"))
        $("#yhxmk").attr("readOnly", set(""))
        $("#yhklk").attr("readOnly", set(""))
        $("#cfklk").attr("readOnly", set(""))
        $("#yhbmk").attr("disabled", set(""))
        $("#yhxb").attr("disabled", set(""))
        $("#mydatepicker").attr("disabled", set(""))
        $("#pxhk").attr("readOnly", set(""))
        $("#choose").attr("disabled", set(""))

        /**
         * 获取前传值 解码后传入表格
         */

        if (type != "IN") {
            $("#yhidk").val(getValueFromUrl("yhid"))
            $("#yhxmk").val(getValueFromUrl("yhxm"))
            $("#yhklk").val(getValueFromUrl("yhkl"))
            $("#cfklk").val(getValueFromUrl("yhkl"))
            $("#yhbmk").val(getValueFromUrl("yhbm"))
            $("#yhxb").val(getValueFromUrl("yhxb"))
            $("#mydatepicker").val(turnTime(getValueFromUrl("csrq")))
            $("#pxhk").val(getValueFromUrl("pxh"))
            $("#choose").prop("checked", (getValueFromUrl("sfjy") == "1" ? true : false))
        }
    })

    //这是是否可以编辑 内部三种情况对应的内容
    function set(value) {
        if (type == "IN") {
            return false
        } else if (type == "SEE") {
            $("#save").attr("hidden", true)
            return true
        } else {
            if (value != "") {
                return true
            } else {
                return false
            }
        }
    }

    //从传入url获取值
    function getValueFromUrl(value) {
        var message = afterUrl.split("&");
        for (let x of message) {
            var item = x.split("=");
            if (value == item[0]) {
                return item[1];
            }
        }
    }

    //转时间显示格式
    function turnTime(str) {
        if (str == "") {
            return ""
        }
        var s1 = str.substring(0, 4);
        var s2 = str.substring(4, 6);
        var s3 = str.substring(6, 8);
        return s1 + "-" + s2 + "-" + s3;
    }

</script>
<style>
    .k {
    }
</style>
</html>
