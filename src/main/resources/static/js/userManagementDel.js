$(function(){
	$('body').on('click','.userDelete',function(){
			var id = $(this).parent().siblings('td').eq(0).html();
			if (confirm("确定要删除吗")) {
				var id = id;
			} else {
				return;
			}
			var datas = { id: id };
			$.ajax({
				url: '/message/user/delete',
				dataType: 'json',
				type: "post",
				data: JSON.stringify(datas),
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function (data) {
					reload();
					alert('删除成功');
				},
				error: function (data) {
					console.log(data);
				}
			});
		});
	});
