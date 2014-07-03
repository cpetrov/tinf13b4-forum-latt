"use strict";

(function(){
	var app = angular.module("RegisterApp", []);
	
	app.controller("RegisterController", function($scope, $http){
		$scope.errors = {};
		
		$scope.hasError = function(key){
			return $scope.errors[key] ? "has-error" : "";
		};
		
		$scope.register = function(){
			$("div.alert").remove();
			$scope.errors = {};
			
			var username = $scope.username;
			var emailAddress = $scope.emailAddress;
			var password = $scope.password;
			var confirm = $scope.confirm;
			$scope.errors.username = !username || username.length < 3;
			
			if($scope.errors.username){
				showAlert("danger", "The userusername must be atleast 3 characters long!");
			}
			
			$scope.errors.emailAddress = !emailAddress || emailAddress.indexOf("@") === -1;
			// TODO - emailAddress Validation Like Validator Bean
			
			if($scope.errors.emailAddress){
				showAlert("danger", "The emailAddress adress must be valid.");
			}
			
			$scope.errors.password = !password || password.length < 8;
			// TODO - Password Validation Like Validator Bean
			
			if($scope.errors.password){
				showAlert("danger", "The password must be atleast 8 characters long!");
			}
			
			$scope.errors.confirm = !confirm || password !== confirm;
			
			if($scope.errors.confirm){
				showAlert("danger", "The passwords do not match.");
			}
						 
			$scope.isLoading = true;
			
			$http.post("api/register", {
				username: username,
				emailAddress: emailAddress,
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
