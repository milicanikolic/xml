app.controller('zahtevCtrl', function($scope, $window, $rootScope, zahtevS) {
	
	$scope.init = function() {
		$scope.ulogovanaFirma = $rootScope.firmaUlogovana;

	}
	
	
	$scope.posaljiZahtev = function(zahtev) {
		zahtev.brojRacuna=$scope.ulogovanaFirma.brojRacuna;
		console.log(zahtev);
		zahtevS.posaljiZahtev(zahtev)
			   .then(function(response) {
			
		})

	}
	
	
})