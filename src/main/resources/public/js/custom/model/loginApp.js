/**
 * 
 */
var loginApp = angular.module('loginApp', ['ngRoute']);
loginApp.controller('loginController', function($rootScope,$scope,$http) {
	$rootScope.user='guest';
})
.config(['$routeProvider',function($routeProvider){
		$routeProvider.
		when("/",{
			templateUrl:"/login.html",
			controller:	"loginController"
		})
		
}]);