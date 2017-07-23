app.controller('loginCtrl', function ($scope,$window, loginS) {
	
	$scope.loginSubmit=function(){
		loginS.loginSubmit($scope.korisnickoIme, $scope.sifra);
		$window.location.href='#/pocetna';
			//	.then(function(response){
				//	$window.location.href='#/pocetna';
				//})
	}
})