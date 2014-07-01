"use strict";

(function(){
	var app = angular.module("RegisterApp", []);
	
	app.controller("RegisterController", function($scope, $http){
		$scope.errors = {};
		
		$scope.hasError = function(key){
			return $scope.errors[key] ? "has-error" : "";
		};
		
		$scope.submit = function(){
			$("div.alert").remove();
			
			if($scope.register.$valid){
				$scope.isLoading = true;
				
				$http.post("api/register", {
					username: $scope.username,
					emailAddress: $scope.emailAddress,
					password: $scope.password,
					confirm: $scope.confirm,
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
			}
		};
	});
	
	function showAlert(type, message){
		var html = '<div class="alert alert-{type} alert-dismissable">{message}</div>';
		html = html.replace(/{type}/, type).replace(/{message}/, message);
		
		$("form").before(html);
	}
}());
