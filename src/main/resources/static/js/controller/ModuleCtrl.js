 angular
        .module("UmApp")

        .controller('moduleController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
                $http.get("http://localhost:8080/api/get/modules")
                        .then(function (response) {
                            $scope.restModules = response.data;
                        });

                $scope.removeModule = function (index) {
                    $http.delete('/api/modules/' + $scope.restModules[index].id)
                            .success(function (response, status, headers, config) {
                                console.log($scope.restModules.length);
                                console.log(index);
                                console.log('/api/modules/' + $scope.restModules[index].id);
                                $scope.restModules.splice(index, 1);
                            })
                            .error(function (response, status, headers, config) {
                                $scope.error_message = response.error_message;
                            });
                };
                $scope.editUser = function (index) {
                    $location.path('/module/editModule');
                    $http.put("http://localhost:8080/api/post/modules/" + $scope.names[index].id, $scope.user).success(function (data, status) {
                        if (status === 200)
                        {
                            $scope.messages = [{type: 'success', text: 'Save success'}];
                            $location.path('/modules');
                        }
                        if (status === 400)
                        {
                            console.log('Something is wrong');
                        }
                    });
                };

            }])

        .controller('addModuleCtrl', ['$scope', '$http', '$stateParams', '$location', function ($scope, $http, $stateParams, $location) {
//                var id = $stateParams.id;
//                if (id === 'new') {
//                } else {
//                    $http.get("http://localhost:8080/api/get/modules/" + id)
//                            .then(function (response) {
//                                $scope.module = response.data;
//                            });
//                }
                $scope.sendPost = function () {
                    $http.post("http://localhost:8080/api/post/modules", $scope.module).success(function (data, status) {
                        if (status === 200)
                        {
                            $scope.messages = [{type: 'success', text: 'Save success'}];
                            $location.path('/modules');
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
      