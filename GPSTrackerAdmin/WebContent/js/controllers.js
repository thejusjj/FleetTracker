function MainCtrl($scope, UserService, $state) {
	$scope.userName = "";
	$scope.password = "";
	$scope.errorMessage = "";
	$scope.isErrorMessage = false;
	$scope.userService = UserService;
	$scope.login = function(){
		$scope.userService.login($scope.userName, $scope.password, function (data, status) {
			if (status == 200){
				if(data.UserId == 0){
					$scope.isErrorMessage = true;
					$scope.errorMessage = "Incorrect Login Details. Please Try again.";
				}else{
					$state.go("gpstracker.userhome");
				}
			}
	    });
	};
};

function UserHomeController($scope, UserService, $state, $stateParams) {
	$scope.mobileNumber = "";
	$scope.errorMessage = "";
	$scope.isErrorMessage = false;
	$scope.userService = UserService;
	$scope.userPosition = {"latitude" : "12.9667", "longitude" : "77.5667"};
	/*if($stateParams.userId == null){
		$state.go("login");
	}*/
	$scope.getUserPosition = function(){
		$scope.userService.getUserPosition($scope.mobileNumber, function (data, status) {
			if (status == 200){
				if(data.UserPosition ==null || data.UserPosition.length == 0){
					$scope.isErrorMessage = true;
					$scope.errorMessage = "No records found. Please try again.";
				}else{
					$scope.userPosition = data.UserPosition;
					$scope.marker = [{
				        coords: {
				            latitude: $scope.userPosition.latitude,
				            longitude: $scope.userPosition.longitude,
				        },
				        options:{
				          title: "I am here"
				        },
				        id: 0
				    }];
				}
			}
	    });
	};
	$scope.map = {center: {latitude: $scope.userPosition.latitude, longitude: $scope.userPosition.longitude }, zoom: 4 };
    $scope.options = {scrollwheel: false};
    $scope.marker = [];

    $scope.windowOptions = {
        visible: false
    };

    $scope.onClick = function() {
        $scope.windowOptions.visible = !$scope.windowOptions.visible;
    };

    $scope.closeClick = function() {
        $scope.windowOptions.visible = false;
    };
};

angular
    .module('inspinia')
    .controller('MainCtrl', MainCtrl)
	.controller('UserHomeController', UserHomeController);

