           var commons=(function(){
        	   //复选框全选控制，o为jquery对象
        	   var checkAll=function(o){
        	          o.change(function () {
        	               var set = jQuery(this).attr("data-set");
        	               var checked = jQuery(this).is(":checked");
        	               jQuery(set).each(function () {
        	                   if (checked) {
        	                       $(this).attr("checked", true);
        	                   } else {
        	                       $(this).attr("checked", false);
        	                   }
        	               });
        	               jQuery.uniform.update(set);
        	           });
        	   };
        	   var c=function(){
        		   alert("cccc")
        	   };
        	   var msg=function (content) {
        		    $(".trigs-content").remove();
        		    $(".trigs-in").remove();
        		    var con = '<div class="trigs-content"';
        		    
        		    con+='>';
        		    con += '<div class="trigs-title"><span class="trigs-title-c ">' + content.title + '</span><span class="trigs-close ">X</span></div>';
        		    con += '<div class="trigs-body">';
        		    con += content.content;
        		    con += '</div></div><div class="trigs-in"></div>';
        		    $('body').append($(con));
        		    if(content.css){
        		        $('.trigs-content').css(content.css);
        		    }
        		    if (content.time !== 0) {
        		        var time = 3000;
        		        if (content.time) {
        		            time = content.time;
        		        }
        		        clearTimeout(t);
        		        var t = setTimeout(function () {
        		            $(".trigs-content").remove();
        		            $(".trigs-in").remove();	
        		            if(content.location){
        		                window.location.href = content.location;
        		            }
        		        }, content.time);
        		    }
        		    $(".trigs-close").off('click').on('click',function(){
        		    	$(".trigs-content").remove();
     		            $(".trigs-in").remove();
        		    })
        		};       	   
        		
        	   return {
        		   checkAll:checkAll,
        		   msg:msg,
        		   c:c
        	   }
           }())
           
