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

function onProductResponse() {
    const responseText = JSON.parse(this.responseText);
    const productsDiv = document.getElementById("products");
    const productsTable = document.createElement("table");

    const header = productsTable.createTHead();
    const row = header.insertRow(0);
    const headerList = ["Product Id", "Name of Product", "Unitprice", "Units in Stock", "Name of Category", "Name of Supplier"]
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

        tdProductId.innerHTML = responseText[i].productId;
        tdProductName.innerHTML = responseText[i].productName;
        tdUnitPrice.innerHTML = responseText[i].unitPrice;
        tdUnitsInStock.innerHTML = responseText[i].unitsInStock;
        tdCategoryName.innerHTML = responseText[i].categoryName;
        tdSupplierName.innerHTML = responseText[i].supplierName;

        tr.appendChild(tdProductId);
        tr.appendChild(tdProductName);
        tr.appendChild(tdUnitPrice);
        tr.appendChild(tdUnitsInStock);
        tr.appendChild(tdCategoryName);
        tr.appendChild(tdSupplierName);

        productsTable.appendChild(tr);
        }

        productsDiv.appendChild(productsTable);

        productsDiv.classList.remove("hidden");
}

