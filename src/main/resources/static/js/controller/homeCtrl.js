angular
        .module("UmApp")
        .controller('homeCtrl', function ($scope, $http, $window) {
            $http.get("api/users")
                    .then(function (response) {
                        $scope.names = response.data;
                    });
            $scope.logout = function () {
                delete $http.defaults.headers.common.Authorization;
                $window.sessionStorage.removeItem('token');
            };
        }
        );
