angular.module("umApp").controller('GetAllModuleCtrl', function ($scope, restModuleApiService) {
    $scope.restModules = restModuleApiService.query();
    $scope.removeModule = function (index) {
        restModuleApiService.remove({id: $scope.restModules[index].id}, function () {
            $scope.restModules.splice(index, 1);
        });
    };
});

angular.module("umApp").controller("AddEditModuleCtrl", function ($scope, $state, $stateParams, restModuleApiService) {
    var id = $stateParams.id;
    if (id === 'new') {
        $scope.module = new restModuleApiService();
    } else {
        $scope.module = restModuleApiService.get({id: id});
    }
    $scope.editModule = function () {
        if (id === 'new') {
            $scope.module.$create();
            $state.go('home.modules');
        } else {
            $scope.module.$update();
            $state.go('home.modules');
        }
    };
});