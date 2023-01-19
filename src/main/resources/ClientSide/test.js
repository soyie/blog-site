{
    var id;
}

function showNavigation(){
    let data = {email: window.sessionStorage.getItem("email")};
    const template = document.getElementById("navigator").innerText;
    const compiledFunction = Handlebars.compile(template);
    document.getElementById('navigation').innerHTML = compiledFunction(data);
}


function login(){
    const form = document.getElementById("form");
    form.addEventListener("submit", (event) => {
        event.preventDefault();

        const data = new FormData(event.target);
        const email = data.get("email");


        const options = {
            method: 'POST',
            body: JSON.stringify({email: email})
        };


            let res = fetch(`/people`, options);
            res.then(response => response.json())
            .then(data => {
//                console.log(data.id);
                window.sessionStorage.setItem("id", data.id);
//                console.log(data.email);
                window.sessionStorage.setItem("email", data.email);
                window.location.href = '#/expenses';
            })
            .catch(error => {
                document.getElementById('results').innerHTML = "Failed";
//                throw(error);
            });

    });;
}

function expenses(){

    const options = {
        method: 'GET',
    };

    let id = window.sessionStorage.getItem("id");
    let res = fetch(`/expenses/person/${id}`, options);
    res.then(response => response.json())
    .then(data => {
//        console.log(data);
        if(data.length > 0){

            let expenses = [];

            for(let i = 0 ; i < data.length ; i++){
            expenses.push({expense:{
                when: data[i].date,
                what: data[i].description,
                amountSpent: data[i].amount,
                paymentsRequested: data[i].totalPaymentsRequested,
                paymentsReceived: data[i].totalPaymentsReceived,
                nettExpense: data[i].nettAmount
            }});
            }

            data = {
                expenses: expenses
            };
            const template = document.getElementById("expenses-present").innerText;
            const compiledFunction = Handlebars.compile(template);
            document.getElementById('results').innerHTML = compiledFunction(data);
        }
        else{
            const template = document.getElementById("expenses-absent").innerText;
            const compiledFunction = Handlebars.compile(template);
            document.getElementById('results').innerHTML = compiledFunction();
        }


    })
    .catch(error => {
        document.getElementById('results').innerHTML = "Failed";
    });
}

function getPerson(id){
    return fetch(`/people/${id}`, {method: "GET"})
        .then(response => response.json())
        .then(data => {
        return data.email;});
}

function getDescription(id){
     return fetch(`/expenses/${id}`, {method: "GET"})
        .then(response => response.json())
        .then(data => {
        return data.description;});
}

function paymentRequestsSent(){

    const options = {
        method: 'GET',
    };

    let id = window.sessionStorage.getItem("id");
    let res = fetch(`/paymentrequests/sent/${id}`, options);
    res.then(response => response.json())
    .then(data => {
        if(data.length > 0){

            let requestsSent = [];

            for(let i = 0 ; i < data.length ; i++){
            temp = data;
                getPerson(temp[i].toPersonId).then((value) => {
                    let person = value;

                    getDescription(temp[i].expenseId).then((value) => {

                        requestsSent.push({requestSent:{
                            who: person,
                            what: value,
                            due: temp[i].date,
                            amount: temp[i].amount
                        }});
                        if(i == temp.length - 1){
                            data = {

                                2
                            };
                            const template = document.getElementById("paymentrequests-sent-present").innerText;
                            const compiledFunction = Handlebars.compile(template);
                            document.getElementById('results').innerHTML = compiledFunction(data);
                        }
                })});

            }
        }
        else{
            const template = document.getElementById("paymentrequests-sent-absent").innerText;
            const compiledFunction = Handlebars.compile(template);
            document.getElementById('results').innerHTML = compiledFunction();
        }
    })
    .catch(error => {
        document.getElementById('results').innerHTML = "Failed";
    });
}

function paymentRequestsReceived(){
    const options = {
        method: 'GET',
    };

    let id = window.sessionStorage.getItem("id");
    let res = fetch(`/paymentrequests/received/${id}`, options);
    res.then(response => response.json())
    .then(data => {
        if(data.length > 0){

            let requestsReceived = [];

            for(let i = 0 ; i < data.length ; i++){
            temp = data;
                getPerson(temp[i].fromPersonId).then((value) => {
                    let person = value;

                    getDescription(temp[i].expenseId).then((value) => {

                        requestsReceived.push({requestReceived:{
                            who: person,
                            what: value,
                            due: temp[i].date,
                            amount: temp[i].amount
                        }});
                        if(i == temp.length - 1){
                            data = {
                                requestsReceived: requestsReceived
                            };
                            const template = document.getElementById("paymentrequests-received-present").innerText;
                            const compiledFunction = Handlebars.compile(template);
                            document.getElementById('results').innerHTML = compiledFunction(data);
                        }
                })});

            }
        }
        else{
            const template = document.getElementById("paymentrequests-received-absent").innerText;
            const compiledFunction = Handlebars.compile(template);
            document.getElementById('results').innerHTML = compiledFunction();
        }
    })
    .catch(error => {
        document.getElementById('results').innerHTML = "Failed";
    });
}




function paymentRequests(){

    const options = {
        method: 'GET',
    };

    let id = window.sessionStorage.getItem("id");
    let res = fetch(`/expenses/person/${id}`, options);
    res.then(response => response.json())
    .then(data => {
//        console.log(data);
        if(data.length > 0){

            let expenses = [];

            for(let i = 0 ; i < data.length ; i++){
            expenses.push({expense:{
                when: data[i].date,
                what: data[i].description,
                amountSpent: data[i].amount,
            }});
            }
//
            info = {
                date: expenses[1].expense.when,
                description: expenses[1].expense.what,
                amount: expenses[1].expense.amountSpent
            };

            const template = document.getElementById("paymentrequest").innerText;
            const compiledFunction = Handlebars.compile(template);
            document.getElementById('results').innerHTML = compiledFunction(data);
        }

//        else{
//            const template = document.getElementById("paymentrequest").innerText;
//            const compiledFunction = Handlebars.compile(template);
//            document.getElementById('results').innerHTML = compiledFunction();
//        }


    })
    .catch(error => {
        document.getElementById('results').innerHTML = "Failed";
    });
}



window.addEventListener('load', () => {
  const app = $('#main');

  const loginTemplate = Handlebars.compile($('#login').html());
  const expensesTemplate = Handlebars.compile($('#expenses').html());
  const sentTemplate = Handlebars.compile($('#paymentrequests-sent').html());
  const receivedTemplate = Handlebars.compile($('#paymentrequests-received').html());
  const newExpenseTemplate = Handlebars.compile($('#newexpense').html());
  const paymentRequestTemplate = Handlebars.compile($('#paymentrequest').html());



  const router = new Router({
    mode:'hash',
    root:'index.html',
    page404: (path) => {
      const html = loginTemplate();
      app.html(html);
    }
  });

  router.add('/login', async () => {
      window.sessionStorage.clear();
      html = loginTemplate();
      app.html(html);
      login();
    });

  router.add('/expenses', async () => {
    html = expensesTemplate();
    app.html(html);
    showNavigation();
    expenses();
  });

  router.add('/paymentrequests-sent', async () => {
      html = sentTemplate();
      app.html(html);
      showNavigation();
      paymentRequestsSent()
    });

router.add('/paymentrequests-received', async () => {
    html = receivedTemplate();
    app.html(html);
    showNavigation();
    paymentRequestsReceived()
  });

  router.add('/newexpense', async () => {
      html = newExpenseTemplate();
      app.html(html);
      showNavigation();
    });

  router.add('/paymentrequests', async () => {
     html = paymentRequestTemplate();
     app.html(html);
     paymentRequests();
  })


  router.addUriListener();

  $('a').on('click', (event) => {
    event.preventDefault();
    const target = $(event.target);
    const href = target.attr('href');
    const path = href.substring(href.lastIndexOf('/'));
    router.navigateTo(path);
  });

  router.navigateTo('/#login');
});