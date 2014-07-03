"use strict";

(function(){
	var app = angular.module("LoginApp", []);
	
	app.controller("LoginController", function($scope, $http){
		$scope.errors = {};
		
		$scope.hasError = function(key){
			return $scope.errors[key] ? "has-error" : "";
		};
		
		$scope.login = function(){
			$("div.alert").remove();
			$scope.errors = {};
			
			var username = $scope.username;
			var password = $scope.password;
			
//			$scope.errors.username = !username || username.length < 1;
//			
//			if($scope.errors.username){
//				showAlert("danger", "The Userusername should not be empty!");
//			}
//			
//			$scope.errors.password = !password || password.length < 8;
//			
//			if($scope.errors.password){
//				showAlert("danger", "The password must be at least 8 characters long!");
//			}
			
			$scope.isLoading = true;
			
			$http.post("api/login", {
				username: username,
				password: password,
			}).success(function(data){
				$scope.isLoading = false;
				
				if(data.errors){
					for(var i = 0; i < data.errors.length; i++){
						var error = data.errors[i];
						
						showAlert("danger", error);
					}
				} else {
					window.location.href = "/index.jsp";
				}
			}).error(function(){
				$scope.isLoading = false;
			});
		};
	});
	
	
	function showAlert(type, message){
		var html = '<div class="alert alert-{type} alert-dismissable">{message}</div>';
		html = html.replace(/{type}/, type).replace(/{message}/, message);
		
		$("form").before(html);
	}
}());
