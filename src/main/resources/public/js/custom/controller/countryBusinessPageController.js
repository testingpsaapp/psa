/**
 * countryBusinessPageController starts here
 */

psaapp.controller('countryBusinessPageController', function($scope,$http) 
{
	$scope.listOfCountryConfig=[];
	$scope.successMessageModel=false;
    $scope.failureMessageModel=false;
    $scope.tableCountryConfig=false;
	$scope.changeCountryConfigDD =function changeCountryConfigDD(ddVal,dd)
	 {
			//alert(ddVal);
			document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
			document.getElementById(dd).value =ddVal;
			var scopeModel = dd.split('_butt')[0];
			//console.log(scopeModel);
			$scope[scopeModel] = ddVal;
	 };
	 
	 $scope.saveConfiguration=function()
	 {
		 $scope.saveMessage=[];
		 $scope.countryConfig =
		 {
				 "countryCode" :$scope.country_code,
				 "countryName" :$scope.country_name,
				 "region" :$scope.region,
				 "countryDlist":$scope.country_dlist,
				 "techHead":$scope.tech_head_email_id,
				 "ontHead":$scope.ont_head_email_id,
				 "cbmHead":$scope.cbm_email_id
				 
		 };
		 $http.post('/countries/save', $scope.countryConfig)
		 .then(function(data){
			 
			 $scope.saveMessage = data.data;
             console.log($scope.saveMessage);
             $scope.successMessageModel=true;
             $scope.failureMessageModel=false;
             $scope.country_code="";
             $scope.country_name="";
             $scope.region="";
             $scope.country_dlist="";
             $scope.tech_head_email_id="";
             $scope.ont_head_email_id="";
             $scope.cbm_email_id="";
             
		 },function(data){
			 
			 
		 });
	 };
	 
	 $scope.searchConfiguration=function()
	 {
		 $http.get('/countries').then(function(data){
             $scope.listOfCountryConfig = data.data;
             $scope.tableCountryConfig=true;
             console.log($scope.listOfLobConfig);
        });
	 }
});