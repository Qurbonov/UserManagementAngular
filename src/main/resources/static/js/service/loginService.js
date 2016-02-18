'use strict';
angular.module("umApp").factory('loginService', function () {
    return {
        login: function (user) {
            console.log("from service !!!!!!!!");
        }
    };
});
