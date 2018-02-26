/**
 * 
 */

psaapp.controller('submitSanityController', function($scope,$http) 
{
	console.log("Inside submitSanityController");
	var url= window.location.href;
	$scope.appName=(((url.split('?')[1]).split("&"))[0]).split('=')[1];
	$scope.changeNum=(((url.split('?')[1]).split("&"))[1]).split('=')[1];
	console.log(url.split('?')[1]);
	$scope.noOfSanityScope=['1'];
	$scope.submitSanity=true;
	
	$http.get("/changeComm/"+$scope.changeNum+"/country").then(function(data){
        $scope.selectCountry = data.data;
	});
	$scope.addSanity = function()
	{
		var x=$scope.noOfSanityScope;
		$scope.noOfSanityScope.push(parseInt(x.length)+1 +"");
		
	};
	$scope.deleteSanity = function(x)
	{
		if($scope.noOfSanityScope.length == 1)
		{
			$scope.noOfSanityScope=[];
			$scope.noOfSanityScope=["1"];
		}
		else
		{
			var index = $scope.noOfSanityScope.indexOf("\""+x+"\"")
			$scope.noOfSanityScope.splice(index,1);
		}
	};
	
	$scope.submitSanity = function()
	{
		$scope.sanityScope=[];
		for(x in $scope.noOfSanityScope)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.sanityScope.push("{\"application\":\""+$('#application_'+a).val()+"\","+"\"country\":\"" +$('#country_'+a).val()+"\","+"\"sanityType\":\""+$('#sanity_type_'+a).val()+"\",\"sanityStep\":\""+$('#sanity_step_'+a).val()+"\"}");
			
		}
		
		console.log($scope.sanityScope);
		
		$http.post("/changeComm/submitSanityScope/"+$scope.changeNum+"/"+$scope.appName,$scope.sanityScope)
		 .then(function(data){
			 $scope.saveMessage = data.data;
             console.log($scope.saveMessage);
             $scope.successMessageModel=true;
             $scope.failureMessageModel=false;
		 },function(data){
			 $scope.saveMessage = data.data;
	         console.log($scope.saveMessage);
	         $scope.failureMessageModel=true;
	         $scope.successMessageModel=false;
		 });
	}
	
});