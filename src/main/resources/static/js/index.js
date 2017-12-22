	$(function(){
		
//		projectfileoptions = {
//	        showUpload:false,
//	        showRemove:false,
//	        language:'zh',
//	        allowedPreviewTypes:[ 'image' ],
//	        allowedFileExtensions:[ 'jpg', 'png', 'gif' ],
//	        maxFileSize:2000,
//	    },
//		// 文件上传框
//		$('input[class=projectfile]').each(function() {
//		    var imageurl = $(this).attr("value");
//		
//		    if (imageurl) {
//		        var op = $.extend({
//		            initialPreview:[ // 预览图片的设置
//		            "<img src='" + imageurl + "' class='file-preview-image'>", ]
//		        }, projectfileoptions);
//		
//		        $(this).fileinput(op);
//		    } else {
//		        $(this).fileinput(projectfileoptions);
//		    }
//		});

		$('body').on('click', '.logout', function() {
			if (confirm('是否确定退出')) {
				window.location.href = "/message/login";
			} else {
				return;
			}
		});
		
		
		$('.sectioonLeft').find('li').click(function(){
			$(this).addClass('active').siblings().removeClass('active');
		});
		self ={
			reload:function(name,url){
				$(name).load(url);
				console.log(name);
			}
		}
		self.reload('.sectioonRight','/message/user/');
		
		var height = $(window).height();
		var setHeight = (height - 80)+'px';
		$('.sectioonRight').height(setHeight);     
		
		
		$('#li1').click(function(){
			self.reload('.sectioonRight','/message/user/');
		});
		
		$('#li2').click(function(){
			self.reload('.sectioonRight','/message/semantic/');
		});
		
		$('#li3').click(function(){
			self.reload('.sectioonRight','/message/verbal/');
		});
		
		$('#li4').click(function(){
			self.reload('.sectioonRight','/message/msg/');
		});
		
		$('#li5').click(function(){
			self.reload('.sectioonRight','/message/uploadFile/');
		});
	});
	
	var check=document.getElementById("check");
	var useName = document.getElementById('userName');
	var passpwd = document.getElementById('passWord');
	
	//设置缓存
	loUser=localStorage.getItem("useName") || "",
	loPass=localStorage.getItem("password") || "";
	useName.value = loUser;
	passpwd.value = loPass;
	if(loUser!== "" && loPass!== ""){
		check.setAttribute("checked","");
	}
	
	function login(){
		var userName = $('#userName').val();
		var passWord = $('#passWord').val();
		
		postData = {
			userName:userName,
			passWord:passWord,
		}
		
		$.ajax({
				url: '/message/loginUser',
				dataType: 'json',
				type: "post",
				data: JSON.stringify(postData),
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json",
				},
				success: function (data) {
					if(data.resp == 0){
						alert('登录成功');
						window.location.href = '/message/index';
						sessionStorage.setItem("userId",userName);
					}else if(data.resp == -1){
						alert('请确认账号或者密码是否填写正确');
					}
				},
				error: function (data) {
					console.log(data);
				}
			});
			
			//记住账号密码
			if(check.checked){
				localStorage.setItem("useName",userName);
				localStorage.setItem("password",passWord);
			}else{
				localStorage.setItem("useName","");
				localStorage.setItem("password","");
			}
	}

