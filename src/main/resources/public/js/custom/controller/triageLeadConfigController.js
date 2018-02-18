/**
 * triageLeadConfigController starts
 */

psaapp.controller('triageLeadConfigController', function($scope,$http) 
{
	 $scope.changeTriageLeadConfigDD =function changeTriageLeadConfigDD(ddVal,dd)
	 {
		//alert(ddVal);
			document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
			document.getElementById(dd).value =ddVal;
			var scopeModel = dd.split('_butt')[0];
			//console.log(scopeModel);
			$scope[scopeModel] = ddVal;
			
			if(dd=="region_butt")
			{
				$scope.countries = [];
			         $http.get('/countries/'+ddVal).then(function(data){
			              $scope.countries = data.data;
			              //console.log($scope.countries);
			      });
			    
			}
			//console.log($scope[scopeModel]); 
	 };
	 
	 $scope.saveTriageLeadConfig =function saveTriageLeadConfig()
	 {
		 $scope.saveMessage=[];
		 if($scope.tlSoeId=="" || $scope.tlSoeId==undefined)
		 {
			// console.log("Please Select Communication Type");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Enter Triage Lead SOE ID";
		 } 
		 else if($scope.region=="" || $scope.region==undefined)
		 {
				// console.log("Please Select Communication Type");
				 $scope.failureMessageModel=true;
				 $scope.saveMessage.message="Please Select Region";
		 }
		 else if($scope.country=="" || $scope.country==undefined)
		 {
				// console.log("Please Select Communication Type");
				 $scope.failureMessageModel=true;
				 $scope.saveMessage.message="Please Select Country";
		 }
		 else
		 {
			 $scope.failureMessageModel=false;
			 $scope.triageLeadConfig = {
					 "tlSoeId" :$scope.tlSoeId,
					 "region" :$scope.region,
					 "country" :$scope.country
					
			 };
			 
			 $http.post('/triageLeadConfig', $scope.triageLeadConfig)
			 .then(function(data){
	             $scope.saveMessage = data.data;
	             console.log($scope.saveMessage);
	             $scope.successMessageModel=true;
	             $scope.tlSoeId="";
	             $scope.region="";
	             $scope.country="";
	            
	        }, function (data) {
	
	        	$scope.saveMessage = data.data;
	            console.log($scope.saveMessage);
	            $scope.failureMessageModel=true;
	        	// this function handles error
	
	        	});
		 }
	 };
	 
	 $scope.searchTriageLeadConfig =function searchTriageLeadConfig()
	 {
		 $scope.listOfTriageLeadConfig = [];
         $http.get('/triageLeadConfig/all').then(function(data){
              $scope.listOfTriageLeadConfig = data.data;
              $scope.tableTriageLeadConfig=true;
              //console.log($scope.countries);
         });
	 };
});