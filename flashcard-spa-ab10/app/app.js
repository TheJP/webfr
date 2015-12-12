angular
	.module('flashcard', ['ngAnimate', 'ngRoute', 'ui.bootstrap'])
	.config(['$routeProvider', '$locationProvider',
function($routeProvider, $locationProvider){
	$routeProvider
		.when('/update/:questionnaireId', {
			templateUrl: 'questionnaires/update.html',
			controller: 'QuestionnairesUpdateController as controller',
			resolve: {}
		})
		.when('/', {
			templateUrl: 'questionnaires/index.html',
			controller: 'QuestionnairesController as questionnairesController',
			resolve: {}
		});
	$locationProvider.html5Mode(true);
}]);
