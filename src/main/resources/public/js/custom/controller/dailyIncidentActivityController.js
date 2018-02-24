/**
 * 
 */
psaapp.controller('dailyIncidentActivityController', function($scope,$http) 
{
	$scope.listOfCountries=[];
	$http.get('/countries').then(function(data){
        $scope.listOfCountries = data.data;
        //$scope.tableDailyIncActy=true;
       //console.log($scope.countries);
   });
	$scope.saveInc=function (){
		$scope.saveMessage=[];
		if($scope.inc_num=="" || $scope.inc_num==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Enter Incident Number";
		}
		else if($scope.incident_date=="" || $scope.incident_date==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Enter Incident Date";
		}
		else if($scope.action_taken=="" || $scope.action_taken==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Enter Action Taken";
		}
		else if($scope.action_category=="" || $scope.action_category==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Select Action Category";
		}
		else if($scope.new_priority!="" && $scope.old_priority =="")
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Select Old Priority If New Priority Has Been Set";
		}
		else if($scope.old_priority =="" || $scope.old_priority ==undefined)
		{
			$scope.old_priority="";
		}
		else if($scope.new_priority =="" || $scope.new_priority ==undefined)
		{
			$scope.new_priority="";
		}
		else if($scope.country=="" || $scope.country==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Select Country";
		}
		else
		{
			$scope.dailyIncAct =
			{
				"incNum" : $scope.inc_num,
				"date" : $scope.incident_date,
				"oldPriority" : $scope.old_priority,
				"newPriority" : $scope.new_priority,
				"actTaken" : $scope.action_taken,
				"actCategory" : $scope.action_category,
				"ctry" : $scope.country
			}
			$http.post('/dailyIncAct',$scope.dailyIncAct)
			.then(function(data){
				$scope.saveMessage = data.data;
	            console.log($scope.saveMessage);
	            $scope.successMessageModel=true;
	            $scope.failureMessageModel=false;
	            $scope.inc_num="";
	            $scope.incident_date="";
	            $scope.old_priority="";
	            $scope.new_priority="";
	            $scope.action_category="";
	            $scope.action_taken="";
	            $scope.country="";
			},function(data){
				$scope.saveMessage = data.data;
	            console.log($scope.saveMessage);
	            $scope.failureMessageModel=true;
	            $scope.successMessageModel=false;
				
			})
		}
	};
	
	$scope.searchInc=function (){
		$scope.listOfIncident = [];
        $http.get('/dailyIncAct').then(function(data){
             $scope.listOfIncident = data.data;
             $scope.tableDailyIncActy=true;
            //console.log($scope.countries);
        });
	};
});