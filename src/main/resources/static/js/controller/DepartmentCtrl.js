angular
        .module("UmApp")
        .controller('departmentsController', ['$scope', '$http', '$location', '$stateParams',
            function ($scope, $http, $location, $stateParams) {
                $http.get("http://localhost:8080/api/get/departments")
                        .then(function (response) {
                            $scope.departments = response.data;
                        });
                $scope.AddDepartment = function () {
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

                $scope.removeDepartment = function (index) {
                    $http.delete('/api/get/departments/' + $scope.departments[index].id)
                            .success(function (response, status, headers, config) {
                                console.log("lenght " + $scope.departments.length);
                                console.log("index " + index);
                                console.log("111  " + $scope.departments[index].id);
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

//                $scope.editDepartment = function () {
//                    console.log("--- " + $stateParams.id);
//                    $http.get("http://localhost:8080/api/get/departments/" + $stateParams.id)
//                        .then(function (otvet) {
//                                console.log("responce " + otvet);
//                                $scope.department = otvet.data;
//                            });
//                    $http.put('/api/post/departments/' + $stateParams.id, $scope.department)
//                            .success(function (data, status) {
//                                if (status === 200)
//                                {
//                                    $scope.messages = [{type: 'success', text: 'Save success'}];
//                                    $location.path('/departments');
//                                }
//                                if (status === 400)
//                                {
//                                    console.log('Something is wrong');
//                                }
//                            });
//                };

            }])

        .controller('editDepController', ['$scope', '$http', '$location', '$stateParams', function ($scope, $http, $location, $stateParams) {
                console.log("--- " + $stateParams.id);
                $http.get("http://localhost:8080/api/get/departments/" + $stateParams.id)
                        .then(function (response) {
                            console.log(response.data);
                            $scope.department = response.data;
                            console.log($scope.department);
                        });

                $scope.editDepartment = function () {
//                    $scope.department = {departmentName: 'default'};
                    $http.put('/api/post/departments/' + $stateParams.id, $scope.department)
                            .success(function (data, status) {
                                if (status === 200)
                                {
                                    $scope.messages = [{type: 'success', text: 'Save success'}];
//                                    $location.path('/departments');
                                }
                                if (status === 400)
                                {
                                    console.log('Something is wrong');
                                }
                            });
                };
            }
        ]);
