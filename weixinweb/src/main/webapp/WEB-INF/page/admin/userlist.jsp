<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	var toolbar = [ {
		id : 'button-search',
		text : '检索',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '新增',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
        id : 'button-redheart',
        text : '喜欢的帖子',
        iconCls : 'icon-redheart',
        handler : doLike
    }, {
        id : 'button-browse',
        text : '浏览过的忒子',
        iconCls : 'icon-browse',
        handler : doBrowse
    }, {
        id : 'button-person',
        text : '我的帖子',
        iconCls : 'icon-person',
        handler : doShowMyTopic
    }];

	//冻结列
    var frozenColumn = [ [ {
        field : 'userId',
        title : '编号',
        width : 60,
        rowspan : 2,
        align : 'center'
    } ] ];

	// 定义标题栏
	var columns = [ [ {
		field : 'nickname',
		title : '昵称',
		width : 60,
		rowspan : 2,
		align : 'center'
    } ,{
        field : 'sex',
        title : '性别',
        width : 60,
        rowspan : 2,
        align : 'center'
    } ,{
        field : 'autograph',
        title : '个性签名',
        width : 250,
        rowspan : 2,
        align : 'center'
    } ,{
        field : 'schoolname',
        title : '学校',
        width : 300,
        rowspan : 2,
        align : 'center'
    } ,{
        field : 'headimage',
        title : '头像图片全路径',
        width : 500,
        rowspan : 2,
        align : 'center'
    } ] ];
	$(function(){
		// 初始化 datagrid
		// 创建grid
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
            rownumbers : true,
			striped : true,
			toolbar : toolbar,
			url : "userPageQuery.action",
			columns : columns,
			onClickRow : onClickRow,
			onDblClickRow : doDblClickRow,
            method : "GET",
            pagination : true,
            nowrap : true,
		   frozenColumns : frozenColumn
		});
		
		$("body").css({visibility:"visible"});
		
	});
	// 双击
	function doDblClickRow(rowIndex, rowData) {
		var items = $('#grid').datagrid('selectRow',rowIndex);

	}
	// 单击
	function onClickRow(rowIndex){

	}
	
	function doAdd() {
		alert("添加用户");

		location.href="${pageContext.request.contextPath}/page_admin_userinfo.action";
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
		for(var i=0; i<items.length; i++){
		    ids.push(items[i].id);	    
		}
			
		console.info(ids.join(","));
		
		$('#grid').datagrid('reload');
		$('#grid').datagrid('uncheckAll');
	}

	function  doShowMyTopic() {
        //获取数据表格中所有选中的行，返回数组对象
        var rows = $("#grid").datagrid("getSelections");
        if(rows.length == 0){
            //没有选中记录，弹出提示
            $.messager.alert("提示信息","请选择需要查看他所有帖子的用户！","warning");
        }else if(rows.length != 1){
            $.messager.alert("提示信息","一次只能查看一个用户！","warning");
        }else{
            window.location.replace("page_admin_mytopic.action?userId="+rows[0].userId);
        }
    }

    function  doLike() {
        //获取数据表格中所有选中的行，返回数组对象
        var rows = $("#grid").datagrid("getSelections");
        if(rows.length == 0){
            //没有选中记录，弹出提示
            $.messager.alert("提示信息","请选择需要查看他所有帖子的用户！","warning");
        }else if(rows.length != 1){
            $.messager.alert("提示信息","一次只能查看一个用户！","warning");
        }else{
            window.location.replace("page_admin_myliketopic.action?userId="+rows[0].userId);
        }
    }


    function  doBrowse() {
        alert("显示我的足迹");
    }

    function doSearch() {
		alert("检索");
    }

</script>		
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="center" border="false">
    	<table id="grid"></table>
	</div>
</body>
</html>