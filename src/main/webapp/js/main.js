$(function() {
	"use strict";

	window.forum = window.forum || {};
	if (typeof CKEDITOR !== "undefined") {
		console.log("hi");
		forum.editor = CKEDITOR.instances.editor;
	}
	var layout = new forum.layout();
	layout.addListeners();
});