var app = angular.module('catsvsdogs', []);
var socket = io.connect({transports:['polling']});

var bg1 = document.getElementById('background-stats-1');
var bg2 = document.getElementById('background-stats-2');

app.controller('statsCtrl', function($scope){
  $scope.aPercent = 50;
  $scope.bPercent = 50;

  var updateAccountSummary = function(){
    socket.on('account', function (json) {
       data = JSON.parse(json);
       accountBalance = data.balance
       console.log("JZJZ the account is:" + data.balance)
       $scope.$apply(function () {
         $scope.total = accountBalance;
       });
    });
  };

  var init = function(){
    document.body.style.opacity=1;
    updateAccountSummary();
  };
  socket.on('message',function(data){
    init();
  });
});