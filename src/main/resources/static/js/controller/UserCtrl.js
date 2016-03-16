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

        $scope.editUser = function ()
            {
                if (id === 'new')
                    {
                        console.log($scope.user.name, $scope.user.password);
                        $scope.lp = $scope.user.name + $scope.user.password;
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

angular.module("umApp").controller("checkUser", function ($scope, restUserApiService, Upload, $timeout)
    {
        $scope.checkUserFunction = function ()
            {
                $scope.m = $scope.t.username + $scope.t.password;
                $scope.userHash = CryptoJS.MD5($scope.m).toString();
                restUserApiService.get({id: 73}, function (response)
                    {
                        $scope.hash = response.hash;
                        if ($scope.userHash === $scope.hash)
                            {
                                console.log("fer");
                                $scope.resultMsg = "hash is true";
                            } else
                            {
                                console.log("hash in DB " + $scope.hash);
                                console.log("hash " + $scope.userHash);
                                console.log("error");
                                $scope.resultMsg = "hash is false";
                            }
                    });
            };


        $scope.uploadPic = function (file)
            {
                file.upload = Upload.upload({
                    url: '/uploadFile',
//                    data: {name: "user", file: file},
                    data: {file: file},
                });

                file.upload.then(function (response)
                    {
                        $timeout(function ()
                            {
                                file.result = response.data;

                            });
                    }, function (response)
                    {
                        if (response.status > 0)
                            $scope.errorMsg = response.status + ': ' + response.data;
                    }, function (evt)
                    {
                        // Math.min is to fix IE which reports 200% sometimes
                        file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
                    });
            }
    });
    