let jsonArray;
let templateObject;


window.onload = getConcerts();

function getConcerts() {
    console.log('GET_CONCERTS');
    $("#adminPanelInteractionButtons").css({"display":"flex"});

    fetch(`/api/admin/concerts`, {
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
        jsonArray = data;
        templateObject = data[0];
        console.log(templateObject);
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
                    table_value += "<div id='legend' style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px; text-align: center;'>"
                        + capitalizeFirstLetter(keys[i])
                        + `<input id='first-row' class="form-control" type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
                }

                table_value += `<button class="films btn btn-primary" onclick="deleteConcert('${values[0]}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Delete</button>`
                table_value += `<button class="films btn btn-info" onclick="updateConcert('${values[0]}','${counter}')" style="width: 100px; margin-left: 25px; align-self: flex-end">Update</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
            else {
                keys = Object.keys(data[j]);
                values = Object.values(data[j]);
                let table_value = "<div style='display: flex'>";

                for (let i = 0; i < keys.length; i++) {
                    table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                        + `<input class="form-control row-insert" type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
                }

                table_value += `<button class="films btn btn-primary" onclick="deleteConcert('${values[0]}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Delete</button>`
                table_value += `<button class="films btn btn-info" onclick="updateConcert('${values[0]}','${counter}')" style="width: 100px; margin-left: 25px; align-self: flex-end">Update</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
        }



        let table_value_second = "<div style='display: flex'>";
        for (let i = 0; i < keys.length; i++) {
            table_value_second += "<div id='insert-legend' style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px; text-align: center'>"
                + capitalizeFirstLetter(keys[i])
                + `<input class="form-control table-row" type="text" id='${keys[i]}${counter}' style='width: 160px'/></div> `;
        }
        table_value_second += `<button class="films btn btn-success" onclick="insertConcert('${counter}')" style="width: 100px; margin-left: 18px; align-self: flex-end">Insert</button>`;
        result.innerHTML += table_value_second + "<br/></div>";
        counter++;
    });
}


// TODO: error if inputs are empty
function insertConcert(num) {
    console.log("INSERT");

    let insertObject = jsonArray[num - 1];
    let updKeys = Object.keys(insertObject);
    let updValues = Object.values(insertObject);

    for (let i = 0; i < updKeys.length; i++) {
        updValues[i] = document.getElementById(updKeys[i] + num).value;
        console.log(updValues[i]);
        insertObject[updKeys[i]] = updValues[i];
    }
    console.log(insertObject);

    fetch(`/api/admin/concerts`,
        {
            method: "POST",
            headers:
                {
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                }, body: JSON.stringify(insertObject)
        }).then(res => {
        return res.json();
    }).then((data) => {
        console.log(data);
        getConcerts();
    });
}



function updateConcert(id, num) {
    console.log("UPDATE");

    console.log(jsonArray[num]);

    let updObject = jsonArray[num];
    let updKeys = Object.keys(updObject);
    let updValues = Object.values(updObject);

    for (let i = 0; i < updKeys.length; i++) {
        updValues[i] = document.getElementById(updKeys[i] + num).value;
        console.log(updValues[i]);
        updObject[updKeys[i]] = updValues[i];
    }
    console.log(updObject);

    fetch(`/api/admin/concerts/${id.toString()}`,
        {
            method: "PUT",
            headers:
                {
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                }, body: JSON.stringify(updObject)
        }).then(res => {
        alert("Updated successfully.");
        getConcerts();
        return res.json();
    }).then((data) => {
        console.log(data);
    })
}



function deleteConcert(id) {
    alert(id + ' has been deleted.');
    console.log("delete_element: " + " name: " + id);

    fetch(`/api/admin/concerts/${id.toString()}`, {
        method: "DELETE",
        headers: {
            "Content-Type": 'application/json; charset=utf-8',
            "Accept": 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
        }

    }).then(res => {
        getConcerts();
        return res.json();
    }).then((data) => {
        console.log(data);
    })
}


function logout() {
    localStorage.clear();
    document.location.href = "/login";
}


function search() {
    let name = document.getElementById('search').value;
    console.log('SEARCH_CONCERTS');
    fetch(`/api/admin/concerts/${name}`, {
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
        document.getElementById('search').value = "";
        jsonArray = data;
        templateObject = data[0];
        console.log(templateObject);
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
                    table_value += "<div id='legend' style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px; text-align: center;'>"
                        + capitalizeFirstLetter(keys[i])
                        + `<input id='first-row' class="form-control" type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
                }

                table_value += `<button class="films btn btn-primary" onclick="deleteConcert('${values[0]}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Delete</button>`
                table_value += `<button class="films btn btn-info" onclick="updateConcert('${values[0]}','${counter}')" style="width: 100px; margin-left: 25px; align-self: flex-end">Update</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
            else {
                keys = Object.keys(data[j]);
                values = Object.values(data[j]);
                let table_value = "<div style='display: flex'>";

                for (let i = 0; i < keys.length; i++) {
                    table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                        + `<input class="form-control row-insert" type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
                }

                table_value += `<button class="films btn btn-primary" onclick="deleteConcert('${values[0]}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Delete</button>`
                table_value += `<button class="films btn btn-info" onclick="updateConcert('${values[0]}','${counter}')" style="width: 100px; margin-left: 25px; align-self: flex-end">Update</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
        }
    });
}



function filter(By) {
    console.log('FILTER_CONCERTS');
    fetch(`/api/admin/filter${By}`, {
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
        jsonArray = data;
        templateObject = data[0];
        console.log(templateObject);
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
                    table_value += "<div id='legend' style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px; text-align: center;'>"
                        + capitalizeFirstLetter(keys[i])
                        + `<input id='first-row' class="form-control" type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
                }

                table_value += `<button class="films btn btn-primary" onclick="deleteConcert('${values[0]}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Delete</button>`
                table_value += `<button class="films btn btn-info" onclick="updateConcert('${values[0]}','${counter}')" style="width: 100px; margin-left: 25px; align-self: flex-end">Update</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
            else {
                keys = Object.keys(data[j]);
                values = Object.values(data[j]);
                let table_value = "<div style='display: flex'>";

                for (let i = 0; i < keys.length; i++) {
                    table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                        + `<input class="form-control row-insert" type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
                }

                table_value += `<button class="films btn btn-primary" onclick="deleteConcert('${values[0]}')" style="width: 100px; align-self: flex-end; margin-left: 18px">Delete</button>`
                table_value += `<button class="films btn btn-info" onclick="updateConcert('${values[0]}','${counter}')" style="width: 100px; margin-left: 25px; align-self: flex-end">Update</button>`
                result.innerHTML += table_value + "<br/></div>";
                counter++;
            }
        }
    });
}


function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
