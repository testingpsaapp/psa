/**
 * incidentCommController starts
 */
psaapp.controller('incidentCommController', function($scope,$http) 
{
	$scope.incidentSeverity=[];
	$scope.countries = [];
	$scope.applications=[];
	$scope.noOfimpact=["1"];
	$scope.approveButt=false;
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
		$scope.noOfimpact.push(parseInt(($scope.noOfimpact.length))+1);
	}
	
	$scope.deleteImpact=function(x)
	{
		if($scope.noOfimpact.length == 1)
		{
			$scope.noOfimpact=[];
			$scope.noOfimpact=["1"];
		}
		else
		{
			var index = $scope.noOfimpact.indexOf("\""+x+"\"")
			$scope.noOfimpact.splice(index,1);
		}
	}
	
	$scope.submitForApproval = function()
	{
		$scope.impact=[];
		for(x in $scope.noOfimpact)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.impact.push("{\"channel\":"+$('#impacted_channel_'+a).val()+","+"\"natureOfImpact\":" +$('#nature_of_impact_'+a).val()+","+"\"volumeOfImpact\":"+$('#volume_of_impact_'+a).val()+"}");
		}
		console.log($scope.impact);
		$scope.impacted_sector=[];
		if($scope.impacted_sector_cti==true)
		{
			$scope.impacted_sector.push("CTI");
		}
		if($scope.impacted_sector_gcg==true)
		{
			$scope.impacted_sector.push("GCG");
		}
		if($scope.impacted_sector_icg==true)
		{
			$scope.impacted_sector.push("ICG");
		}
		$scope.incidentCommObj={
			"incidentNum":$scope.inc_num,
			"incidentSeverity":$scope.incident_severity,
			"commTyp":$scope.comm_type,
			"incidentDate":$scope.incident_date,
			"impactStartTime":$scope.start_time,
			"impactEndTime":$scope.end_time,
			"impactedLob":$scope.impacted_lob,
			"title":$scope.title,
			"description":$scope.description,
			"impactedCountry":$scope.impacted_country,
			"impact":$scope.impact,
			"fix":$scope.fix_details,
			"preparedBy":"default",
			"reviewedBy":"default",
			"status" :$scope.comm_type,
			"impactedRegion" : $scope.impacted_region,
			"impactedSector" : $scope.impacted_sector
		};
		console.log($scope.incidentCommObj);
		 $http.post('/incidentCommunication/review', $scope.incidentCommObj)
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

