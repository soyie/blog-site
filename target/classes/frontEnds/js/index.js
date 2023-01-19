function gettingStage() {
        const options = {
            method: 'GET',
        };
        fetch(`http://localhost:8000/stage`, options)
            .then(response => response.json())
            .then(data => {
                data = {
                    word: data.stage
                };
                document.getElementById("main").innerHTML = data.word;
                stage = data.word;
                console.log(stage);
            });
}

function savePerson(){
   const options = {
               method: 'GET',
           };
           fetch(`http://localhost:8000/response`, options)
               .then(response => response.json())
               .then(data => {
                   data = {
                       word: data.stage
                   };
                   document.getElementById("main").innerHTML = data.word;
                   if(data.word = true){
                        document.getElementById("response-text").text = "Data has been saved"
                   }
                   else{
                        document.getElementById("response-text").text = "passwords must be equal"
                   }

               }
               });
   }
}


posting();
