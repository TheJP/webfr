angular.module('flashcard').service('QuestionnairesRepository', ['$http', '$cacheFactory', function($http, $cacheFactory){
	var cache = $cacheFactory.get('$http');
	this.getAll = function getAll(){
		return $http.get('/flashcard-rest/questionnaires', { cache: false });
	};
	this.get = function get(id){
		return $http.get('/flashcard-rest/questionnaires/' + id, { cache: true });
	};
	this.add = function add(name){
		return this.save({ title: name, description: 'Lorem ipsum...' });
	};
	this.save = function save(questionnaire){
		if(!questionnaire.id){
			return $http.post('/flashcard-rest/questionnaires/', questionnaire, { cache: false });
		}
		cache.remove('/flashcard-rest/questionnaires/' + questionnaire.id);
		return $http.put('/flashcard-rest/questionnaires/' + questionnaire.id, questionnaire, { cache: false });
	};
	this.remove = function remove(id){
		cache.remove('/flashcard-rest/questionnaires/' + id);
		return $http['delete']('/flashcard-rest/questionnaires/' + id, { cache: false });
	};
}]);
