app.controller('nalogCtrl', function ($scope,$window,$rootScope, nalogS) {
	
	$scope.init=function(){
		$scope.uzmiSveFakture();
		$scope.ulogovanaFirma=$rootScope.firmaUlogovana;
	}
	
	$scope.uzmiSveFakture=function(){
		nalogS.uzmiSveFakture()
			.then(function(response){
				$scope.fakture=response.data;
				console.log($scope.fakture);
			})
	}
	
	
	$scope.popuniFakturu=function(faktura){
		$scope.faktura=faktura;
	}
	$scope.plati=function(faktura){
		var nalog={};
		var today = new Date();
		nalog.idPoruke=faktura.zaglavljeFakture.idPoruke;
		nalog.duznik=faktura.zaglavljeFakture.nazivKupac;
		nalog.svrhaPlacanja="";
		nalog.primalac=faktura.zaglavljeFakture.nazivDobavljac;
		nalog.datumNaloga=today;
		nalog.datumValute=faktura.zaglavljeFakture.datumValute;
		nalog.racunDuznik=$scope.ulogovanaFirma.brojRacuna;
		nalog.modelZaduzenja="";
		nalog.pozivNaBrZaduzenja="";
		nalog.racunPoverioca=faktura.zaglavljeFakture.uplataNaRacun;
		nalog.modelOdobrenja="";
		nalog.pozivNaBrOdobrenja="";
		nalog.iznos=faktura.zaglavljeFakture.iznosZaUplatu;
		nalog.oznakaValute=faktura.zaglavljeFakture.valuta;
		nalog.hitno="";
		$scope.nalog=nalog;
		
	}
	
	$scope.posaljiNalog=function(nalog){
		console.log(nalog);
		nalogS.posaljiNalog(nalog)
		.then(function(response){
			
		})
		
	}
	
	
})