var app = angular.module('usermanageApp', ['ngTable']).
        controller('UsermanageController', function($scope, ngTableParams, $sce) {
            var data = [{Id: "1", Name: "Dummy", Mail: "Dummy@Dummy.de", Picture: "DummyPic", Confirmed: "true"},
                        ];
            
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