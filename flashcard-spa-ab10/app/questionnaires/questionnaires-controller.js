angular.module('flashcard').controller('QuestionnairesController', ['QuestionnairesRepository', function(QuestionnairesRepository){
	this.name = '';
	this.getAll = QuestionnairesRepository.getAll;
//	this.questionnaires = QuestionnairesRepository.getAll(); (Works too)
	this.add = function add(){
		QuestionnairesRepository.add(this.name);
		this.name = '';
	};
	this.remove = QuestionnairesRepository.remove;
}]);
