app.controller('zahtevCtrl', function($scope, $window, $rootScope, zahtevS) {
	
	$scope.init = function() {
		$scope.ulogovanaFirma = $rootScope.firmaUlogovana;

	}
	$scope.init2 = function() {
		$scope.presek = $rootScope.presek;

	}
	
	$scope.posaljiZahtev = function(zahtev) {
		zahtev.brojRacuna=$scope.ulogovanaFirma.brojRacuna;
		zahtevS.posaljiZahtev(zahtev)
		.then(function(response) {
			 console.log(response.data);
			   $rootScope.presek=response.data;
			   $window.location.href = '#/presek';
		})
	

	}
	
	
})