(function() {
	"use strict";

	window.forum = window.forum || {};
	forum.ckUtil = forum.ckUtil || {};

	var ckUtil = forum.ckUtil;

	ckUtil.moveCarretToEndOfInput = function() {
		var range = forum.editor.createRange();
		range.moveToElementEditEnd(range.root);
		forum.editor.getSelection().selectRanges([ range ]);
	};

	ckUtil.setEditorQuote = function(quote) {
		forum.editor.setData("<blockquote>" + quote + "</blockquote><br/>",
				function() {
					ckUtil.moveCarretToEndOfInput();
				});
	};
})();