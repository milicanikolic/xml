app.factory('zahtevS', [ '$http', function($http, $scope) {

	var zahtevService = {};

	zahtevService.posaljiZahtev = function(zahtev) {
		console.log(zahtev)
		return $http.post('/Firma/rest/zahtev', zahtev);
	}


	return zahtevService;

} ]);