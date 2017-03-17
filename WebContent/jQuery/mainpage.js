
$(document).ready(function(){
	//选项卡
    $(".left span").click(function(){
        $(this).addClass('highlight').siblings("span").removeClass('highlight');
        var index = $(".left span").index(this);

        $(".left div").eq(index).show().siblings('div').hide();
    });
    
    //登陆
    $(".btn1").click(function(){

        var data = {
                    name:$(".login .login-name").val(),
                    password:$(".login .login-password").val()
                    };
        data = JSON.stringify(data);
        $.get('loginServlet',{json:data},function(data1) {
        	if(data1.status== "manager"){
        		window.location.href = "managerpage.html";
        	}else if(data1.status== "customer"){
        		window.location.href = "selfpage.html";
        	}else if(data1.status=="failure"){
        		$(".login input[type='text']").val("");
        		alert("登录失败，请重试")
             setTimeout(function(){
            	 $(".login p").remove();
             },1500);
             
        	}

        },"json");
        return false;
    });
    
    //注册
    $(".btn3").click(function(){

        var data = {
                    name:$(".register .register-name").val(),
                    password:$(".register .register-password").val(),
                    phone:$(".register .register-phone").val()
                    };
        data = JSON.stringify(data);
        $.get('registerServlet',{json:data},function(data1) {
       
        	if(data1.success== "success"){
        		$(".register").children().hide();
        		$(".register").html("<br/><br/><br/><br/><br/><br/>注册成功，两秒后，重新登陆！");      		
        		
        		setTimeout(function(){
        			$("span.highlight").removeClass("highlight").siblings("span").addClass('highlight');
        			$(".login").show();
        		       
        		},2000);      		
        	}
        },"json")
        return false;
    });
    

    
 
});