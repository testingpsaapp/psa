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
			when("/psaAccessPage",{
				templateUrl:"/psaAccessPage.html",
				controller:	"psaAccessController"
			}).
			when("/triageLeadConfigPage",{templateUrl:"/triageLeadConfigPage.html"})
		}]);


/*psaAccessController Starts below*/
psaapp.controller('commDlistConfigAppController', function($scope,$http) 
{
	
});
		
