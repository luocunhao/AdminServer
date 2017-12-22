$(function(){
	//	$('.uploadEdit').click(function(){
		$('body').on('click','.uploadEdit',function(){	
			var id = $(this).parent().siblings('td').eq(0).html();
			$('#editFileId').val(id);
			$('#fileName').val($(this).parent().siblings('td').eq(1).html());
			$('#fileType').val($(this).parent().siblings('td').eq(2).html());
			$('#fileUrl').val($(this).parent().siblings('td').eq(3).html());
			$('#fileTheam').val($(this).parent().siblings('td').eq(4).html());
			$('#uploadModal1').modal({show:true});
		});
		
})

function editFile(){
		var fileId = $('#editFileId').val();
		var fileTheam = $('#fileTheam').val();
		
		if(fileTheam == ''|| fileTheam == null){
			alert('广告主题不能为空');
			return false;
		}
		
		var postData = {
			media_id:fileId, 
			media_subject:fileTheam
		}
		
		$.ajax({
				url: '/message/updateFile',
				dataType: 'json',
				type: "post",
				data: JSON.stringify(postData),
				headers: {
					Accept: "application/json",
					"Content-Type": "application/json"
				},
				success: function (data) {
					if (data.resp == '修改成功') {
						alert('修改成功');
						$('#uploadModal1').modal('hide');
					}
				},
				error: function (data) {
					console.log(data);
				}
			});
	}
