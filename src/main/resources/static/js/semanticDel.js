$(function(){
	$('body').on('click','.semDelete',function(){
            var id = $(this).parent().siblings('td').eq(0).html();
            if (confirm("确定要删除吗")) {
                    var template_id = id;
            } else {
                    return;
            }
            var datas = { template_id: id };
            $.ajax({
                    url: '/message/semantic/deleteSemantic',
                    dataType: 'json',
                    type: "post",
                    data: JSON.stringify(datas),
                    headers: {
                            Accept: "application/json",
                            "Content-Type": "application/json"
                    },
                    success: function (data) {
                            alert('删除成功');
                            location.reload(true);
                    },
                    error: function (data) {
                    //      if(data.status == 401){
                    //      console.log('权限不够')
                    //      $('.alert').show();
                    //    }
                    }
            })
    });

})