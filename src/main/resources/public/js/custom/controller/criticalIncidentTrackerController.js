/**
 * 
 */

psaapp.controller('criticalIncidentTrackerController', function($scope,$http) 
{
	$scope.listOfPP =[];
	$scope.countries=[];
	$scope.listOfPPInvolved=[];
	$scope.populateCountry=function()
	{
		$http.get('/countries/'+$scope.impacted_region).then(function(data){
            $scope.countries = data.data;
		});
	}
	$http.get('/appConfig/all').then(function(data){
         $scope.listOfPP = data.data;
	});
	
	$scope.populateCurrentOwningPP=function()
	{
		$scope.listOfPPInvolved=$scope.pp_involved;
	};
	
	$scope.submitInc = function()
	{
		$scope.criticalIncident=
		{
				"incNum": $scope.inc_num,
				"impactingReg":$scope.impacted_region,
				"impactingCtry":$scope.impacted_country,
				"highlightedBy":$scope.highlighted_by,
				"highlightedDate":$scope.highlighted_date,
				"impactingDept":$scope.impacting_dept,
				"reasonOfCriticality":$scope.reason_of_criticality,
				"actionItem":$scope.action_items,
				"incState":$scope.inc_state,
				"ppInvolved":$scope.pp_involved,
				"update":$scope.update,
				"currOwner":$scope.current_owner,
				"currOwnerApp":$scope.current_owning_pp,
				"incidentPriority":$scope.inc_priority,
				"impact":$scope.impact
	
		};
		
		console.log($scope.criticalIncident);
		 $http.post('/DailyCallIncidentTracker', $scope.criticalIncident)
		 .then(function(data){
			 $scope.saveMessage = data.data;
            console.log($scope.saveMessage);
            $scope.successMessageModel=true;
            $scope.failureMessageModel=false;
		 },function(data){
			 $scope.saveMessage = data.data;
	            console.log($scope.saveMessage);
	          $scope.failureMessageModel=true;
	          $scope.successMessageModel=false;
		 });
	};
});