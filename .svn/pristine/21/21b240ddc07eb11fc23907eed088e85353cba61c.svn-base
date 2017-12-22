$(function() {
	   $('body').on('click','#openAdd1',function(){
            $('#myModal').modal({show:true});
    });

    
    $('body').on('click','.semEdit',function(){
            var id = $(this).parent().siblings('td').eq(0).html();
        //    $("#edid").val(id);
			$('#semEditId').val(id);
            $('#editModelCode').val($(this).parent().siblings('td').eq(1).html());
            var semanticcode = $('#editModelCode').val();
            $('#editModelName').val($(this).parent().siblings('td').eq(2).html());
            $('#editActionUrl').val($(this).parent().siblings('td').eq(3).html());
            $('#editActionSuccess').val($(this).parent().siblings('td').eq(4).html());
            $('#editActionFailure').val($(this).parent().siblings('td').eq(5).html());
            $('#editLastReponse').val($(this).parent().siblings('td').eq(6).html());
            $('#editCreatorname').val($(this).parent().siblings('td').eq(7).html());
            $('#myModal1').modal({show:true});

            var postData = {template_id:semanticcode}
            console.log(semanticcode)
            $.ajax({
                    url: '/message/semantic/selectSemanticSlot',
                    dataType: 'json',
                    type: "post",
                    data: JSON.stringify(postData),
                    headers: {
                            Accept: "application/json",
                            "Content-Type": "application/json"
                    },
                    success: function (data) {
                            console.log(data);
                            console.log(data.resp.length);
						$('.addLine').empty();

                            
                            for(var i=0;i<data.resp.length;i++){
                            	var result = '';
                                    var checks = data.resp[i].required ==1 ? 'checked' : '';                    
                                    $("#codeUrl").val(data.resp[i].actionUrl);
                                    $("#editModelName").val(data.resp[i].semanticname);
                                    $("#editModelCode").val(data.resp[i].semanticcode);
                                    result += "<tr>";
                                    result += "<th><input value='"+data.resp[i].slot_order+"' style='width: 100%;'/></th>";
                                    result += "<th><input type='checkbox' checked=checks style='width: 100%;height: 16px;'/></th>";
                                    result += "<th><input value='"+data.resp[i].slot_name+"' style='width: 100%;'/></th>";
                                    result += "<th><input value='"+data.resp[i].slot_code+"' style='width: 100%;'/></th>";
                                    result += "<th><input value='"+data.resp[i].default_value+"' style='width: 100%;'/></th>";
                                    result += "<th><input value='"+data.resp[i].prompt+"' style='width: 100%;'/></th>";
                                    result += "<th><input value='"+data.resp[i].word_class+"' style='width: 100%;'/></th>";
                                    result += "<th><input value='"+data.resp[i].slot_value+"' style='width: 100%;'/></th>";
                                    result += "<th><input value='"+data.resp[i].try_count+"'/></th>";
                                    result += "<th style='display:none'><input value='"+data.resp[i].slot_id+"' style='width: 100%;'/></th>";
                                    result += "<th><button onclick='deLine(this)'>×</button></th>";                             
                                    result += "</tr>";
									$(".addLine").append(result);
                            }
                                      

                    },
                    error: function (data) {
                            console.log(data);
                    }
            })
    });
});

	

		function cancelRe() {
			$('#myModal1').modal('hide');

		}

		//获取时间
		function getTime() {
			var date = new Date();
			var time = '';
			time = date.getFullYear() + "-";
			time = time + (date.getMonth() + 1) + "-";
			time += date.getDate() + " ";
			return(time);
		}

		//新增语义模板列表
		function addList() {
			var template_code = $('#semanticCode').val();
			var template_name = $('#semanticName').val();
			var action_url = $('#actionUrl').val();
			var action_success = $('#actionSuccess').val();
			var action_failure = $('#actionFailure').val();
			var last_response = $('#lastReponse').val();
			var creator_name = $('#creator').val();
			var create_time = getTime();

			if(template_code == '' || template_code == null) {
				alert("语义模板代码不能为空")
				return false;
			}
			if(template_name == '' || template_name == null) {
				alert("语义模板名称不能为空")
				return false;
			}
			if(action_url == '' || action_url == null) {
				alert("业务调用URL不能为空")
				return false;
			}
			if(action_success == '' || action_success == null) {
				alert("业务调用成功话术")
				return false;
			}
			if(action_failure == '' || action_failure == null) {
				alert("业务调用失败话术")
				return false;
			}
			if(last_response == '' || last_response == null) {
				alert("缺省结束话术不能为空")
				return false;
			}
			if(creator_name == '' || creator_name == null) {
				alert("创建者不能为空")
				return false;
			}

			var postData = {
				template_code: template_code,
				template_name: template_name,
				action_url: action_url,
				action_success: action_success,
				action_failure: action_failure,
				last_response: last_response,
				creator_name: creator_name,
				create_time: create_time
			}

			$.ajax({
				url: '/message/semantic/insertSemantic',
				dataType: 'json',
				type: "post",
				data: JSON.stringify(postData),
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) {
					//              if (data.resp == '新增成功') {
					alert('新增成功')
					$('#myModal').modal('hide');

					//              }
				},
				error: function(data) {
					console.log(data);
				}
			});
		}

		//新增语义槽模板
		function addSure() {
			var temId = $('#semEditId').val();
			var template_code = $("#editModelCode").val();
			var template_name = $('#editModelName').val();
			var action_success = $('#editActionSuccess').val();
			var action_failure = $('#editActionFailure').val();
			var last_response = $('#editLastReponse').val();
			var action_url = $('#editActionUrl').val();
			var creator_name = $('#editCreatorname').val();
			var create_time = getTime();

			if(template_code == '' || template_code == null) {
				alert("语义模板代码不能为空");
				return false;
			}
			if(template_name == '' || template_name == null) {
				alert("语义模板名称不能为空");
				return false;
			}
			if(action_success == '' || action_success == null) {
				alert("业务调用成功话术不能为空");
				return false;
			}
			if(action_failure == '' || action_failure == null) {
				alert("业务调用失败话术不能为空");
				return false;
			}
			if(last_response == '' || last_response == null) {
				alert("缺省结束话术不能为空");
				return false;
			}
			if(action_url == '' || action_url == null) {
				alert("动作接口不能为空");
				return false;
			}
			if(creator_name == '' || creator_name == null) {
				alert("创建者不能为空");
				return false;
			}

			if($("#tab").find("tr").length == 0) {
				alert('请在语义槽里面插入至少一条数据，并完整填好');
				return false;
			}

			var len = $("#tab").find("tr").size();
			var postData = new Array();
			for(var i = 0; i < len; i++) {
				var cols0 = $("#tab").find("tr").eq(i).children().find('input').eq(0).val();
				var cols1 = $("#tab").find("tr").eq(i).children().find('input:[type="checkbox"]:checked').is(':checked');
				var cols2 = $("#tab").find("tr").eq(i).children().find('input').eq(2).val();
				var cols3 = $("#tab").find("tr").eq(i).children().find('input').eq(3).val();
				var cols4 = $("#tab").find("tr").eq(i).children().find('input').eq(4).val();
				var cols5 = $("#tab").find("tr").eq(i).children().find('input').eq(5).val();
				var cols6 = $("#tab").find("tr").eq(i).children().find('input').eq(6).val();
				var cols7 = $("#tab").find("tr").eq(i).children().find('input').eq(7).val();
				var cols8 = $("#tab").find("tr").eq(i).children().find('input').eq(8).val();
				var cols9 = $("#tab").find("tr").eq(i).children().find('input').eq(9).val();

				if(cols1 == true) {
					cols1 = 1;
				} else {
					cols1 = 0;
				}
				postData.push({
					temId: temId,
					template_code: template_code,
					template_name: template_name,
					action_success: action_success,
					action_failure: action_failure,
					last_response: last_response,
					action_url: action_url,
					create_time: create_time,
					creator_name: creator_name,

					slot_order: cols0,
					required: cols1,
					slot_name: cols2,
					slot_code: cols3,
					default_value: cols4,
					prompt: cols5,
					word_class: cols6,
					slot_value: cols7,
					try_count: cols8,
					slot_id: cols9,
				});
			}

			$.ajax({
				url: '/message/semantic/insertSemanticSlot',
				dataType: 'json',
				type: "post",
				data: JSON.stringify(postData),
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function(data) {
					console.log(data.resp);
					//      if (data.resp == '新增成功') {
					alert('修改成功')
					$('#myModal1').modal('hide');

					//      }
				},
				error: function(data) {
					console.log(data);
				}
			});
		}

		//点击增加一行
		function addLine() {
			var result = '';
			result += '<tr>';
			result += '<th><input value="" style="width: 100%;" class="num"/></th>';
			result += '<th><input type="checkbox" value="" style="width: 100%;height: 16px;"/></th>';
			result += '<th><input value="" style="width: 100%;" class="codeName"/></th>';
			result += '<th><input value="" style="width: 100%;" class="codes"/></th>';
			result += '<th><input value="" style="width: 100%;" class="codeVal"/></th>';
			result += '<th><input value="" style="width: 100%;" class="answer"/></th>';
			result += '<th><input value="" style="width: 100%;" class="wordClass"/></th>';
			result += '<th><input value="" style="width: 100%;" class="slotValue"/></th>';
			result += '<th><input value="" class="tryCount"/></th>';
			result += "<th style='display:none'><input value='0' style='width: 100%;'/></th>";
			result += '<th><button onclick="deLine(this)">×</button></th>';
			result += '</tr>';
			$(".addLine").append(result);
		}

		//点击删除一行
		function deLine(obj) {
			var tr = obj.parentNode.parentNode;
			var tbody = tr.parentNode;
			tbody.removeChild(tr);
		}