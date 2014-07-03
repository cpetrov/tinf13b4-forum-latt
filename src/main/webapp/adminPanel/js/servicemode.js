$(document).ready(function(){
	$(function(){
		$("#myonoffswitch").on("click", function(){
		 var checked = $(this).prop("checked");
		 
		 $("#service_reason").prop("disabled", !checked);
		});
	});
	$(function(){
		$("#save").on("click", function(){
		$("#service_reason").prop("disabled", false);
		});
	});
});