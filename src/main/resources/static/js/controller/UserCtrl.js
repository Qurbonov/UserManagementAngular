angular.module("umApp")

        .controller("userEditCtrl",
                function ($scope, $stateParams, restUserApiService, restAllDepartmentApiService, restModuleApiService, restRoleApiService, Upload, $timeout) {
                    var id = $stateParams.id;
                    if (id === 'new') {
                        $scope.user = new restUserApiService();
                    }
                    else {
                        $scope.user = restUserApiService.get({id: id});
                        $scope.certificates = restUserApiService.certificates({
                            id: id});
                    }
                    $scope.departments = restAllDepartmentApiService.query();
                    $scope.modules = restModuleApiService.query();
                    $scope.roles = restRoleApiService.query();


                    $scope.editUser = function () {
                        if (id === 'new') {
                            $scope.user.$create(function (response) {
                                $scope.uploadCertificate($scope.certFile, response.id, function () {
//                                    $state.go('home.users');
                                });
                            }, function () {
                                console.log("error massage")
                            });
                        }
                        else {
                            $scope.user.$update(function (response) {
                                $scope.uploadCertificate($scope.certFile, response.id, function () {
//                                    $state.go('home.users');
                                });
                                window.location.reload(true);
                            }
                            )
                        }
                    };
                    $scope.uploadCertificate = function (file, userId, callback) {
                        if (file === undefined) {
                            return null;
                        }
                        file.upload = Upload.upload({
                            url: '/uploadFile',
                            data: {
                                file: file,
                                userId: userId
                            }
                        });
                        file.upload.then(function (response) {
                            $scope.certificates = restUserApiService.certificates({
                                id: id});
                            $timeout(function () {
                                file.result = response.data;
                            });
                        }, function (response) {
                            if (response.status > 0)
                                $scope.errorMsg = response.status + ': ' + response.data;
                        }, function (evt) {
                            file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
                        });
                        callback();
                    }
                }); 