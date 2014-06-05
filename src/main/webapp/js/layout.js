(function() {
	"use strict";

	window.forum = window.forum || {};
	
	var ckUtil = forum.ckUtil;
	
	forum.layout = function() {
		this.newPostButton = $('button.newPost');
		this.quoteButton = $('.answerBody button');
	};
	
	var layout = forum.layout;

	layout.prototype = {
			
			updateBackground : function() {
			if ($(window).scrollTop() > 300) {
				if (!$("body").data("under300")) {
					$("body").data("under300", true);
					$("body").removeClass("notUnder300");
					$("body").addClass("under300");
				}
			} else {
				if ($("body").data("under300")) {
					$("body").data("under300", false);
					$("body").removeClass("under300");
					$("body").addClass("notUnder300");
				}
			}
		},
	
		addListeners : function() {
			var _this = this;
			$(window).scroll(function() {
				_this.updateBackground();
			});
			this.newPostButton.click(function() {
				_this.showNewPost();
			});
			this.quoteButton.click(function() {
				var quote = $(this).parent().find('.content').text();
				_this.showNewPost();
				ckUtil.setEditorQuote(quote);
			});
			if (forum.posted) {
				$(window).scrollTop($(document).height());
			}
			$(".ucp .userPictureHolder").click(function() {
				$("input[type='file']").trigger('click');
			});
			$("input[type='file']").change(function() {
				var holder=$(".ucp .userPictureHolder");
				holder.css('background-color', 'rgba(248, 79, 67, 0.75)');
				holder.css('background-image', 'none');
				holder.text("Picture will be changed.");
				holder.unbind('mouseenter mouseleave');
			});
			$(".ucp .userPictureHolder").hover(function() {
				$(this).css('background-color', 'rgba(248, 79, 67, 0.75)');
				$(this).css('background-image', 'none');
				$(this).text("Change picture");
			}, function() {
				$(this).css('background-color', '');
				$(this).css('background-image', '');
				$(this).text("");
			});
		},
		
		showNewPost : function() {
			$('div.newPost').show();
			this.scrollToBottom();
		},
		
		scrollToBottom: function() {
			$("body").animate({
				scrollTop : $(document).height()
			}, 1000, function() {
				forum.editor.focus();
			});
		}

	};

})();