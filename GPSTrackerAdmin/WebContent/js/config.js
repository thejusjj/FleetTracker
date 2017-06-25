function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider, KeepaliveProvider,$httpProvider,uiGmapGoogleMapApiProvider) {
	
	$httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    
    // Configure Idle settings
    IdleProvider.idle(5); // in seconds
    IdleProvider.timeout(120); // in seconds
    
    uiGmapGoogleMapApiProvider.configure({
        key: 'AIzaSyDbqSnGhUEwUNTAjqXt8W5pgZgqwDosHiA',
        v: '3.20',
        libraries: 'weather,geometry,visualization'
    });
    
    $urlRouterProvider.otherwise("/login");

    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });

    $stateProvider
	    .state('login', {
	        url: "/login",
	        templateUrl: "views/login.html",
	        data: { pageTitle: 'Login', specialClass: 'gray-bg' }
	    })
        .state('gpstracker', {
            abstract: true,
            url: "/gpstracker",
            params: { userId: null },
            templateUrl: "views/common/content.html",
        })
        .state('gpstracker.userhome', {
            url: "/userhome",
            params: { userId: null },
            templateUrl: "views/userhome.html",
        });
}
angular
    .module('inspinia')
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });
