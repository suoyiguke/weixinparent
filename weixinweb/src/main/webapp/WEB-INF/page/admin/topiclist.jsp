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
		text : '搜索',
		iconCls : 'icon-search',
		handler : doSearch
	},{
        id : 'button-edit',
        text : '编辑',
        iconCls : 'icon-edit',
        handler : doEdit
    }, {
		id : 'button-add',
		text : '新增',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-remove',
		text : '删除',
		iconCls : 'icon-remove',
		handler : doRemove
	}, {
        id : 'button-ok',
        text : '加精',
        iconCls : 'icon-ok',
        handler : doOk
    }, {
        id : 'button-no',
        text : '取消加精',
        iconCls : 'icon-no',
        handler : doNo
    }, {
        id : 'button-redo',
        text : '置顶',
        iconCls : 'icon-redo',
        handler : doRedo
    }, {
        id : 'button-undo',
        text : '取消置顶',
        iconCls : 'icon-undo',
        handler : doUndo
    }, {
        id : 'button-tip',
        text : '审核',
        iconCls : 'icon-tip',
        handler : doTip
    }];
	//定义冻结列
	var frozenColumns = [ [ {
		field : 'id',
		checkbox : true,
		rowspan : 2
	}] ];


	// 定义标题栏
	var columns = [ [{
        field : 'topicId',
        title : '编号',
        width : 60,
        rowspan : 2,
        align : 'center'
    } , {
		field : 'title',
		title : '标题',
		width : 100,
		rowspan : 2,
		align : 'center'
    }, {
        field : 'content',
        title : '内容',
        width : 200,
        rowspan : 2,
        align : 'center'
    } ,{
        field : 'forumName',
        title : '所属板块',
        width : 60,
        rowspan : 2,
        align : 'center'
    } ,{
        field : 'nickname',
        title : '作者',
        width : 60,
        rowspan : 2,
        align : 'center'
    } ,{
        field : 'schoolName',
        title : '学校',
        width : 200,
        rowspan : 2,
        align : 'center'
    } ,{
        field : 'createtime',
        title : '发布时间',
        width : 150,
        rowspan : 2,
        align : 'center'
    },{
        field : 'replycount',
        title : '回复数',
        width : 100,
        rowspan : 2,
        align : 'center'
    },{
        field : 'likenum',
        title : '收藏数',
        width : 100,
        rowspan : 2,
        align : 'center'
    },{
        field : 'type',
        title : '是否审核',
        width : 100,
        rowspan : 2,
        align : 'center',
		formatter : function (data) {
			if(data =="1"){
			    return "已审核";
			}else{
                return "未审核";
			}
        }
    }] ];
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
			url : "topicPageQuery.action",
			idField : 'id', 
			frozenColumns : frozenColumns,
			columns : columns,
			onClickRow : onClickRow,
			onDblClickRow : doDblClickRow,
            method : "GET",
            pagination : true
		});
		
		$("body").css({visibility:"visible"});



        $("#searchWindow").window({
            height:300,
            width:500,
            minimizable:false,
            maximizable:false,
            closed:true,
            modal:true,
            title:"查询帖子窗口",
            style:"top:20px;left:200px",

            inline: true

        });
        $("#btn").click(function () {
            var title = $("input[name='topic.title']").val();
            var userId = $("input[name='userId']").val();
            var schoolId = $("input[name='schoolId']").val();
            var forumId = $("input[name='forumId']").val();
            var startDate = $("input[name='startDate']").val();
            var endDate = $("input[name='endDate']").val();

		     $("#grid").datagrid("reload",{
                 "topic.title" : title,
				 "topic.userId":userId,
				 "schoolId":schoolId,
				 "topic.forumId":forumId,
				 "startDate":startDate,
				 "endDate":endDate
            });
            $("#searchWindow").window("close");

        });

    });
	// 双击
	function doDblClickRow(rowIndex, rowData) {
		var items = $('#grid').datagrid('selectRow',rowIndex);
		doView();
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

	function doSearch() {
		alert("搜索");
		$("#searchWindow").window("open");

    }

    function doRemove() {
		alert("删除");
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


	<!-- 查询帖子topic -->
	<div class="easyui-window" id="searchWindow" >
		<div style="overflow:auto;padding:5px;" border="false">
			<form>
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>标题</td>
						<td><input type="text" name="topic.title"/></td>
					</tr>
					<tr>
						<td>所属板块</td>
						<td><input  name="forumId"/></td>
					</tr>
					<tr>
						<td>作者</td>
						<td><input name="userId"/></td>
					</tr>
					<tr>
						<td>学校</td>
						<td><input name="schoolId"/></td>
					</tr>
					<tr>
						<td>开始日期</td>
						<td><input type="text" name="startDate" class="easyui-datebox" /></td>
					</tr>
					<tr>
						<td>结束日期</td>
						<td><input type="text" name="endDate" class="easyui-datebox" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
<<script>
$(function(){

	$('input[name=forumId]').combobox({
    url: 'serachForumByLikeName.action',
	valueField:'forumId',
	textField:'name',
    mode: 'remote',
    method: 'GET'

   });

	$('input[name=userId]').combobox({
	url: 'serachUserByName.action',
	valueField:'userId',
	textField:'nickname',
	mode: 'remote',
	method: 'GET'

	});


	$('input[name=schoolId]').combobox({
	url: 'serachSchoolByName.action',
	valueField:'schoolId',
	textField:'name',
	mode: 'remote',
	method: 'GET'

	});



});

</script>