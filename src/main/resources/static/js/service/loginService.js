'use strict';
angular.module("UmApp").factory('loginService', function () {
    return {
        login: function (user) {
            console.log("from service !!!!!!!!");
        }
    };
});
