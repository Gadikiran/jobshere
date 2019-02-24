/**
 * UserCtrl
 * To get UserService=
 * function($http){var userService={},userService.userRegistrion=function(user),return userService}
 */
app.controller('UserCtrl',function($scope,UserService,$location){
	$scope.userRegistration=function(user){
		//Call a function in a service and pass the user object to that function
		UserService.userRegistration(user).then(
				function(response){//change the path to /login
					$location.path('/login')
				},
				function(response){//Display the error message in registrationform.html
					//Send the data from controller to the view
					//Data is ErrorClazz object
					$scope.error=response.data//response.data is ErrorClazz object
					
				})
	}
	$scope.login=function(user){//user object in json fmt -> {'email':'...','password':'...'}
		UserService.login(user).then(function(response){
			$location.path('/home')
		},function(response){
			$scope.error=response.data//response.data is ErrorClazz object from Restful service (middleware)
		})
	}
})
