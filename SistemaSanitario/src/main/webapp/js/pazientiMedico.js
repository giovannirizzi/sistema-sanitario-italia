$(document).ready( function () {
    getPazienti();
    $('#myTable').DataTable({
        "dom": 'lrtip'
    });
} );

function getPazienti(){
    
    var p;
    $.ajax({
        url : "medico/pazienti",
        dataType : 'json',
        error : function() {
            alert("Error Occured");
        },
        success : function(data) {
        
            var col = [];
        for (var i = 0; i < data.length; i++) {
            for (var key in data[i]) {
                if (col.indexOf(key) === -1) {
                    col.push(key);
                }
            }
        }

        // CREATE DYNAMIC TABLE.
        var table = document.createElement("table");
        table.setAttribute('class', 'table');
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
        for (var i = 0; i < data.length; i++) {

            tr = body.insertRow(-1);

            for (var j = 0; j < arrayHeader.length; j++) {
                var tabCell = tr.insertCell(-1);
                if(j===0)
                    tabCell.innerHTML = data[i][col[j]] + " " + data[i][col[j+1]];
                else
                    tabCell.innerHTML = data[i][col[j+1]];
            }
        }

        // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
        var divContainer = document.getElementById("showData");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);

        }
    });

    /*var pazienti = [
            {
                "nome": "John",
                "cognome": "Doe",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Peter",
                "cognome": "Jones",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            },
            {
                "nome": "Anna",
                "cognome": "Smith",
                "ultima visita": "01/01/01",
                "ultima ricetta": "02/02/02"
            }
        ];

        // EXTRACT VALUE FOR HTML HEADER.
        var col = [];
        for (var i = 0; i < pazienti.length; i++) {
            for (var key in pazienti[i]) {
                if (col.indexOf(key) === -1) {
                    col.push(key);
                }
            }
        }

        // CREATE DYNAMIC TABLE.
        var table = document.createElement("table");
        table.setAttribute('class', 'table');
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
        for (var i = 0; i < pazienti.length; i++) {

            tr = body.insertRow(-1);

            for (var j = 0; j < arrayHeader.length; j++) {
                var tabCell = tr.insertCell(-1);
                if(j===0)
                    tabCell.innerHTML = pazienti[i][col[j]] + " " + pazienti[i][col[j+1]];
                else
                    tabCell.innerHTML = pazienti[i][col[j+1]];
            }
        }

        // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
        var divContainer = document.getElementById("showData");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);*/
}

function filterData() {
    var table = $('#myTable').DataTable();
    $('#myInput').on( 'keyup', function () {
        table.search( this.value ).draw();
    } );
}


