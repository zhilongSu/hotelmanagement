
$(document).ready(function(){
	//预订弹框
    $("li a.change").click(function(){
        $("div.change_div").show();
    });
    $("div.change_div input.submit").click(function(){
        $("div.change_div").hide();
    });
    $("div.change_div input.no").click(function(){
        $("div.change_div").hide();
    });
    //修改订单弹框
    $("li a.stop").click(function(){
        $("div.stop_div").show();
    });
    $("div.stop_div input.submit").click(function(){
        $("div.stop_div").hide();
    })
    $("div.stop_div input.no").click(function(){
        $("div.stop_div").hide();
    });
    
    //下单
    $("input.smal1submit").click(function(){
    	var data = {
                    account:$("#account").val(),
                    size:"单人房",
                    start:$("#start").val(),
                    stop:$("#stop").val()                   
    				};
        data = JSON.stringify(data);
        var account1 = $("#account").val();
        $.post('RegisterRoomServlet',{json:data},function(data1) {
        	if(data1.money=== 0){
        		alert("下单失败！");
        	}else{
        		var string ='';
        		for(i =0;i<data1.arr.length;i++){
        			
        			string = string+data1.arr[i].floor+"楼"+data1.arr[i].number+"房 |";
        		}       		
        		alert("您的房间是："+string);
        		$("#account").val('');
        		$("#start").val('');
        		$("#stop").val('');                   
        		}
        },"json");
        });
        
        $(".middleSubmit").click(function(){
        	var data = {
                        account:$("#account1").val(),
                        size:"双人房",
                        start:$("#start1").val(),
                        stop:$("#stop1").val()                   
        				};
            data = JSON.stringify(data);
            var account1 = $("#account1").val();
            $.get('RegisterRoomServlet1',{json:data},function(data1) {
            	if(data1.money=== 0){
            		alert("下单失败！");
            	}else{
            		var string ='';
            		for(i =0;i<data1.arr.length;i++){
            		
            			string = string+data1.arr[i].floor+"楼"+data1.arr[i].number+"房 |";
            		}       		
            		alert("您的房间是："+string);
            		$("#account1").val('');
            		$("#start1").val('');
            		$("#stop1").val('');    
            	}
            	},"json");
            });
        
        $(" input.bigsubmit").click(function(){
        	var data = {
                        account:$("#account2").val(),
                        size:"套房",
                        start:$("#start2").val(),
                        stop:$("#stop2").val()                   
        				};
            data = JSON.stringify(data);
            var account1 = $("#account2").val();
            $.get('RegisterRoomServlet2',{json:data},function(data1) {
            	if(data1.money=== 0){
            		alert("下单失败！");
            	}else{
            		var string ='';
            		for(i =0;i<data1.arr.length;i++){
            			
            			string = string+data1.arr[i].floor+"楼"+data1.arr[i].number+"房 |";
            		}       		
            		alert("您的房间是："+string);
            		$("#account2").val('');
            		$("#start2").val('');
            		$("#stop2").val('');    
            	}
            },"json");
            });
        
        //退房
        $(".stop_div input.submit").click(function(){
        	var data = {
                    floor:$(".stop_div .floor").val(),
                    number:$(".stop_div .number").val() 
    				};
        	data = JSON.stringify(data);
        	$.get('stopRoomServlet',{json:data},function(data){
        		if(data.status=="true"){
        			$(".stop_div .floor").val('');
        			$(".stop_div .number").val('') ;
        			alert("退房成功");
        		}
        	},"json");
        	
        	
        	
        });
       
        	
    
    })
    
    
    
    
    
    
    
