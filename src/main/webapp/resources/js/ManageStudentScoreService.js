/// <reference path="/resources/frameworks/angular/angular.min.js"/>

var app = angular.module("ManageStudentScore", []);

app.controller("ManageStudentScoreController", function ($scope, $http, $location, $anchorScroll) {

    $scope.students =[]; /* {"name":"","surname":"","score":} */

    $scope.updatedStudentResponse = [];

    $scope.studentDetailsForm = {
        id : null,
        name : "",
        surname : "",
        score : ""
    };

    $scope.newStudentScore = 0;
    $scope.oldStudentScore = 0;

    $scope.studentFullName = "";

    $scope.averageScore = 0;


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


    /* Bind student name, surname and score to text field to save score */
    $scope.editScore = function (student) {

        $scope.averageScore = 0;
        $scope.studentDetailsForm.id = student.id;
        $scope.studentDetailsForm.name = student.name;
        $scope.studentDetailsForm.surname = student.surname;
        $scope.studentDetailsForm.score = student.score;
        $scope.studentFullName = $scope.studentDetailsForm.name + " " + $scope.studentDetailsForm.surname;
    }


    /* capture score */
    $scope.captureScore = function () {

        if(parseInt(angular.element('#inputscore_save').val()) ==  parseInt( $scope.studentDetailsForm.score)){
            alert('New Score is the same as Current Score');
        }else if(parseInt(angular.element('#inputscore_save').val()) >  parseInt( $scope.studentDetailsForm.score)){
            alert('New Score is better that the Current score');
        }else {
            alert('New Score is lower that the Current score');
        }

        /* Get the value of the new score and update student detail */
        $scope.oldStudentScore = $scope.studentDetailsForm.score;
        $scope.studentDetailsForm.score = angular.element('#inputscore_save').val();

        $http({
            method : 'POST',
            url : '/student/update',
            data : angular.toJson($scope.studentDetailsForm),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then(function successCallBack(response) {
            $scope.updatedStudentResponse = response.data;

            $scope.studentDetailsForm.id = null;
            $scope.studentDetailsForm.name = "";
            $scope.studentDetailsForm.surname = "";
            $scope.studentDetailsForm.score = 0;
            $scope.oldStudentScore = 0;
            document.getElementById("inputscore_save").value = "";

            clearNamesAndScore();
            /* After deleting refresh students list hence html bindings */
            getAllStudents();

        }, function errorCallBack(response) {
            console.log(response.statusText);
        });
    }

    /* Find student average */
    $scope.calculateScore = function () {

        clearAverageScore();

        var totalScore = 0;
        var numberOfStudents = 0;

        angular.forEach($scope.students, function(value, key, obj){
            var student = value;

            console.log(student);
            console.log(value);
            console.log(key);
            totalScore += student.score;
            numberOfStudents += 1;

        });
        $scope.averageScore = totalScore / numberOfStudents;
        /*document.getElementById("inputaveragescore_view").innerText =  $scope.averageScore;*/
    }

    /* Clear student full name */
    function clearNamesAndScore(){
        $scope.studentFullName = "";
        $scope.newStudentScore = null;


    }

    /* Clear  */
    function clearAverageScore(){
        $scope.averageScore = 0;
    }

    /* Check whethere score is greater, leaser or the same as current score */
    function scoreRatingMessage(){

        if($scope.newStudentScore ==  $scope.studentDetailsForm.score){
            alert('New Score is the same as Current Score');
        }else if($scope.newStudentScore >  $scope.studentDetailsForm.score){
            alert('New Score is better that the Current score');
        }else {
            alert('New Score is lower that the Current score');
        }
    }

    $scope.scrollTo = function(id) {
        $location.hash(id);
        $anchorScroll();
    }

});