// JavaScript Document
$(function(){
	//导航切换
		$(".nav_box li").click(function(){
			$(".nav_box li").removeClass("nav_active");
			$(this).addClass("nav_active");
			})
	//小导航切换
	$(".right_nav li").click(function(){
			$(".right_nav li").removeClass("list_active");
			$(this).addClass("list_active");
			})
	//关闭右边二级导航
	$(".close_click2").click(function(){
		$(".list_active").hide();
		})
	//左侧切换
		$(".left_box li").click(function(){
		$(".left_box  li").removeClass("active_type");
		$(this).addClass("active_type");
		})	
    //表格单双行变色
		$(".list_edit:even").css("background","#f7f7f7");
		$(".list_edit:odd").css("background","#fff;");
	//添加点播
		 $(".add_click").click(function(){
			 $(".page-shadow").show();
			 $(".add_pop").show();
		 })
	  $(".close_click").click(function(){
		  $(".page-shadow").hide();
		  $(".add_pop").hide();
		  })
	//添加管理员
		 $(".add_admin").click(function(){
			$(".page-shadow").show(); 
		 	$(".admin_pop").show();
		 })
		 $(".close_click").click(function(){
			  $(".page-shadow").hide();
		  	  $(".admin_pop").hide();
		  })
	//添加角色
		 $(".add_name").click(function(){
			  $(".page-shadow").show();
		  	  $(".rlole_pop").show();
		  })
	    $(".close_click").click(function(){
			 $(".page-shadow").hide();
		     $(".rlole_pop").hide();
		  })
	//添加权限
		$(".add_qx").click(function(){
		   $(".page-shadow").show();
		   $(".jurisd_pop").show();
		   })
		$(".close_click").click(function(){
		   $(".page-shadow").hide();
		   $(".jurisd_pop").hide();
		  })
	//编辑弹窗
		$(".edit_btn").click(function(){
			$(".page-shadow").show();
			$(".edit_pop").show();
		})
		$(".close_click,.cancel_btn").click(function(){
			 $(".page-shadow").hide();
		     $(".edit_pop").hide();	
		})
//拒绝弹窗
		$(".no_click").click(function(){
			$(".page-shadow").show();
			$(".no_pop").show();
		})
		$(".cancel_btn").click(function(){
			 $(".page-shadow").hide();
		     $(".no_pop").hide();	
		})		
		  
	//单选
		$(".check_single").click(function(){
			 if($(this).next().attr("checked"))
			     {
				 $(this).removeClass("check_on");
				 $(this).next().removeAttr("checked");
				 }
			else{
				$(this).addClass("check_on");
				$(this).next().attr("checked",true);
				}
			
			var num=0;
			$(".check_single").each(function(){
				 if($(this).next().attr("checked"))
				     {
					 num++;
					 }
			})
			if(num==$(".check_single").length)
			{
				$(".checkbox_all").addClass("check_on");
				$(".checkbox_alls").attr("checked",true);
			}
			else{
				$(".checkbox_all").removeClass("check_on");
				$(".checkbox_alls").removeAttr("checked");
				
				}
			
		})		
		
	//全选、反选
		$(".checkbox_all").click(function(){
			if($(".checkbox_alls").attr("checked")){
				$(".check_single,.checkbox_all").removeClass("check_on");
				$(".check_input,.checkbox_alls").removeAttr("checked")
				}
			else{
				$(".check_single,.checkbox_all").addClass("check_on");
				$(".check_input,.checkbox_alls").attr("checked",true)
				
				}
		
			
			})
			
			
	//权限【点播单选】
		$(".check_single2").click(function(){
				if($(this).next().attr("checked")){
					$(this).removeClass("check_on");
					$(this).next().removeAttr("checked");
					}
				else{
					$(this).addClass("check_on");
					$(this).next().attr("checked",true);	
					}
				var num2=0;
				$(".check_single2").each(function(){
					if($(this).next().attr("checked")){
						num2++;
						}
				})
				if(num2==$(".check_single2").length){
					$(".checkbox_all2").addClass("check_on");
					$(".checkbox_alls2").attr("checked",true);
					}
				else{
					$(".checkbox_all2").removeClass("check_on");
					$(".checkbox_alls2").removeAttr("checked");
					}
		 })
	//权限【点播全选】
		$(".checkbox_all2 ").click(function(){
			if($(".checkbox_alls2").attr("checked")){
				$(".check_single2,.checkbox_all2").removeClass("check_on");
				$(".check_single2,.checkbox_alls2").removeAttr("checked");
				}
			else{
				$(".check_single2,.checkbox_all2").addClass("check_on");
				$(".check_single2,.checkbox_alls2").attr("checked",true);
				}
			})			
    //发布管理【单选】
		$(".check_single3").click(function(){
			if($(this).next().attr("checked"))
			{
				$(this).removeClass("check_on");
				$(this).next().removeAttr("checked");
			}
			else
			{
				$(this).addClass("check_on");
				$(this).next().attr("checked",true);
			}
			var num3=0;
			$(".check_single3").each(function(){
				if($(this).next().attr("checked"))
				{
					num3++;
				}	
			})
			if(num3==$(".check_single3").length)
			{
				$(".checkbox_all3").addClass("check_on");
				$(this).next().attr("checked",true);
			}
			else{
				$(".checkbox_all3").removeClass("check_on");
				$(this).next().attr("checked");
				
				}
			
		 })	
	//发布管理【全选】
	    $(".checkbox_all3").click(function(){
			 if($(this).next().attr("checked"))
			 {
				$(".checkbox_all3,.check_single3").removeClass("check_on");
				$(".checkbox_alls3,.check_input3").removeAttr("checked"); 
			 }
			 else
			 {
				$(".checkbox_all3,.check_single3").addClass("check_on");
				$(".checkbox_alls3,.check_input3").attr("checked",true); 
			 } 
		  })
	//数据统计【单选】		
		$(".check_single4").click(function(){
			if($(this).next().attr("checked"))
			{
				$(this).removeClass("check_on");
				$(this).next().removeAttr("checked");
			}
			else{
				$(this).addClass("check_on");
				$(this).next().attr("checked",true);
				}
			var num4=0;
			$(".check_single4").each(function(){
				if($(this).next().attr("checked")){
					 num4++;
					}
				
				})
			if(num4==$(".check_single4").length)
			{
				$(".checkbox_all4").addClass("check_on");
				$(this).attr("checked",true)
			}
			else{
				$(".checkbox_all4").removeClass("check_on");
				$(this).removeAttr("checked")
				
				}
			
		})
	//数据统计【全选】
	    $(".checkbox_all4").click(function(){
			if($(this).next().attr("checked"))
			{
				$(".check_single4,.checkbox_all4").removeClass("check_on");
				$(".check_input4,.checkbox_alls4").removeAttr("checked")		
			}
			else{
				
				$(".check_single4,.checkbox_all4").addClass("check_on");
				$(".check_input4,.checkbox_alls4").attr("checked",true);
			}
		  })	
    //移动管理【单选】
		$(".check_single5").click(function(){
			if($(this).next().attr("checked")){
				$(this).removeClass("check_on");
				$(this).next().removeAttr("checked");
				}
			else{
				$(this).addClass("check_on");
				$(this).next().attr("checked",true);
				}
		var num5=0;
			$(".check_single5").each(function(){
				if($(this).next().attr("checked"))
				{
					num5++;
				}
			})
			if(num5==$(".check_single5").length){
				$(".checkbox_all5").addClass("check_on");
				$(".checkbox_alls5").attr("checked",true);
				}
			else{
				
				$(".checkbox_all5").removeClass("check_on");
				$(".checkbox_alls5").removeAttr("checked");
				}	
		})  
	//移动管理【全选】
	 	$(".checkbox_all5").click(function(){
			if($(this).next().attr("checked"))
			{
				$(".check_single5,.checkbox_all5").removeClass("check_on");
				$(".check_input5,.checkbox_alls5").removeAttr("checked")		
			}
			else{
				$(".check_single5,.checkbox_all5").addClass("check_on");
				$(".check_input5,.checkbox_alls5").attr("checked",true);
				
				}
			})
	 		
	 		
})