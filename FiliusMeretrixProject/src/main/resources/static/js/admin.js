let jsonArray;
let templateObject;

function getPlays() {
    console.log('GET_PLAYS');
    fetch(`/api/admin/plays`, {
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

        data.forEach(element => {
            keys = Object.keys(element);
            values = Object.values(element);
            let table_value = "<div style='display: flex'>";

            for (let i = 0; i < keys.length; i++) {
                table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                    + keys[i] + ": "
                    + `<input type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
            }

            table_value += `<button class="films" onclick="deletePlay('${values[0]}')" style="width: 90px; margin-left: 190px; align-self: flex-end">Delete</button>`
            table_value += `<button class="films" onclick="updatePlay('${values[0]}','${counter}')" style="width: 90px; margin-left: 60px; align-self: flex-end">Update</button>`
            result.innerHTML += table_value + "<br/></div>";
            counter++;
        });
        let table_value_second = "<div style='display: flex'>";

        for (let i = 0; i < keys.length; i++) {
            table_value_second += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                + keys[i] + ": "
                + `<input type="text" id='${keys[i]}${counter}' style='width: 160px'/></div> `;
        }
        table_value_second += `<button class="films" onclick="insertPlay('${counter}')" style="width: 90px;  margin: 5px; align-self: flex-end">Insert</button>`;
        result.innerHTML += table_value_second + "<br/></div>";
        counter++;
    });
}

// TODO: error if inputs are empty
function insertPlay(num) {
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

    fetch(`/api/admin/plays`,
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
        getPlays();
    });
}

function updatePlay(id, num) {
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

    fetch(`/api/admin/plays/${id.toString()}`,
        {
            method: "PUT",
            headers:
                {
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                }, body: JSON.stringify(updObject)
        }).then(res => {
        alert("Обновление прошло успешно");
        getPlays();
        return res.json();
    }).then((data) => {
        console.log(data);
    })
}

function deletePlay(id) {
    alert(id);
    console.log("delete_element: " + " name: " + id);

    fetch(`/api/admin/plays/${id.toString()}`, {
        method: "DELETE",
        headers: {
            "Content-Type": 'application/json; charset=utf-8',
            "Accept": 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
        }

    }).then(res => {
        getPlays();
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
    console.log('SEARCH_PLAYS');
    fetch(`/api/admin/plays/${name}`, {
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

        data.forEach(element => {
            keys = Object.keys(element);
            values = Object.values(element);
            let table_value = "<div style='display: flex'>";

            for (let i = 0; i < keys.length; i++) {
                table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                    + keys[i] + ": "
                    + `<input type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
            }

            table_value += `<button class="plays" onclick="deletePlay('${values[0]}')" style="width: 90px; margin: 5px; align-self: flex-end">Delete</button>`
            table_value += `<button class="plays" onclick="updatePlay('${values[0]}','${counter}')" style="width: 90px; margin: 5px; align-self: flex-end">Update</button>`
            result.innerHTML += table_value + "<br/></div>";
            counter++;
        });
    });
}

function filter() {
    console.log('FILTER_PLAYS');
    fetch(`/api/admin/filterPlays`, {
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

        data.forEach(element => {
            keys = Object.keys(element);
            values = Object.values(element);
            let table_value = "<div style='display: flex'>";

            for (let i = 0; i < keys.length; i++) {
                table_value += "<div style='display: flex; flex-direction: column; margin-right: 10px; margin-left: 10px'>"
                    + keys[i] + ": "
                    + `<input type="text" value='${values[i]}' id='${keys[i]}${counter}' style='width: 160px'/></div>`;
            }

            table_value += `<button class="plays" onclick="deletePlay('${values[0]}')" style="width: 90px; margin: 5px; align-self: flex-end">Delete</button>`
            table_value += `<button class="plays" onclick="updatePlay('${values[0]}','${counter}')" style="width: 90px; margin: 5px; align-self: flex-end">Update</button>`
            result.innerHTML += table_value + "<br/></div>";
            counter++;
        });
    });
}