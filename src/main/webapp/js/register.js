"use strict";

(function(){
	var app = angular.module("RegisterApp", []);
	
	app.controller("RegisterController", function($scope, $http){
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
				showAlert("danger", "The username must be atleast 3 characters long!");
				return;
			}
			
			$scope.errors.email = !email || email.indexOf("@") === -1;
			// TODO - Email Validation Like Validator Bean
			
			if($scope.errors.email){
				showAlert("danger", "The email adress must be valid.");
				return;
			}
			
			$scope.errors.password = !password || password.length < 8;
			// TODO - Password Validation Like Validator Bean
			
			if($scope.errors.password){
				showAlert("danger", "The password must be atleast 8 characters long!");
				return;
			}
			
			$scope.errors.confirm = !confirm || password !== confirm;
			
			if($scope.errors.confirm){
				showAlert("danger", "The passwords do not match.");
				return;
			}
						 
			$scope.isLoading = true;
			
			$http.post("api/register", {
				name: name,
				email: email,
				password: password,
				confirm: confirm
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
