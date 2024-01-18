

indexMain();


function indexMain(){
    document.getElementById("button").onclick = sendForexRequest;
}


async function sendForexRequest(){
    const startDate = document.getElementById("startDate").value.replaceAll("-", "/");
    const endDate = document.getElementById("endDate").value.replaceAll("-", "/");
    const currency = document.getElementById("currency").value;

    const requestPayload = {
        startDate: startDate,
        endDate: endDate,
        currency: currency
    };

    // Assuming you are using fetch API to send the request
    let response =  await fetch('/forex', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestPayload)
    });

    let data = await response.json();
    // Do something with the response data
    document.getElementById("result").innerHTML = JSON.stringify(data);

}