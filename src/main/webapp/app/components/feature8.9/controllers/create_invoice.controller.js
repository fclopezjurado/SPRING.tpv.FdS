angular.module("tpv").controller("CreateInvoiceController",
    function ($scope, getTicketsByUserEmailService, getTicketsByUserMobileService, createInvoiceService) {
		"use strict";
		
		const SEARCH_BY_EMAIL 				= 1;
		const SEARCH_BY_MOBILE_PHONE_NUMBER = 2;
		const FIELDS_ARE_EMPTY 				= "ERROR. Seleccione al menos una opción de búsqueda";
		const BAD_REQUEST_MESSAGE			= "ERROR en la petición";
		const THERE_ARE_NOT_TICKETS_MESSAGE	= "ADVERTENCIA. El usuario no tiene tickets asociados";
		const OK_RESPONSE_CODE				= 200;
		const TICKET_OPTION_IS_EMPTY 		= "ERROR. Seleccione al menos un ticket";
		const INVOICE_HAS_BEEN_CREATED		= "PETICIÓN SATISFACTORIA. La factura ha sido generada"
		
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
            if (((vm.email !== undefined) && (vm.email.length > 0)) || ((vm.mobile !== undefined)
                && (vm.mobile.length > 0))) {
                vm.userFormError = null;

                if (vm.email !== undefined)
                    vm.requestToGetTickets(getTicketsByUserEmailService, vm.email);
                else
                    vm.requestToGetTickets(getTicketsByUserMobileService, vm.mobile);
            }
            else 
            	vm.userFormError = FIELDS_ARE_EMPTY;
        };
        
        vm.requestToGetTickets = function (getUserService, queryParameter) {
            var serverResponseBody;

            serverResponseBody 	= angular.fromJson(getUserService.getTickets(queryParameter));
            vm.tickets 			= serverResponseBody.data;
            vm.showTickets		= true;
            
            if (vm.tickets.length === 0) {
            	vm.ticketsFormError 	= THERE_ARE_NOT_TICKETS_MESSAGE;
            	vm.disableCreateInvoice = true;
            }
            else {
            	vm.showTicketsTable 	= true;
            	vm.disableCreateInvoice = false;
            }
        }
        
        vm.createInvoice = function () {
            if ((vm.ticketID !== undefined) && (vm.ticketID.length > 0)) {
                vm.ticketsFormError = null;
                
                vm.requestToCreateInvoice(createInvoiceService, vm.ticketID);
            }
            else 
            	vm.ticketsFormError = TICKET_OPTION_IS_EMPTY;
        };
        
        vm.requestToCreateInvoice = function (newInvoiceService, queryParameter) {
            var serverResponseBody;

            serverResponseBody 	= angular.fromJson(newInvoiceService.create(queryParameter));
            vm.invoice 			= serverResponseBody.data;
            
            if (vm.invoice.length === 0)
            	vm.ticketsFormError = BAD_REQUEST_MESSAGE;
            else
            	vm.ticketsFormError = INVOICE_HAS_BEEN_CREATED;
        }
    });