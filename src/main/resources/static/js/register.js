	$(function(){
			var btnCust = '';
			$("#avatar-3").fileinput({
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
			    defaultPreviewContent: '<img src="../img/default_avatar_male.jpg" alt="Your Avatar" id="imgSrcs">',
			    layoutTemplates: {main2: '{preview} ' +  btnCust + ' {remove} {browse}'},
			    allowedFileExtensions: ["jpg", "png", "gif"]
			});
		})
		
		var pic;
		
		function register(){
			
			var avatar = document.getElementById('avatar-3').files[0];
			if(!/image\/\w+/.test(avatar.type)){     
	                alert("请确保文件为图像类型");   
	                return false;   
	        }   
	        var reader = new FileReader(); 
	        reader.readAsDataURL(avatar); 
	        reader.onload = function(e){  
	        	
	   			pic = this.result;
	   			console.log(pic);
	   			
	   			postData = {
		        	username:regName,
		        	passwords:regWord,
		        	pic:pic
	        	}
	        	
	        	      
		        console.log(postData);
		    //    return false;
	       
	        	
	   			$.ajax({
		        	url: '/message/regist',
					dataType: 'json',
					type: "post",
					data: JSON.stringify(postData),
					headers: {
						Accept: "application/json",
						"Content-Type": "application/json"
					},
					success: function (data) {
						console.log(data.resp);
						if (data.resp == '新增成功') {
							alert('用户注册成功');
								window.location.href = '/message/index';
						}else if(data.resp == '新增失败'){
							alert('用户注册失败');
						}
					},
					error: function (data) {
						console.log(data);
					}
		        });
	        
	        }
			
			console.log(pic)
			
			var regName = $('#regName').val();
			var regWord = $('#regWord').val();
			var regWords = $('#regWords').val();
			
			if(regName == null || regName == ''){
				alert('请输入用户注册名');
				return false;
			};
			if(regWord == null || regWord == ''){
				alert('请输入用户注册密码');
				return false;
			};
			if(regWords == null || regWords == ''){
				alert('请再次输入用户注册密码');
				return false;
			};
			if(regWord !== regWords){
				alert('请确定两次输入密码是否一样');
				return false;
			}

	 
			
		}