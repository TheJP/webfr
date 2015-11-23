/** outadted by service */
angular.module('flashcard').factory('QuestionnairesRepository', function(){
	var questionnaires = [
		{id:1, title: 'Q1', description: 'Lorem ipsum...'},
		{id:2, title: 'Q2', description: 'Lorem ipsum...'},
		{id:3, title: 'Q3', description: 'Lorem ipsum...'}
	];
	return {
		getAll: function getAll(){
			return questionnaires;
		},
		add: function add(name){
			questionnaires.push({ id: Math.floor(Math.random() * 100), title: name, description: 'Lorem ipsum...' });
		}
	};
});