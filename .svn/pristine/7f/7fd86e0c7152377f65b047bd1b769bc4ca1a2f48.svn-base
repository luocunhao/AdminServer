$(function(){
	$('body').on('click', '.verDelete', function() {
		var id = $(this).parent().siblings('td').eq(0).html();
		if (confirm("确定要删除吗")) {
			var id = id;
		} else {
			return;
		}
		var datas = {
			id : id
		};
		$.ajax({
			url : '/message/verbal/delete',
			dataType : 'json',
			type : "post",
			data : JSON.stringify(datas),
			headers : {
				Accept : "application/json",
				"Content-Type" : "application/json"
			},
			success : function(data) {
				alert('删除成功');
				location.reload(true);
			},
			error : function(data) {
				console.log(data.status);
				if (data.status == 401) {
					console.log('权限不够');
					$('.alert').show();
				}
			}
		});
	});
})