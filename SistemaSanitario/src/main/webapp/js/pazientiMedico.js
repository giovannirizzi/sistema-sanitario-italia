$(document).ready( function() { 
    getPazienti();
    
    $("#exampleModal").on('hidden.bs.modal', function(e){ 
        $('#modalTitle').text("Scheda di");
        $('#cf').text("");
        $('#birthDate').text("");
        $('#birthPlace').text("");
        $('#showPrescriptions').text("");
    });
 });

$(document).on("click", "tr[name='patientRow']", function(e) {

    $("#exampleModal").modal();

    var id = this.id;
    var myId = this.getAttribute('myattr');
    $.ajax({
      url: "/SistemaSanitario/services/dashboard",
      type: "get", //send it through get method
      dataType : 'json',
      data: {"id":id},
      success: function(response) {
            $('#modalTitle').text("Scheda di "+ patients.patient[myId].name + " " + patients.patient[myId].surname);
            if(patients.patient[myId].photo !== null){
                var imageContainer = document.getElementById("myImage");
                imageContainer.setAttribute('src', 'http://localhost:8080/SistemaSanitario/services/avatar?id='+id);
                var svgContainer = document.getElementById("defaultAvatar");
                svgContainer.style.display = 'none';
                imageContainer.style.display = 'block';
            } else {
                var imageContainer = document.getElementById("myImage");
                var svgContainer = document.getElementById("defaultAvatar");
                imageContainer.style.display = 'none';
                svgContainer.style.display = 'block';
            }
            // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
            var pContainer = document.getElementById("cf");
            pContainer.innerHTML = "<b>Codice fiscale: </b>" + patients.patient[myId].cf;
            pContainer = document.getElementById("birthDate");
            pContainer.innerHTML = "<b>Data di nascita: </b>" + patients.patient[myId].birthDate;
            pContainer = document.getElementById("birthPlace");
            pContainer.innerHTML = "<b>Luogo di nascita: </b>" + patients.patient[myId].birthPlace + " (" + patients.patient[myId].province + ")";
            
            if(response.exams.length !== 0){
                var col = [];
                for (var i = 0; i < response.exams.length; i++) {
                    for (var key in response.exams[i]) {
                        if (col.indexOf(key) === -1) {
                            col.push(key);
                        }
                    }
                }

                // CREATE DYNAMIC TABLE.
                var table = document.createElement("table");
                table.setAttribute('class', 'table');
                table.setAttribute('id', 'mytable');
                var header = table.createTHead();
                header.setAttribute('class', 'thead-light');

                // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

                var tr = header.insertRow(-1);                   // TABLE ROW.

                var arrayHeader = ["Data","Esame","Descrizione"];

                for (var i = 0; i < arrayHeader.length; i++) {
                    var th = document.createElement("th");      // TABLE HEADER.
                    th.innerHTML = arrayHeader[i];
                    tr.appendChild(th);
                }

                var body = table.createTBody();

                // ADD JSON DATA TO THE TABLE AS ROWS.
                for (var i = 0; i < response.exams.length; i++) {

                    tr = body.insertRow(-1);

                    for (var j = 0; j < arrayHeader.length; j++) {
                        var tabCell = tr.insertCell(-1);
                        tabCell.innerHTML = response.exams[i][col[j]];
                    }
                }

                // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
                var divContainer = document.getElementById("showPrescriptions");
                divContainer.innerHTML = "";
                divContainer.appendChild(table);
            } else {
                var divContainer = document.getElementById("showPrescriptions");
                divContainer.innerHTML = "Nessuna prescrizione per questo paziente";
            }
      },
      error: function(xhr, status, error) {
           console.log(xhr.responseText);
      }
    });
});

var patients;

function getPazienti(){
    
    var p;
    $.ajax({
        url : "/SistemaSanitario/services/dashboard",
        dataType : 'json',
        error: function(xhr, status, error) {
            console.log(xhr.responseText);
        },
        success : function(data) {
            
            patients = data;
            
            var col = [];
            for (var i = 0; i < data.patient.length; i++) {
                for (var key in data.patient[i]) {
                    if (col.indexOf(key) === -1) {
                        col.push(key);
                    }
                }
            }
            console.log(col);

            // CREATE DYNAMIC TABLE.
            var table = document.createElement("table");
            table.setAttribute('class', 'table table-hover table-bordered table-striped dt-responsive nowrap');
            table.setAttribute('id', 'myTable');
            var header = table.createTHead();
            header.setAttribute('class', 'thead-light');

            // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

            var tr = header.insertRow(-1);                   // TABLE ROW.

            var arrayHeader = ["Cognome e nome","Ultima visita","Ultima ricetta"];

            for (var i = 0; i < arrayHeader.length; i++) {
                var th = document.createElement("th");      // TABLE HEADER.
                th.innerHTML = arrayHeader[i];
                tr.appendChild(th);
            }

            var body = table.createTBody();

            // ADD JSON DATA TO THE TABLE AS ROWS.
            for (var i = 0; i < data.patient.length; i++) {

                tr = body.insertRow(-1);
                tr.setAttribute('id',""+data.patient[i]["id"]+"");
                tr.setAttribute('name', 'patientRow');
                tr.setAttribute('myattr', i);
                tr.setAttribute('style','cursor:pointer;');

                for (var j = 0; j < arrayHeader.length; j++) {
                    var tabCell = tr.insertCell(-1);
                    switch(j) {
                        case 0:
                          tabCell.innerHTML = data.patient[i]["name"] + " " + data.patient[i]["surname"];
                          break;
                        case 1:
                          tabCell.innerHTML = data.patient[i]["lastVisit"];
                          break;
                        default:
                          tabCell.innerHTML = data.patient[i]["lastMedicine"];
                    }
                }
            }

            // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
            var divContainer = document.getElementById("showData");
            divContainer.innerHTML = "";
            divContainer.appendChild(table);
            $("#myTable").dataTable({
                "dom": '<"top"i>rt<"bottom"lp><"clear">',
                "responsive": true
            });
            console.log(data);
        }  
    });
}

function filterData() {
    var table = $('#myTable').DataTable();
    $('#myInput').on( 'keyup', function () {
        table.search( this.value ).draw();
    } );
}
