jQuery(document).ready(
	function($) {
		$('.ui.modal')
  		.modal('hide');
		$("#createTheaterResponse").hide();
		//Create Theater
		$("#createTheater").click(function(event) {
			event.preventDefault();
			var data = {}
			data["name"] = $("#name").val();
			data["location"] = $("#location").val();
			$.ajax({
		             type: "POST",
		             contentType: "application/json",
		             url: "http://localhost:8080/admin/createTheater",
		             data: JSON.stringify(data),
		             dataType: 'json',
		             timeout: 600000,
		             success: function (data) {
		                 console.log(data);
										 $("#createTheaterResponse").addClass("green");
										 $("#createTheaterResponse").html(data.message);
										 $("#createTheaterResponse").show();

		             },
		             error: function (e) {
									 $("#createTheaterResponse").addClass("red");
									 $("#createTheaterResponse").html("error");
									 $("#createTheaterResponse").show();
		             }
			});


		});

		//Reset Theater Creation
		$("#createTheaterReset").click(function(event) {
			$("#createTheaterResponse").removeClass("red");
			$("#createTheaterResponse").removeClass("green");
			$("#createTheaterResponse").hide();
			$("#name").val('');
			$("#location").val('');
		});

		//Get All Theaters
		$("#listTheaters").click(function(){
	    $.get("http://localhost:8080/admin/getAllTheaterDetails", function(data, status){
	        var listOfTheaters = data.message;
					console.log(listOfTheaters);
					drawTable(listOfTheaters);
	    });
		});
		function drawTable(data) {
	    for (var i = 0; i < data.length; i++) {
	        drawRow(data[i]);
	    }
		}

		function drawRow(rowData) {
		    var row = $("<tr />")
		    $("#listTheatersTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
		    row.append($("<td>" + rowData.id + "</td>"));
		    row.append($("<td>" + rowData.name + "</td>"));
		    row.append($("<td>" + rowData.location + "</td>"));
				row.append($("<td> <button class='small ui button red'>AddSeats</button></td>"))
		}
		//variable to store table id //
		var tid = 0;
		$("#listTheatersTable").on('click','tr td .button',function(e) {
			var txt = $(this).parent().parent().find('td').html();
			tid= txt;
			$('.ui.modal')
	  		.modal('show');
		});
		$("#addSeats").click(function(event) {
			var data = {}
			data["id"] = tid;
			data["seats"] = $("#seatsToAdd").val();
			data["indicator"] = $("#indicator").val();
			$.ajax({
		             type: "POST",
		             contentType: "application/json",
		             url: "http://localhost:8080/admin/createSeats",
		             data: JSON.stringify(data),
		             dataType: 'json',
		             timeout: 600000,
		             success: function (data) {
		                 alert(data.message);
										 $('.ui.modal')
											 .modal('hide');
		             },
		             error: function (e) {
		             }
			});
		});
	});
