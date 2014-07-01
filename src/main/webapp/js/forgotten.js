"use strict";

(function(){
	var app = angular.module("ForgottenApp", []);
	
	app.controller("ForgottenController", function($scope, $http){
		$scope.errors = {};
		
		$scope.hasError = function(key){
			return $scope.errors[key] ? "has-error" : "";
		};
		
		$scope.submit = function(){
			$("div.alert").remove();
			
			var username = $scope.username;
			var emailAddress = $scope.emailAddress;
			
			if($scope.forgotten.$valid){
				
				if(username && emailAddress) {
					showAlert("danger", "Insert username OR E-Mail address!");
					return;
				} else {
					
					// Catch Nullpointer Exception
					if(!username){
						username = "none";
					}
					
					// Catch Nullpointer Exception
					if(!emailAddress){
						emailAddress = "none";
					}
				}
				
				$scope.isLoading = true;
				
				$http.post("api/forgotten", {
					username: username,
					emailAddress: emailAddress,
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
