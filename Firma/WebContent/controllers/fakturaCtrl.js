app.controller('fakturaCtrl', function ($scope,$window,$rootScope, fakturaS) {


	$scope.$watch(function() {
		$('.selectpicker').selectpicker('refresh')
		
	}
	);

	
	$scope.izaberiStavke=function() {
		var selected=$scope.stavkeIzabrane;
		$scope.stavkeZaKupovinu=selected;
		$scope.izabraneStavke=true;
		console.log($scope.stavkeZaKupovinu);
		
	}
	
	
	
	$scope.selektovana=function(){

		var jedna={};
		for(jedna in $scope.stavkeIzabrane){
			$scope.stavkeIzabrane[jedna].kolicina=0;
			console.log($scope.stavkeIzabrane[jedna]);
			console.log($scope.stavkeIzabrane[jedna].kolicina);
		}
		
		console.log($scope.stavkeIzabrane);
		$scope.izabraneStavke=$scope.stavkeIzabrane;
	}
	
	$scope.ispis=function(jednaFirma) {
		console.log("SELEKTOVANA " + jednaFirma.naziv)
	}
	
	$scope.dodajStavku=function(){
		fakturaS.dodajStavku()
				//.then(function(response){
				//})
	}
	

	
	$scope.uzmiFirme=function(){
		fakturaS.uzmiFirme($rootScope.firmaUlogovana)
			.then(function(response){
				$scope.firme=response.data;
				console.log($scope.firme);
			})
	}
	
	
	$scope.naruciStavke=function(){
		fakturaS.naruciStavke($scope.izabraneStavke);
	}
	
	$scope.init=function(){
		//$scope.jednaFirma={};
		$scope.uzmiFirme();
	}
})