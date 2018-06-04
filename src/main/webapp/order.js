function onListOrderButtonClicked() {
    showContents(["logout-content", "profile-content", "orders"])
    const ordersDiv = document.getElementById("orders");
    removeAllChildren(ordersDiv);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onOrderResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/ordersservlet');
    xhr.send();
}

function onOrderResponse() {
    const responseText = JSON.parse(this.responseText);
    const ordersDiv = document.getElementById("orders");
    const ordersTable = document.createElement("table");

    const header = ordersTable.createTHead();
    const row = header.insertRow(0);
    const headerList = ["Order Id", "Number of Products Ordered", "Total Price"]
    for (let h = 0; h < headerList.length; h++) {
        let cell = row.insertCell(h);
        cell.innerHTML = headerList[h];
        cell.style.fontWeight = "900";
        cell.style.backgroundColor = "green";
        cell.style.color = "white";
    }

    for (let i = 0; i < responseText.length; i++) {
        const tr = document.createElement("tr");
        const tdOrderId = document.createElement("td");
        const tdNumberOfProductsOrdered = document.createElement("td");
        const tdTotalPrice = document.createElement("td");

        tdOrderId.innerHTML = responseText[i].orderId;
        tdNumberOfProductsOrdered.innerHTML = responseText[i].numberOfProductsOrdered;
        tdTotalPrice.innerHTML = responseText[i].totalPrice;

        tr.appendChild(tdOrderId);
        tr.appendChild(tdNumberOfProductsOrdered);
        tr.appendChild(tdTotalPrice);

        ordersTable.appendChild(tr);
        }

        ordersDiv.appendChild(ordersTable);

        ordersDiv.classList.remove("hidden");
}

function onListDetailedOrderButtonClicked() {
    showContents(["logout-content", "profile-content", "orders2"])
    const orders2Div = document.getElementById("orders2");
    removeAllChildren(orders2Div);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onDetailedOrderResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/ordersservlet');
    xhr.send();
}

function onDetailedOrderResponse() {
    const responseText = JSON.parse(this.responseText);
    const orders2Div = document.getElementById("orders2");
    const orders2Table = document.createElement("table");

    const header = orders2Table.createTHead();
    const row = header.insertRow(0);
    const headerList = ["Product Id", "Name of Product", "Unitprice"]
    for (let h = 0; h < headerList.length; h++) {
        let cell = row.insertCell(h);
        cell.innerHTML = headerList[h];
        cell.style.fontWeight = "900";
        cell.style.backgroundColor = "green";
        cell.style.color = "white";
    }

    for (let i = 0; i < responseText.length; i++) {
        const tr = document.createElement("tr");
        const tdProductId = document.createElement("td");
        const tdProductName = document.createElement("td");
        const tdUnitPrice = document.createElement("td");

        tdProductId.innerHTML = responseText[i].productId;
        tdProductName.innerHTML = responseText[i].productName;
        tdUnitPrice.innerHTML = responseText[i].unitPrice;

        tr.appendChild(tdProductId);
        tr.appendChild(tdProductName);
        tr.appendChild(tdUnitPrice);

        orders2Table.appendChild(tr);
        }

        orders2Div.appendChild(orders2Table);

        orders2Div.classList.remove("hidden");
}

