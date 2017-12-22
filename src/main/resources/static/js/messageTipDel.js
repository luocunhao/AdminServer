$(function(){
	//删除
		$('body').on('click','.msgDelete',function(){

			var message_uuid = $(this).parent().siblings('td').eq(0).html();
			if (confirm("确定要删除吗")) {
				var message_uuid = message_uuid;
			} else {
				return;
			}
			var datas = { message_uuid: message_uuid };
			$.ajax({
				url: '/message/msg/deleteMessage',
				dataType: 'json',
				type: "post",
				data: JSON.stringify(datas),
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function (data) {
					reload();
					console.log(data.resp);
					if(data.resp == '删除成功'){
						alert('删除成功');
					}else if(data.resp == '删除失败'){
						alert('删除失败')
					}
					
				},
				error: function (data) {
					console.log(data);
				}
			});
		});
})