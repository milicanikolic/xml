app.factory('fakturaS',['$http',function($http,$scope){
	
	var fakturaService={};
	
	fakturaService.uzmiFirme=function(firmaUlogovana){
		return $http.post('/Firma/rest/firma',firmaUlogovana);
	}
	
	fakturaService.naruciStavke=function(listaStavki){
		console.log("servis faktura");
		return $http.post('/Firma/rest/firma/kreirajFakturu',listaStavki);
	}
	
	return fakturaService;
}]);