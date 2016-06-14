angular.module('ticketmonster').controller('PerformanceProblemController', function($scope, $location, locationParser, $http, PerformanceProblemResource) {
  $scope.disabled = false;
  $scope.$location = $location;

  $scope.problems = PerformanceProblemResource.query();

  $scope.onProblemClick = function(perfProblem) {
    // create copy and switch active state
    var newProblem = {
      name: perfProblem.name,
      active: !perfProblem.active
    };

    var response = PerformanceProblemResource.save(newProblem, function() {
      $scope.problems = response;
    });
  };
});
