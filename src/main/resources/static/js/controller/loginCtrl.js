'use strick';
angular
        .module("UmApp")
        .controller('loginCtrl', function ($scope, $http, $window, $location, loginService) {
            $scope.load = function () {
                $http.post('/api/uuid')
                        .success(function (response) {
                            console.log("saved");
                        }).error(function (responce) {
                    $scope.error = "Please check your login and password ";
                    console.log(response);
                });
                $http.get('api/uuid').success(function (response) {
                     console.log("get" + response);
                }).error(function (response) {
                    console.log("error" + response);
                })
            }
            $scope.login = function (user) {
                var token = 'Basic ' + btoa(user.login + ':' + user.password);
                $http.defaults.headers.common.Authorization = token;
                $http.get('api/auth').success(function (response) {
                    $window.sessionStorage.setItem('token', token);
                    $location.path('/home/users');
                }).error(function (response) {
                    $scope.error = "Please check your login and password ";
                    console.log(response);
                });
            };

        }
        );
        