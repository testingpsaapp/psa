/**
 * 
 */
psaapp.controller('psaAccessController', function($scope,$http) 
{
	
	 $scope.changePSAAccessDD =function changeAppConfigDD(ddVal,dd)
	 {
			//alert(ddVal);
			document.getElementById(dd).innerHTML=ddVal+"<span class=\"caret\"></span>";
			document.getElementById(dd).value =ddVal;
			var scopeModel = dd.split('_butt')[0];
			//console.log(scopeModel);
			$scope[scopeModel] = ddVal;
			//console.log($scope[scopeModel]);
			
		};
	
});//Main Controller Ends Here
