$(function(){	
	
//			var btnCust = '<button type="button" class="btn btn-secondary" title="Add picture tags" ' + 
//		    'onclick="alert(\'Call your custom code here.\')">' +
//		    '<i class="glyphicon glyphicon-tag"></i>' +
//		    '</button>'; 
	var btnCust = '';
	$("#avatar-1,#avatar-2").fileinput({
	    overwriteInitial: true,
	    maxFileSize: 1500,
	    showClose: false,
	    showCaption: false,
	    browseLabel: '',
	    removeLabel: '',
	    browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>',
	    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
	    removeTitle: 'Cancel or reset changes',
	    elErrorContainer: '#kv-avatar-errors-1',
	    msgErrorClass: 'alert alert-block alert-danger',
	    defaultPreviewContent: '<img src="../img/default_avatar_male.jpg" alt="Your Avatar" id="imgSrc">',
	    layoutTemplates: {main2: '{preview} ' +  btnCust + ' {remove} {browse}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	});
	
	
	var currentPage; 
	var totalPage;
	reload();
	
	$("#userSelect").change(function(){
		var selt = $(this).children('option:selected').val();
		console.log(selt);
		$.ajax({
			url: '/message/user/select_page?pageNum='+selt,
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
	})
	
	
	$('body').on('click','.addBtn',function(){
		var id = $("#editId").val();
		var editName = $(this).parent(".form-group").siblings().find('input[name="editName"]').val();
		var editAcount = $(this).parent(".form-group").siblings().find('input[name="editAcount"]').val();
	//	var role = $(this).parent().siblings().find('input[name="editRole"]').val();
		$("#edid").val(id);
		$("#roleAcount").val(editAcount);
		$('#roleName').val(editName);
		console.log(editName);
		console.log(editAcount);
		console.log(id);
		var postData = {id:id}
		
		$.ajax({
				url: '/message/user/getRolesAndUserRoles',
				dataType: 'json',
				type: "post",
				data: JSON.stringify(postData),
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success:function(data){
					console.log(data.userRoles);
					console.log(data.roles);
					$('#box1').empty();
					$('#box2').empty();
					for(var i=0;i<data.roles.length;i++){
						var html = '<li class="boxText">'+data.roles[i]+'</li>';
						$('#box1').append(html);
					}
					for(var i=0;i<data.userRoles.length;i++){
						var html = '<li class="boxText">'+data.userRoles[i]+'</li>';
						$('#box2').append(html);
					}
				},
		
		});
	});

		$('body').on('click','.userEdit',function(){
			var id = $(this).parent().siblings('td').eq(0).html();
			var avaImg = $(this).parent().siblings('td').eq(7).html();
			$("#editId").val(id);
			$('#editUserName').val($(this).parent().siblings('td').eq(1).html());
			$('#editUserAccount').val($(this).parent().siblings('td').eq(2).html());
			$('#editDepartment').val($(this).parent().siblings('td').eq(3).html());
			$('#editUserEmail').val($(this).parent().siblings('td').eq(4).html());
			$('#editUserRole').val($(this).parent().siblings('td').eq(6).html());
//			$('#avatar-2').attr('src',avaImg);
			$('#imgSrc').attr('src',avaImg);
			$('#myModal1').modal({show:true});
			console.log(avaImg);
			console.log(id);
		});
		
		$('body .box1,.box2').on('click','li',function(){
			if($(this).hasClass('active')){
				$(this).removeClass('active');
			}else{
				$(this).addClass('active');
			}
		});
		
		$('body').on('click','.toRight',function(){
			var $li=$('#box1 li');
				for(var i=0;i<=$('#box1').length;i++){
					if($li.eq(i).hasClass('active')){
					$('#box2').append($li.get(i));
						$li.eq(i).removeClass('active');
					}
				}
		});
		
		$('body').on('click','.toLeft',function(){
			var $li=$('#box2 li');
				for(var i=0;i<=$('#box2').length;i++){
					if($li.eq(i).hasClass('active')){
					$('#box1').append($li.get(i));
						$li.eq(i).removeClass('active');
					}
				}
		});
		

		$('body').on('click','#userNext',function(){
			var currentPages = $('#currentPage').text();
			currentPage = Number(currentPages) + 1;
			$.ajax({
				url: '/message/user/select_page?pageNum='+currentPage,
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
	

	$('body').on('click','#userPre',function(){
		var currentPages = $('#currentPage').text();
			currentPage = Number(currentPages) - 1;
		$.ajax({
			url: '/message/user/select_page?pageNum='+currentPage,
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
	

	$('body').on('click','#pageIndex',function(){
	//	currentPage = 0;
		$.ajax({
			url: '/message/user/select_page?pageNum=0',
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
	
	$('body').on('click','#pageLast',function(){
		totalPage = $('#totalPage').text();
		console.log(totalPage);
		$.ajax({
			url: '/message/user/select_page?pageNum='+totalPage,
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
	
	function reload(){
		$.ajax({
			url: '/message/user/select_page?pageNum=1',
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
	}
	
	function addRole(){
			var liLen = $('#box2 li').length;
			var arr = [];
			for(var i=0;i<liLen;i++){
				var liText = $('#box2').find('li').eq(i).text();
				arr.push(liText);
				console.log(arr);
				$("#editUserRole").val(arr);	
			}
			$('#myModal2').modal('hide');
		}


function getTime(){
		var date = new Date();
		var time = '';
		time = date.getFullYear() + "-";
		time = time + (date.getMonth() + 1) + "-";
		time += date.getDate() + " "; 
		return(time);
	}
	
	function addMessage(){
	//	var av = $("#avatar-1").val();
		var avatar = document.getElementById('avatar-1').files[0];
		if(!/image\/\w+/.test(avatar.type)){     
                alert("请确保文件为图像类型");   
                return false;   
        }   
        var reader = new FileReader();   
        reader.readAsDataURL(avatar);   
        reader.onload = function(e){   
        	
        	pic = this.result;
   			console.log(pic);
        }   
		console.log(pic);
		
		var time = getTime();
		var cn_name = $("#userName").val();
		var mail_name = $("#userAccount").val();
		var department = $("#department").val();
		var email = $("#userEmail").val();
		var role = $("#userRole").val();

		if (cn_name == '' || cn_name == null) {
			alert("用户名称不能为空");
			return false;
		}
		if (mail_name == '' || mail_name == null) {
			alert('用户账号不能为空');
			return false;
		}
		if (department == '' || department == null) {
			alert('用户部门不能为空');
			return false;
		}
		if (email == '' || email == null) {
			alert('用户邮箱地址不能为空');
			return false;
		}
		if (role == '' || role == null) {
			alert('用户角色不能为空');
			return false;
		}
		
		var postData = {
			cn_name: cn_name,
			mail_name: mail_name,
			department: department,
			email: email,
			role: role,
			create_time:time,
			pic:pic
		}
		
		console.log(postData)

		$.ajax({
			url: '/message/user/insertUser',
			dataType: 'json',
			type: "post",
			data: JSON.stringify(postData),
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function (data) {
				reload();
				console.log(data.resp);
				if (data.resp == '新增成功') {
					alert('新增用户信息成功');
					$('#myModal').modal('hide');
				}
				$("#userName").val('');
				$("#userAccount").val('');
				$("#userEmail").val('');
				$("#userRole").val('');
			},
			error: function (data) {
				console.log(data);
			}
		});
	}
	
	function editMessage(){
		var avatar = document.getElementById('avatar-2').files[0];
		if(!/image\/\w+/.test(avatar.type)){     
                alert("请确保文件为图像类型");   
                return false;   
        }   
        var reader = new FileReader();   
        reader.readAsDataURL(avatar);   
        reader.onload = function(e){   
        	
        	pic = this.result;
   			console.log(pic);
        }   
		
		var id = $('#editId').val();
		var time = getTime();
		var cn_name = $("#editUserName").val();
		var mail_name = $("#editUserAccount").val();
		var department = $("#editDepartment").val();
		var email = $("#editUserEmail").val();
		var role = $("#editUserRole").val();

		if (cn_name == '' || cn_name == null) {
			alert("用户名称不能为空");
			return false;
		}
		if (mail_name == '' || mail_name == null) {
			alert('用户账号不能为空');
			return false;
		}
		if (department == '' || department == null) {
			alert('用户部门不能为空');
			return false;
		}
		if (email == '' || email == null) {
			alert('用户邮箱地址不能为空');
			return false;
		}
		if (role == '' || role == null) {
			alert('用户角色不能为空');
			return false;
		}
		
		var postData = {
			id:id,
			create_time:time,
			cn_name: cn_name,
			mail_name: mail_name,
			department: department,
			email: email,
			role: role,
			pic:pic
		}
		$.ajax({
			url: '/message/user/update',
			dataType: 'json',
			type: "post",
			data: JSON.stringify(postData),
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function (data) {
				console.log(data.resp);
				if (data.resp == '修改成功') {
					alert('用户信息修改成功');
					$('#myModal1').modal('hide');
				}
			},
			error: function (data) {
				console.log(data);
			}
		});
	}

function appResult(data){
	for (var item=0;item<data.allData.length;item++) {
			var result = '';
			result += '<tr class="">';
			result += '<td style="text-align: center;">' + data.allData[item].id + '</td>';
			result += '<td style="text-align: center;">' + data.allData[item].cn_name + '</td>';
			result += '<td style="text-align: center;">' + data.allData[item].mail_name + '</td>';
			result += '<td style="text-align: center;">' + data.allData[item].department + '</td>';
			result += '<td style="text-align: center;">' + data.allData[item].email + '</td>';
			result += '<td style="text-align: center;">' + data.allData[item].create_time + '</td>';
			result += '<td style="text-align: center;">' + data.allData[item].role + '</td>';
			result += '<td style="text-align: center;display:none">' + data.allData[item].pic + '</td>';
			result += '<td class="updaId" style="text-align: center;"><input type="button" value="修改" class="userEdit comStlBtn"/><input type="button" value="删除" class="userDelete comStlBtn"/></td>';
			result += '</tr>';
			$("#tabList").append(result);
		}
		$('#currentPage').text(data.page.currentPage);
		 $("#totalRecord").text(data.page.totalRecord);
		$('#totalPage').text(data.page.totalPage);
		$('#currentPage').text(data.page.currentPage); 
		$('#mySelect').val(data.page.pageSize);
}