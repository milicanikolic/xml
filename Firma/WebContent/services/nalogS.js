app.factory('nalogS',['$http',function($http,$scope){
	
	var nalogService={};

	nalogService.uzmiSveFakture=function(){
		return $http.get('/Firma/rest/faktura');
	}
	
	nalogService.posaljiNalog=function(nalog){
		return $http.post('/Firma/rest/nalog',nalog);
	}

return nalogService;

}]);