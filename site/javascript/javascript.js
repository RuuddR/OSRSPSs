displayServers();

function displayServers() {
    servers.forEach(function(server) {
        serverCard( server)
    });
}

function serverCard(serverData) {
    var cards = document.getElementsByClassName("cards")[0];
    var card = document.createElement("div");
    var image = document.createElement("img");
    var info = document.createElement("div");
    var dl = document.createElement("dl");
    var name = document.createElement("dt");
    name.textContent = serverData.name;
    dl.appendChild(name);
    dl.appendChild(property("Client", serverData.client))
    dl.appendChild(property("Framework", serverData.framework));
    info.className = "info";
    info.appendChild(dl);
    image.src = "servers/" + serverData.name + "/1.png";
    card.className = "card";
    card.appendChild(image);
    card.appendChild(info);
    card.onclick = function() {cardClick(serverData)};
    cards.appendChild(card);
}

function property(name, value) {
    text = document.createTextNode(name + " ");
    bold = document.createElement("strong");
    bold.appendChild(text);
    dd = document.createElement("dd");
    dd.appendChild(bold);
    dd.appendChild(document.createTextNode(value));
    return dd;
}

function cardClick(serverData) {
    window.open("servers/" + serverData.name + "/" + serverData.name + ".zip", '_blank');
}