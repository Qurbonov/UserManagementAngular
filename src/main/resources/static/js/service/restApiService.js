'use strict';
angular
        .module("UmApp")
        .factory('restUserApiService', function ($resource) {
            return $resource('api/users/:id', {id: '@id'});
        })
        .factory('restDepartmentApiService', function ($resource) {
            return $resource('api/departments/:id', {id: '@id'});
        })
        .factory('restModuleApiService', function ($resource) {
            return $resource('api/modules/:id', {id: '@id'});
        })
        .factory('restRoleApiService', function ($resource) {
            return $resource('api/roles/:id', {id: '@id'});
        });

