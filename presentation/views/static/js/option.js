//rest urls.
const url_get = 'http://localhost:8080/todo_ameliored/listOptions';
const url_update = 'http://localhost:8080/todo_ameliored/updateOption';
const url_delete = 'http://localhost:8080/todo_ameliored/deleteOption';
const url_post = 'http://localhost:8080/todo_ameliored/addOption';

//body of the table.
const _tbody = document.getElementById('tbody');

//buttons.
const _addOption = document.getElementById('addOption');

//inputs.
const _nomInput = document.getElementById('nomInput');
const _descriptionInput = document.getElementById('descriptionInput');

// events :

//fetching data on the load of the page.

window.addEventListener('load',async()=>{

    try {

        const response = await fetch(url_get);

        const data  = await response.json();

        data.forEach(option=>{
            addToTable(option);
        });

    }   catch(error){
        console.log(error);
    }

});

//methods

const addToTable = (option)=>{

    //tr
    let _tr = document.createElement('tr');
    //tds
    let _td_nom = document.createElement('td');
    let _td_description = document.createElement('td');
    let _td_action = document.createElement('td');

    //buttons --edit and delete.
    let editBtn = document.createElement('button');
    let deleteBtn = document.createElement('button');

    //adding style and content.
    editBtn.className = 'btn btn-primary';
    deleteBtn.className = 'btn btn-outline-primary';
    editBtn.innerText = 'edit';
    deleteBtn.innerText = 'delete';

    //adding an event to btns.
    //first don't forget to add an id so we can use it either to remove or edit.
    editBtn.id = option.nom;
    deleteBtn.id = option.nom;

    editBtn.addEventListener('click',e=>{

        if(e.target.innerText === 'edit') {

            let nom_value = e.target.parentElement.parentElement.children[0].innerText;
            let description_value = e.target.parentElement.parentElement.children[1].innerText;

            e.target.parentElement.parentElement.children[0].innerHTML = '<input id="nom_Input_update" type="text" class="form-control" value="' + nom_value + '">';
            e.target.parentElement.parentElement.children[1].innerHTML = '<input id="description_Input_update" type="text" class="form-control" value="' + description_value + '">';

            e.target.innerText = 'ok';
        }

        if(e.target.innerText === 'ok'){

           e.target.addEventListener('click',async()=>{

               try {

                   let nom_upd = e.target.parentElement.parentElement.children[0].firstElementChild.value;
                   let description_upd = e.target.parentElement.parentElement.children[1].firstElementChild.value;

                   let form = new FormData();
                   form.append('nom',nom_upd);
                   form.append('description',description_upd);

                   const response = await fetch(url_update,{
                       "method" : 'post',
                       "body" : form
                   });

                   const data = await response.json();

                   if(data == '1'){
                       console.log(nom_upd,description_upd);
                      e.target.parentNode.parentNode.children[0].innerText = nom_upd;
                      e.target.parentNode.parentNode.children[1].innerText = description_upd;
                      e.target.innerText = 'edit';
                   }

               } catch(error){
                   console.log(error);
               }

           })

        }


    })

    deleteBtn.addEventListener('click',async e=>{

      let nom = e.target.parentElement.parentElement.children[0].innerText; // or the target id.
      let description = e.target.parentElement.parentElement.children[1].innerText;
      let form = new FormData();
      form.append('nom',nom);
      form.append('description',description);

      console.log(nom,description);
    try {
        const response = await fetch(url_delete, {
            "method": 'post',
            "body": form
        });

        const data = await response.json();

        if (data == '1') {
            e.target.parentElement.parentElement.remove();
        }
    } catch(error){
        console.log(error);
    }

    })
    //adding content to tds.
    _td_nom.innerText = option.nom;
    _td_description.innerText = option.description;
    _td_action.appendChild(editBtn);
    _td_action.appendChild(deleteBtn);

    // adding the tds to the tr.
    _tr.appendChild(_td_nom);
    _tr.appendChild(_td_description);
    _tr.appendChild(_td_action);

    //adding tr to the root element.
    _tbody.appendChild(_tr);
}

_addOption.addEventListener('click',async ()=>{

    let nom = _nomInput.value;
    let description = _descriptionInput.value;

    let form = new FormData();
    form.append('nom',nom);
    form.append('description',description);

    let option = {
        'nom' : nom,
        'description' : description
    };

    try {

        const response = await fetch(url_post,{
            "method" : 'post',
            "body" :form
        });

        const data = await response.json();

        if(data == '1'){
            addToTable(option);
            _nomInput.value='';
            _descriptionInput.value = '';
        }

    }catch(error){
        console.log(error);
    }


})

