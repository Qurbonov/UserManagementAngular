angular.module("umApp").controller('GetAllDepartmentCtrl', function ($scope, restAllDepartmentApiService, restDepartmentApiService) {
    $scope.departments = restAllDepartmentApiService.query();
    $scope.removeDepartment = function (index) {
        restDepartmentApiService.remove({id: $scope.departments[index].id}, function () {
            $scope.departments.splice(index, 1);
        });
    };
});

angular.module("umApp").controller("AddEditDepartmentCtrl",
        function ($scope, restAllDepartmentApiService, $uibModalInstance, restDepartmentApiService, departmentId) {
            var id = departmentId;
            if (id === 0) {
                $scope.department = new restDepartmentApiService();
            } else {
                $scope.department = restDepartmentApiService.get({id: departmentId});
            }
            $scope.editDepartment = function () {
                if (id === 0) {
                    $scope.department.$create();
                    $uibModalInstance.dismiss('cancel');
                } else {
                    $scope.department.$update();
                    $uibModalInstance.dismiss('cancel');
                }
            };
            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
            $scope.departments = restAllDepartmentApiService.query();
        });
