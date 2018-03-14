/**
 * 
 */

(function(){
	var app = angular.module("Module",[]);
	var ctrl = function($scope,$http,$log){
		var url="http://localhost:9010/api/";
		var tmpuser;
		$scope.user={"fname":"","lname":"","username":"","password":"","mailid":""};
		$scope.create = function(){
			$log.info("user:",$scope.user);
			validate();
		}

		var validate = function(){
			$log.info(url+"users",$scope.user);
			
			$http.get(url+"users/"+$scope.user.username)
				.then(function($response){
					if($response.data.username != $scope.user.username){
						$log.info("posting data...");
						$http.post(url+"users",$scope.user);
					}
					else{$scope.error="username already exists.";} 
				});
		}
		
/*		$scope.createRecord = function(){
			
			$log.info("assigning values to create record...",$scope.record);
			
			$scope.record.user = "bharat";
			if($scope.record.topic == ""){$scope.record.topic="physics";}
			if($scope.record.type == ""){$scope.record.topic="1";}
			if($scope.record.status == ""){$scope.record.topic="1";}
			if($scope.record.pririty == ""){$scope.record.topic="1";}
			$scope.record.tag.push("physics");
			$scope.record.tag.push($scope.tag);
			$scope.record.solutions.push($scope.solution);

			$log.info("creating new record...",$scope.record);
			
			$http.post(url+"records/",$scope.record)
				.then(function(){
					$log.info("record created successfully.");
				});
		}
*/	}
	app.controller("MainController",ctrl);
}());