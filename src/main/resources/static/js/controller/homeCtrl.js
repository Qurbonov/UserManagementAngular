angular
        .module("UmApp")
        .controller('homeCtrl', ['$scope', '$http', function ($scope, $http) {
                $http.get("http://localhost:8080/api/get/users")
                        .then(function (response) {
                            $scope.names = response.data;
                        });
            }
        ]);
