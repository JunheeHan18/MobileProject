/* globals Chart:false, feather:false */

var xMLHttpRequest = new XMLHttpRequest();
var labelset =[];
var dataset =[];
var getData;
var myChart;
var newDataset = {
    type: 'line',
    data: {
      labels:[],
      datasets: [{
        data:[],
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  };


(function () {

  'use strict'
  feather.replace()
  // Graphs
	xMLHttpRequest.open("Get","/RankServlet?key=list",true);
	  xMLHttpRequest.onreadystatechange= function(){
		  if(xMLHttpRequest.readyState == 4 && xMLHttpRequest.status == 200){
			  getData = JSON.parse(xMLHttpRequest.responseText);	
			  for(var i=0; i<getData.date.length;i++){
				newDataset.data.labels.push(getData.date[i]);
				newDataset.data.datasets[0].data.push(getData.data[i]);
			  }
			//alert(newDataset.data.labels);
			//alert(newDataset.data.datasets[0].data);
		  }
		  
	  };
	  xMLHttpRequest.send(null);
  var ctx = document.getElementById('myChart')
  // eslint-disable-next-line no-unused-vars
  myChart = new Chart(ctx, newDataset)
}())

function chartupdate(){
	myChart.update();
}


