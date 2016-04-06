angular.module("umApp")
        .directive('customPopover', function () {
            return {
                restrict: 'A',
                scope: {
                    departmentId: '@',
                    onRemove: '&',
                    onEdit: '&'
                },
                template: '<a ng-click="openModalWindow(departmentId)"><i class="fa fa-pencil-square-o fa-1x"></i></a>&nbsp \n\
<a ng-click="remove(departmentId)"><i class="fa fa-trash-o fa-1x"></i></a>',
                controller: function ($scope) {
                    $scope.remove = function (departmentId) {
                        $scope.onRemove();
                    };
                    $scope.openModalWindow = function (departmentId) {
                        $scope.onEdit();
                    }
                }
            }
        })
        .controller('treeCtrl', function ($scope, $stateParams, $uibModal, restDepartmentApiService, restAllDepartmentApiService, restUserApiService) {
            $scope.removeUser = function (index) {
                restUserApiService.remove({id: $scope.names[index].id},
                        function () {
                            $scope.names.splice(index, 1);
                        });
            };
            $scope.departments = restDepartmentApiService.query();

            $scope.departmentTreeOptions = {
                isLeaf: function (department) {
                    return !department.hasChildren;
                }
            };
            $scope.expandedDepartments = [];

            $scope.removeDepartment = function (department) {
                restDepartmentApiService.remove({id: department.id});

                function findRecursive(list, id) {
                    for (var i = 0; i < list.length; i++)
                    {
                        var item = list[i];
                        if (item.id === id) {
                            return item;
                        }
                        if (item.children) {
                            return findRecursive(item.children, id);
                        }
                    }
                    return null;
                }

                function removeItem(array, item) {
                    return array.splice(array.indexOf(item), 1);
                }

                var parent = department.parent;
                if (parent) {
                    var parent1 = findRecursive($scope.departments, parent.id);
                    parent1.children = parent1.children.filter(function (item) {
                        return item.id !== department.id;
                    });
                }
            };
            $scope.names = restUserApiService.query();
            $scope.showSelected = function (department) {
                var id = department.id;
                console.log(id);
                $scope.names = restAllDepartmentApiService.users({id: id});

                if (department.hasChildren && !department.loaded) {
                    department.loaded = true;
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