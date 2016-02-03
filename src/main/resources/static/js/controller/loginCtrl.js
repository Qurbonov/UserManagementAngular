'use strick';
angular
        .module("UmApp")
        .controller('loginCtrl', function ($scope, $http, $window, $location, loginService) {
            $scope.login = function (user) {
                 console.log("response");
                 var eSigner = getESigner();
                            if (!eSigner.signDigest) {
                                event.preventDefault();
                                alert('Sorry, esigner is not installed.');
                            }
                
                
                
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
        