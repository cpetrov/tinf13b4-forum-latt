"use strict";

(function(){
	var app = angular.module("LoginApp", []);
	
	app.controller("LoginController", function($scope, $http){
		$scope.errors = {};
		
		$scope.hasError = function(key){
			return $scope.errors[key] ? "has-error" : "";
		};
		
		$scope.register = function(){
			$("form").prev("div.alert").remove();
			
			var name = $scope.name;
			var password = $scope.password;
			
			$scope.errors.name = !name || name.length < 1;
			
			if($scope.errors.name){
				showAlert("danger", "The Username should not be empty!");
				return;
			}
			
			$scope.errors.password = !password || password.length < 8;
			
			if($scope.errors.password){
				showAlert("danger", "The password must be atleast 8 characters long!");
				return;
			}
			
			$scope.isLoading = true;
			
			$http.post("api/login", {
				name: name,
				password: password,
			}).success(function(data){
				$scope.isLoading = false;
				
			}).error(function(){
				$scope.isLoading = false;
				
				//TODO - Errors response
			});
		};
	});
	
	function showAlert(type, message){
		var html = '<div class="alert alert-{type} alert-dismissable">{message}</div>';
		html = html.replace(/{type}/, type).replace(/{message}/, message);
		
		$("form").before(html);
	}
}());