$(document).ready(function() {
	 $("input[name='userType']").click(function(){
	    $("input[name='userType']").each(function(i,element){
             if(element.checked){
                $(".userType_view_"+element.value).show();
             }else{
             	 $(".userType_view_"+element.value).hide();
             }
        });
	 });
});