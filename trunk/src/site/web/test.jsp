<%--
  Created by IntelliJ IDEA.
  User: Evgeniy
  Date: 07.02.2012
  Time: 19:31:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <script type="text/javascript" src="/rp/js/jquery-1.7.js"></script>

    <script type="text/javascript">
var chart;
$(document).ready(function() {
	chart = new Highcharts.Chart({
		chart: {
			renderTo: 'container',
			defaultSeriesType: 'column',
			margin: [ 50, 50, 100, 80]
		},
		title: {
			text: 'World\'s largest cities per 2008'
		},
		xAxis: {
			categories: [
				'Tokyo',
				'Jakarta',
				'New York',
				'Seoul',
				'Manila',
				'Mumbai',
				'Sao Paulo',
				'Mexico City',
				'Dehli',
				'Osaka',
				'Cairo',
				'Kolkata',
				'Los Angeles',
				'Shanghai',
				'Moscow',
				'Beijing',
				'Buenos Aires',
				'Guangzhou',
				'Shenzhen',
				'Istanbul'
			],
			labels: {
				rotation: -45,
				align: 'right',
				style: {
					font: 'normal 13px Verdana, sans-serif'
				}
			}
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Population (millions)'
			}
		},
		legend: {
			enabled: false
		},
		tooltip: {
			formatter: function() {
				return '<b>'+ this.x +'</b><br/>'+
					'Population in 2008: '+ Highcharts.numberFormat(this.y, 1) +
					' millions';
			}
		},
			series: [{
			name: 'Population',
			data: [34.4, 21.8, 20.1, 20, 19.6, 19.5, 19.1, 18.4, 18,
				17.3, 16.8, 15, 14.7, 14.5, 13.3, 12.8, 12.4, 11.8,
				11.7, 11.2],
			dataLabels: {
				enabled: true,
				rotation: -90,
				color: '#FFFFFF',
				align: 'right',
				x: -3,
				y: 10,
				formatter: function() {
					return this.y;
				},
				style: {
					font: 'normal 13px Verdana, sans-serif'
				}
			}
		}]
	});
});

    </script>
    <script type="text/javascript" src="/rp/js/js/highcharts.js"></script>
    <script type="text/javascript" src="/rp/js/js/modules/exporting.js"></script>
    <script type="text/javascript" src="/rp/js/js/modules/canvas-tools.js"></script>
    <script type="text/javascript" src="/rp/js/js/adapters/mootools-adapter.js"></script>
    <script type="text/javascript" src="/rp/js/js/adapters/prototype-adapter.js"></script>


</head>
<body>
,l,ml
<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>

</body>
</html>