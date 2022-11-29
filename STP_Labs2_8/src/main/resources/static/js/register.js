async function Register() {
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    // TODO: error message styles
    let response = await fetch("/register",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                email: email,
                password: password
            })
        });

    if (response.status === 200) {
        // window.location.replace("http://localhost:8080/login");
        console.log("ok");
    } else {
        let data = await response.json();
        document.querySelector("#result").innerHTML = "User already exists!";
        ClearInput();
        console.log(data.error);
    }
}

function ClearInput() {
    document.getElementById("username").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}

