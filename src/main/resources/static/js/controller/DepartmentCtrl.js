angular.module("UmApp").controller('GetAllDepartmentCtrl', function ($scope, restAllDepartmentApiService, restDepartmentApiService) {
    $scope.departments = restAllDepartmentApiService.query();
    $scope.removeDepartment = function (index) {
        restDepartmentApiService.remove({id: $scope.departments[index].id}, function () {
        
            $scope.departments.splice(index, 1);
        });
    };
});

angular.module("UmApp").controller("AddEditDepartmentCtrl",
        function ($scope, restAllDepartmentApiService, $uibModalInstance, $state, $stateParams, restDepartmentApiService, departmentId) {
//            $uibModalInstance.close(departmentId);
            var id = departmentId;
            if (id === 0) {
                $scope.department = new restDepartmentApiService();
                console.log("createww");
            } else {
                $scope.department = restDepartmentApiService.get({id: departmentId});
                console.log("update");
            }
            $scope.editDepartment = function () {
                if (id === 0) {
                    $scope.department.$create();
                    debugger;
                    $state.go('home.departments');
                    $uibModalInstance.dismiss('cancel');
                } else {
                    $scope.department.$update();
                    $state.go('home.departments');
                    $uibModalInstance.dismiss('cancel');
                }
            };
            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
            $scope.departments = restAllDepartmentApiService.query();
        });
