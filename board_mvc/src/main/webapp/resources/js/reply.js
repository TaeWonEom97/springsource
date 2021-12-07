/**
 *  reply 관련된 스크립트
 */

//자바스크립트 모듈화
let replyService = (function(){
	function add(reply,callback){
		console.log("add method 실행");
		
		$.ajax({
			url:'/replies/new',
			type:'post',
			contentType:'application/json',
			data:JSON.stringify(reply),
			success:function(result){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,err){
				error(err);
			}
		})
	}//add end
	
	 function getList(param,callback){
		let bno = param.bno;
		let page = param.page || 1;
		$.getJSON({
			url:'/replies/pages/'+bno+'/'+page,
			success:function(data){
				callback(data.replyCnt,data.list);
			}
		})
	}//getList end
	
	function remove(rno,callback,error){
		$.ajax({
			url:'/replies/'+rno,
			type:'delete',
			success:function(result){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,err){
				if(error){
					error(xhr.responseText);
				}
			}
		})
	}//remove end
	
	
	function update(reply,callback,error){
		$.ajax({
			url:'/replies/'+reply.rno,
			type:'put',
			contentType:'application/json',
			data:JSON.stringify(reply),
			success:function(data){
				if(callback){
					callback(data);
				}
			},
			error:function(xhr,status,err){
				if(error){
					error(xhr.responseText);
				}
			}
		})
	}//update end
	
	function get(rno,callback){
		$.getJSON({
			url:'/replies/'+rno,
			success:function(data){
				if(callback){
					callback(data);
				}
			}
		})
	}//get end
	
	//날짜 바꾸기 함수
	function displayTime(timeValue){
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dataObj = new Date(timeValue);
		var str="";
		
		// 댓글 단 날짜가 24시간보다 작다면 댓글 작성일을 시/분/초로 처리
		if(gap<(1000 * 60 * 60 * 24)){
			var hh=dataObj.getHours();
			var mi=dataObj.getMinutes();
			var ss=dataObj.getSeconds();
			
			return [(hh>9?'':'0')+hh,':',(mi>9?'':'0')+mi,':',(ss>9?'':'0')+ss].join('');
		}else{ //24시간보다 크기 때문에 년/월/일로 처리
			var yy = dataObj.getFullYear();
			var mm = dataObj.getMonth()+1;
			var dd = dataObj.getDate();
			
			return [yy, '/', (mm>9?'':'0')+mm,'/',(dd>9?'':'0')+dd].join('');
			
		}
	}//displayTime end
	
	
	return {
		add:add,
		getList:getList,
		remove:remove,
		update:update,
		get:get,
		displayTime:displayTime
	};
})();