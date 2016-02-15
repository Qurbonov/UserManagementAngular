'use strict';
angular
        .module("UmApp")
        .factory('restUserApiService', function ($resource) {
            return $resource('api/users/:id', {id: '@id'});
        })
        .factory('restAllDepartmentApiService', function ($resource) {
            return $resource('/api/allDepartments/:id', {id: '@id'});
        })
        .factory('restDepartmentApiService', function ($resource) {
            return $resource('api/departments/:id', {id: '@id'}, {
                query: {method: 'GET', isArray: true},
                children: {
                    method: 'GET',
                    url: 'api/departments/:id/children',
                    isArray: true
                },
                get: {method: 'GET'},
                create: {method: 'POST'},
                update: {
                    method: 'PUT',
                    params: {
                        id: '@id'
                    }
                },
                remove: {method: 'DELETE'}
            });
        })
        .factory('restModuleApiService', function ($resource) {
            return $resource('api/modules/:id', {id: '@id'});
        })
        .factory('restRoleApiService', function ($resource) {
            return $resource('api/roles/:id', {id: '@id'});
        });

