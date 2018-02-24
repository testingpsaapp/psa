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
				templateUrl:"/appConfigPage.html",
				controller:	"appConfigController"
			}).
			when("/psaAccessPage",{
				templateUrl:"/psaAccessPage.html",
				controller:	"psaAccessController"
			}).
			when("/triageLeadConfigPage",{
				templateUrl:"/triageLeadConfigPage.html",
				controller: "triageLeadConfigController"
			}).
			when("/incidentMIMComm",{
				templateUrl:"/incident_comm_page.html",
				controller: "incidentCommController"
			}).
			when("/countryBusinessPage",{
				templateUrl:"/countryBusinessPage.html",
				controller: "countryBusinessPageController"
			}).
			when("/briefingPaperPublisher",{
				templateUrl:"/briefingPaperPublisherPage.html",
				controller: "briefingPaperPublisherController"
			}).
			when("/triageLeadIncidentDashboard",{
				templateUrl:"/triageLeadAssignedIncidentDashboard.html",
				//templateUrl:"<canvas id=\"bar\" class=\"chart chart-bar\" chart-data=\"data\" chart-labels=\"labels\" chart-series=\"series\"></canvas>",
				controller: "BarCtrl"
				
			}).
			when("/dailyIncidentActivity",{
				templateUrl:"/dailyIncidentActivityPage.html",
				controller: "dailyIncidentActivityController"				
			})
		}]);



		
