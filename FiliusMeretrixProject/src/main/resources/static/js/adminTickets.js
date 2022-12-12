let jsonArrayTickets;
let templateObjectTickets;


function getTickets() {
    console.log('GET_TICKETS');
    $("#adminPanelInteractionButtons").css({"display":"none"});


    fetch(`/api/admin/tickets`, {
        method: 'GET',
        headers:
            {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            }
    }).then(res => {
        return res.json();
    }).then(data => {
        console.log(data);
        jsonArrayTickets = data;
        templateObjectTickets = data[0];
        console.log(templateObjectTickets);
        result.innerHTML = "";

        let counter = 0;
        let keys;
        let values;


        for (let j = 0; j < data.length; j++) {
            if (j === 0) {
                keys = Object.keys(data[j]);
                values = Object.values(data[j]);
                let table_value = "<div style='display: flex'>";

                for (let i = 0; i < keys.length; i++) {
                    table_value += "<div id='legend' style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px; margin-top: 50px; text-align: center;'>"
                        + capitalizeFirstLetter(keys[i])
                        + `<input id="first-row" class="form-control" type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 180px'/></div>`;
                }

                table_value += `<button class="films btn btn-primary" onclick="deleteTicket('${counter}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Delete</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
            else {
                keys = Object.keys(data[j]);
                values = Object.values(data[j]);
                let table_value = "<div style='display: flex'>";

                for (let i = 0; i < keys.length; i++) {
                    table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                        + `<input class="form-control row-insert" type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 180px'/></div>`;
                }

                table_value += `<button class="films btn btn-primary" onclick="deleteTicket('${counter}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Delete</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
        }


        let table_value_second = "<div style='display: flex'>";

        for (let i = 0; i < keys.length; i++) {
            table_value_second += "<div id='insert-legend' style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px; text-align: center;'>"
                + capitalizeFirstLetter(keys[i])
                + `<input class="form-control table-row" type="text" id='${keys[i]}${counter}' style='width: 180px'/></div> `;
        }
        table_value_second += `<button class="films btn btn-success" onclick="insertTicket('${counter}')" style="width: 100px; margin-left: 18px; align-self: flex-end">Insert</button>`;
        result.innerHTML += table_value_second + "<br/></div>";
        counter++;
    });
}



function insertTicket(num) {
    console.log("INSERT_TICKETS");
    console.log(num);

    let insertObject = jsonArrayTickets[num - 1];
    let updKeys = Object.keys(insertObject);
    let updValues = Object.values(insertObject);

    for (let i = 0; i < updKeys.length; i++) {
        updValues[i] = document.getElementById(updKeys[i] + num).value;
        console.log(updValues[i]);
        insertObject[updKeys[i]] = updValues[i];
    }
    console.log(insertObject);

    fetch(`/api/admin/tickets`,
        {
            method: "POST",
            headers:
                {
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                }, body: JSON.stringify(insertObject)
        }).then(res => {
        getTickets();
        return res.json();
    }).then((data) => {
        console.log(data);
    });
}



function deleteTicket(id) {
    alert('Ticket deleted successfully.');
    console.log("delete_element: " + " name: " + id);

    fetch(`/api/admin/tickets/${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": 'application/json; charset=utf-8',
            "Accept": 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
        }

    }).then(res => {
        getTickets();
        return res.json();
    }).then((data) => {
        console.log(data);
    })
}



function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
