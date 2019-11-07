$(document).ready( function () {
    getPazienti();
    $('#myTable').DataTable({
        "searching": false
    } );
    //$('.dataTables_length').addClass('bs-select');
} );

function getPazienti(){

    var pazienti = [
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
        divContainer.appendChild(table);
}

function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }       
    }
}



