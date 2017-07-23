app.controller('loginCtrl', function ($scope,$window, loginS) {
	
	$scope.loginSubmit=function(){
		loginS.loginSubmit($scope.korisnickoIme, $scope.sifra)
				.then(function(response){
	
					if(response.data!=""){
						$scope.firma=response.data;
						$window.location.href='#/pocetna';
					}else{
						console.log("Firma ne postoji");
					}
				})
	}
})