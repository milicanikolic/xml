app.controller('fakturaCtrl', function ($scope,$window,$rootScope, fakturaS) {


	$scope.$watch(function() {
		$('.selectpicker').selectpicker('refresh')
		
	}
	);


	
	$scope.selektovana=function(){

		var jedna={};
		for(jedna in $scope.stavkeIzabrane){
			$scope.stavkeIzabrane[jedna].kolicina=0;
			
		}
		
		$scope.izabraneStavke=$scope.stavkeIzabrane;
	}
	
	
	$scope.uzmiFirme=function(){
		fakturaS.uzmiFirme($rootScope.firmaUlogovana)
			.then(function(response){
				$scope.firme=response.data;
				console.log($scope.firme);
			})
	}
	
	
	$scope.naruciStavke=function(){
		fakturaS.naruciStavke($scope.izabraneStavke, $rootScope.firmaUlogovana.username, $scope.jednaFirma.username)
				.then(function(response){
					$scope.faktura=response.data;
					console.log($scope.faktura);
				})
	}
	
	$scope.init=function(){
		//$scope.jednaFirma={};
		$scope.uzmiFirme();
	}
})