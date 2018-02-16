/**
 * angular custom js file
 */
var psaapp = angular.module('mainPsaApp', ['ngRoute']);
psaapp.controller('mainPsaAppController', function($rootScope,$scope,$http) {
		
})
.config(['$routeProvider',function($routeProvider){
		$routeProvider.
			when("/commDlistConfigPage",{
				templateUrl:"/commDlistConfigPage.html",
				controller:	"commDlistConfigAppController"
			}).
			when("/lobConfigPage",{templateUrl:"/lobConfigPage.html"}).
			when("/appConfigPage",{templateUrl:"/appConfigPage.html"}).
			when("/psaAccessPage",{templateUrl:"/psaAccessPage.html"}).
			when("/triageLeadConfigPage",{templateUrl:"/triageLeadConfigPage.html"})
		}]);

psaapp.controller('commDlistConfigAppController', function($scope,$http) 
{
	 $scope.loadData = function(){
		 $scope.countries = [];
         $http.get('/countries').then(function(data){
              $scope.countries = data.data;
              //console.log($scope.countries);
         })
    };
	 $scope.loadData();
	 $scope.changeCommDlistDD =function changeCommDlistDD(ddVal,dd)
	 {
			//alert(ddVal);
			document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
			document.getElementById(dd).value =ddVal;
			var scopeModel = dd.split('_butt')[0];
			//console.log(scopeModel);
			$scope[scopeModel] = ddVal;
			//console.log($scope[scopeModel]);
			
		}
	 $scope.saveConfig = function()
	 {
		 $scope.comm = {
				 "commType" :$scope.comm_type,
				 "incType" :$scope.inc_type,
				 "incPriority" :$scope.inc_priority,
				 "impactedCtry" :$scope.impacted_country,
				 "impactedLob" :$scope.impacted_lob,
				 "toIds" :$scope.to_mail_id,
				 "cc" :$scope.cc_mail_id,
				 "bcc" :$scope.bcc_mail_id,
		 };
		 
		 $http.post('/commDlistConfig', $scope.comm)
		 .then(function(data){
             $scope.saveMessage = data.data;
             console.log($scope.saveMessage);
             $scope.successMessageModel=true;
             $timeout(function () {
                 $scope.successMessageModel = false;
               }, 5000);
        }, function (data) {

        	$scope.saveMessage = data.data;
            console.log($scope.saveMessage);
            $scope.failureMessageModel=true;
        	// this function handles error

        	});
	 };
	 
	 $scope.searchConfig = function()
	 {
		 $scope.listOfcommconfig = [];
         $http.get('/commDlistConfig/all').then(function(data){
              $scope.listOfcommconfig = data.data;
              $scope.tableConfigDList = true;
              //console.log($scope.countries);
         })
	 };
});

