<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="static com.exam.service.SelectUserService.*" %>
<%@ page import="java.util.List" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="static com.exam.mapper.CodeToSome.MAP_BM" %>
<%@ page import="com.exam.entity.TUser" %>
<%@ page import="java.util.Map" %>
<%@ page import="static com.exam.mapper.CodeToSome.MAP_XB" %>
<jsp:directive.page import="org.springframework.web.context.WebApplicationContext"/>
<%@ page import="com.exam.mapper.SelectUserMapper" %>
<%
    /**
     * 默认全部数据加载
     */
    WebApplicationContext context = (WebApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    SelectUserMapper selectUserMapper = (SelectUserMapper)context.getBean("SelectUserMapper");
    String yhid = "";
    String yhbm = "";
    List<TUser> tUserList1 = selectUserMapper.selectUserByIdBm(yhid, yhbm);
    JSONArray jsonArray1 = JSONArray.fromObject(tUserList1);
%>
<!DOCTYPE html><html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script src="../js/jquery.min.js"></script>
    <title>表单</title>
    <meta charset="utf-8">
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        td {
            width: 160px;
            border: 1px solid #ddd;
            padding: 6px 8px;
        }

        .a1 {
            background: black;
            color: #fff;
            font-weight: bolder;
        }

        tr:hover {
            background: skyblue;
            color: #fff;
            font-weight: bolder;
        }

        .bj {
            readOnly: true;
        }

        form {

            margin: 0px;
            display: inline
        }
    </style>
    <jsp:include page="/resources/v2/webui.jsp">
        <jsp:param name="UIS" value="FORM_DGRID_LAYER_BTN_LAYOUT_PAGE"/>
    </jsp:include>
</head>
<body>
<div style="display: flex;">
    <div>
        用户姓名用户ID:<input id="UserId"/> &nbsp;&nbsp;&nbsp;&nbsp;
        用户部门:
        <select style="width: 100px; " id="UserBM">
            <option value="" selected="selected" style="text-align: center;"></option>
            <%
                for (Map.Entry<String, String> entry : MAP_BM.entrySet()) {
            %>
            <option value="<%=entry.getKey()%>"><%=entry.getValue()%>
            </option>
            <%
                }
            %>

        </select>
    </div>
    <div style="width: 20px"></div>
    <div>
        <button id="search" onclick="search()"> 查询</button>
        <button id="insert" onclick="insert()">新增</button>
        <button id="cancel" type="submit" onclick="cancel()">注销</button>
    </div>
</div>
<div style="height: 20px;"></div>
<div>
    <table border="1" style="width: 100%;">
        <colgroup>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
        </colgroup>
        <thead>
        <tr class="a1">
            <td align="center">序列</td>
            <td align="center">查看</td>
            <td align="center">修改</td>
            <td align="center">删除</td>
            <td align="center">姓名</td>
            <td align="center">账号</td>
            <td align="center">部门</td>
            <td align="center">性别</td>
            <td align="center">排序号</td>
            <td align="center">是否禁用</td>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
</body>
<script>
    var Type = "";
    //模拟查询所得数据
    var Data =
    <%=jsonArray1%>
    //自动路径
    var path = window.location.href.slice(0, window.location.href.lastIndexOf('/')) + '/window.jsp'
    //获得窗口的垂直位置
    var iTop = (window.screen.availHeight) / 2 - 190;
    //获得窗口的水平位置
    var iLeft = (window.screen.availWidth) / 2 - 333;
    $(document).ready(function () {
        initData();
    })

    /**
     public String yhid;
     public String yhxm;
     public String yhkl;
     public String yhxb;
     public String yhbm;
     public String csrq;
     public String sfjy;
     public String pxh;
     */
    function initData() {
        var html = '';
        if (Data == '') {
            html += '<tr > <td colspan="10" align="center"> 信息为空 </td> </tr>'
        } else {
            for (var i = 0; i < Data.length; i++) { //对stus进行循环遍历，并建立tr标签
                var Temp = Data[i];
                html += '<tr >';
                html += '<td align="center">' + (i + 1) + '</td> ';
                html += '<td align="center"><img src="../img/chakan.png"   width = "30px" onclick="imgClick(\'look\',' + i + ')"/></td>';
                html += '<td align="center"><img src="../img/xiugai.png"  width = "30px" onclick="imgClick(\'upd\',' + i + ')"/></td> ';
                html += '<td align="center"><img src="../img/shanchu.png"  width = "30px" onclick="imgClick(\'del\',' + i + ')"/> </td>';
                html += '<td align="left">' + Temp.yhxm + '</td> ';
                html += '<td align="left" id=' + i + '>' + Temp.yhid + '</td> ';
                html += '<td align="center">' + Temp.yhbm + '</td> ';
                html += '<td align="center">' + Temp.yhxb + '</td> ';
                html += '<td align="center">' + Temp.pxh + '</td> ';
                html += '<td align="center">' + (Temp.sfjy == '1' ? '是' : '否') + '</td> ';

                html += '</tr>';
            }
        }
        $("tbody").html(html);
    }

    function imgClick(type, index) {
        var Item = Data[index];
        if (type == 'look') {
            window.open(path + encodeURI('?message=' + "SEE&csrq=" + Item.csrq + "&pxh=" + Item.pxh + "&sfjy=" + Item.sfjy + "&yhbm=" + Item.yhbm +
                "&yhid=" + Item.yhid + "&yhkl=" + Item.yhkl + "&yhxb=" + Item.yhxb + "&yhxm=" + Item.yhxm), '新的窗口',
                'height=380px,width=666px,top=' + iTop + ',left=' + iLeft +
                ',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')
        } else if (type == 'upd') {
            window.open(path + encodeURI('?message=' + "UPDATE&csrq=" + Item.csrq + "&pxh=" + Item.pxh + "&sfjy=" + Item.sfjy + "&yhbm=" + Item.yhbm +
                "&yhid=" + Item.yhid + "&yhkl=" + Item.yhkl + "&yhxb=" + Item.yhxb + "&yhxm=" + Item.yhxm), '新的窗口',
                'height=380px,width=666px,top=' + iTop + ',left=' + iLeft +
                ',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')
        } else if (type == 'del') {
            if (confirm("确认删除么")) {
                $.post("operation", {
                    type: "DELETE",
                    yhid: $("#" + index).text()
                }, function (data, status) {
                    var parse = JSON.parse(data);
                    if (status == "success" && parseInt(parse.number) >= 1) {
                        $.post("operationservelt", {
                            type: "SELECT",
                            yhid: $("#UserId").val().trim(),
                            yhbm: $("#UserBM").val().trim()
                        }, function (data, status) {
                            if (status == "success") {
                                Data = JSON.parse(data)
                                alert("删除成功")
                                initData()
                            } else {
                                alert("网络异常")
                            }
                        })
                    } else {
                        alert("删除失败")
                    }
                })
            }
        } else {
            console.log("未知操作")
        }
    }

    //查找信息
    function search() {
        $.post("operationservelt", {
            type: "SELECT",
            yhid: $("#UserId").val().trim(),
            yhbm: $("#UserBM").val().trim()
        }, function (data, status) {
            if (status == "success") {
                Data = JSON.parse(data)
                initData()
            } else {
                alert("网络异常")
            }

        });
    }

    //新增按钮
    function insert() {
        window.open(path + '?message=' + "IN", '新的窗口', 'height=380px,width=666px,top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')
    }

    //删缓存注销并跳回
    function cancel() {
        delCookie("yhid");
        delCookie("yhbm");
        delCookie("del");
        delCookie("account");
        delCookie("pwd");
        window.location.href = "logoutservelt";
    }

    /**
     * 删除cookie
     * @param name
     */
    function delCookie(name) {
        const exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null) {
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
        }
    }

    /**
     * 比较高效率的分割获取cookie值
     * @param name
     * @returns {string|null}
     */
    function getCookie(name) {
        var prefix = name + "="
        var start = document.cookie.indexOf(prefix)
        if (start == -1) {
            return null;
        }
        var end = document.cookie.indexOf(";", start + prefix.length)
        if (end == -1) {
            end = document.cookie.length;
        }
        var value = document.cookie.substring(start + prefix.length, end)
        return unescape(value);
    }
</script>
</html>