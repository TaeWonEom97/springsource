/**
 * read.jsp와 관련됨
 */

//목록버튼
$(function(){
	
	let form=$("#actionForm");
	
	$(".btn-secondary").click(function(){
		location.href="/book/list";
	})
	
	//삭제버튼 클릭 시 전송(Get)
	$(".btn-danger").click(function(){
		form.attr("action","/book/remove");
		form.submit();
	})
	
	$(".btn-primary").click(function(){
		form.attr("action","/book/modify");
		form.submit();
		
	})
})
