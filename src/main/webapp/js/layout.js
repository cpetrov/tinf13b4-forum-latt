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