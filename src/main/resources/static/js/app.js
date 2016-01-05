angular.module('UmApp', ['ui.router', 'ui-notification'])
        .controller('notificationController', function ($scope, Notification) {
            $scope.success = function () {
                Notification.success('Success notification');
            };
        })
        .config(function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');
            $stateProvider
                    .state('home', {
                        url: '/',
                        templateUrl: 'partials/home.html',
                        controller: 'homeCtrl'
                    })
//User Route
                    .state('users', {
                        url: '/users',
                        templateUrl: 'partials/user_folder/users.html',
                        controller: 'usersController'
                    })
                    .state('addUser', {
                        url: '/users/addUser',
                        templateUrl: 'partials/user_folder/addUser.html',
                        controller: 'addUserCtrl'
                    })
                    .state('editUser', {
                        url: '/users/{id:[0-9]*}',
                        templateUrl: 'partials/user_folder/editUser.html'
                    })
//Department Route 
                    .state('departments', {
                        url: '/departments',
                        templateUrl: 'partials/department_folder/departments.html',
                        controller: 'departmentsController'
                    })
                    .state('addDepartment', {
                        url: '/departments/new',
                        templateUrl: 'partials/department_folder/addDepartment.html',
                        controller: 'departmentsController'})
                    .state('editDepartment', {
                        url: '/departments/{id}',
                        templateUrl: 'partials/department_folder/editDepartment.html'
                    })
//Module Route 
                    .state('modules', {
                        url: '/modules',
                        templateUrl: 'partials/module_folder/modules.html',
                        controller: 'moduleController'
                    })
                    .state('addModule', {
                        url: '/modules/new',
                        templateUrl: 'partials/module_folder/addModule.html',
                        controller: 'moduleController'});
//                    .state('editDepartment', {
//                        url: '/departments/{id}',
//                        templateUrl: 'partials/department_folder/editDepartment.html'
//                    });
        });
