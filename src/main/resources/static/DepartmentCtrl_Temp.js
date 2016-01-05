angular
        .module("UmApp")
        .controller('departmentsControllers', ['$scope', '$http', '$location', function ($scope, $http, $location) {
                $http.get("http://localhost:8080/api/get/departments")
                        .then(function (response) {
                            $scope.departments = response.data;
                        });

                $scope.removeDepartment = function (index) {
                    $http.delete('/api/get/departments/' + $scope.departments[index].id)
                            .success(function (response, status, headers, config) {
                                console.log($scope.departments.length);
                                console.log(index);
                                console.log('/api/get/departments/' + $scope.departments[index].id);
                                $scope.departments.splice(index, 1);

                            })
                            .error(function (response, status, headers, config) {
                                if (status === 500)
                                {
                                    $scope.messages = [{type: 'error', text: 'in this have a child'}];
                                }
                                $scope.error_message = response.error_message;
                            });
                };
                $scope.editDepartment = function (index) {
//                    $location.path('/users/editUser');
                    $http.put("http://localhost:8080/api/post/departments/" + $scope.names[index].id, $scope.departments).success(function (data, status) {
                        if (status === 200)
                        {
                            $scope.messages = [{type: 'success', text: 'Save success'}];
                            $location.path('/departments');
                        }
                        if (status === 400)
                        {
                            console.log('Something is wrong');
                        }
                    });
                };

            }])

        .controller('addDepartmentCtrl', ['$scope', '$http', '$stateParams', '$location', function ($scope, $http, $stateParams, $location) {
                var id = $stateParams.id;
         $scope.department = {departmentName: 'default'};
//                if (id === 'new') {
//                   
//                } else {
//                    $http.get('/api/get/departments/' + id).success(function (response) {
//                        $scope.department = response;
//                    });
//                }
                $scope.UpdateDepartment = function () {
                    $http.post("/api/post/departments", $scope.department).success(function (data, status) {
                        if (status === 200)
                        {
                            $scope.messages = [{type: 'success', text: 'Save success'}];
                            $location.path('/departments');
                        }
                        if (status === 400)
                        {
                            console.log('Something is wrong');
                        }
                    });
                };
                $http.get("http://localhost:8080/api/get/departments")
                        .success(function (response) {
                            console.log(response);
                            $scope.departments = response;
                        });
            }
        ]);
      