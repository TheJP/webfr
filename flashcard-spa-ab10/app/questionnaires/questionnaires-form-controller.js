angular
	.module('flashcard')
	.controller('QuestionnairesFormController', ['$uibModalInstance',
function($uibModalInstance){
	this.title = '';
	this.description = '';
	this.ok = function ok(){
		$uibModalInstance.close({ title: this.title, description: this.description });
	};
	this.cancel = function cancel(){
		$uibModalInstance.dismiss('cancel');
	};
	this.submit = this.ok;
}]);