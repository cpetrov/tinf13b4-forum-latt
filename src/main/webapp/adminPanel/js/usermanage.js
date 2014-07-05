"use strict";

var app = angular.module('UsermanageApp', ['ngTable']).
controller('UsermanageController', function($scope, ngTableParams, $sce) {
	var data = [{}];
	
    $scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10           // count per page
    }, {
        total: data.length, // length of data
        getData: function($defer, params) {
            $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        }
    });
});
