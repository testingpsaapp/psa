/*psaAccessController Starts below*/
psaapp.controller('psaAccessController', function($scope,$http) 
{
	$scope.modules=[];
	$http.get('/accessModule/all').then(function(data){
         $scope.modules = data.data;
     });
	$scope.subModules=[];
	$scope.roles=[];
	 $scope.changePSAAccessDD =function changePSAAccessDD(ddVal,dd)
	 {
			//alert(ddVal);
			document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
			document.getElementById(dd).value =ddVal;
			var scopeModel = dd.split('_butt')[0];
			//console.log(scopeModel);
			$scope[scopeModel] = ddVal;
			if(scopeModel=='module')
			{
				$http.get('/accessModule/'+$scope.module).then(function(data){
			         $scope.subModules = data.data;
			     });
			}
			else if(scopeModel=='sub_module')
			{
				$http.get('/accessModule/'+$scope.module+"/"+ddVal).then(function(data){
			         $scope.roles = data.data;
			     });
			}
			//console.log($scope[scopeModel]);
			
		};
	
	$scope.saveConfiguration= function(){
		$scope.saveMessage=[];
		 if($scope.module=="" || $scope.module==undefined)
		 {
			 //console.log("Please Select Communication Type");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Select Main Module";
		 }
		 else if($scope.sub_module=="" || $scope.sub_module==undefined)
		 {
			 //console.log("Please Select Incident Type");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Select Sub Module";
		 }
		 else if($scope.roles=="" || $scope.role==undefined)
		 {
			 //console.log("Please Select Incident Priority");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Enter Role";
		 }
		 else if($scope.soeId=="" || $scope.soeId==undefined)
		 {
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Enter Soe Id";
		 }
		 else
		 {
			 $scope.failureMessageModel=false;
			 $scope.psaaccessconfig = {
					 "soeId" :$scope.soeId,
					 "mainModule" :$scope.module,
					 "subModule" :$scope.sub_module,
					 "role" :$scope.role
					 
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
         });
	 };
});