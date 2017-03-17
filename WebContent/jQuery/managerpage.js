
$(document).ready(function(){
	
    
    //添加房间
    $("div.add input[type='button']").click(function(){
    	var data = {
                    floor:$("div.add .floor").val(),
                    number:$("div.add .number").val(),
                    size:$("div.add .size").val(),
                    price:$("div.add .price").val(),
                    status:$("div.add .status").val(),
                    };
        data = JSON.stringify(data);
        $.get('AddRoomServlet',{json:data},function(data1) {
        	if(data1.status== "true"){
        		$("div.add p.result").append("添加成功!");
        		$("li input[type='text']").val("");
        		setTimeout(function(){	$("p.result").html("");},1500);
        	}else{
        		$("div.add p.result").append("添加失败!");
        		setTimeout(function(){	$("p.result").html("");},1500);
        		}
        },"json")
    })         
   
    //删除房间
     $("div.delete input[type='button']").click(function(){
    	var data = {
                    floor:$("div.delete .floor").val(),
                    number:$("div.delete .number").val(),
                    };
        data = JSON.stringify(data);
        $.get('DeleteRoomServlet',{json:data},function(data1) {
        	if(data1.status== "true"){
        		$("div.delete p.result").append("删除成功!");
        		$("li input[type='text']").val("");
        		setTimeout(function(){	$("p.result").html("");},1500);
        	}else{
        		$("div.delete p.result").append("删除失败,房间不为空!");
        		$("li input[type='text']").val("");
        		setTimeout(function(){	$("p.result").html("");},1500);
        	}
        },"json")
    });
    
    
    //修改房间信息
     $("div.change input[type='button']").click(function(){
    	var data = {
                    floor:$("div.change .floor").val(),
                    number:$("div.change .number").val(),
                    price:$("div.change .price").val(),
                    status:$("div.change .status").val(),
                    };
        data = JSON.stringify(data);
        $.get('ChangeRoomServlet',{json:data},function(data1) {
        	if(data1.status== "true"){
        		$("div.add p.result").append("添加成功!");
        		$("li input[type='text']").val("");
        		setTimeout(function(){	$("p.result").html("");},1500);
        	}else{
        		$("div.add p.result").append("添加失败!");
        		$("li input[type='text']").val("");
        		setTimeout(function(){	$("p.result").html("");},1500);
        	}
        },"json")
    });
    
    //查询房间
     $("div.search1 input[type='button']").click(function(){
    	var data = {
                    floor:$("div.search1 .floor").val(),
                    number:$("div.search1 .number").val(),
                    };
        data = JSON.stringify(data);
        $.get('SearchRoomServlet',{json:data},function(data1) {
        	if(data1.roomId!=0){
        		var roomid = data1.roomId;
        		var floor = data1.floor;
        		var number = data1.number;
        		var price = data1.price;
        		var size = data1.size;
        		var status = data1.status;
        		$("li input[type='text']").val("");
        		$("div.search1 p.result").append("房间ID:"+roomid+"  楼层:"+floor+"  房号:"+number+"  户型:"+size+"  单价:"+price+"  入住情况:"+status);
        		$("li input[type='text']").focus(function(){
        			$("p.result").html("");
        		});
        	}else if(data1.status==="failure"){
        		$("div.search1 p.result").append("查找失败!");
        	}
        
        },"json")
    });
    
    
    
    
});