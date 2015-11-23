angular.module('flashcard').directive('entry', function(){
	return {
		restrict: 'A',
		scope: {
			questionnaire: '=questionnaire',
			controller: '=controller'
		},
		templateUrl: 'questionnaires/entry.html'
	};
});