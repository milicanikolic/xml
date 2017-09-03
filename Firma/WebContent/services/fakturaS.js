app.factory('fakturaS', [
		'$http',
		function($http, $scope) {

			var fakturaService = {};

			fakturaService.uzmiFirme = function(firmaUlogovana) {
				return $http.post('/Firma/rest/firma', firmaUlogovana);
			}
			
			
			fakturaService.pdf = function(brFakture) {
				return $http.post('/Firma/rest/faktura',brFakture);
			}
			
			
			

			fakturaService.naruciStavke = function(listaStavki, ulogovan,
					izabrana) {
				console.log("servis faktura");
				console.log(ulogovan);
				console.log(izabrana);
				return $http.post('/Firma/rest/firma/' + ulogovan + '/'
						+ izabrana, listaStavki);
			}

			return fakturaService;
		} ]);