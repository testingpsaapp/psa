/**
 * lobConfigController starts below
 */

psaapp.controller('lobConfigController', function($scope,$http) 
{
	$scope.listOfApplication =[];
	$scope.changeLobConfigDD =function changePSAAccessDD(ddVal,dd)
	 {
			//alert(ddVal);
			document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
			document.getElementById(dd).value =ddVal;
			var scopeModel = dd.split('_butt')[0];
			//console.log(scopeModel);
			$scope[scopeModel] = ddVal;
			//console.log($scope[scopeModel]);
			if(scopeModel=='impacted_lob')
			{
				$http.get('/appConfig/'+ddVal).then(function(data){
			              $scope.listOfApplication = data.data;
			             
			         });
			}
			 
		};
	 
		$scope.saveConfiguration= function(){
			$scope.saveMessage=[];
			 if($scope.soeId=="" || $scope.soeId==undefined)
			 {
				 //console.log("Please Select Communication Type");
				 $scope.failureMessageModel=true;
				 $scope.saveMessage.message="Please Enter SoeId";
			 }
			 else if($scope.impacted_lob=="" || $scope.impacted_lob==undefined)
			 {
				 //console.log("Please Select Incident Type");
				 $scope.failureMessageModel=true;
				 $scope.saveMessage.message="Please Select Impacted Lob";
			 }
			 else if($scope.application=="" || $scope.application==undefined)
			 {
				 //console.log("Please Select Incident Priority");
				 $scope.failureMessageModel=true;
				 $scope.saveMessage.message="Please Select Application";
			 }
			 else if($scope.role=="" || $scope.role==undefined)
			 {
				 //console.log("Please Select Incident Priority");
				 $scope.failureMessageModel=true;
				 $scope.saveMessage.message="Please Select Role";
			 }
			 else
			 {
				 $scope.failureMessageModel=false;
				 $scope.lobConfig = {
						 "lob" :$scope.impacted_lob,
						 "app" :$scope.application,
						 "role" :$scope.role,
						 "soeId" : $scope.soeId
						 
				 };
				 $http.post('/lobConfig', $scope.lobConfig)
				 .then(function(data){
					 $scope.saveMessage = data.data;
		             console.log($scope.saveMessage);
		             $scope.successMessageModel=true;
		             $scope.impacted_lob="";
		             $scope.application="";
		             $scope.role="";
		             $scope.soeId="";
					 
				 },function (data) {
						
			        	$scope.saveMessage = data.data;
			            console.log($scope.saveMessage);
			            $scope.failureMessageModel=true;
			        	// this function handles error
			
			      });
			 }
			
		};
		
		$scope.listOfLobConfig =[];
		$scope.searchConfiguration= function(){
			
			$http.get('/lobConfig/all').then(function(data){
	              $scope.listOfLobConfig = data.data;
	              $scope.tableLobConfigList=true;
	              console.log($scope.listOfLobConfig);
	         });
		}
		
});//main controller ends here