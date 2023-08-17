angular.module('app',[]).controller('indexController', function ($scope, $http){

    const contextPath = 'http://localhost:8189/app';

    $scope.loadStudents = function () {
        $http.get(contextPath + '/students')
            .then(function (response) {
                console.log(response);
                $scope.studentList = response.data;
            })
    };

    $scope.loadStudents();



})