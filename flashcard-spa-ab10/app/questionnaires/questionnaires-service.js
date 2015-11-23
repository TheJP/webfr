angular.module('flashcard').service('QuestionnairesRepository', function(){
	var questionnaires = {
		1: {id: 1, title: 'Q1', description: 'Lorem ipsum...'},
		2: {id: 2, title: 'Q2', description: 'Lorem ipsum...'},
		3: {id: 3, title: 'Q3', description: 'Lorem ipsum...'}
	};
	this.getAll = function getAll(){
		return questionnaires;
	};
	this.add = function add(name){
		var id = Math.floor(Math.random() * 100);
		questionnaires[id] = { id: id, title: name, description: 'Lorem ipsum...' };
	};
	this.remove = function remove(id){
		delete questionnaires[id];
	};
});