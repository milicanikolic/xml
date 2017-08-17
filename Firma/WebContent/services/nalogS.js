app.factory('nalogS',['$http',function($http,$scope){
	
	var nalogService={};

	nalogService.uzmiSveFakture=function(pib){
		console.log("pib " + pib)
		return $http.get('/Firma/rest/faktura/'+pib);
	}
	
	nalogService.posaljiNalog=function(nalog){
		return $http.post('/Firma/rest/nalog',nalog);
	}

return nalogService;

}]);