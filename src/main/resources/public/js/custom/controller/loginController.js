/**
 * login controller starts here
 */

loginApp.controller('loginController', function($rootScope,$scope,$http,$location) 
{
	$scope.applications=[];
	$scope.failureMessageModel=false;
	$scope.successMessageModel=false;
	$scope.saveMessage=[];
	$scope.reg_soe_id='';
	$scope.first_name='';
	$scope.last_name='';
	$scope.region=[];
	$scope.lob='';
	$scope.application=[];
	$scope.reg_password='';
	$scope.reg_confirm_password='';
	$scope.getApplications = function()
	{
		$http.get('/appConfig/'+$scope.lob)
		 .then(function(data){
			 $scope.applications=data.data;
		 }, function(data)
		 {
			 
		 });
	};
	
	$scope.login=function()
	{
		var encodedString = '?username=' +encodeURIComponent($scope.soe_id);
		console.log('hi');
		window.location.href='http://localhost:8080/home.html'+encodedString+"#!/worklist";
	};
	
	$scope.register = function()
	{
		//alert("hi");
		if($scope.reg_soe_id == '' || $scope.reg_soe_id == undefined)
		{
			$scope.saveMessage.message="Please Enter SOE ID";
			$scope.failureMessageModel=true;
		}
		else if($scope.first_name=='' || $scope.first_name == undefined)
		{
			$scope.saveMessage.message="Please Enter First Name";
			$scope.failureMessageModel=true;
		}
		else if($scope.last_name=='' || $scope.last_name == undefined)
		{
			$scope.saveMessage.message="Please Enter Last Name";
			$scope.failureMessageModel=true;
		}
		else if($scope.region=='' || $scope.region == undefined)
		{
			$scope.saveMessage.message="Please Select Region";
			$scope.failureMessageModel=true;
		}
		else if($scope.lob=='' || $scope.lob == undefined)
		{
			$scope.saveMessage.message="Please Select LOB";
			$scope.failureMessageModel=true;
		}
		else if($scope.application=='' || $scope.application == undefined)
		{
			$scope.saveMessage.message="Please Select Applications";
			$scope.failureMessageModel=true;
		}
		else if($scope.reg_password=='' || $scope.reg_password == undefined)
		{
			$scope.saveMessage.message="Please Enter Password";
			$scope.failureMessageModel=true;
		}
		else if($scope.reg_confirm_password=='' || $scope.reg_confirm_password == undefined)
		{
			$scope.saveMessage.message="Please Enter Confirm Password";
			$scope.failureMessageModel=true;
		}
		else
		{
			$scope.user={
				'soeId':$scope.reg_soe_id,
				'firstName':$scope.first_name,
				'lastName':$scope.last_name,
				'region':$scope.region,
				'lob':$scope.lob,
				'applications':$scope.application,
				'password':$scope.reg_password,
				'confirmPassword':$scope.reg_confirm_password
			};
			console.log($scope.user);
			$http.post('/register', $scope.user)
			 .then(function(data){
				 console.log(data);
				 //console.log(data.status);
				 //loginService.user=$scope.reg_soe_id;
				 if(data.data['message'] == 'User Already Exists.'){
					 //window.location.href = "/index.html";
					 $scope.saveMessage.message=data.data['message'];
					 $scope.failureMessageModel=true;
					 $scope.successMessageModel=false;
				 }
				 else if(data.data['message'] == 'Registration Failed')
				 {
					 $scope.saveMessage.message=data.data['message'];
					 $scope.failureMessageModel=true;
					 $scope.successMessageModel=false;
				 }
				 else if(data.data['message'] == 'Registration Successful. Please Login To Proceed.')
				 {
					 $scope.saveMessage.message=data.data['message'];
					 $scope.successMessageModel=true; 
					 $scope.failureMessageModel=false;
				 }
			 },function(data){
				 $scope.saveMessage.message=data.data;
				 $scope.failureMessageModel=true;
			 });
		}
	}
});