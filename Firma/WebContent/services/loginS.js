app.factory('loginS',[function($http,$scope){
	
	var loginService={};
	
	loginService.loginSubmit=function(kIme, sifra){
		//return $http.get('Firma/rest/nestoo');
		console.log("service: "+kIme+", "+sifra);
		return true;
	}
	
	return loginService;
}]);