async function Login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let response = await fetch("/login",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });


    let data = await response.json();

    if (response.status === 200) {
        localStorage.setItem("jwt", data.token);

        if (username === "root") {
            document.location.href = "/admin";
        } else {
            document.location.href = "/user";
        }
    } else {
        let data = await response.json();
        document.querySelector("#result").innerHTML = "Enter correct data!";
        $("#result").css("display", "block");
        ClearInput();
        console.log(data.error);
    }
}


function ClearInput() {
    document.getElementById("username").value = "";
    document.getElementById("password").value = "";
}
