/**
 * 
 */

psaapp.controller('worklistController', function($rootScope,$scope,$http) 
{
	//alert($rootScope.user);
	$scope.listOfWorklist=[];
	$http.get('/worklist/'+$rootScope.user+'/all').then(function(data){
        $scope.listOfWorklist = data.data;
        //$scope.tableAppConfig = true;
        console.log($scope.listOfWorklist);
	});
})