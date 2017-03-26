angular.module("tpv").controller("GetInvoiceController",
    function ($scope, getUsersService, getInvoicesService, getTicketByInvoiceIDService) {
		"use strict";
		
		const THERE_ARE_NOT_USERS_MESSAGE 		= "ADVERTENCIA. No existen usuarios en el sistema";
		const USER_OPTION_IS_EMPTY 				= "ERROR. Seleccione al menos un usuario";
		const THERE_ARE_NOT_INVOICES_MESSAGE 	= "ADVERTENCIA. No existen facturas para el usuario seleccionado";
		const INVOICE_OPTION_IS_EMPTY 			= "ERROR. Seleccione al menos una factura";
		const THERE_IS_NOT_TICKET_MESSAGE 		= "ERROR. No existe ticket para la factura seleccionada";
		const BAD_REQUEST_MESSAGE				= "ERROR en la petición";
		const OK_RESPONSE_CODE					= 200;
		
        var vm 								= this;
        vm.formError 						= null;
        vm.ticket							= null;
        vm.disableGetInvoice				= false;
        vm.showTicket						= false;
		vm.users							= [];
		vm.invoices							= [];
        
        vm.getUsers = function () {
        	getUsersService.getUsers().then(function (serverResponse) {
                var serverResponse = angular.fromJson(serverResponse);
                
                if (serverResponse.status === OK_RESPONSE_CODE) {
                	vm.users = serverResponse.data.userList;
                	
                	if (vm.users.length === 0) {
                    	vm.formError = THERE_ARE_NOT_USERS_MESSAGE;
            			vm.disableGetInvoice = true;
                    }
                    else
            			vm.disableGetInvoice = false;
                }
                else {
                	vm.formError 			= BAD_REQUEST_MESSAGE;
        			vm.disableGetInvoice 	= true;
                }
            }, function () {
            	vm.formError 			= BAD_REQUEST_MESSAGE;
    			vm.disableGetInvoice 	= true;
            });
        };
        
        vm.getInvoices = function () {
            if ((vm.userMobile !== undefined) && (vm.userMobile.length > 0)) {
                vm.formError = null;
                
                vm.requestToGetInvoices(getInvoicesService, vm.userMobile);
            }
            else 
            	vm.formError = USER_OPTION_IS_EMPTY;
        };
        
        vm.requestToGetInvoices = function (serviceToGetInvoices, queryParameter) {
            var serverResponseBody;

            serverResponseBody 	= angular.fromJson(serviceToGetInvoices.getInvoices(queryParameter));
            vm.invoices 		= serverResponseBody.data;
            
            if (vm.invoices.length === 0) {
            	vm.formError = THERE_ARE_NOT_INVOICES_MESSAGE;
            	vm.disableGetInvoice = true;
            }
            else {
            	vm.formError = null;
            	vm.disableGetInvoice = false;
            }
        }
        
        vm.getTicket = function () {
            if ((vm.invoiceID !== undefined) && (vm.invoiceID.length > 0)) {
                vm.formError = null;

                vm.requestToGetTicket(getTicketByInvoiceIDService, vm.invoiceID);
            }
            else 
            	vm.formError = INVOICE_OPTION_IS_EMPTY;
        };
        
        vm.requestToGetTicket = function (getTicketService, queryParameter) {
            var serverResponseBody;

            serverResponseBody 	= angular.fromJson(getTicketService.getTicket(queryParameter));
            vm.ticket 			= serverResponseBody.data;
            
            if (vm.ticket.length === 0)
            	vm.ticketsFormError 	= THERE_IS_NOT_TICKET_MESSAGE;
            else {
            	vm.formError 	= null;
            	vm.showTicket	= true;
            	vm.ticket 		= vm.ticket[0];
            }
        }
    });