function create() {
    let category = document.getElementById('categories').value;
    let firstname = document.getElementById('firstname').value;
    let lastname = document.getElementById("lastname").value;
    let city = document.getElementById("city").value;
    let phone = document.getElementById("phone").value;
    let price = document.getElementById("price").value;
    let aboutMyself = document.getElementById("about").value;
    let picture = document.getElementById("picture_file").value;
    picture = picture.replace("C:\\fakepath","http://127.0.0.1:8887");
    let username = sessionStorage.getItem('username');

    (async () => {
        await fetch(`http://localhost:8080/employees`, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "category": category,
                "firstName": firstname,
                "lastName": lastname,
                "username": username,
                "city": city,
                "price": price,
                "phoneNumber": phone,
                "pictureUrl": picture,
                "aboutMyself": aboutMyself,
            })
        })
    })()
    changeButton_new3();
    location.reload()
    alert("Done")

}

function deleteCard(id) {
    let url = `http://localhost:8080/employees`;
    fetch(url + `/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(result => {
            if (result.status === 200) {
                document.getElementById(id).remove()
                location.reload()

                alert("Deleted")
            } else {
                alert("Error")
            }
        })
    document.getElementById('#mycards').style.display = 'block'
    document.getElementById('#logout_link').style.display = 'block'
}

