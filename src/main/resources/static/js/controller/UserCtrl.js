angular.module("umApp").controller('usersController', function ($scope, restUserApiService)
    {
        $scope.names = restUserApiService.query();
        $scope.removeUser = function (index)
            {
                restUserApiService.remove({id: $scope.names[index].id}, function ()
                    {
                        $scope.names.splice(index, 1);
                    });
            };
    });

angular.module("umApp").controller("userEditCtrl", function ($scope, $state, $stateParams, restUserApiService, restDepartmentApiService, restModuleApiService, restRoleApiService)
    {
        var id = $stateParams.id;
        if (id === 'new')
            {
                $scope.user = new restUserApiService();

            } else
            {
                $scope.user = restUserApiService.get({id: id});
            }
        $scope.departments = restDepartmentApiService.query();
        $scope.modules = restModuleApiService.query();
        $scope.roles = restRoleApiService.query();

//    $state.go('home.editUser', {id: 100});

        $scope.editUser = function ()
            {
                if (id === 'new')
                    {
                        $scope.lp = $scope.user.username + $scope.user.password;
                        $scope.user.hash = CryptoJS.MD5($scope.lp).toString();
                        $scope.user.$create();
                        $state.go('home.users');
                    } else
                    {
                        $scope.user.$update();
                        $state.go('home.users');
                    }
            };
    });

angular.module("umApp").controller("checkUser", function ($scope, $stateParams, restUserApiService)
    {
        $scope.checkUserFunction = function ()
            {
                console.log($stateParams);
                $scope.m = $scope.t.username + $scope.t.password;
                $scope.userHash = CryptoJS.MD5($scope.m).toString();

                restUserApiService.get({id: 50}, function (response)
                    {
                        $scope.hash = response.hash;
                        if ($scope.userHash === $scope.hash)
                            {
                                console.log("fer");
                                $scope.resultMsg = "hash is true";
                            } else
                            {
                                console.log("error");
                            }

                    });

            };
    });
    