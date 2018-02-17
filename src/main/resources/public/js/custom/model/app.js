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
			when("/lobConfigPage",{
				templateUrl:"/lobConfigPage.html",
				controller:	"lobConfigController"
			}).
			when("/appConfigPage",{
				templateUrl:"/appConfigPage.html"
				controller:	"appConfigController"
			}).
			when("/psaAccessPage",{
				templateUrl:"/psaAccessPage.html",
				controller:	"psaAccessController"
			}).
			when("/triageLeadConfigPage",{templateUrl:"/triageLeadConfigPage.html"})
		}]);



		
