angular
	.module('flashcard')
	.controller('QuestionnairesUpdateController', ['$routeParams', '$location', 'QuestionnairesRepository',
function($routeParams, $location, QuestionnairesRepository){
	var questionaire = QuestionnairesRepository.get($routeParams.questionnaireId);
	this.title = questionaire.title;
	this.description = questionaire.description;
	this.update = function update(){
		QuestionnairesRepository.save({ id: questionaire.id, title: this.title, description: this.description });
		$location.path('/');
	}
	this.submit = this.update;
}]);