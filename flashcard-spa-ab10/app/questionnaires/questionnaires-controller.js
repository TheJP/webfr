angular
	.module('flashcard')
	.controller('QuestionnairesController', ['$uibModal', '$route', '$routeParams', '$location', 'QuestionnairesRepository',
function($uibModal, $route, $routeParams, $location, QuestionnairesRepository){
	//Initialize route
	this.$route = $route;
	this.$location = $location;
	this.$routeParams = $routeParams;

	this.name = '';
	this.getAll = QuestionnairesRepository.getAll;
//	this.questionnaires = QuestionnairesRepository.getAll(); (Works too)
	this.add = function add(){
		QuestionnairesRepository.add(this.name);
		this.name = '';
	};
	this.remove = function remove(id){
		if(confirm('Really delete ' + QuestionnairesRepository.get(id).title + '?')){
			QuestionnairesRepository.remove(id);
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
			QuestionnairesRepository.save(item);
	    }, function cancel() {
	    });
	};
}]);
