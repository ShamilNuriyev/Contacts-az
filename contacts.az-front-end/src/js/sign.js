const registration = async () => {
    let email = document.getElementById('email_id').value;
    let psw = document.getElementById('password_id').value;
    let psw_2 = document.getElementById("2password_id").value;

    const result = await (await fetch('http://localhost:8081/auth/signup', {
        credentials: 'include',
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            "username": email,
            "password": psw,
        }),
    })).json();
    alert("Account has been created.")
}

function logout() {
    sessionStorage.clear();
    document.getElementById("signin_link").hidden = false
    document.getElementById("reg_link").style.display = "inline-block"
    document.getElementById("cre_link").style.display = "none"
    document.getElementById("logout_link").hidden = true
    document.getElementById("mycards").hidden = true
    loadCharacters();

    // location.reload()
}

const auth = async () => {
    let accessToken = sessionStorage.getItem('accessToken')
    let refreshT = sessionStorage.getItem('refreshToken')
    console.log(sessionStorage.getItem('accessToken'))
    await (await fetch('http://localhost:8081/home/user', {
        method: 'GET',
        credentials: 'include',
        headers: {"Authorization": "Bearer " + accessToken}

    }).then(response => {
        if (response.status === 200) {
            document.getElementById("signin_link").hidden = true
            document.getElementById("reg_link").style.display = "none"
            document.getElementById("cre_link").style.display = "inline-block"
            document.getElementById("logout_link").hidden = false
            document.getElementById("mycards").hidden = false

        } else if (response.status === 401) {
            refreshToken();
        }
        return response.json()
    }));
}

const login = async () => {
    let email = document.getElementById('email').value;
    let psw = document.getElementById('psw').value;

    let response = await (await fetch('http://localhost:8081/auth/signin', {
        method: 'POST',
        credentials: 'include', // Needed to include the cookie
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            "username": email,
            "password": psw,
        }),

    })).json();

    sessionStorage.setItem('accessToken', response.accessToken)
    sessionStorage.setItem('refreshToken', response.refreshToken)
    sessionStorage.setItem('username', response.username)

    await auth()
}

async function refreshToken() {
    let response = await (
        await fetch('http://localhost:8081/auth/refreshtoken', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "refreshToken": sessionStorage.getItem('refreshToken'),

            }),
        }).then((response) => {
            return response.json();
        }));
    sessionStorage.setItem('accessToken', response.accessToken)
    sessionStorage.setItem('refreshToken', response.refreshToken)
    auth();
}

auth();