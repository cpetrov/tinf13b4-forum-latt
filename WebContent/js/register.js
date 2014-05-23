"use strict";

(function(){
	var app = angular.module("RegisterApp", []);
	
	app.controller("RegisterController", function($scope){
		$scope.errors = {};
		
		$scope.hasError = function(key){
			return $scope.errors[key] ? "has-error" : "";
		};
		
		$scope.register = function(){
			$("form").prev("div.alert").remove();
			
			var name = $scope.name;
			var email = $scope.email;
			var password = $scope.password;
			var confirm = $scope.confirm;
			$scope.errors.name = !name || name.length < 3;
			
			if($scope.errors.name){
				showAlert("danger", "Der Benutzername muss mindestens 3 Zeichen lang sein");
				return;
			}
			
			$scope.errors.email = !email || email.indexOf("@") === -1;
			
			if($scope.errors.email){
				showAlert("danger", "Die Email Adresse muss gültig sein");
				return;
			}
			
			$scope.errors.password = !password || password.length < 8;
			
			if($scope.errors.password){
				showAlert("danger", "Das Passwort muss mindestens 8 Zeichen lang sein");
				return;
			}
			
			$scope.errors.confirm = !confirm || password !== confirm;
			
			if($scope.errors.confirm){
				showAlert("danger", "Die Passwörter stimmen nicht überein");
				return;
			}
		};
	});
	
	function showAlert(type, message){
		var html = '<div class="alert alert-{type} alert-dismissable"><button type="button" class="close" data-dismiss="alert">&times;</button>{message}</div>';
		html = html.replace(/{type}/, type).replace(/{message}/, message);
		
		$("form").before(html);
	}
}());