/**
 * 
 */

psaapp.controller('changeCommController', function($scope,$http) 
{
	$scope.countries=[];
	$scope.listOfPP =[];
	$scope.noOfSanityScope=["1"];
	$scope.submitButt=true;
	$scope.populateCountry=function()
	{
		$http.get('/countries/'+$scope.impacted_region).then(function(data){
            $scope.countries = data.data;
		});
	};
	$http.get('/appConfig/all').then(function(data){
        $scope.listOfPP = data.data;
	});
	$scope.populateApplication= function(){
		$scope.applications=$scope.pp_involved;
	};
	$scope.populateSanityCountry =function(){
		$scope.selectCountry = $scope.impacted_country;
	};
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
	
	$scope.submitForCompletion = function()
	{
		$scope.sanity_scope=[];
		for(x in $scope.noOfSanityScope)
		{
			var a=parseInt(x)+1;
			//alert($('#impacted_channel_'+a).val());
			$scope.sanity_scope.push("{\"application\":\""+$('#application_'+a).val()+"\","+"\"country\":\"" +$('#country_'+a).val()+"\","+"\"sanityType\":\""+$('#sanity_type_'+a).val()+"\",\"sanityStep\":\""+$('#sanity_step_'+a).val()+"\"}");
			
		}
		console.log($scope.sanity_scope);
		$scope.changeCommObj={
				"changNum":$scope.change_num,
				"changDate":$scope.change_date,
				"changStartTime":$('#change_start_time').val(),
				"changeEndTime":$('#change_end_time').val(),
				"impactedPp":$scope.pp_involved,
				"impactedCountry":$scope.impacted_country,
				"sanityScope":$scope.sanity_scope,
				"impactedRegion":$scope.impacted_region,
				"changeTitle":$scope.change_title,
				"changeDesc":$scope.change_desc,
				"changeOwner":$scope.change_owner
			};
			console.log($scope.changeCommObj);
			 $http.post('/changeComm/submitForSanityScope', $scope.changeCommObj)
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
	};
});