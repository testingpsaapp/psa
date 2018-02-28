/**
 * briefingPaperPublisherController starts
 */

psaapp.controller('briefingPaperPublisherController', function($scope,$http) 
{
	$scope.successMessageModel=false;
	$scope.failureMessageModel=false;
	$scope.submitButt=true;
	$scope.approveButtPSM=false;
	$scope.approveButtPSSM=false;
	$scope.approveButtLobLead=false
	$scope.noOfimpact=["1"];
	$scope.noOfChrono=["1"];
	var t = window.location.href.indexOf('action=briefingPaperReviewLobLead');
	var q = window.location.href.indexOf('action=briefingPaperReviewPSM');
	var r = window.location.href.indexOf('action=briefingPaperReviewPSSM');
	$scope.reviewer="";
	$scope.mim_num_retrieve="";
	$scope.appName="";
	$scope.briefingPaperRetrieve=[];
	$scope.changeBriefingPaperCommDD=function(ddVal,dd)
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
	
	
	
	$http.get('/appConfig/all').then(function(data){
        $scope.allApplications = data.data;
    });
	
	
	
	$scope.getApplications=function()
	{
		//alert($scope.impacted_lob);
		$http.post('/appConfig/multiple',$scope.impacted_lob).then(function(data){
            $scope.applications = data.data;
            //console.log(data);
            //console.log($scope.applications);
		});
	};
	
	$scope.addImpact = function()
	{
		$scope.noOfimpact.push(parseInt(($scope.noOfimpact.length))+1);
	};
	
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
	};
	
	$scope.addChrono=function()
	{
		$scope.noOfChrono.push(parseInt(($scope.noOfChrono.length))+1);
	};
	
	$scope.deleteChrono=function(x)
	{
		if($scope.noOfChrono.length == 1)
		{
			$scope.noOfChrono=[];
			$scope.noOfChrono=["1"];
		}
		else
		{
			var index = $scope.noOfChrono.indexOf("\""+x+"\"")
			$scope.noOfChrono.splice(index,1);
		}
	};
	
	$scope.submitForApproval = function()
	{
		$scope.chronology=[];
		for(x in $scope.noOfChrono)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.chronology.push("{\"chrono\":\""+$('#chrono_'+a).val()+"\","+"\"chronoAction\":\"" +$('#chrono_action_'+a).val()+"\","+"\"chronoDateTime\":\""+$('#chrono_datetime_'+a).val()+"\"}");
		}
		$scope.impact=[];
		for(x in $scope.noOfimpact)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.impact.push("{\"channel\":\""+$('#impacted_channel_'+a).val()+"\","+"\"natureOfImpact\":\"" +$('#nature_of_impact_'+a).val()+"\","+"\"volumeOfImpact\":\""+$('#volume_of_impact_'+a).val()+"\"}");
		}
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
		$scope.briefingPaperObj={
				"mimNum":$scope.inc_num,
				"mimDate":$scope.incident_date,
				"mimStartDate":$scope.incident_date,
				"mimEndDate":$scope.incident_date,
				"impactedLob":$scope.impacted_lob,
				"title":$scope.title,
				"description":$scope.description,
				"impactedCountry":$scope.impacted_country,
				"impactedDepartment":"citiphone",
				"impact":$scope.impact,
				"fix":$scope.fix_details,
				"preventiveAct":$scope.preventive_actions,
				"chronology":$scope.chronology,
				"impactedRegion":$scope.impacted_region,
				"impactedSector":$scope.impacted_sector,
				"causingApp":$scope.causing_app,
				"severity" :$scope.incident_severity
		};
		console.log($scope.briefingPaperObj);
		 $http.post('/briefingPaper/review/lobleads', $scope.briefingPaperObj)
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
	
	$scope.approveLobLead=function()
	{
		$scope.chronology=[];
		for(x in $scope.noOfChrono)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.chronology.push("{\"chrono\":\""+$('#chrono_'+a).val()+"\","+"\"chronoAction\":\"" +$('#chrono_action_'+a).val()+"\","+"\"chronoDateTime\":\""+$('#chrono_datetime_'+a).val()+"\"}");
		}
		$scope.impact=[];
		for(x in $scope.noOfimpact)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.impact.push("{\"channel\":\""+$('#impacted_channel_'+a).val()+"\","+"\"natureOfImpact\":\"" +$('#nature_of_impact_'+a).val()+"\","+"\"volumeOfImpact\":\""+$('#volume_of_impact_'+a).val()+"\"}");
		}
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
		$scope.briefingPaperObj={
				"id" : $scope.briefingPaperRetrieve.id,
				"mimNum":$scope.inc_num,
				"mimDate":$scope.incident_date,
				"mimStartDate":$scope.incident_date,
				"mimEndDate":$scope.incident_date,
				"impactedLob":$scope.impacted_lob,
				"title":$scope.title,
				"description":$scope.description,
				"impactedCountry":$scope.impacted_country,
				"impactedDepartment":"citiphone",
				"impact":$scope.impact,
				"fix":$scope.fix_details,
				"preventiveAct":$scope.preventive_actions,
				"chronology":$scope.chronology,
				"impactedRegion":$scope.impacted_region,
				"impactedSector":$scope.impacted_sector,
				"causingApp":$scope.causing_app,
				"severity" :$scope.incident_severity
				
		};
		console.log($scope.briefingPaperObj);
		 $http.put('/briefingPaper/review/{$scope.mim_num_retrieve}/lobleads/{$scope.reviewer}/{$scope.appName}', $scope.briefingPaperObj)
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
	
	
	$scope.approvePSM=function()
	{
		$scope.chronology=[];
		for(x in $scope.noOfChrono)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.chronology.push("{\"chrono\":\""+$('#chrono_'+a).val()+"\","+"\"chronoAction\":\"" +$('#chrono_action_'+a).val()+"\","+"\"chronoDateTime\":\""+$('#chrono_datetime_'+a).val()+"\"}");
		}
		$scope.impact=[];
		for(x in $scope.noOfimpact)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.impact.push("{\"channel\":\""+$('#impacted_channel_'+a).val()+"\","+"\"natureOfImpact\":\"" +$('#nature_of_impact_'+a).val()+"\","+"\"volumeOfImpact\":\""+$('#volume_of_impact_'+a).val()+"\"}");
		}
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
		$scope.briefingPaperObj={
				"id" : $scope.briefingPaperRetrieve.id,
				"mimNum":$scope.inc_num,
				"mimDate":$scope.incident_date,
				"mimStartDate":$scope.incident_date,
				"mimEndDate":$scope.incident_date,
				"impactedLob":$scope.impacted_lob,
				"title":$scope.title,
				"description":$scope.description,
				"impactedCountry":$scope.impacted_country,
				"impactedDepartment":"citiphone",
				"impact":$scope.impact,
				"fix":$scope.fix_details,
				"preventiveAct":$scope.preventive_actions,
				"chronology":$scope.chronology,
				"impactedRegion":$scope.impacted_region,
				"impactedSector":$scope.impacted_sector,
				"causingApp":$scope.causing_app,
				"severity" :$scope.incident_severity
				
		};
		console.log($scope.briefingPaperObj);
		 $http.put('/briefingPaper/review/{$scope.mim_num_retrieve}/psm/{$scope.reviewer}/{$scope.appName}', $scope.briefingPaperObj)
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
	
	
	$scope.approvePSSM=function()
	{
		$scope.chronology=[];
		for(x in $scope.noOfChrono)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.chronology.push("{\"chrono\":\""+$('#chrono_'+a).val()+"\","+"\"chronoAction\":\"" +$('#chrono_action_'+a).val()+"\","+"\"chronoDateTime\":\""+$('#chrono_datetime_'+a).val()+"\"}");
		}
		$scope.impact=[];
		for(x in $scope.noOfimpact)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.impact.push("{\"channel\":\""+$('#impacted_channel_'+a).val()+"\","+"\"natureOfImpact\":\"" +$('#nature_of_impact_'+a).val()+"\","+"\"volumeOfImpact\":\""+$('#volume_of_impact_'+a).val()+"\"}");
		}
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
		$scope.briefingPaperObj={
				"id" : $scope.briefingPaperRetrieve.id,
				"mimNum":$scope.inc_num,
				"mimDate":$scope.incident_date,
				"mimStartDate":$scope.incident_date,
				"mimEndDate":$scope.incident_date,
				"impactedLob":$scope.impacted_lob,
				"title":$scope.title,
				"description":$scope.description,
				"impactedCountry":$scope.impacted_country,
				"impactedDepartment":"citiphone",
				"impact":$scope.impact,
				"fix":$scope.fix_details,
				"preventiveAct":$scope.preventive_actions,
				"chronology":$scope.chronology,
				"impactedRegion":$scope.impacted_region,
				"impactedSector":$scope.impacted_sector,
				"causingApp":$scope.causing_app,
				"severity" :$scope.incident_severity
				
		};
		console.log($scope.briefingPaperObj);
		 $http.put('/briefingPaper/review/{$scope.mim_num_retrieve}/pssm/{$scope.reviewer}/{$scope.appName}', $scope.briefingPaperObj)
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
	
	if(t!=-1 || q!=-1 || r!=-1)
	{
		$scope.submitButt=false;
		$scope.mim_num_retrieve=window.location.href.split('?')[1].split('&')[1].split('=')[1];
		$scope.reviewer = window.location.href.split('?')[1].split('&')[2].split('=')[1];
		$scope.appName= window.location.href.split('?')[1].split('&')[3].split('=')[1];
		$http.get('/briefingPaper/'+$scope.mim_num_retrieve).then(function(data){
            $scope.briefingPaperRetrieve = data.data;
            //console.log($scope.briefingPaperRetrieve);
		});
		
		if(t!=-1)
		{
			$scope.approveButtLobLead=true;
			$scope.approveButtPSM=false;
			$scope.approveButtPSSM=false;
//			$http.get('/briefingPaper/'+$scope.mim_num_retrieve).then(function(data){
//	            $scope.briefingPaperRetrieve = data.data;
//	            //console.log($scope.briefingPaperRetrieve);
//			});
		}
		else if(q!=-1)
		{
			$scope.approveButtPSM=true;
			$scope.approveButtLobLead=false;
			$scope.approveButtPSSM=false;
//			$http.get('/briefingPaper/'+$scope.mim_num_retrieve).then(function(data){
//	            $scope.briefingPaperRetrieve = data.data;
//	            //console.log($scope.briefingPaperRetrieve);
//			});
		}
		else if(r!=-1)
		{
			$scope.approveButtPSSM=true;
			$scope.approveButtLobLead=false;
			$scope.approveButtPSM=false;
//			$http.get('/briefingPaper/'+$scope.mim_num_retrieve).then(function(data){
//	            $scope.briefingPaperRetrieve = data.data;
//	            //console.log($scope.briefingPaperRetrieve);
//			},function(data){});
		}
		
		
		/*console.log($scope.mim_num_retrieve);
		console.log($scope.reviewer);
		
		console.log($scope.briefingPaperRetrieve);*/
		$scope.changeBriefingPaperCommDD($scope.briefingPaperRetrieve.severity,'incident_severity_butt');
		var tempIncType=$scope.briefingPaperRetrieve.severity;
		if((tempIncType).indexOf('Priority')> -1)
        {
			$scope.changeBriefingPaperCommDD('Standard Incident','incident_type_butt');
        }
        else
        {
        	$scope.changeBriefingPaperCommDD('Major Incident','incident_type_butt');
        }
		$scope.incident_date=$scope.briefingPaperRetrieve.briefingPaperRetrieve;
		$scope.changeBriefingPaperCommDD($scope.briefingPaperRetrieve.impactedRegion,'impacted_region_butt');
		$scope.impacted_country=$scope.briefingPaperRetrieve.impactedCountry;
		var tempImpSec=$scope.briefingPaperRetrieve.impactedSector;
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
        $('#date').val($scope.briefingPaperRetrieve.incidentDate);
        $scope.start_time=$scope.briefingPaperRetrieve.impactStartTime;
        $scope.end_time=$scope.briefingPaperRetrieve.impactEndTime;
        $scope.impacted_lob=$scope.briefingPaperRetrieve.impactedLob;
        $http.post('/appConfig/multiple',$scope.impacted_lob).then(function(data){
            $scope.applications = data.data;
            
		});
        $scope.title = $scope.briefingPaperRetrieve.title;
        $scope.description=$scope.briefingPaperRetrieve.description;
        $scope.fix_details=$scope.briefingPaperRetrieve.fix;
        var tempImpact=$scope.briefingPaperRetrieve.impact;
        if(tempImpact.length>1)
        {
      	  var x=[];
      	  
            for(var i=0;i<tempImpact.length;i++)
            {
          	  x.push((parseInt(i)+1))
            }
            
            $scope.noOfimpact=x;
            //console.log($scope.briefingPaperRetrieve.impact);
            $(document).ready(
          	  function()
          	  {
		              for(var i=0;i<tempImpact.length;i++)
		              {
		            	  var impactObj=JSON.parse($scope.briefingPaperRetrieve.impact[i]);
		            	  //console.log(impactObj.channel);
		            	  //console.log(impactObj.natureOfImpact);
		            	  //console.log(impactObj.volumeOfImpact);
		            	  var j=(parseInt(i)+1);
		            	  //$scope.impacted_channel[i+'']=impactObj.channel;
		            	  $("#impacted_channel_"+j+" option[value='"+impactObj.channel+"']").attr("selected", "selected");
		            	  $('#nature_of_impact_'+j).val(impactObj.natureOfImpact);
		            	  $('#volume_of_impact_'+j).val(impactObj.volumeOfImpact);
		            	  //console.log($('#volume_of_impact_'+j));
		              }
            	  }
            );
            
        }
        else
        {
      	  
        }
        $scope.preventive_actions = $scope.briefingPaperRetrieve.preventiveAct;
        var tempCausingApp=$scope.briefingPaperRetrieve.causingApp;
        $("#causing_app"+" option[value='"+tempCausingApp+"']").attr("selected", "selected");
        
        var tempChrono=$scope.briefingPaperRetrieve.chronology;
        if(tempChrono.length>1)
        {
      	  var x=[];
      	  
            for(var i=0;i<tempChrono.length;i++)
            {
          	  x.push((parseInt(i)+1))
            }
            
            $scope.noOfChrono=x;
            //console.log($scope.briefingPaperRetrieve.chronology);
            $(document).ready(
          	  function()
          	  {
		              for(var i=0;i<tempChrono.length;i++)
		              {
		            	  var chronoObj=JSON.parse($scope.briefingPaperRetrieve.chronology[i]);
		            	  //console.log(chronoObj.chrono);
		            	  //console.log(chronoObj.chronoAction);
		            	  //console.log(chronoObj.chronoDateTime);
		            	  var j=(parseInt(i)+1);
		            	  //$scope.impacted_channel[i+'']=impactObj.channel;
		            	  $("#chrono_"+j).val(chronoObj.chrono);
		            	  $('#chrono_action_'+j).val(chronoObj.chronoAction);
		            	  $('#chrono_datetime_'+j).val(chronoObj.chronoDateTime);
		            	  //console.log($('#volume_of_impact_'+j));
		              }
            	  }
            );
            
        }
        else
        {
      	  
        }
	}
});