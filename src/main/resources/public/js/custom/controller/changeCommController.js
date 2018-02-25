/**
 * 
 */

psaapp.controller('changeCommController', function($scope,$http) 
{
	$scope.countries=[];
	$scope.listOfPP =[];
	$scope.noOfSanityScope=["1"];
	$scope.submitButt=true;
	$scope.populateCountry=function()
	{
		$http.get('/countries/'+$scope.impacted_region).then(function(data){
            $scope.countries = data.data;
		});
	};
	$http.get('/appConfig/all').then(function(data){
        $scope.listOfPP = data.data;
	});
});