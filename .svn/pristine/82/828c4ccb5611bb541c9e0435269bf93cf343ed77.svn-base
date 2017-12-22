var currentPages = 1;
var categorys = "";
var types = "";
var totalPages;

$(function() {

	$("#verSelect").change(function() {
		var cpage = $('#verSelect').children('option:selected').val();
						$.ajax({
							url: '/message/verbal/select_page?type='+types+'&category='+categorys+'&pageNum='+cpage,
							dataType: 'json',
							type: "GET",
							headers: {
								Accept: "application/json",
								"Content-Type": "application/json"
							},
							success : function(data) {
								result(data);
							},
							error : function(data) {
								console.log(data);
							}
						});
					});

	$('#huashudalie')
			.change(
					function() {
						var data = {
							type : $(this).val()
						}
						$.ajax({
									url : '/message/verbal/getAllCategoryByType',
									dataType : 'json',
									type : "post",
									data : JSON.stringify(data),
									headers : {
										Accept : "application/json",
										"Content-Type" : "application/json"
									},
									success : function(data) {

										$('#huashuxiaolie').empty();
										if (data.resp == "[]") {
											var html = '<option value=""> 请选择。。。</option>';
											$('#huashuxiaolie').append(html);
										}
										var datas = JSON.parse(data.resp);
										for ( var a in datas) {
											var html = '<option value="'
													+ datas[a] + '">'
													+ datas[a] + '</option>';
											$('#huashuxiaolie').append(html);
										}
									},
									error : function(data) {
										console.log(data);
									}
								});
					});

	$('body').on('click', '.verEdit', function() {
		var id = $(this).parent().siblings('td').eq(0).html();
		$("#edid").val(id);
		$('#editHuashuName1').val($(this).parent().siblings('td').eq(1).html());
		$('#editHuashuCode1').val($(this).parent().siblings('td').eq(2).html());
		$('#editHuashuName2').val($(this).parent().siblings('td').eq(3).html());
		$('#editHuashuCode2').val($(this).parent().siblings('td').eq(4).html());
		$('#editHaushuMod').val($(this).parent().siblings('td').eq(5).html());
		$('#myModal1').modal({
			show : true
		});
	});

	
	$('#verNext').click(function(){
		currentPages =  Number(currentPages)+1;
		$.ajax({
			url: '/message/verbal/select_page?type='+types+'&category='+categorys+'&pageNum='+currentPages,
			dataType: 'json',
			type: "GET",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function (data) {
				$("#tabList").empty();
				appResult(data);
			},
		});
	});
	
	$('#verPre').click(function(){
		currentPages = Number(currentPages)-1;
		console.log(currentPages);
		$.ajax({
			url: '/message/verbal/select_page?type='+types+'&category='+categorys+'&pageNum='+currentPages,
			dataType: 'json',
			type: "GET",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function (data) {
				console.log(data);
				$("#tabList").empty();
				appResult(data);
			},
		});
	});
	
	$('#verPageIndex').click(function(){
		currentPages = 0;
		console.log(currentPages);
		$.ajax({
			url: '/message/verbal/select_page?type='+types+'&category='+categorys+'&pageNum='+currentPages,
			dataType: 'json',
			type: "GET",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function (data) {
				console.log(data);
				$("#tabList").empty();
				appResult(data);
			},
		});
	});
	
	$('#verPageLast').click(function(){
		totalPages=$('#totalPage').text();
		
//		totalPages = Number(totalPages);
		console.log(currentPages);
		$.ajax({
			url: '/message/verbal/select_page?type='+types+'&category='+categorys+'&pageNum='+totalPages,
			dataType: 'json',
			type: "GET",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function (data) {
				console.log(data);
				$("#tabList").empty();
				appResult(data);
			},
		});
	});
});


	function selects(){
		var type = $('#huashudalie').val();
		var category = $('#huashuxiaolie').val();
		var postData = {
			type:type,
			category:category
		}
		$.ajax({
			url: '/message/verbal/select_page/?type='+type+'&category='+category,
			dataType: 'json',
			type: "GET",
//			data: decodeURI(JSON.stringify(postData)),
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function(data) {
				console.log(data);
				result(data);
				currentPages = data.page.currentPage;
				categorys = data.category;
				totalPages = data.page.totalPage;
				types = data.type;
				$('#mySelect').empty();
                for(var i=1;i<=totalPages;i++){
                	console.log(i);
                	$('#mySelect').append("<option value='"+i+"'>"+i+"</option>");
                }
			},
		});
	}

function getTime() {
	var date = new Date();
	var time = '';
	time = date.getFullYear() + "-"; // 取年份
	time = time + (date.getMonth() + 1) + "-";// 取月份
	time += date.getDate() + " "; // 取日期
	return (time);
}

function onSave() {
	var time = getTime();
	var type_desc = $("#huashuName1").val();
	var type = $("#huashuCode1").val();
	var category_desc = $("#huashuName2").val();
	var category = $("#huashuCode2").val();
	var content = $("#haushuMod").val();

	if (type_desc == '' || type_desc == null) {
		alert("话术大类名称不能为空");
		return false;
	}
	if (type == '' || type == null) {
		alert('话术大类代码不能为空');
		return false;
	}
	if (category_desc == '' || category_desc == null) {
		alert('话术小类名称不能为空');
		return false;
	}
	if (category == '' || category == null) {
		alert('话术小类代码不能为空');
		return false;
	}
	if (content == '' || content == null) {
		alert('话术模板不能为空');
		return false;
	}
	var postData = {
		type_desc : type_desc,
		type : type,
		category_desc : category_desc,
		category : category,
		content : content,
		create_name : 'admin',
		create_time : time,
	}

	$.ajax({
		url : '/message/verbal/insert',
		dataType : 'json',
		type : "post",
		data : JSON.stringify(postData),
		headers : {
			Accept : "application/json",
			"Content-Type" : "application/json"
		},
		success : function(data) {
			console.log(data.resp);
			if (data.resp == '新增成功') {
				alert('新增成功');
				$('#myModal').modal('hide');
				location.reload(true);
			}
		},
		error : function(data) {
			console.log(data);
		}
	});
}

function updata() {
	var id = $('#edid').val();
	var time = getTime();
	var type_desc = $("#editHuashuName1").val();
	var type = $("#editHuashuCode1").val();
	var category_desc = $("#editHuashuName2").val();
	var category = $("#editHuashuCode2").val();
	var content = $("#editHaushuMod").val();

	if (type_desc == '' || type_desc == null) {
		alert('话术大类名称不能为空');
		return false;
	}
	if (type == '' || type == null) {
		alert('话术大类代码不能为空');
		return false;
	}
	if (category_desc == '' || category_desc == null) {
		alert('话术小类名称不能为空');
		return false;
	}
	if (category == '' || category == null) {
		alert('话术大类代码不能为空');
		return false;
	}
	if (content == '' || content == null) {
		alert('话术模板不能为空');
		return false;
	}
	var postData = {
		id : id,
		type_desc : type_desc,
		type : type,
		category_desc : category_desc,
		category : category,
		content : content,
		create_name : 'admin',
		create_time : time,
	}

	$.ajax({
		url : '/message/verbal/update',
		dataType : 'json',
		type : "post",
		data : JSON.stringify(postData),
		headers : {
			Accept : "application/json",
			"Content-Type" : "application/json"
		},
		success : function(data) {
			alert('修改成功');
			$("#myModal1").modal('hide');
			location.reload(true)
		}
	});
}

function result(data){
	$("#tabList").empty();
	for (var item in data.resp) {
			var result = '';
			result += '<tr class="">';
			result += '<td style="text-align: center;">' + data.resp[item].id + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].type_desc + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].type + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].category_desc + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].category + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].content + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].create_name + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].create_time + '</td>';
			result += '<td class="updaId" style="text-align: center;"><input type="button" value="修改" class="verEdit comStlBtn"/><input type="button" value="删除" class="verDelete comStlBtn"/></td>';
			result += '</tr>';
			$("#tabList").append(result);
		}

	
	 $("#totalRecord").text(data.page.totalRecord);
		$('#totalPage').text(data.page.totalPage);
		$('#currentPage').text(data.page.currentPage); 
		$('#mySelect').val(data.page.pageSize);
}

function appResult(data){
	for (var item=0;item<data.resp.length;item++) {
			var result = '';
			result += '<tr class="">';
			result += '<td style="text-align: center;">' + data.resp[item].id + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].type_desc + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].type + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].category_desc + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].category + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].content + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].create_name + '</td>';
			result += '<td style="text-align: center;">' + data.resp[item].create_time + '</td>';
			result += '<td class="updaId" style="text-align: center;"><input type="button" value="修改" class="verEdit"/><input type="button" value="删除" class="verDelete"/></td>';
			result += '</tr>';
			$("#tabList").append(result);
		}
	 $("#totalRecord").text(data.page.totalRecord);
		$('#totalPage').text(data.page.totalPage);
		$('#currentPage').text(data.page.currentPage); 
		$('#mySelect').val(data.page.pageSize);
}
