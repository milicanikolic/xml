app.controller('loginCtrl', function ($scope,$window,$rootScope, loginS) {
	
	$scope.popuniLogin=function(){
		
		document.getElementById("loginIme").value="firmaA";
		document.getElementById("loginSifra").value="firmaA";
	}
	
	
	$scope.loginSubmit=function(){
		loginS.loginSubmit($scope.korisnickoIme, $scope.sifra)
				.then(function(response){
	
					if(response.data!=""){
						$scope.firmaUlogovana=response.data;
						$rootScope.firmaUlogovana=response.data;
						console.log($rootScope.firmaUlogovana);
						$window.location.href='#/pocetna';
					}else{
						console.log("Firma ne postoji");
					}
				})
	}
})