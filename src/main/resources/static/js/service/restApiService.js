'use strict';
angular
        .module("umApp")
        .factory('restUserApiService', function ($resource) {
            return $resource('api/users/:id', {id: '@id'}, {
                certificates: {
                    method: 'GET',
                    url: 'api/users/:id/certificates',
                    isArray: true
                }
            });
        })
        .factory('restAllDepartmentApiService', function ($resource) {
            return $resource('/api/allDepartments/:id', {id: '@id'}, {
                users: {
                    method: 'GET',
                    url: 'api/departments/:id/users',
                    isArray: true
                }

            });
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
        })
        .factory('restCertificationApiService', function ($resource) {
            return $resource('api/certificates/:id', {id: '@id'});
        });

