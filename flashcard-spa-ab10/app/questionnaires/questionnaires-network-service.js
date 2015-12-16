angular.module('flashcard').service('QuestionnairesRepository', function(){
	var questionnaires = {
		1: {id: 1, title: 'Q1', description: 'Lorem ipsum...'},
		2: {id: 2, title: 'Q2', description: 'Lorem ipsum...'},
		3: {id: 3, title: 'Q3', description: 'Lorem ipsum...'}
	};
	this.getAll = function getAll(){
		return questionnaires;
	};
	this.get = function get(id){
		return questionnaires[id];
	};
	this.add = function add(name){
		this.save({ title: name, description: 'Lorem ipsum...' });
	};
	this.save = function save(questionnaire){
		if(!questionnaire.id){
			var id = Math.floor(Math.random() * 100);
			questionnaire.id = id;
		}
		questionnaires[questionnaire.id] = questionnaire;
	};
	this.remove = function remove(id){
		delete questionnaires[id];
	};
});