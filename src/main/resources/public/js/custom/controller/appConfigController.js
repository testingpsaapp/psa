/**
 * 
 */
psaapp.controller('appConfigController', function($scope,$http) 
{
	
	 $scope.changeAppConfigDD =function changeAppConfigDD(ddVal,dd)
	 {
			//alert(ddVal);
			document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
			document.getElementById(dd).value =ddVal;
			var scopeModel = dd.split('_butt')[0];
			//console.log(scopeModel);
			$scope[scopeModel] = ddVal;
			//console.log($scope[scopeModel]);
			
		};
		
	$scope.saveConfiguration=function (){
		$scope.saveMessage=[];
		if($scope.appName=="" || $scope.appName==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Enter Application Name";
		}
		else if($scope.lob=="" || $scope.lob==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Select Lob";
		}
		else if($scope.dList =="" || $scope.dList ==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Enter DList";
		}
		else if($scope.snowdList=="" || $scope.snowdList==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Enter Snow DList";
		}
		else if($scope.lobLead=="" || $scope.lobLead==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Enter Lob Lead";
		}
		else if($scope.psm=="" || $scope.psm==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Enter PSM";
		}
		else if($scope.pssm=="" || $scope.pssm==undefined)
		{
			$scope.failureMessageModel=true;
			$scope.saveMessage.message="Please Enter PSSM";
		}
		else
		{
			$scope.failureMessageModel=false;
			 $scope.appConfig = {
					 "appName" :$scope.appName,
					 "lob" :$scope.lob,
					 "dList" :$scope.dList,
					 "snowDlist" :$scope.snowdList,
					 "lobLead" :$scope.lobLead,
					 "psm" :$scope.psm,
					 "pssm" :$scope.pssm
					 
			 };
			 $http.post('/appConfig', $scope.appConfig)
			 .then(function(data){
				 $scope.saveMessage = data.data;
	             console.log($scope.saveMessage);
	             $scope.successMessageModel=true;
	             $scope.appName="";
	             $scope.lob ="";
	             $scope.dList="";
	             $scope.snowdList="";
	             $scope.lobLead="";
	             $scope.psm="";
	             $scope.pssm="";
	             document.getElementById("lob_butt").innerHTML="Select LOB"+"<span class=\"caret\"></span>";
	             document.getElementById("lob_butt").value="";	 
			 },function (data) {
					
		        	$scope.saveMessage = data.data;
		            console.log($scope.saveMessage);
		            $scope.failureMessageModel=true;
		        	// this function handles error
		
		      });
		}
	};
	
	$scope.searchConfiguration=function(){
		 $scope.listOfAppConfig = [];
         $http.get('/appConfig/all').then(function(data){
              $scope.listOfAppConfig = data.data;
              $scope.tableAppConfig = true;
              //console.log($scope.countries);
         });
	};
});//Main Controller Ends Here
