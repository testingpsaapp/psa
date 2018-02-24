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
	$scope.submitButt=true;
	var t = window.location.href.indexOf('action=review');
	$scope.incident_date="";
	$scope.emailList=[];
	//alert(t);
	if(t!=-1)
	{
		$scope.approveButt=true;
		$scope.submitButt=false;
		$scope.incCommRetrieve=[];
		$scope.inc_num=window.location.href.split('?')[1].split('=')[1].split('&')[0];
		
			$http.get('/incidentCommunication/'+$scope.inc_num).then(function(data){
	              $scope.incCommRetrieve = data.data;
	              
	              console.log($scope.incCommRetrieve);
	              $scope.changeIncidentCommDD($scope.incCommRetrieve.incidentSeverity,'incident_severity_butt');
	              var tempIncType=$scope.incCommRetrieve.incidentSeverity;
	              if((tempIncType).indexOf('Priority')> -1)
	              {
	            	  $scope.changeIncidentCommDD('Standard Incident','incident_type_butt');
	              }
	              else
	              {
	            	  $scope.changeIncidentCommDD('Major Incident','incident_type_butt');
	              }
	              
	              $scope.changeIncidentCommDD($scope.incCommRetrieve.impactedRegion,'impacted_region_butt');
	              //$scope.changeIncidentCommDD($scope.incCommRetrieve.impactedCountry,'impacted_country_butt');
	              $scope.impacted_country=$scope.incCommRetrieve.impactedCountry;
	              $scope.incident_date=$scope.incCommRetrieve.incidentDate;
	              $('#date').val($scope.incCommRetrieve.incidentDate);
	              $scope.start_time=$scope.incCommRetrieve.impactStartTime;
	              $scope.end_time=$scope.incCommRetrieve.impactEndTime;
	              var tempImpSec=$scope.incCommRetrieve.impactedSector;
	              for(var i=0; i<tempImpSec.length;i++)
	              {
	            	  if(tempImpSec[i]=='CTI')
	            	  {
	            		  $scope.impacted_sector_cti=true;
	            	  }
	            	  else if(tempImpSec[i]=='GCG')
	            	  {
	            		  $scope.impacted_sector_gcg=true;
	            	  }
	            	  else if(tempImpSec[i]=='ICG')
	            	  {
	            		  $scope.impacted_sector_icg=true;
	            	  }
	              }
	              $scope.impacted_lob=$scope.incCommRetrieve.impactedLob;
	            //alert($scope.impacted_lob);
		      		$http.post('/appConfig/multiple',$scope.impacted_lob).then(function(data){
		                  $scope.applications = data.data;
		                  //console.log(data);
		                  //console.log($scope.applications);
		      		});
	              $scope.title = $scope.incCommRetrieve.title;
	              $scope.description=$scope.incCommRetrieve.description;
	              $scope.comm_type=$scope.incCommRetrieve.commTyp;
	              $scope.fix_details=$scope.incCommRetrieve.fix;
	              var tempImpact=$scope.incCommRetrieve.impact;
	              if(tempImpact.length>1)
	              {
	            	  var x=[];
	            	  
		              for(var i=0;i<tempImpact.length;i++)
		              {
		            	  x.push((parseInt(i)+1))
		              }
		              
		              $scope.noOfimpact=x;
		              console.log($scope.incCommRetrieve.impact);
		              $(document).ready(
		            	  function()
		            	  {
				              for(var i=0;i<tempImpact.length;i++)
				              {
				            	  var impactObj=JSON.parse($scope.incCommRetrieve.impact[i]);
				            	  console.log(impactObj.channel);
				            	  console.log(impactObj.natureOfImpact);
				            	  console.log(impactObj.volumeOfImpact);
				            	  var j=(parseInt(i)+1);
				            	  //$scope.impacted_channel[i+'']=impactObj.channel;
				            	  $("#impacted_channel_"+j+" option[value='"+impactObj.channel+"']").attr("selected", "selected");
				            	  $('#nature_of_impact_'+j).val(impactObj.natureOfImpact);
				            	  $('#volume_of_impact_'+j).val(impactObj.volumeOfImpact);
				            	  console.log($('#volume_of_impact_'+j));
				              }
		              	  }
		              );
		              
	              }
	              else
	              {
	            	  
	              }
			});
		
		
	}
	
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
			$scope.impact.push("{\"channel\":\""+$('#impacted_channel_'+a).val()+"\","+"\"natureOfImpact\":\"" +$('#nature_of_impact_'+a).val()+"\","+"\"volumeOfImpact\":\""+$('#volume_of_impact_'+a).val()+"\"}");
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
	
	$scope.approve=function()
	{
		$scope.impact=[];
		for(x in $scope.noOfimpact)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.impact.push("{\"channel\":\""+$('#impacted_channel_'+a).val()+"\","+"\"natureOfImpact\":\"" +$('#nature_of_impact_'+a).val()+"\","+"\"volumeOfImpact\":\""+$('#volume_of_impact_'+a).val()+"\"}");
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
			"id" : $scope.incCommRetrieve["id"],
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
			"impactedSector" : $scope.impacted_sector,
			
		};
		
		console.log($scope.incidentCommObj);
		$http.put('/incidentCommunication', $scope.incidentCommObj)
		 .then(function(data){
			 $scope.emailList["listOfToMailId"]=($scope.to_mail_id).split(',');
			 $scope.emailList["listOfCCMailId"]=($scope.cc_mail_id).split(',');
			 $scope.emailList["listOfBCCMailId"]=($scope.bcc_mail_id).split(',');
			 $http.post('/incidentCommunication/approve/'+$scope.inc_num,$scope.emailList)
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
		 },function(data){
			 $scope.saveMessage = data.data;
	            console.log($scope.saveMessage);
	          $scope.failureMessageModel=true;
	          $scope.successMessageModel=false;
		 });
	};
	
	$scope.checkBeforeApprove=function()
	{
		$http.get('/incidentCommunication/'+$scope.inc_num+'/emailList').then(function(data){
			$scope.emailList = data.data;
			console.log($scope.emailList);
			$scope.to_mail_id=$scope.emailList["listOfToMailId"];
			$scope.cc_mail_id=$scope.emailList["listOfCCMailId"];
			$scope.bcc_mail_id=$scope.emailList["listOfBCCMailId"];
			
		});
	};
	
});

