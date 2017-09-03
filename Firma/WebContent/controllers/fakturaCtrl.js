app.controller('fakturaCtrl', function($scope, $window, $rootScope, fakturaS,nalogS) {

					$scope.$watch(function() {
						$('.selectpicker').selectpicker('refresh')

					});

					$scope.selektovana = function() {

						var jedna = {};
						for (jedna in $scope.stavkeIzabrane) {
							$scope.stavkeIzabrane[jedna].kolicina = 0;

						}

						$scope.izabraneStavke = $scope.stavkeIzabrane;
					}

					$scope.uzmiFirme = function() {
						fakturaS.uzmiFirme($rootScope.firmaUlogovana).then(
								function(response) {
									$scope.firme = response.data;
									console.log($scope.firme);
								})
					}
					
					
					
					
					
					
					
					$scope.pdf = function(brFakture) {
						console.log('brFakture ' + brFakture)
						fakturaS.pdf(brFakture).then(
								function(response) {
								
									console.log("nesto");
								})
					}
					
					
					
					
					

					$scope.naruciStavke = function() {
						$rootScope.jednaFirma=$scope.jednaFirma;
						fakturaS
								.naruciStavke($scope.izabraneStavke,
										$rootScope.firmaUlogovana.username,
										$scope.jednaFirma.username)
								.then(
										function(response) {

											var x2js = new X2JS();
											$scope.faktura = x2js
													.xml_str2json(response.data);
											$rootScope.faktura = x2js
													.xml_str2json(response.data);
											$scope.stavkeFakture = x2js
													.asArray($scope.faktura.faktura.stavkaFakture);
											$rootScope.stavkeFakture = x2js
													.asArray($scope.faktura.faktura.stavkaFakture);
											console.log($scope.faktura)

											$window.location.href = '#/fakture';
										})
					}

					$scope.init = function() {
						$scope.uzmiFirme();
					}

					$scope.dobaviFakture = function() {
						$scope.ulogovanaFirma=$rootScope.firmaUlogovana;
						nalogS.uzmiSveFakture($scope.ulogovanaFirma.PIB)

						.then(function(response) {
							$scope.fakture = response.data;
							console.log($scope.fakture);
						})
					//	$scope.faktura = $rootScope.faktura;
					//	$scope.stavkeFakture = $rootScope.stavkeFakture;
						
					}
				})