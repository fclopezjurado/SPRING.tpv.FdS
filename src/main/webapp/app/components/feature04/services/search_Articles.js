angular.module("tpv").factory("busquedaDeArticulos", function ($http) {
    const baseURL 			= "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0/products";
    const responseMockWrong = "{\"error\": \"Not found\", \"code\": 404, \"data\": []}";
    const responseMockOK 	= "{\"error\": \"\", \"code\": 200, \"data\": [{\"id\": 45896, \"created\": 1504389600000, " +
	"\"reference\": \"ADF45DAEDU7\", \"ticketState\": \"OPENED\", \"user_id\": 5445646}, {\"id\": 89546, " +
	"\"created\": 1504389500000, \"reference\": \"ADF458AFSD7\", \"ticketState\": \"CLOSED\", \"user_id\": 5445641}]}";
    
    
    
 	var articles = {"code":200,"data":[

 		{
 			"id": 1,
 			"reference": "REFERENCE1",
 			"description": "DESCRIPTION 1",
 		},
 		{
 			"id": 2,
 			"reference": "REFERENCE2",
 			"description": "DESCRIPTION 2",
 		},
 		{
 			"id": 3,
 			"reference": "REFERENCE3",
 			"description": "DESCRIPTION 3",
 			
 		},
 		{
 			"id": 4,
 			"reference": "REFERENCE4",
 			"description": "DESCRIPTION 4",
 		}
 	]};
    
 	var embroideries = [
 		{
 			"id": 5,
 			"reference": "REFERENCE5",
 			"description": "DESCRIPTION 5",
 		},
 		{
 			"id": 6,
 			"reference": "REFERENCE6",
 			"description": "DESCRIPTION 6",
 			
 		},
 		{
 			"id": 7,
 			"reference": "REFERENCE7",
 			"description": "DESCRIPTION 7",
 		},
 		{
 			"id": 8,
 			"reference": "REFERENCE8",
 			"description": "DESCRIPTION 8",
 		}
 	];
 	
 	var textilePrintings = [
 		{
 			"id": 9,
 			"reference": "REFERENCE9",
 			"description": "DESCRIPTION 9",
 			
 		},
 		{
 			"id": 10,
 			"reference": "REFERENCE10",
 			"description": "DESCRIPTION 10",
 			
 		},
 		{
 			"id": 11,
 			"reference": "REFERENCE11",
 			"description": "DESCRIPTION 11",
 			
 		},
 		{
 			"id": 12,
 			"reference": "REFERENCE12",
 			"description": "DESCRIPTION 12",
 			
 		}
 	];
    
 	this.getArticles = () =>{
 		return articles;
 	}
    
 	this.getEmbroideries = () =>{
 		return embroideries;
 	}
 	
 	this.getTextilePrintings = () =>{
 		return textilePrintings;
 	}
    
    
    
    /**
	 * TODO: This end-point will return a set of ticket wrappers, when the user
	 * has information in "DNI", "USERNAME" and "ADDRESS" attributes. The user
	 * who uses this end point must be logged in.
	 */
    return {
    	getProducts: function (products) {
            
            return articles;
        }
    }
	
	    

	  
}); 