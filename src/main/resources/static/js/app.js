angular.module('umApp', ['ngResource', 'ui.router', 'treeControl', 'ui.bootstrap','ntt.TreeDnD','treeGrid'])
        .controller('notificationController', function ($scope, Notification) {
            $scope.success = function () {
                Notification.success('Success notification');
            };
        })
        .config(function ($stateProvider, $urlRouterProvider, $resourceProvider) {
            $urlRouterProvider.otherwise('/');
            $stateProvider
                    .state('login', {
                        url: '/',
                        templateUrl: 'partials/login/login.html',
                        controller: 'loginCtrl'
                    })
                    .state('home', {
                        url: '/home',
                        templateUrl: 'partials/home.html',
                        controller: 'homeCtrl'
                    })
//User Route
                    .state('home.users', {
                        url: '/users',
                        templateUrl: 'partials/user/users.html',
                        controller: 'usersController'
                    })
                    .state('home.editUser', {
                        url: '/users/:id',
                        templateUrl: 'partials/user/editUser.html'
                    })
//Department Route 
                    .state('home.departments', {
                        url: '/departments',
                        templateUrl: 'partials/department/departments.html',
                        controller: 'GetAllDepartmentCtrl'
                    })
                    .state('home.addDepartment', {
                        url: '/departments/:id',
                        templateUrl: 'partials/department/addDepartment.html'
                    })
                    .state('home.departmentModalWindow', {
                        url: '/departmentmodalWindow',
                        templateUrl: 'partials/department/departmentModalWindow.html'
                    })
//Module Route 
                    .state('home.modules', {
                        url: '/modules',
                        templateUrl: 'partials/module/modules.html',
                        controller: 'GetAllModuleCtrl'
                    })
                    .state('home.editModule', {
                        url: '/modules/:id',
                        templateUrl: 'partials/module/editModule.html'

                    });
            $urlRouterProvider.otherwise('/');

            $resourceProvider.defaults.actions = {
                query: {method: 'GET', isArray: true},
                get: {method: 'GET'},
                create: {method: 'POST'},
                update: {
                    method: 'PUT',
                    params: {
                        id: '@id'
                    }
                },
                remove: {method: 'DELETE'}
            };
        })
        .run([
            '$window', '$http', '$rootScope', '$state',
            function ($window, $http, $rootScope, $state) {
                var getAuthorization = function ($window) {
                    return $window.sessionStorage.getItem('token');
                };

                $http.defaults.headers.common.Authorization = getAuthorization($window);

                $rootScope.$on('$stateChangeStart', function (event, toState) {

                    if (toState.name !== 'login' && !getAuthorization($window)) {
                        event.preventDefault();
                        $state.go('login');
                    }
                });
            }
        ]);
