const charactersList = document.getElementById('charactersList');
const cardList = document.getElementById('card-container');
const searchBar = document.getElementById('search');

let hpCharacters = [];

const loadCharacters = async () => {

    document.getElementById("box").style.display = 'block'

    try {
        const res = await fetch('http://localhost:8080/employees');
        hpCharacters = await res.json();
        displayCharacters(hpCharacters);
        displayCharacter(hpCharacters);
    } catch (err) {
        console.error(err);
    }
};

let input = document.getElementById("search");
input.addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        document.getElementById("myBtn").click();
    }
});

function searchEmployee() {
    const searchString = searchBar.value.toLowerCase().trim();
    const filteredCharacters = hpCharacters.filter((character) => {
        if (character.category.toLowerCase().includes(searchString)) {
            return character.category.toLowerCase().includes(searchString);
        } else {

            document.getElementById("box").style.display = 'none'

        }
    });
    displayCharacters(filteredCharacters);
    // scrollToTop();
}



const displayCharacters = (characters) => {
    // document.getElementById("notfound").hidden = true;

    const htmlString = characters

        .map((character) => {
            return `
             <div class="card" id="card-${character.id1}">
                 <img src="${character.pictureUrl}" class="card-img" alt="inaccessible">
                <div class="card-body">

                <h2 class="title">${character.category}</h2>
                <p><span class="basic-words">Name:</span> ${character.firstName + "  " + character.lastName}</p>
                 <!--<p><span class="basic-words">Username:</span> ${character.username}</p>-->
                <p><span class="basic-words">City:</span> ${character.city}</p>
                <p><span class="basic-words">Price:</span> ${character.price}</p>
                <p><span class="basic-words">Phone:</span> ${character.phoneNumber}</p>
                <div id="rating-${character.id1}" style="display: none"><span class="basic-words">Rating:</span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        </p>
                        </div>
                        <div id="about-${character.id1}" style="display: none;" >
                        <span class="basic-words">About myself:</span>
                        <p>${character.aboutMyself}</p>
                        </div>
                 <a onclick="card(${character.id1}); scrollToTop2()" class="card-btn" id="card-btn-${character.id1}">More info <i class="fas fa-long-arrow-alt-right"></i>
                 </a>
                    </div>
                    </div>
        `;
        })
        .join('');

    charactersList.innerHTML = htmlString;

};

let card = function (id) {
    console.log(id)
    document.getElementById(`${id}`).style.display = 'block'
    document.getElementById('charactersList').style.display = 'none'
    document.getElementById('card-container').style.display = 'block'
}

const displayCharacter = (characters) => {

    const htmlString = characters

        .map((character) => {
            return `
             <div class="card" id="${character.id1}" style="transform: none;display: none; width: 360px;height: 525px">
                 <img src="${character.pictureUrl}" class="card-img" alt="inaccessible">
                <div class="card-body">

                <h2 class="title">${character.category}</h2>
                <p><span class="basic-words">Name:</span> ${character.firstName + "  " + character.lastName}</p>
                 <!--<p><span class="basic-words">Username:</span> ${character.username}</p>-->
                <p><span class="basic-words">City:</span> ${character.city}</p>
                <p><span class="basic-words">Price:</span> ${character.price}</p>
                <p><span class="basic-words">Phone:</span> ${character.phoneNumber}</p>
                
                <div id="rating-${character.id1}" style="display: block"><span class="basic-words">Rating:</span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        </p>
                        </div>
                        <div id="about-${character.id1}" style="display: block;" >
                        <span class="basic-words">About myself:</span>
                        <br>
                        <p style="margin-top: 5px">${character.aboutMyself}</p>
                        </div>
                    </div>
                    </div>
        `;
        })
        .join('');
    cardList.innerHTML = htmlString;
};

const displayCharactersForDelete = (characters) => {
    const htmlString = characters

        .map((character) => {
            return `
             <div class="card" id="card-${character.id1}">
                 <img src="${character.pictureUrl}" class="card-img" alt="inaccessible">
                <div class="card-body">

                <h2 class="title">${character.category}</h2>
                <button onclick="deleteCard(${character.id1})" class="deleteBtn" id="deleteBtn" style="float: right; background: white;color: red; font-weight: bold;cursor: pointer;border: none">Delete</button>
                <p><span class="basic-words">Name:</span> ${character.firstName + "  " + character.lastName}</p>
                 <!--<p><span class="basic-words">Username:</span> ${character.username}</p>-->
                <p><span class="basic-words">City:</span> ${character.city}</p>
                <p><span class="basic-words">Price:</span> ${character.price}</p>
                <p><span class="basic-words">Phone:</span> ${character.phoneNumber}</p>
                <div id="rating-${character.id1}" style="display: none"><span class="basic-words">Rating:</span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        </p>
                        </div>
                        <div id="about-${character.id1}" style="display: none;" >
                        <span class="basic-words">About myself:</span>
                        <p>${character.aboutMyself}</p>
                        </div>
                 <a onclick="card(${character.id1}); scrollToTop2()" class="card-btn" id="card-btn-${character.id1}">More info <i class="fas fa-long-arrow-alt-right"></i>
                 </a>
                    </div>
                    </div>
        `;
        })
        .join('');

    charactersList.innerHTML = htmlString;

};

function cardByUsername() {
    const filteredCharacters = hpCharacters.filter((character) => {
        return (
            character.username === sessionStorage.getItem('username')

        );
    });
    displayCharactersForDelete(filteredCharacters);
    document.getElementById('charactersList').style.minHeight = '550px'
    // deleteLoad()
}


let back = function () {
    displayCharacter(hpCharacters)
    document.getElementById("charactersList").style.display = 'flex'
    document.getElementById("card-container").style.display = 'none'
}

loadCharacters();
