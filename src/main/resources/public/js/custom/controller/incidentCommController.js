/**
 * incidentCommController starts
 */
psaapp.controller('incidentCommController', function($scope,$http) 
{
	$scope.incidentSeverity=[];
	$scope.countries = [];
	$scope.applications=[];
	$scope.changeIncidentCommDD=function changeIncidentCommDD(ddVal,dd)
	{
		document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
		document.getElementById(dd).value =ddVal;
		var scopeModel = dd.split('_butt')[0];
		//console.log(scopeModel);
		$scope[scopeModel] = ddVal;
		if(scopeModel=='incident_type' && ddVal=='Major Incident')
		{
			$scope.incidentSeverity=[{"severityType":"Severity 1"},{"severityType":"Severity 2 High"},{"severityType":"Severity 2 Standard"},{"severityType":"Severity 3"}];
		}
		else if (scopeModel=='incident_type' && ddVal=='Standard Incident')
		{
			$scope.incidentSeverity=[{"severityType":"Priority 1"},{"severityType":"Priority 2"},{"severityType":"Priority 3"},{"severityType":"Priority 4"}];
		}
		else if(dd=="impacted_region_butt")
		{
			$http.get('/countries/'+ddVal).then(function(data){
	              $scope.countries = data.data;
	              //console.log($scope.countries);
	      });
		}
	};
	
	$scope.getApplications=function()
	{
		//alert($scope.impacted_lob);
		$http.post('/appConfig/multiple',$scope.impacted_lob).then(function(data){
            $scope.applications = data.data;
            //console.log(data);
            //console.log($scope.applications);
		});
	};
	
	$scope.addImpact=function()
	{
		$('#impact_table tbody').append('<tr><td><select class="form-control" id="impacted_channel_1" ng-model="impacted_channel_1">'+
				        '<option ng-repeat="x in applications">{{x.appName}}</option></select></td><td><textarea class="form-control" rows="5" id="nature_of_impact_1" ng-model="nature_of_impact_1"></textarea>'+
				        '</td><td><input type="text" class="form-control" id="volume_of_impact" ng-model="volume_of_impact_1">'+
				        '</td><td>'+
				        '<button type="button" class="btn btn-success" ng-click="addImpact()">+</button>'+
		        	    '<button type="button" class="btn btn-danger" ng-click="deleteImpact()">-</button>'+
		        	    '</td></tr>')
	}
	
	$scope.submitForApproval = function()
	{
		
	};
});

