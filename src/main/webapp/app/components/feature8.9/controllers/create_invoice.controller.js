angular.module("tpv").controller("CreateInvoiceController",
    function ($scope, getTicketsByUserEmailService, getTicketsByUserMobileService, createInvoiceService) {
		"use strict";
		
		const SEARCH_BY_EMAIL 				= 1;
		const SEARCH_BY_MOBILE_PHONE_NUMBER = 2;
		const FIELDS_ARE_EMPTY 				= "ERROR. Seleccione al menos una opción de búsqueda";
		const BAD_REQUEST_MESSAGE			= "ERROR en la petición";
		const THERE_ARE_NOT_TICKETS_MESSAGE	= "ADVERTENCIA. El usuario no tiene tickets asociados";
		const TICKET_OPTION_IS_EMPTY 		= "ERROR. Seleccione al menos un ticket";
		const INVOICE_HAS_BEEN_CREATED		= "PETICIÓN SATISFACTORIA. La factura ha sido generada"
		const THERE_IS_NOT_USER_WITH_MOBILE	= "ERROR. No existe usuario con el número de móvil ";
		const THERE_IS_NOT_USER_WITH_EMAIL 	= "ERROR. No existe usuario con el correo ";
		const OK_RESPONSE_CODE				= 200;
		
        var vm 								= this;
        vm.showInputForMails 				= false;
        vm.showInputForMobilePhoneNumbers 	= false;
        vm.showTickets						= false;
        vm.showTicketsTable					= false;
        vm.disableCreateInvoice				= false;
        vm.invoice							= null;
        vm.tickets							= [];
        
        vm.changeSearchMode = function() {
        	vm.userFormError = null;
        	
        	if (parseInt(vm.searchMode) === SEARCH_BY_EMAIL) {
        		vm.showInputForMails 				= true;
        		vm.showInputForMobilePhoneNumbers 	= false;
        	}
        	else if (parseInt(vm.searchMode) === SEARCH_BY_MOBILE_PHONE_NUMBER) {
        		vm.showInputForMails 				= false;
        		vm.showInputForMobilePhoneNumbers 	= true;
        	}
        	else {
        		vm.showInputForMails 				= false;
        		vm.showInputForMobilePhoneNumbers 	= false;
        	}
        }
        
        vm.getTickets = function () {
            if (((vm.email !== undefined) && (vm.email !== null) && (vm.email.length > 0)) || ((vm.mobile !== undefined)
                && (vm.mobile !== undefined) && (vm.mobile.length > 0))) {
                vm.userFormError = null;

                if ((vm.email !== undefined) && (vm.email !== null) && (vm.email.length > 0))
                    vm.requestToGetTickets(getTicketsByUserEmailService, vm.email);
                else
                    vm.requestToGetTickets(getTicketsByUserMobileService, vm.mobile);
            }
            else {
            	vm.userFormError 	= FIELDS_ARE_EMPTY;
            	vm.email 			= null;
            	vm.mobile 			= null;
            } 
        };
        
        vm.requestToGetTickets = function (getUserService, queryParameter) {
            getUserService.getTickets(queryParameter).then(function (serverResponse) {
                var serverResponse = angular.fromJson(serverResponse);
                
                if (serverResponse.status === OK_RESPONSE_CODE) {
                	vm.tickets = serverResponse.data.tickets;
                	vm.showTickets = true;
                	
                	if (vm.tickets.length === 0) {
                    	vm.ticketsFormError 	= THERE_ARE_NOT_TICKETS_MESSAGE;
                    	vm.disableCreateInvoice = true;
                    }
                    else {
                    	vm.ticketsFormError 	= null;
                    	vm.showTicketsTable 	= true;
                    	vm.disableCreateInvoice = false;
                    }
                }
                else {
            		if (vm.showInputForMobilePhoneNumbers)
                		vm.userFormError = THERE_IS_NOT_USER_WITH_MOBILE + queryParameter;
                	else
                		vm.userFormError = THERE_IS_NOT_USER_WITH_EMAIL + queryParameter;
                	
                	vm.email 	= null;
                	vm.mobile 	= null;
                }
            }, function () {
                vm.userFormError 	= BAD_REQUEST_MESSAGE;
                vm.email 			= null;
            	vm.mobile 			= null;
            });
        }
        
        vm.createInvoice = function () {
            if ((vm.ticketReference !== undefined) && (vm.ticketReference.length > 0)) {
                vm.ticketsFormError = null;
                
                vm.requestToCreateInvoice(createInvoiceService, vm.ticketReference);
            }
            else 
            	vm.ticketsFormError = TICKET_OPTION_IS_EMPTY;
        };
        
        vm.requestToCreateInvoice = function (newInvoiceService, queryParameter) {
            newInvoiceService.create(queryParameter).then(function (serverResponse) {
                var serverResponse = angular.fromJson(serverResponse);
                
                if (serverResponse.status === OK_RESPONSE_CODE) {
                	vm.invoice = serverResponse.data;
                	
                	if (vm.invoice.length === 0)
                		vm.ticketsFormError = BAD_REQUEST_MESSAGE;
                    else
                    	vm.ticketsFormError = INVOICE_HAS_BEEN_CREATED;
                }
                else
                	vm.ticketsFormError = BAD_REQUEST_MESSAGE;
            }, function () {
            	vm.ticketsFormError = BAD_REQUEST_MESSAGE;
            });
        }
    });