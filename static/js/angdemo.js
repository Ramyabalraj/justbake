var userId;

var app = angular.module("myApp", [ "ngRoute" ]);
app.config(function($routeProvider) {

	$routeProvider.when("/", {
		templateUrl : "login.html",
	}).when("/displayorder", {
		templateUrl : "displayorder.html",
		controller : "displayOrder",
		resolve : {
			"check" : function($location, $route) {
				if (userId > 0) {

					alert("Welcome");
				} else {
					$location.path("/"); // redirect user .
					alert("You don't have access here");
				}
			}
		}
	})

	.when("/category", {
		templateUrl : "category.html",
		controller : "displayCategory"

	}).when("/product/:catName", {
		templateUrl : "product.html",
		controller : "productFromCategory"

	}).when("/home", {
		templateUrl : "home.html"

	}).when("/cart", {
		templateUrl : "cart.html",
		controller : "cart",
		resolve : {
			"check" : function($location) {
				if (userId > 0) {
					alert("Welcome");
				} else {
					$location.path("/"); // redirect user .
					alert("You don't have access here");
				}
			}
		}
	}).when("/carttoorder/:productId", {
		templateUrl : "order.html",
		controller : "cartToOrder"

	}).when("/addtocart/:productId", {
		templateUrl : "product.html",
		controller : "addCart",
		resolve : {
			"check" : function($location) {
				if (userId > 0) {

				} else {
					$location.path("/"); // redirect user .
					console.log(userId);
					alert("You don't have access here");
				}
			}
		}

	})

	.when("/order/:productId", {
		templateUrl : "order.html",
		controller : "order"
	}).when("/placeorder/:productId/:qty/:wgt", {
		templateUrl : "order.html",
		controller : "placeOrder",
		resolve : {
			"check" : function($location) {
				if (userId > 0) {

				} else {
					$location.path("/"); // redirect user .
					alert("You don't have access here");
				}
			}
		}

	}).when("/deletecart/:userId/:cartId", {
		templateUrl : "cart.html",
		controller : "deleteCart"

	}).when("/Displayproducts", {
		templateUrl : "displayprd.html",
		controller : "product"

	}).when("/prdaddtocart/:productId", {
		templateUrl : "displayprd.html",
		controller : "productAddCart",
		resolve : {
			"check" : function($location) {
				if (userId > 0) {
					alert("Added To Cart");
				} else {
					$location.path("/"); // redirect user .
					alert("You don't have access here");
				}
			}
		}
	})

});

app
		.controller(
				"category",
				function($scope, $http, $location) {

					$http({
						method : "GET",
						url : "http://localhost:8080/AngularSpring/category/"
					}).then(function mySuccess(response) {
						$scope.category = (response.data);
						$scope.selectedOption = $scope.category;

					}, function myError(response) {
						$scope.category = response.statusText;
					},

					$scope.categoryArray = function() {
						$scope.arr = [];

						for (var i = 0; i < $scope.category.length; i++) {
							$scope.arr[i] = $scope.category[i];
							console.log($scope.arr[i]);
						}

					});
					
					// 1st function
					
					$scope.displayCategory = function($scope) {
						console.log("userId" + userId);
						$scope.categoryArray();

					}
					
					
					// 2nd function
					$scope.productFromCategory = function($scope, $routeParams) {
					
						if ($routeParams.catName != null) {
							$scope.productCategory($routeParams.catName);
						}
					}
					
					$scope.productCategory = function(catname) {

						$http(
								{
									method : "GET",
									url : "http://localhost:8080/AngularSpring/product/selectProductCakes/"
											+ catname
								}).then(function mySuccess(response) {
							$scope.products = (response.data);
							console.log($scope.product);
							$scope.selectproduct = $scope.product[0];
							// window.location = "nav1.html/";
						}, function myError(response) {
							$scope.product = response.statusText;
						});
					}

					
					// 3rd function
					$scope.product = function() {

						$scope.productDisplay();
					}
					
					$scope.productDisplay = function() {
						console.log("userId" + userId);
						$http(
								{
									method : "GET",
									url : "http://localhost:8080/AngularSpring/product/"
								}).then(function mySuccess(response) {
							$scope.products = (response.data);
							console.log($scope.products);

							$scope.selectedprds = $scope.products[0];
						}, function myError(response) {
							alert();
							$scope.products = response.statusText;
						});
					}

				
					
					// 4th function
					$scope.cart = function($scope, $routeParams) {
						$scope.userid = $scope.cartDisplay();
					}
					$scope.cartDisplay = function() {
						console.log("userId" + userId);
						$http(
								{
									method : "GET",
									url : "http://localhost:8080/AngularSpring/cart/"
											+ userId
								}).then(function mySuccess(response) {
							$scope.cartdet = (response.data);
							console.log($scope.cartdet);
							$scope.selectedcart = $scope.cartdet[0];
						}, function myError(response) {
							alert();
							$scope.cartdet = response.statusText;
						});
					}
				
					
					// 5th function
					$scope.productAddCart = function($scope, $routeParams) {
						$scope.addToCart($routeParams.productId);
					}
					$scope.addCart = function($scope, $routeParams) {

						$scope.addToCart($routeParams.productId);
					}
					$scope.addToCart = function(productId) {

						$scope.cartMessage = "Added to cart successfully";
						$scope.cartMessagebool = true;
						$http(
								{
									method : "POST",
									url : "http://localhost:8080/AngularSpring/cart/"
											+ userId + "/" + productId
								}).then(function mySuccess(response) {
							$scope.cartdet = (response.data);
							console.log($scope.cartdet);
							$scope.selectedcart = $scope.cartdet[0];
						}, function myError(response) {
							$scope.cartdet = response.statusText;
						});

					}
				
					
					// 6th function
					$scope.order = function($scope, $routeParams) {

						$scope.productForOrder($routeParams.productId);
					}

					$scope.cartToOrder = function($scope, $routeParams) {

						$scope.productForOrder($routeParams.productId);
					}
				

					$scope.productForOrder = function(productId) {

						$http(
								{
									method : "GET",
									url : "http://localhost:8080/AngularSpring/product/"
											+ productId,

								}).then(function mySuccess(response) {
							$scope.data = (response.data);
							console.log($scope.order);

						}, function myError(response) {
							$scope.data = response.statusText;
						});

					}
					
					
					// 7th function
					$scope.placeOrder = function($scope, $routeParams) {

						$scope.orderPlace($routeParams.productId,
								$routeParams.qty, $routeParams.wgt);
					}
					$scope.orderPlace = function(productId, qty, wgt) {
						$http({
							method : "POST",
							url : "http://localhost:8080/AngularSpring/order/",
							data : {

								"userId" : userId,
								"productId" : productId,
								"quantity" : qty,
								"weight" : wgt

							}
						})
								.then(
										function mySuccess(response) {
											$scope.data = (response.data);
											console.log($scope.data);
											$scope.successMessage = "Your order is successfully";
											$scope.successMessagebool = true;

										}, function myError(response) {
											$scope.data = response.statusText;
										});

					}
					
					
					
					//8th function			
					$scope.deleteCart = function($scope, $routeParams) {

						$scope.deleteFromCart($routeParams.userId,
								$routeParams.cartId);
					}
					$scope.deleteFromCart = function(userId, cartId) {

						$http(
								{
									method : "DELETE",
									url : "http://localhost:8080/AngularSpring/cart/"
											+ userId + "/" + cartId

								}).then(function mySuccess(response) {
							$scope.data = (response.data);
							console.log($scope.order);
						}, function myError(response) {
							$scope.data = response.statusText;
						});
						$http(
								{
									method : "GET",
									url : "http://localhost:8080/AngularSpring/cart/"
											+ userId
								}).then(function mySuccess(response) {

							$scope.cartdet = (response.data);
							console.log($scope.cartdet);

							$scope.selectedcart = $scope.cartdet[0];

						}, function myError(response) {

							$scope.cartdet = response.statusText;
						});

					}

					
					
					// 9th function
					$scope.displayOrder = function($scope, $routeParams) {
						$scope.viewOrder();

					}
					$scope.viewOrder = function() {

						$http(
								{
									method : "GET",
									url : "http://localhost:8080/AngularSpring/order/"
											+ userId
								}).then(function mySuccess(response) {
							$scope.orderdetails = (response.data);
							console.log($scope.orderdetails);

							$scope.selectedcart = $scope.orderdetails[0];

						}, function myError(response) {

							$scope.orderdetails = response.statusText;
						});

					}

					
					
					// 10th function
					$scope.Login = function(userName, pass) {
						$scope.login = [];
						$http(
								{
									method : "GET",
									url : "http://localhost:8080/AngularSpring/login/"
											+ userName + "/" + pass
								})
								.then(
										function mySuccess(response) {
											$scope.logindet = (response.data);
											if ($scope.logindet == "") {
												alert("Wrong Login");
											} else {

												alert("Welcome to Wonder Cakery with Love!!!");
												for (var i = 0; i < $scope.logindet.length; i++) {

													$scope.login[i] = $scope.logindet[i];
													userId = $scope.logindet[i].userId;
													console.log("grt" + userId);
													console
															.log($scope.login[i]);
												}

												$scope.selectedlogindet = $scope.logindet[0];
											}

										},
										function myError(response) {

											$scope.logindet = response.statusText;
										});
					}

				});
