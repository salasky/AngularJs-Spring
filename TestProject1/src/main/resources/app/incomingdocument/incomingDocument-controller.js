angular
    .module('app')
    .controller('IncomingDocumentController', IncomingDocumentController);

function IncomingDocumentController($uibModal, $rootScope, dataService, URLS) {

    const vm = this;
    vm.activeTabNo = 0;
    vm.tabs = [];
    _refreshIncomingDocuments();

    function _refreshIncomingDocuments() {
        const dataPromise = dataService.getData(URLS.baseUrl+URLS.incomingDocuments);
        dataPromise.then(function (incomingDocuments) {
            $rootScope.rootIncomingDocuments = incomingDocuments;
        }).catch(error => console.error(error));
    }

    function personInfo(incomingDocument) {
        const dataPromise = dataService.getData(URLS.baseUrl+URLS.persons + incomingDocument.authorId);
        dataPromise.then(function(person) {
            vm.author = person;
            let tabNo = incomingDocument;
            tabNo.author = vm.author;
            tabNo.index = incomingDocument.name + ' ' + incomingDocument.id.substring(0, 3)
            if (vm.tabs.includes(tabNo)) {
                vm.activeTabNo = tabNo;
            } else {
                vm.tabs.push(tabNo);
                vm.activeTabNo = tabNo;
            }
        }).catch(error => console.error(error));
    }

    vm.openModal = function (incomingDocument) {
        let modalInstance = $uibModal.open({
            templateUrl: 'incomingdocument/modalWindow.html',
            controller: 'IncomingDocumentModalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => incomingDocument,
            }
        });
        return modalInstance;
    };

    vm.info = function (incomingDocument) {
        personInfo(incomingDocument);
    };

    vm.remove = function (index) {
        if (index === 0) {
            if (vm.activeTabNo === vm.tabs[0]) {
                vm.activeTabNo = 0;
            }
        } else {
            if (vm.activeTabNo === vm.tabs[index]) {
                vm.activeTabNo = vm.tabs[index - 1];
            }
        }
        vm.tabs.splice(index, 1);
    };
    vm.activateTab = function (tabNo) {
        vm.activeTabNo = tabNo;
    };
};



