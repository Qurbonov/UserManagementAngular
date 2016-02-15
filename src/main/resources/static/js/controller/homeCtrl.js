angular
        .module("UmApp")
        .controller('homeCtrl', function ($scope, $http, $window) {
            $scope.logout = function () {
                delete $http.defaults.headers.common.Authorization;
                $window.sessionStorage.removeItem('token');
            };
        }
        );
