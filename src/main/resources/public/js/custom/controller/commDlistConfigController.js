/*commDlistConfigAppController starts below*/
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
		 $scope.saveMessage=[];
		 if($scope.comm_type=="" || $scope.comm_type==undefined)
		 {
			 console.log("Please Select Communication Type");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Select Communication Type";
		 }
		 else if($scope.inc_type=="" || $scope.inc_type==undefined)
		 {
			 console.log("Please Select Incident Type");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Select Incident Type";
		 }
		 else if($scope.inc_priority=="" || $scope.inc_priority==undefined)
		 {
			 console.log("Please Select Incident Priority");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Select Incident Priority";
		 }
		 else if($scope.impacted_country=="" || $scope.impacted_country==undefined)
		 {
			 console.log("Please Select Impacted Country");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Select Impacted Country";
		 }
		 else if($scope.impacted_lob=="" || $scope.impacted_lob==undefined)
		 {
			 console.log("Please Select Impacted LOB");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Select Impacted LOB";
		 }
		 else if($scope.to_mail_id=="" || $scope.to_mail_id==undefined)
		 {
			 console.log("Please Enter Valid To Mail Ids");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Enter Valid To Mail Ids";
		 }
		 else if($scope.cc_mail_id=="" || $scope.cc_mail_id==undefined)
		 {
			 console.log("Please Enter Valid CC Mail Ids");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Enter Valid CC Mail Ids";
		 }
		 else if($scope.bcc_mail_id=="" || $scope.bcc_mail_id==undefined)
		 {
			 console.log("Please Enter Valid BCC Mail Ids");
			 $scope.failureMessageModel=true;
			 $scope.saveMessage.message="Please Enter Valid BCC Mail Ids";
		 }
		 else
		 {
			 $scope.failureMessageModel=false;
			 $scope.comm = {
					 "commType" :$scope.comm_type,
					 "incType" :$scope.inc_type,
					 "incPriority" :$scope.inc_priority,
					 "impactedCtry" :$scope.impacted_country,
					 "impactedLob" :$scope.impacted_lob,
					 "toIds" :$scope.to_mail_id,
					 "cc" :$scope.cc_mail_id,
					 "bcc" :$scope.bcc_mail_id
			 };
			 
			 /*$scope.alreadyExistCommDlistConfig=[];
			 $http.post('/commDlistConfig/check',$scope.comm).then(function(data){
				 $scope.alreadyExistCommDlistConfig = data.data;
	             
	             console.log($scope.alreadyExistCommDlistConfig.message);
	         });
			 
			 if($scope.alreadyExistCommDlistConfig.message =="Data Exists")
			 {
				 $scope.failureMessageModel=true;
				 $scope.saveMessage.message="Configuration Already Exists, Please Edit and Save!";
				 $scope.to_mail_id=alreadyExistCommDlistConfig.toIds;
				 $scope.cc_mail_id=alreadyExistCommDlistConfig.cc;
				 $scope.bcc_mail_id=alreadyExistCommDlistConfig.bcc;
				 console.log("data already exists");
			 }
			 else
			 {*/
				 $http.post('/commDlistConfig', $scope.comm)
				 .then(function(data){
		             $scope.saveMessage = data.data;
		             console.log($scope.saveMessage);
		             $scope.successMessageModel=true;
		             $scope.comm_type="";
		             $scope.inc_priority="";
		             $scope.impacted_country="";
		             $scope.impacted_lob="";
		             $scope.to_mail_id="";
		             $scope.cc_mail_id="";
		             $scope.bcc_mail_id="";
		        }, function (data) {
		
		        	$scope.saveMessage = data.data;
		            console.log($scope.saveMessage);
		            $scope.failureMessageModel=true;
		        	// this function handles error
		
		        	});
		//}}
		 }
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
	 
	 psaapp.directive('validateEmail', function() {
		  var EMAIL_REGEXP = /^[_a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,24})$/;

		  return {
		    require: 'ngModel',
		    restrict: '',
		    link: function(scope, elm, attrs, ctrl) {
		      // only apply the validator if ngModel is present and Angular has added the email validator
		      if (ctrl && ctrl.$validators.email) {

		        // this will overwrite the default Angular email validator
		        ctrl.$validators.email = function(modelValue) {
		          return ctrl.$isEmpty(modelValue) || EMAIL_REGEXP.test(modelValue);
		        };
		      }
		    }
		  };
		});
	 
});