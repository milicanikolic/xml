app.factory('loginS', [ '$http', function($http, $scope) {

	var loginService = {};

	loginService.loginSubmit = function(kIme, sifra) {
		console.log("servise" + kIme + " , " + sifra);
		return $http.get('/Firma/rest/firma/' + kIme + '/' + sifra);
	};

	return loginService;

} ]);