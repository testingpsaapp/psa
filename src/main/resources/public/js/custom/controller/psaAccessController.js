/*psaAccessController Starts below*/
psaapp.controller('psaAccessController', function($scope,$http) 
{
	 $scope.changePSAAccessDD =function changePSAAccessDD(ddVal,dd)
	 {
			//alert(ddVal);
			document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
			document.getElementById(dd).value =ddVal;
			var scopeModel = dd.split('_butt')[0];
			//console.log(scopeModel);
			$scope[scopeModel] = ddVal;
			//console.log($scope[scopeModel]);
			
		};
	
	$scope.saveConfiguration= function(){
		$scope.saveMessage=[];
		 if($scope.access_type=="" || $scope.access_type==undefined)
		 {
			 //console.log("Please Select Communication Type");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Select Access Type";
		 }
		 else if($scope.access_role=="" || $scope.access_role==undefined)
		 {
			 //console.log("Please Select Incident Type");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Select Access Role";
		 }
		 else if($scope.soeId=="" || $scope.soeId==undefined)
		 {
			 //console.log("Please Select Incident Priority");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Enter SoeId";
		 }
		 else
		 {
			 $scope.failureMessageModel=false;
			 $scope.psaaccessconfig = {
					 "soeId" :$scope.soeId,
					 "accessType" :$scope.access_type,
					 "accessRole" :$scope.access_role
					 
			 };
			 $http.post('/cLAAcessConfig', $scope.psaaccessconfig)
			 .then(function(data){
				 $scope.saveMessage = data.data;
	             console.log($scope.saveMessage);
	             $scope.successMessageModel=true;
	             $scope.soeId="";
	             $scope.access_type="";
	             $scope.access_role="";
				 
			 },function (data) {
					
		        	$scope.saveMessage = data.data;
		            console.log($scope.saveMessage);
		            $scope.failureMessageModel=true;
		        	// this function handles error
		
		      });
		 }
		
	};
	 
	
	 $scope.searchConfiguration = function()
	 {
		 $scope.listOfPsaAccessConfig = [];
         $http.get('/cLAAcessConfig/all').then(function(data){
              $scope.listOfPsaAccessConfig = data.data;
              $scope.tablePsaAccess = true;
              //console.log($scope.countries);
         })
	 };
});