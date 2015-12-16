angular
	.module('flashcard')
	.controller('QuestionnairesUpdateController', ['$routeParams', '$location', 'QuestionnairesRepository',
function($routeParams, $location, QuestionnairesRepository){

	var $scope = this;
	var questionaire = {};
	this.title = '';
	this.description = '';

	var refresh = function refresh(q){
		questionaire = q;
		$scope.title = q.title;
		$scope.description = q.description;
	};

	//Load
	QuestionnairesRepository.get($routeParams.questionnaireId).then(function done(response){
		refresh(response.data);
	}, function error(response){ alert('Could not find questionnaire'); console.log(response); });

	this.update = function update(){
		QuestionnairesRepository.save({ id: questionaire.id, title: this.title, description: this.description });
		$location.path('/');
	}
	this.submit = this.update;
}]);
