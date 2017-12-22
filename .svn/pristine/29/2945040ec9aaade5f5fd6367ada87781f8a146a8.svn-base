$(function(){
	
//	reload();
	
	var currentPage; 
	var totalPage;

		$('body').on('click','.msgEdit',function(){        
			var message_uuid = $(this).parent().siblings('td').eq(0).html();
			$("#editMsgId").val(message_uuid);
			$('#editMsgName').val($(this).parent().siblings('td').eq(1).html());
			$('#editMsgType').val($(this).parent().siblings('td').eq(2).html());
			$('#editDataSource').val($(this).parent().siblings('td').eq(3).html());
			$('#editScheduleType').val($(this).parent().siblings('td').eq(4).html());
			$('#editDataSql').val($(this).parent().siblings('td').eq(5).text());
			$('#editMsgTamplate').val($(this).parent().siblings('td').eq(6).html());
			$('#editExpireTime').val($(this).parent().siblings('td').eq(7).html());
			$('#msgModal1').modal({show:true});
			var postData = {message_uuid:message_uuid};
			console.log(message_uuid)
			$.ajax({
				url:'/message/msg/selectMessageParam?message_uuid='+message_uuid,
				dataType:'json',
				type:'post',
				data: JSON.stringify(postData),
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function (data) {
					console.log(data);
					$('#msgtab').empty();
                    
                    for(var i=0;i<data.resp.length;i++){  
                    		var result = '';
                            result += "<tr>";
                            result += "<th style='display:none'><input value='"+data.resp[i].message_param_uuid+"' /></th>";
                            result += "<th><input value='"+data.resp[i].message_param_name+"' /></th>";
                            result += "<th><input value='"+data.resp[i].message_param_index+"' /></th>";
                            result += "<th><input value='"+data.resp[i].message_param_value+"' /></th>";           
                            result += "<th><button onclick='deTipLine(this)'>×</button></th>";                             
                            result += "</tr>";
							$("#msgtab").append(result);   
                    }
				},
			});
			
		});
		
		//下拉选页码跳转
		$("#tipSelect").change(function(){
			var tipSel = $(this).children('option:selected').val();
			$.ajax({
			url: '/message/user/select_page?pageNum='+tipSel,
			dataType: 'json',
			type: "GET",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function (data) {
				console.log(data);
				$("#msgTabList").empty();
				appResult(data);
			},
		});
		})
		
		//点击下一页
				$('#msgNext').click(function(){
					console.log(1);
					var currentPages = $('#msgCurrentPage').text();
					
					currentPage = Number(currentPages) + 1;
					$.ajax({
						url: '/message/msg/select_page?pageNum='+currentPage,
						dataType: 'json',
						type: "GET",
						headers: {
							Accept: "application/json",
							"Content-Type": "application/json"
						},
						success: function (data) {
							console.log(data);
							$("#msgTabList").empty();
							appResult(data);
						},
				});
			});
			
			//点击上一页
			$('#msgPre').click(function(){
				var currentPages = $('#msgCurrentPage').text();
					currentPage = Number(currentPages) - 1;
				$.ajax({
					url: '/message/msg/select_page?pageNum='+currentPage,
					dataType: 'json',
					type: "GET",
					headers: {
						Accept: "application/json",
						"Content-Type": "application/json"
					},
					success: function (data) {
						console.log(data);
						$("#msgTabList").empty();
						appResult(data);
					},
				});
			});
			
			//点击首页
			$('#msgPageIndex').click(function(){
			//	currentPage = 0;
				$.ajax({
					url: '/message/msg/select_page?pageNum=0',
					dataType: 'json',
					type: "GET",
					headers: {
						Accept: "application/json",
						"Content-Type": "application/json"
					},
					success: function (data) {
						console.log(data);
						$("#msgTabList").empty();
						appResult(data);
					},
				});
			});
			
			//点击尾页
			$('#msgLast').click(function(){
				totalPage = $('#msgTotalPage').text();
				console.log(totalPage);
				$.ajax({
					url: '/message/msg/select_page?pageNum='+totalPage,
					dataType: 'json',
					type: "GET",
					headers: {
						Accept: "application/json",
						"Content-Type": "application/json"
					},
					success: function (data) {
						console.log(data);
						$("#msgTabList").empty();
						appResult(data);
					},
				});
			});
	
	});
	
	function reload(){
		$.ajax({
			url: '/message/msg/select_page?pageNum=1',
			dataType: 'json',
			type: "GET",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function (data) {
				console.log(data);
				
				$("#msgTabList").empty();
				appResult(data);
			},
		});
	}

	var num = /^[0-9]\d*$/;　
	
	//添加
	function addMessageTip(){

		var message_name = $("#messageName").val();
		var message_type = $("#messageType").val();
		var data_source = $("#dataSource").val();
		var data_sql = $("#dataSql").val();
		var message_template = $("#msgTamplate").val();
		var scheduleType = $('#scheduleType').val();
		var expire_time = $('#expireTime').val();

		if (message_name == '' || message_name == null) {
			alert("消息名称不能为空");
			return false;
		}
		if (message_type == '' || message_type == null) {
			alert('消息类型不能为空');
			return false;
		}
		if (data_source == '' || data_source == null) {
			alert('数据源不能为空');
			return false;
		}
		if (data_sql == '' || data_sql == null) {
			alert('数据抽取逻辑不能为空');
			return false;
		}
		if (message_template == '' || message_template == null) {
			alert('消息文本模板不能为空');
			return false;   
		}
		if(!expire_time.match(num)){
			alert('请填写整数格式');
			return false;
		}
		
		
		var postData = {
			message_name: message_name,
			message_type: message_type,
			data_source: data_source,
			data_sql: data_sql,
			message_template: message_template,
			schedule_type:scheduleType,
			expire_time:expire_time
		}
		
		console.log(postData);
	
		$.ajax({
			url: '/message/msg/insertMessage',
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
					alert('新增信息提醒配置成功');
					$('#megModal').modal('hide');
				}
				$("#messageName").val('');
				$("#messageType").val('');
				$("#dataSource").val('');
				$("#dataSql").val('');
				$("#msgTamplate").val('');
				$('#expireTime').val('');
			},
			error: function (data) {
				console.log(data);
			}
		});
	}
	
	//修改
	function editMessageTip(){
		var message_uuid = $('#editMsgId').val();
		var message_name = $("#editMsgName").val();
		var message_type = $("#editMsgType").val();
		var data_source = $("#editDataSource").val();
		var data_sql = $("#editDataSql").val();
		var message_template = $("#editMsgTamplate").val();
		var schedule_type = $('#editScheduleType').val();
		var expire_time =$('#editExpireTime').val();

		if (message_name == '' || message_name == null) {
			alert("消息名称不能为空");
			return false;
		}
		if (message_type == '' || message_type == null) {
			alert('消息类型不能为空');
			return false;
		}
		if (data_source == '' || data_source == null) {
			alert('数据源不能为空');
			return false;
		}
		if (data_sql == '' || data_sql == null) {
			alert('数据抽取逻辑不能为空');
			return false;
		}
		if (message_template == '' || message_template == null) {
			alert('消息文本模板不能为空');
			return false;
		}
		if(!expire_time.match(num)){
			alert('请填写整数格式');
			return false;
		}
		
		var postData = new Array();
		
		if($("#msgtab").find("tr").length == 0){
			 postData.push({
            	message_uuid:message_uuid,
				message_name: message_name,
				message_type: message_type,
				data_source: data_source,
				data_sql: data_sql,
				message_template: message_template,
				schedule_type:schedule_type,
				expire_time:expire_time,
				
				message_param_uuid:'',
				message_param_name:'',
				message_param_index:'',
				message_param_value:'',
            });
		}else{
			var len = $("#msgtab").find("tr").size();
	        for(var i = 0;i < len; i++){
	            var cols0 = $("#msgtab").find("tr").eq(i).children().find('input').eq(0).val();
	            var cols1 = $("#msgtab").find("tr").eq(i).children().find('input').eq(1).val();
	            var cols2 = $("#msgtab").find("tr").eq(i).children().find('input').eq(2).val();
	            var cols3 = $("#msgtab").find("tr").eq(i).children().find('input').eq(3).val();
	            
	            postData.push({
	            	message_uuid:message_uuid,
					message_name: message_name,
					message_type: message_type,
					data_source: data_source,
					data_sql: data_sql,
					message_template: message_template,
					schedule_type:schedule_type,
					expire_time:expire_time,
					
					message_param_uuid:cols0,
					message_param_name:cols1,
					message_param_index:cols2,
					message_param_value:cols3,
	            });
			}
		}
        
        console.log(postData)
		
		$.ajax({
			url: '/message/msg/insertMessageParam',
			dataType: 'json',
			type: "post",
			data: JSON.stringify(postData),
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json"
			},
			success: function (data) {
				reload();
				console.log(data);
				if (data>=0) {
					alert('信息提醒配置修改成功');
					$('#msgModal1').modal('hide');
				}else{
					alert('信息提醒配置修改成功');
					return false;
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
				result += '<td style="text-align: center;">' + data.allData[item].message_uuid + '</td>';
				result += '<td style="text-align: center;">' + data.allData[item].message_name + '</td>';
				result += '<td style="text-align: center;">' + data.allData[item].message_type + '</td>';
				result += '<td style="text-align: center;">' + data.allData[item].data_source + '</td>';
				result += '<td style="text-align: center;">' + data.allData[item].schedule_type + '</td>';
				result += '<td style="text-align: center;">' + data.allData[item].data_sql + '</td>';
				result += '<td style="text-align: center;">' + data.allData[item].message_template + '</td>';
				result += '<td style="text-align: center;">' + data.allData[item].expire_time + '</td>';
				result += '<td class="updaId" style="text-align: center;"><input type="button" value="修改" class="msgEdit comStlBtn"/><input type="button" value="删除" class="msgDelete comStlBtn"/></td>';
				result += '</tr>';
				$("#msgTabList").append(result);
			}
		$('#msgCurrentPage').text(data.page.currentPage);
		$('#msgTotalRecord').text(data.page.totalRecord);
		$('#msgTotalPage').text(data.page.totalPage)
	}
	
  	//点击增加一行
    function addLTipLine(){
        var result = '';
            result += '<tr>';
            result += '<th style="display:none"><input value="0" /></th>';
            result += '<th><input value="" /></th>';
            result += '<th><input value="" /></th>';
            result += '<th><input value="" /></th>';
            result += '<th><button onclick="deTipLine(this)">×</button></th>';
            result += '</tr>';
        $(".msgtab").append(result);
	}

    //点击删除一行
    function deTipLine(obj){
        var tr=obj.parentNode.parentNode;
        var tbody=tr.parentNode;
        tbody.removeChild(tr);
	}