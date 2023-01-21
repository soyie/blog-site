
username = null
surname = null
email = null
userId = null
topics = []




function createAccount(){
    document.getElementById("create-account").style.display = "inline";
    document.getElementById("login").style.display = "none"
    document.getElementById("pic").style.display = "none"
    document.getElementById("Stories").style.display = "none"
}
function login(){
    document.getElementById("create-account").style.display = "none";
    document.getElementById("login").style.display = "inline"
    document.getElementById("pic").style.display = "none"
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

function ShowStoryPage(){
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
    Create() {
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
          .then(data => {
            data = {
              name: data.name,
              surname: data.surname,
              email: data.email,
              password: data.password,
              useId: data.userId
            };
            username = data.name
            surname = data.surname
            email = data.email
            password = data.password
            userId = data.useId
          })
          .catch(error => console.error(error));
      stories()
      document.getElementById("writer-name").value = username;
    }

  }
});


const log = new Vue({
  el: '#login',
  data: {
    loginEmail: '',
    loginPassword: ''
  },
  methods: {
    login() {
      let request = new Request('http://localhost:8000/logIn', {
          method: 'POST', // Specify the request method
          headers: {
              'Content-Type': 'application/json' // Specify the content type
          },
          body: JSON.stringify({ // Specify the data to be sent in the request body
              email: loginEmail,
              password: loginPassword
          })
      });
      fetch(request)
          .then(response => response.json())
          .then(data => {
            data = {
              name: data.name,
              surname: data.surname,
              email: data.email,
              password: data.password,
              useId: data.userId
            };
            username = data.name
            surname = data.surname
            email = data.email
            password = data.password
            userId = data.useId
            if (data.useId == null){
              alert("Incorrect Email or Password");
            }
            else{
              document.getElementById("writer-name").innerHTML = username
              disappear()
            }
          })
          .catch(error => console.error(error));
    }


  }
});

function saveStory() {
  // title = ;
  // genre = ;
  // story = ;
      request = new Request('http://localhost:8000/postStory', {
        method: 'POST', // Specify the request method
        headers: {
            'Content-Type': 'application/json' // Specify the content type
        },
        body: JSON.stringify({ // Specify the data to be sent in the request body
            writer: username,
            title: document.getElementById("title").value,
            genre: document.getElementById("genre").value,
            story: document.getElementById("story").value
        })
      });
      fetch(request)
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
    }


    function gettingTowns() {
      towns = [];
      const options = {
          method: 'GET',
      };
      // var provinces = new Array();
      // console.log("working on it")
      fetch(`http://localhost:8000/Stories`, options)
          .then(response => response.json())
          .then(data => {
              console.log("from get towns \n"+data) }
      );
  }

const GetStory = new Vue({
    el: '#home',
    methods: {
      AllStories() {
        console.log("Im here")
        alert("going in")

        request = new Request('http://localhost:8000/Stories', {
        method: 'GET', // Specify the request method
        headers: {
            'Content-Type': 'application/json' // Specify the content type
        },
      });
      fetch(request)
        .then(response => response.json())
        .then(data => {
          console.log(data)
          console.log(data.length)
          for (i=0; i<data.length; i++){

            // document.getElementById("Topic").innerHTML = data[i].topic;
            // document.getElementById("Writer").innerHTML = "Writer: "+ data[i].writer
            // document.getElementById("date-and-time").innerHTML = data[i].date +"<br>"+ data[i].time
            // document.getElementById("genre").innerHTML = data[i].genres
            // document.getElementById("storyline").innerHTML = data[i].story
            // document.getElementById("upv").innerHTML = data[i].upVotes
            // document.getElementById("dv").innerHTML = data[i].downVote
            console.log(data[i])
            topics.push({
              id: i,
              topic: data[i].topic,
              writer: data[i].writer,
              story: data[i].story,
              date: data[i].date +" "+data[i].time,
              genre: data[i].genres,
            })
          }
          document.createElement
          ShowStoryPage()
          console.log(topics)
        
        names = '';
        topics.forEach(topics => {
          names += `
              <div id='Topics'>
              <h5 class="topic"><a onclick="displaystory(${topics.id})">${topics.id+" "+topics.topic}</a></h5>
              </div>`
      })
      document.getElementById("topics").innerHTML = names;
    })
        .catch(error => console.error(error));
      }
    }
  });


function displaystory(storyId){
  document.getElementById("Topic").innerHTML = topics[storyId].topic;
  document.getElementById("Writer").innerHTML = "By "+topics[storyId].writer;
  document.getElementById("date-and-time").innerHTML = topics[storyId].date;
  document.getElementById("genre").innerHTML = topics[storyId].genre;
  document.getElementById("storyline").innerHTML = topics[storyId].story;

}
