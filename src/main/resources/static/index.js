angular.module('app',[]).controller('indexController', function ($scope, $http){

    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadStudents = function (pageIndex = 1) {
        $http({
            url: contextPath + '/students',
            method: 'GET',
            params: {
                part_name: $scope.filter ? $scope.filter.part_name : null,
                min_score: $scope.filter ? $scope.filter.min_score : null,
                max_score: $scope.filter ? $scope.filter.max_score : null
            }
        }).then(function (response) {
            $scope.studentList = response.data.content;
        });
    };

    $scope.deleteStudent = function (studentId) {
      $http.delete(contextPath + '/students/' + studentId)
          .then(function (response) {
              $scope.loadStudents();
          });
    }
    $scope.createStudent = function () {
        $http.post(contextPath + '/students', $scope.newStudent)
            .then(function (response) {
                $scope.loadStudents();
                // $scope.newStudent= null;
            });
    }

    $scope.sumTwoNumbers = function () {
        $http({
            url: contextPath + '/calc/add',
            method: 'GET',
            params: {
                a: $scope.calcAdd.a,
                b: $scope.calcAdd.b
            }
        }).then(function (response) {
            alert('сумма равна: ' + response.data.value);
            $scope.calcAdd = null;
        })
    }

    $scope.loadStudents();



})