async function Register() {
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;


    if (username === "" || email === "" || password === "") {
        console.log('undef');
        document.querySelector("#result").innerHTML = "Fill all inputs!";
        $("#result").css("display", "block");
        return;
    }


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
        window.location.replace("http://localhost:8080/login");
        console.log("ok");
        $("#result").css("display", "none");
    } else {
        let data = await response.json();
        document.querySelector("#result").innerHTML = "User already exists!";
        $("#result").css("display", "block");
        ClearInput();
        console.log(data.error);
    }
}


function ClearInput() {
    document.getElementById("username").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
}

