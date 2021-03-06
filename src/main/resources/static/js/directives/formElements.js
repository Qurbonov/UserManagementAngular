angular.module("umApp")
        .directive("editRemoveButton", function ()
            {
                return {
                    template: '<a ui-sref="home.addDepartment({id: department.id})" class="btn btn-default btn-sm"><i class="fa fa-pencil-square-o fa-1x"></i></a>\n\
                           <a ng-click="removeDepartment($index)" class=" btn btn-danger btn-sm"><i class="fa fa-trash-o fa-1x"></i></a>'
                };
            })
        .directive("formElement", function ()
            {
                return{
                    replace: true,
                    scope: {
                        entity: '=',
                        onClick: '&'
                    },
                    template: '<button type="submit" ng-click="onClick()" class="btn btn-success">{{ entity.id ? "Сохранить" : "Создать" }}</button>'
                };
            });
