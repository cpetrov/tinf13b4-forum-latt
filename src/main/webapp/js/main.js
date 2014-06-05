$(function() {
	"use strict";

	window.forum = window.forum || {};
	if (typeof CKEDITOR !== "undefined") {
		forum.editor = CKEDITOR.instances.editor;
	}
	var layout = new forum.layout();
	layout.addListeners();
});