/**
 * 
 */

loginApp.controller('loginController', function($scope,$http) 
{
	$scope.applications=[];
	$scope.getApplications = function()
	{
		$http.get('/appConfig/'+$scope.lob)
		 .then(function(data){
			 $scope.applications=data.data;
		 }, function(data)
		 {
			 
		 });
	};
});