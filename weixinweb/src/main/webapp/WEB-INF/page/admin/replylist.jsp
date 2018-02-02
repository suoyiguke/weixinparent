<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 导入easyui类库 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
    <script
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        // 工具栏
        var toolbar = [{
            id: 'button-search',
            text: '搜索',
            iconCls: 'icon-search',
            handler: doSearch
        }, {
            id: 'button-edit',
            text: '编辑',
            iconCls: 'icon-edit',
            handler: doEdit
        }, {
            id: 'button-add',
            text: '新增',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-remove',
            text: '删除',
            iconCls: 'icon-remove',
            handler: doRemove
        }];
        //定义冻结列
        var frozenColumns = [[{
            field: 'id',
            checkbox: true,
            rowspan: 2
        }]];


        // 定义标题栏
        var columns = [[{
            field: 'replyId',
            title: '编号',
            width: 60,
            rowspan: 2,
            align: 'center'
        }, {
            field: 'content',
            title: '评论内容',
            width: 300,
            rowspan: 2,
            align: 'center'
        }, {
            field: 'nickname',
            title: '作者名',
            width: 150,
            rowspan: 2,
            align: 'center'
        }, {
            field: 'sex',
            title: '作者性别',
            width: 80,
            rowspan: 2,
            align: 'center'
        }, {
            field: 'createtime',
            title: '评论时间',
            width: 150,
            rowspan: 2,
            align: 'center'
        }, {
            field: 'school',
            title: '作者学校',
            width: 150,
            rowspan: 2,
            align: 'center'
        }, {
            field: 'headImage',
            title: '作者头像',
            width: 300,
            rowspan: 2,
            align: 'center'
        }]];
        $(function () {
            // 初始化 datagrid
            // 创建grid
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,
                striped: true,
                toolbar: toolbar,
                url: "replyPageQuery.action",
                idField: 'replyId',
                frozenColumns: frozenColumns,
                columns: columns,
                onClickRow: onClickRow,
                onDblClickRow: doDblClickRow,
                method: "GET",
                pagination: true
            });

            $("body").css({visibility: "visible"});


            $("#searchWindow").window({
                minimizable: false,
                maximizable: false,
                closed: true,
                modal: true,

                title: "添加版块窗口 ",
                style: "top:20px;left:200px",

                width: 500


            });

            $("#btn").click(function () {
                var name = $("input[name=name]").val();
                var description = $("#textx").val();
                var data = {"name": name, "description": description};
                $.post("addForum.action", data, function (message) {

                    $("#searchWindow").window("close");
                    alert(message.message);
                    $("#grid").datagrid("load");
                }, "json");
            });


        });

        // 双击
        function doDblClickRow(rowIndex, rowData) {
            var items = $('#grid').datagrid('selectRow', rowIndex);
            doView();
        }

        // 单击
        function onClickRow(rowIndex) {

        }

        function doAdd() {
            $("#searchWindow").window("open");
        }

        function doEdit() {
            alert("编辑用户");
            var item = $('#grid').datagrid('getSelected');
            console.info(item);
            //window.location.href = "edit.html";
        }

        function doDelete() {
            alert("删除用户");
            var ids = [];
            var items = $('#grid').datagrid('getSelections');
            for (var i = 0; i < items.length; i++) {
                ids.push(items[i].id);
            }

            console.info(ids.join(","));

            $('#grid').datagrid('reload');
            $('#grid').datagrid('uncheckAll');
        }

        function doSearch() {
            alert("搜索");
            $("#searchWindow").window("open");

        }

        function doRemove() {
            //获取数据表格中所有选中的行，返回数组对象
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 0) {
                //没有选中记录，弹出提示
                $.messager.alert("提示信息", "请选择用户！", "warning");
                return;
            }//选择多个
            var array = new Array();
            for (var i = 0; i < rows.length; i++) {
                var staff = rows[i];//json对象
                var id = staff.forumId;
                array.push(id);
            }
            var ids = array.join(",");
            $.messager.confirm("警告","你确定要删除选中版块吗？",function(r){
                if (r){//确认删除
                    var data = {"forumIds": ids};
                    $.get("deleteForum.action", data, function (message) {
                        alert(message.message);
                    }, 'json');
                    $("#grid").datagrid("load");//重新加载
                }

            });


        }


        function doOk() {
            alert("ok");
        }

        function doNo() {
            alert("no");
        }

        function doRedo() {
            alert("redo");
        }

        function doUndo() {
            alert("undo");
        }

        function doTip() {
            alert("doTip");
        }

    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>


<!-- 添加版块窗口 -->
<div class="easyui-window" id="searchWindow">
    <div style="overflow:auto;padding:5px;" border="false">
        <form>
            <table class="table-edit" width="80%" align="center">
                <tr class="name">
                    <td colspan="2">版块信息</td>
                </tr>
                <tr>
                    <td>版块名</td>
                    <td><input type="text" name="name"/></td>
                </tr>
                <tr>
                    <td>版块说明</td>
                    <td><textarea rows="20" cols="40" name="description" id="textx"></textarea>

                    </td>
                </tr>


                <tr>
                    <td colspan="2">
                        <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>