/// <reference path="/resources/frameworks/angular/angular.min.js"/>

var app = angular.module("ManageStudent", []);

app.controller("ManageStudentController", function ($scope, $http, $location, $anchorScroll) {

    $scope.students =[]; /* {"name":"","surname":"","score":} */

    $scope.deletedStudentResponse = [];

    $scope.updatedStudentResponse = [];

    $scope.studentForm = {
        id : null,
        name : "",
        surname : ""
    };

    /* Get all students */
    function getAllStudents() {
        $http({
            method: 'POST',
            url: '/student/students'
        }).then(function successCallBack(response) {
            $scope.students = response.data;
        }, function errorCallBack(response) {
            console.log(response.statusText);
        });
    }
    /* On load, load all students */
    getAllStudents();


    
    /* Delete student */
    $scope.deleteStudent = function (student, response) {
        $http({
            method : 'POST',
            url : '/student/remove/' + student.id
        }).then(function successCallBack(response) {
            $scope.deletedStudentResponse = response.data;

            /* After deleting refresh students list hence html bindings */
            getAllStudents();

        }, function errorCallBack(response) {
            console.log(response.statusText);
        });
    }
    
    /* Update Process editStudent() -> updateStudent */
    /* Edit first - Selecting Student from linst onto text fields */
    $scope.editStudent = function (student) {

        $scope.studentForm.id = student.id;
        $scope.studentForm.name = student.name;
        $scope.studentForm.surname = student.surname;
        $scope.studentForm.score = student.score;
    }


    /* Update student, given the binding on edit, the studentForm data will be used to update */
    $scope.updateStudent = function () {

        $http({
            method : 'POST',
            url : '/student/update',
            data : angular.toJson($scope.studentForm),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then(function successCallBack(response) {
            $scope.updatedStudentResponse = response.data;

            $scope.studentForm.name = "";
            $scope.studentForm.surname = "";
            /* After deleting refresh students list hence html bindings */
            getAllStudents();

        }, function errorCallBack(response) {
            console.log(response.statusText);
        });
    }

    $scope.scrollTo = function(id) {
        $location.hash(id);
        $anchorScroll();
    }
});