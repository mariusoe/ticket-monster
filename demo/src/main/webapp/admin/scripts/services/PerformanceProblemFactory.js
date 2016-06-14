angular.module('ticketmonster').factory('PerformanceProblemResource', function($resource) {
  var resource = $resource('../rest/problems', {}, {
    save: {
      method: 'POST',
      isArray: true
    }
  });
  return resource;
});
