angular.module("umApp")
        .controller('treeCtrl', function ($scope, $uibModal, restDepartmentApiService, $log) {
            $scope.dynamicPopover = {
                templateUrl: 'myPopoverTemplate.html'
            };
            $scope.items = [
                'Add',
                'Edit',
                'Delete'
            ];
            $scope.toggled = function (open) {
                $log.log('Dropdown is now: ', open);
            };
            
            
            
            
////            var tree;
//            $scope.tree_data = {};
//            $scope.my_tree = tree = {};
//            $scope.my_tree_handler = function (branch) {
//                console.log('you clicked on', branch)
//            }
//            restDepartmentApiService.query(function (response) {
//                $scope.tree_data = response;
//            });
//
//            $scope.my_tree.addFunction = function (node) {
//                console.log(node);
//                alert('Function added in Controller "App.js"');
//            };
//
//            $scope.col_defs = [
//                {
//                    cellTemplate: '<button ng-click="tree.addFunction(node)" class="btn btn-default btn-sm">Added Controller!</button>'
//                },
//                {
//                    cellClass: 'v-middle text-center',
//                    cellTemplate: '<button ng-click="tree.remove_node(node)" class="btn btn-warning btn-sm">Edit</button>'
//                },
//                {
//                    cellTemplate: '<button ng-click="tree.remove_node(node)" class="btn btn-danger btn-sm">Remove</button>'
//                }
//            ];
//            $scope.expanding_property = {
//                field: "departmentName",
//                titleClass: 'text-center',
//                cellClass: 'v-middle',
//                displayName: 'Название'
//            };















            $scope.departments = restDepartmentApiService.query();
            $scope.departmentTreeOptions = {
                isLeaf: function (department) {
                    return !department.hasChildren;
                }
            };
            $scope.expandedDepartments = [];
            $scope.showSelected = function (department) {
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
            }
            ;
        });