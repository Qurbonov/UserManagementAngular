angular.module("UmApp")
        .controller('treeCtrl', function ($scope, $uibModal, restDepartmentApiService) {
            $scope.departments = restDepartmentApiService.query();
            $scope.departmentTreeOptions = {
                isLeaf: function (department) {
                    return !department.hasChildren;
                }
            };
            $scope.expandedDepartments = [];
            $scope.showSelected = function (department) {
                if (department.hasChildren && !department.children) {
                    department.children = restDepartmentApiService.children({id: department.id}, function () {
                        $scope.expandedDepartments.push(department);
                    });
                }
            };
            $scope.open = function () {
                $uibModal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: 'partials/department/departmentModalWindow.html',
                    controller: 'AddEditDepartmentCtrl',
                    size: 'lg',
                    resolve: {
                        departmentId: 0
                    }
                });
                    console.log("new");
            };
            $scope.editDept = function (department) {
                //console.log(department);
                $uibModal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: 'partials/department/departmentModalWindow.html',
                    controller: 'AddEditDepartmentCtrl',
                    size: 'lg',
                    resolve: {
                        departmentId: department.id
                    }
                });
            };
        });