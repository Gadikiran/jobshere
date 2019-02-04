/**
 * JobCtrl
 */
app.controller('JobCtrl',function($scope,JobService){//JobService is an Service object with one function addJob()
	
	//Add Job
	//Get job object from jobform.html
	$scope.addJob=function(){
		//How to access job details here... $scope.job
		//Call function addJob in JobService
		//Query in DAO  - insert into job_S190224 values (...)
		JobService.addJob($scope.job).then(
		function(response){//from restful service, if client get the response with status code in the range 200 to 2xx 
			alert('Job details inserted successfully...')
			//clear the form fields
			$scope.job={}
		},function(response){//from restful service, if client gets the response with status code in the range 400 to 4xx /500 to 5xx 
			//Get the data from the body of http response and add it in $scope variable.
			console.log(response.status)
			if(response.status==400)
				$scope.message="CLIENT ERROR... BAD REQUEST"
				else
			$scope.error=response.data// ErrorClazz object in JSON representation 
			//{'errorCode':1,'errorMessage':'Job details not inserted..something went wrong..'}
		})
	}
	
	//How to get all jobs?
    //statement - calling a function in service
	//Query in DAO  - select * from job_s190224
	JobService.getAllJobs().then(
			function(response){
				//response.data is  Array of Job objects
				$scope.jobs=response.data
			},function(response){
				
			})
})
