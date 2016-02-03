angular.module("UmApp").controller('GetAllDepartmentCtrl', function ($scope, restDepartmentApiService) {
    $scope.departments = restDepartmentApiService.query();
    $scope.removeDepartment = function (index) {
        restDepartmentApiService.remove({id: $scope.departments[index].id}, function () {
            $scope.departments.splice(index, 1);
        });
    };
});

angular.module("UmApp").controller("AddEditDepartmentCtrl", function ($scope, $state, $stateParams, restDepartmentApiService) {
    var id = $stateParams.id;
    console.log(id);
    if (id === 'new') {
        $scope.department = new restDepartmentApiService();
    } else {
        $scope.department = restDepartmentApiService.get({id: id});
    }
    $scope.departments = restDepartmentApiService.query();
    $scope.editDepartment = function () {
        console.log(id);
        if (id === 'new') {
            console.log('creating');
            $scope.department.$create();
            $state.go('home.departments');
        } else {
            console.log('updating');
            $scope.department.$update();
            $state.go('home.departments');
        }
    };
});

//
//angular.module("UmApp").controller('departmentsController', function ($scope, $http, $location, $stateParams) {
//                $http.get("http://localhost:8080/api/get/departments")
//                        .then(function (response) {
//                            $scope.departments = response.data;
//                        });
//                $scope.AddDepartment = function () {
//                    $http.post("/api/post/departments", $scope.department).success(function (data, status) {
//                        if (status === 200)
//                        {
//                            $scope.messages = [{type: 'success', text: 'Save success'}];
//                            $location.path('/departments');
//                        }
//                        if (status === 400)
//                        {
//                            console.log('Something is wrong');
//                        }
//                    });
//                };
//
//                $scope.removeDepartment = function (index) {
//                    $http.delete('/api/get/departments/' + $scope.departments[index].id)
//                            .success(function (response, status, headers, config) {
//                                console.log("lenght " + $scope.departments.length);
//                                console.log("index " + index);
//                                console.log("111  " + $scope.departments[index].id);
//                                $scope.departments.splice(index, 1);
//                            })
//                            .error(function (response, status, headers, config) {
//                                if (status === 500)
//                                {
//                                    $scope.messages = [{type: 'error', text: 'in this have a child'}];
//                                }
//                                $scope.error_message = response.error_message;
//                            });
//                };
//
////                $scope.editDepartment = function () {
////                    console.log("--- " + $stateParams.id);
////                    $http.get("http://localhost:8080/api/get/departments/" + $stateParams.id)
////                        .then(function (otvet) {
////                                console.log("responce " + otvet);
////                                $scope.department = otvet.data;
////                            });
////                    $http.put('/api/post/departments/' + $stateParams.id, $scope.department)
////                            .success(function (data, status) {
////                                if (status === 200)
////                                {
////                                    $scope.messages = [{type: 'success', text: 'Save success'}];
////                                    $location.path('/departments');
////                                }
////                                if (status === 400)
////                                {
////                                    console.log('Something is wrong');
////                                }
////                            });
////                };
//
//            })
//
//        .controller('editDepController', ['$scope', '$http', '$location', '$stateParams', function ($scope, $http, $location, $stateParams) {
//                console.log("--- " + $stateParams.id);
//                $http.get("http://localhost:8080/api/get/departments/" + $stateParams.id)
//                        .then(function (response) {
//                            console.log(response.data);
//                            $scope.department = response.data;
//                            console.log($scope.department);
//                        });
//
//                $scope.editDepartment = function () {
////                    $scope.department = {departmentName: 'default'};
//                    $http.put('/api/post/departments/' + $stateParams.id, $scope.department)
//                            .success(function (data, status) {
//                                if (status === 200)
//                                {
//                                    $scope.messages = [{type: 'success', text: 'Save success'}];
////                                    $location.path('/departments');
//                                }
//                                if (status === 400)
//                                {
//                                    console.log('Something is wrong');
//                                }
//                            });
//                };
//            }
//        ]);
