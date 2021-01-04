 	var xMLHttpRequest = new XMLHttpRequest();
	var labelset =[];
	
	var clients = [
        { "product_code": "product_link", "product_link": "product_link", "product_code_seller": "product_code_seller", "product_name": "Ap #897-1459 Quam Avenue", "keyword_first": "keyword_first", "keyword_second": "keyword_second", "keyword_third": "keyword_third", "keyword_four": "keyword_four" },
    ];
 
	
$(function(){
    $("#jsGrid").jsGrid({
        width: "100%",
        height: "400px",
 
        inserting: true,
        editing: true,
        sorting: true,
        paging: true,
 
        data: clients,
 
        fields: [
            { name: "product_code", type: "text", width: 50, validate: "required" },
            { name: "product_link", type: "text", width: 150 },
            { name: "product_code_seller", type: "text", width: 60 },
            { name: "product_name", type: "text", width: 110 },
            { name: "keyword_first", type: "text", width: 50 },
			{ name: "keyword_second", type: "text", width: 50 },
			{ name: "keyword_third", type: "text", width: 50 },
			{ name: "keyword_four", type: "text", width: 50 },
            { type: "control" }
        ]
    });

		
		
})
    