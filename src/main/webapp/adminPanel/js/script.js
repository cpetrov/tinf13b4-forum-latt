$(document).ready(function(){

//	Javascript Code for Servicemode.jsp
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
	
	
	var expandedNav = true;

	$('.expander').children('i').click(function() {

		if(expandedNav == true) {
			$('.navPt').hide();
			$('.subNav').hide();
			$('.admNav').css({'width' : '100px', 'left' : '-43px'});
			$('.expander').children('i').css({'margin-left' : '-7px'});
			$('.admNav').children('ul').css({'right':'0'});
			$('.admContent').css({'left' : '70px'});

			expandedNav = false;
		}
		else {
			$('.navPt').show();
			$('.subNav').show();
			$('.admNav').css({'width' : '234px', 'left' : '0'});
			$('.expander').children('i').css({'margin-left' : '160px'});
			$('.admNav').children('ul').css({'right':'40px'});
			$('.admContent').css({'left' : '234px'});

			expandedNav = true;
		}
	});

	$('.navIndex').click(function() {
		window.location.href = "index.jsp";
	});

	$('.navGeneral').click(function() {
		window.location.href = "general.jsp";
	});

	$('.navUser').click(function() {
		window.location.href = "user.jsp";
	});

	$('.navManage').click(function() {
		window.location.href = "boardmanagement.jsp";
	});

	$('.navSettings').click(function() {
		window.location.href = "settings.jsp";
	});

	$('.navDescription').click(function() {
		window.location.href = "pagedescription.jsp";
	});

	$('.navImpressum').click(function() {
		window.location.href = "impressumedit.jsp";
	});

	$('.navTermsofuse').click(function() {
		window.location.href = "termsofuse.jsp";
	});

	$('.navUserManage').click(function() {
		window.location.href = "usermanage.jsp";
	});

	$('.navEditCategories').click(function() {
		window.location.href = "editCategories.jsp";
	});
	
	$('.navServiceMode').click(function() {
		window.location.href = "servicemode.jsp";
	});
	
	$('.dashobj').hover(function(){
		$(this).children('p').animate({'background-color':'RGBA(0, 0, 0, 0.25)', 'color':'RGBA(255, 255, 255, 0.8)'}, 250);
	}, function() {
		$(this).children('p').animate({'background-color':'RGBA(0, 0, 0, 0)', 'color':'RGBA(255, 255, 255, 0)'}, 250);
	});

	$('.userList').children('tbody').children('tr').children('td').click(function() {

		var tdText = $(this).text();

		$(this).html('<input value="' + tdText + '"></input>');

		$(this).parent('tr').children('td').children('.editButton').parent('td').html('<i class="saveChanges fa fa-floppy-o"></i>');
	});
});
