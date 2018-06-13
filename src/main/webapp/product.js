let orders = new Array();

function onListProductButtonClicked() {
    showContents(["logout-content", "profile-content", "products"])
    const productsDiv = document.getElementById("products");
    removeAllChildren(productsDiv);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onProductResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/productsservlet');
    xhr.send();
}

function onOrderButtonClicked() {
    orders.push(this.id);
}

function onProductResponse() {
    const responseText = JSON.parse(this.responseText);
    const productsDiv = document.getElementById("products");
    const title = document.createElement('h2');
    title.innerHTML = 'Available Products'
    const productsTable = document.createElement("table");

    const header = productsTable.createTHead();
    const row = header.insertRow(0);
    const headerList = ["Product Id", "Name of Product", "Unitprice", "Units in Stock", "Name of Category", "Name of Supplier", "Order Product"]
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
        const tdUnitsInStock = document.createElement("td");
        const tdCategoryName = document.createElement("td");
        const tdSupplierName = document.createElement("td");
        const tdOrder = document.createElement("td");

        tdProductId.innerHTML = responseText[i].productId;
        tdProductName.innerHTML = responseText[i].productName;
        tdUnitPrice.innerHTML = responseText[i].unitPrice;
        tdUnitsInStock.innerHTML = responseText[i].unitsInStock;
        tdCategoryName.innerHTML = responseText[i].categoryName;
        tdSupplierName.innerHTML = responseText[i].supplierName;
        const orderButtonEl = document.createElement('button');
        orderButtonEl.classList.add('button');
        if (responseText[i].unitsInStock > 0) {
            orderButtonEl.innerHTML = 'Add product';
            orderButtonEl.addEventListener('click', onOrderButtonClicked);
            orderButtonEl.id = responseText[i].productId;
        } else {
            orderButtonEl.innerHTML = 'Out of stock';
            orderButtonEl.classList.add('disabled');
        }


        tdOrder.appendChild(orderButtonEl);
        tr.appendChild(tdProductId);
        tr.appendChild(tdProductName);
        tr.appendChild(tdUnitPrice);
        tr.appendChild(tdUnitsInStock);
        tr.appendChild(tdCategoryName);
        tr.appendChild(tdSupplierName);
        tr.appendChild(tdOrder);

        productsTable.appendChild(tr);
        }
        const submitButtonEl = document.createElement('button');
        submitButtonEl.style.float='right';
        submitButtonEl.style.padding='25px 60px';
        submitButtonEl.innerHTML = 'Order products';
        submitButtonEl.classList.add('button');
        submitButtonEl.addEventListener('click', onSubmitButtonClicked);

        productsDiv.appendChild(title);
        productsDiv.appendChild(productsTable);
        productsDiv.appendChild(submitButtonEl);

        productsDiv.classList.remove("hidden");
}

function onSubmitButtonClicked() {
    const params = new URLSearchParams;
    params.append('orders', orders);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onProductResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/takeorderservlet');
    xhr.send(params);
    alert('Order has sent.')
}

