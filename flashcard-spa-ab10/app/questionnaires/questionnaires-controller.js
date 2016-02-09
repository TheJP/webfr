angular
	.module('flashcard')
	.controller('QuestionnairesController', ['$uibModal', '$route', '$routeParams', '$location', 'QuestionnairesRepository',
function($uibModal, $route, $routeParams, $location, QuestionnairesRepository){
	//Initialize route
	this.$route = $route;
	this.$location = $location;
	this.$routeParams = $routeParams;

	var questionnaires = {};

	this.reload = function reload() {
		QuestionnairesRepository.getAll().then(function(response){
			var data = response.data;
			for(id in questionnaires){ delete questionnaires[id]; }
			for(var i = 0; i < data.length; ++i){
				questionnaires[data[i].id] = data[i];
			}
		}, function error(response){ alert('Could not load data'); console.log(response); });
	};
	this.reload();

	this.name = '';
	this.getAll = function(){
		return questionnaires;
	};

	this.add = function add(){
		QuestionnairesRepository.add(this.name).then(function done(response){
			questionnaires[response.data.id] = response.data;
		}, function error(response){ alert('Could not create questionnaire'); console.log(response); });
		this.name = '';
	};
	this.remove = function remove(id){
		if(confirm('Really delete ' + QuestionnairesRepository.get(id).title + '?')){
			QuestionnairesRepository.remove(id).then(function done(response){
				delete questionnaires[id];
			}, function error(response){ alert('Could not delete questionnaire'); console.log(response); });
		}
	};
	this.openModal = function openModal(){
		var modalInstance = $uibModal.open({
			animation: true,
			templateUrl: 'questionnaires/createModal.html',
			controller: 'QuestionnairesFormController as controller',
			size: 'lg'
	    });
		modalInstance.result.then(function done(item) {
			QuestionnairesRepository.save(item).then(function done(response){
				questionnaires[response.data.id] = response.data;
			}, function error(response){ alert('Could not save questionnaire'); console.log(response); });
	    }, function cancel() {
	    });
	};
	this.update = function update(data){
//		questionnaires[data.id].title = data.title;
//		questionnaires[data.id].description = data.description;
	}
}]);
