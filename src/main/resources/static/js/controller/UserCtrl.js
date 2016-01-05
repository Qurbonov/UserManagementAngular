angular
        .module("UmApp")

        .controller('usersController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
                $http.get("http://localhost:8080/api/get/users")
                        .then(function (response) {
                            $scope.names = response.data;
                        });

                $scope.removeUser = function (index) {
                    $http.delete('/api/users/' + $scope.names[index].id)
                            .success(function (response, status, headers, config) {
                                console.log($scope.names.length);
                                console.log(index);
                                console.log('/api/users/' + $scope.names[index].id);
                                $scope.names.splice(index, 1);
                            })
                            .error(function (response, status, headers, config) {
                                $scope.error_message = response.error_message;
                            });
                };
                $scope.editUser = function (index) {
                    $location.path('/users/editUser');
                    $http.put("http://localhost:8080/api/post/users/" + $scope.names[index].id, $scope.user).success(function (data, status) {
                        if (status === 200)
                        {
                            $scope.messages = [{type: 'success', text: 'Save success'}];
                            $location.path('/users');
                        }
                        if (status === 400)
                        {
                            console.log('Something is wrong');
                        }
                    });
                };

            }])

        .controller('addUserCtrl', ['$scope', '$http', '$stateParams', '$location', function ($scope, $http, $stateParams, $location) {
                var id = $stateParams.id;
                if (id === 'new') {
                } else {
                    $http.get("http://localhost:8080/api/get/users/" + id)
                            .then(function (response) {
                                $scope.user = response.data;
                            });
                }
                $scope.sendPost = function () {
                    $http.post("http://localhost:8080/api/post/users", $scope.user).success(function (data, status) {
                        if (status === 200)
                        {
                            $scope.messages = [{type: 'success', text: 'Save success'}];
                            $location.path('/users');
                        }
                        if (status === 400)
                        {
                            console.log('Something is wrong');
                        }
                    });
                };

                $http.get("http://localhost:8080/api/get/departments")
                        .then(function (response) {
                            $scope.departments = response.data;
                        });
                $http.get("http://localhost:8080/api/get/modules")
                        .then(function (response) {
                            $scope.modules = response.data;
                        });
                $http.get("http://localhost:8080/api/get/roles")
                        .then(function (response) {
                            $scope.roles = response.data;
                        });
            }
        ]);
      