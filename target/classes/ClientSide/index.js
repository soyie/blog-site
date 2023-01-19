function createAccount(){
    document.getElementById("create-account").style.display = "inline";
    document.getElementById("login").style.display = "none"
    document.getElementById("pic").style.display = "none"
    document.getElementById("Stories").style.display = "none"
}
function login(){
    document.getElementById("create-account").style.display = "none";
    document.getElementById("login").style.display = "inline"
    document.getElementById("write-story").style.display = "none"
    document.getElementById("Stories").style.display = "none"
}
function about(){
    document.getElementById("create-account").style.display = "none";
    document.getElementById("login").style.display = "none"
    document.getElementById("write-story").style.display = "inline"
    document.getElementById("Stories").style.display = "none"
}
function stories(){
    document.getElementById("create-account").style.display = "none";
    document.getElementById("login").style.display = "none"
    document.getElementById("write-story").style.display = "none"
    document.getElementById("pic").style.display = "none"
    document.getElementById("home").style.display = "none"
    document.getElementById("Stories").style.display = "inline"
}
function disappear(){
    document.getElementById("write-story").style.display = "flex"
    document.getElementById("create-account").style.display = "none";
    document.getElementById("login").style.display = "none"
    document.getElementById("pic").style.display = "none"
    document.getElementById("home").style.display = "none"
    document.getElementById("Stories").style.display = "none"
}
const app = new Vue({
  el: '#create-account',
  data: {
    username: '',
    surname: '',
    email: '',
    password: ''
  },
  methods: {
    login() {
      let request = new Request('http://localhost:8000/savePerson', {
          method: 'POST', // Specify the request method
          headers: {
              'Content-Type': 'application/json' // Specify the content type
          },
          body: JSON.stringify({ // Specify the data to be sent in the request body
              name: username,
              surname: surname,
              email: email,
              password: password
          })
      });
      fetch(request)
          .then(response => response.json())
          .then(data => console.log(data))
          .catch(error => console.error(error));
      stories()
    }

  }
});


const log = new Vue({
  el: '#login',
  data: {
    email: '',
    password: ''
  },
  methods: {
    login() {
      let request = new Request('http://localhost:8000/savePerson', {
          method: 'POST', // Specify the request method
          headers: {
              'Content-Type': 'application/json' // Specify the content type
          },
          body: JSON.stringify({ // Specify the data to be sent in the request body
              email: email,
              password: password
          })
      });
      fetch(request)
          .then(response => response.json())
          .then(data => console.log(data))
          .catch(error => console.error(error));
    }

  }
});