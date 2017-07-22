var app = angular.module('app', []);
app.controller('ctrl', ['$scope','$http','service',function ($scope, $http, service) {
	

	$scope.send=function(){
		console.log('usao u ctrl send')
	service.send($scope.name)
	}
	
}]);

app.service('service',['$http', function($http){
		
	 
	 
	 
	 this.send=function(name){
			console.log('usao u service send ' + name);
			
			
			}	 
	  
}]);



	