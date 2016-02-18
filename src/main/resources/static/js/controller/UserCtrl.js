angular.module("umApp").controller('usersController', function ($scope, restUserApiService) {
    $scope.names = restUserApiService.query();
    $scope.removeUser = function (index) {
        restUserApiService.remove({id: $scope.names[index].id}, function () {
            $scope.names.splice(index, 1);
        });
    };
});

angular.module("umApp").controller("userEditCtrl", function ($scope, $state, $stateParams, restUserApiService, restDepartmentApiService, restModuleApiService, restRoleApiService) {
    var id = $stateParams.id;
    if (id === 'new') {
        $scope.user = new restUserApiService();
    } else {
        $scope.user = restUserApiService.get({id: id});
    }
    $scope.departments = restDepartmentApiService.query();
    $scope.modules = restModuleApiService.query();
    $scope.roles = restRoleApiService.query();

//    $state.go('home.editUser', {id: 100});

    $scope.editUser = function () {
        if (id === 'new') {
            $scope.user.$create();
            $state.go('home.users');
        } else {
            $scope.user.$update();
            $state.go('home.users');
        }
    };
});
