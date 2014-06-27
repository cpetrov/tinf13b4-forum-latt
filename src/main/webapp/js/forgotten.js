"use strict";

(function(){
	var app = angular.module("ForgottenApp", []);
	
	app.controller("ForgottenController", function($scope, $http){
		$scope.errors = {};
		
		$scope.hasError = function(key){
			return $scope.errors[key] ? "has-error" : "";
		};
		
		$scope.forgotten = function(){
			$("form").prev("div.alert").remove();
			
			var name = $scope.name;
			var email = $scope.email;
			
			//$scope.errors.name = !name || name.length < 1;
			//$scope.errors.email = !email || email.indexOf("@") === -1;
			// TODO - Email Validation Like Validator Bean
			
			if(name || email){
				
				if(!name){
					name = "none";
				}
				
				if(!email){
					email = "none";
				}
			} else {
				showAlert("danger", "Insert username OR Mail Address");
				return;
			}
			
			$scope.isLoading = true;
			
			$http.post("api/forgotten", {
				name: name,
				email: email,
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
