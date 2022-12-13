function logout() {
    localStorage.clear();
    document.location.href = "/login";
}

let jsonArrayOrderTickets;
let templateObjectOrderTickets;
window.onload = getTickets();



function getTickets() {
    console.log('GET_TICKETS');
    fetch(`/api/user/ticketOrder`, {
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
        jsonArrayOrderTickets = data;
        templateObjectOrderTickets = data[0];
        console.log(templateObjectOrderTickets);
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

                console.log(values[0]);
                table_value += `<button class="films btn btn-info" onclick="orderTicket('${counter}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Order</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
            else {
                keys = Object.keys(data[j]);
                values = Object.values(data[j]);
                let table_value = "<div style='display: flex'>";

                for (let i = 0; i < keys.length; i++) {
                    table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px; text-align: center;'>"
                        + `<input class="form-control row-insert" type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 180px'/></div>`;
                }

                console.log(values[0]);
                table_value += `<button class="films btn btn-info" onclick="orderTicket('${counter}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Order</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
        }
    });
}



function orderTicket(num) {
    let userName = prompt("Which user do you want to book a ticket for?")
    let k = num;

    let insertObject = jsonArrayOrderTickets[k];
    let updKeys = Object.keys(insertObject);
    let updValues = Object.values(insertObject);


    for (let i = 0; i < updKeys.length; i++) {
        updValues[i] = document.getElementById(updKeys[i] + k).value;
        console.log(updValues[i]);
        insertObject[updKeys[i]] = updValues[i];
    }
    let id = k;
    insertObject = {...insertObject, userName, id};
    console.log(insertObject);

    fetch(`/api/user/ticketOrder`,
        {
            method: "POST",
            headers:
                {
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                },
            body: JSON.stringify(insertObject)
        })
        .then(() => {
            getTickets();
            alert(`Ticket successfully booked!`);
        });
}




function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

