	$(function(){
	//	$('.uploadDelete').click(function(){
		$('body').on('click','.uploadDelete',function(){		
			var id = $(this).parent().siblings('td').eq(0).html();
			if (confirm("确定要删除吗")) {
				var id = id;
			} else {
				return;
			}
			var datas = { media_id: id };
			$.ajax({
				url: '/message/deleteFile',
				dataType: 'json',
				type: "post",
				data: JSON.stringify(datas),
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function (data) {
					alert('删除成功');
				},
				error: function (data) {
					console.log(data);
				}
			});
		});
	});
	
	
	
	
	

