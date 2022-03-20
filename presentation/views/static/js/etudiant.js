import Etudiant from "../../models/Etudiant.js";

//input fields.
const cneInput = document.getElementById('cneInput');
const prenomInput = document.getElementById('prenomInput');
const nomInput = document.getElementById('nomInput');
const optionSelect = document.getElementById('optionSelect');
const tbody = document.getElementById('tbody_etudiant');
const addBtn = document.getElementById('addEtudiant');

//rest urls.
const url_get = 'http://localhost:8080/todo_ameliored/listEtudiants';
const url_delete = 'http://localhost:8080/todo_ameliored/deleteEtudiant';
const url_post = 'http://localhost:8080/todo_ameliored/addEtudiant';
const url_update = 'http://localhost:8080/todo_ameliored/updateEtudiant';

//url dedicated to get the option.
const url_get_options = 'http://localhost:8080/todo_ameliored/listOptions';

window.addEventListener('load',async ()=>{

    try {

        const response = await fetch(url_get_options);

        const data = await response.json();

        data.forEach(option=>{

            let option_ = document.createElement('option');
            option_.innerText = option.nom;
            optionSelect.appendChild(option_);

        });


    } catch(error){
        console.log(error);
    }

});

const addTotable = (etudiant)=>{

    let tr = document.createElement('tr');

    //tds
    let td_cne = document.createElement('td');
    let td_prenom = document.createElement('td');
    let td_nom = document.createElement('td');
    let td_option = document.createElement('td');
    let td_action = document.createElement('td');

    //filling the tds.
    td_cne.innerText = etudiant.cne;
    td_prenom.innerText = etudiant.prenom;
    td_nom.innerText = etudiant.nom;
    td_option.innerText = etudiant.idOption;

    //creating the remove btn.
    td_action.className = 'd-flex justify-content-between aling-items-center';

    let removeBtn = document.createElement('button');
    removeBtn.innerText = 'supprimer';
    removeBtn.className = 'btn btn-primary';

    removeBtn.addEventListener('click',async e=>{

        let cne = e.target.parentElement.parentElement.children[0].innerText;
        let prenom = e.target.parentElement.parentElement.children[1].innerText;
        let nom = e.target.parentElement.parentElement.children[2].innerText;
        let idOption = e.target.parentElement.parentElement.children[3].innerText;

        let form = new FormData();
        form.append("cne",cne);
        form.append("prenom",prenom);
        form.append("nom",nom);
        form.append("idOption",idOption);

        try {

            const response = await fetch(url_delete,{
                "method" : 'post',
                "body" : form
            });

            const data = await response.json();

            if(data == '1'){
                e.target.parentElement.parentElement.remove();
            }



        } catch(error){
            console.log(error);
        }


    })

    let updateBtn = document.createElement('button');
    updateBtn.innerText = 'modifier';
    updateBtn.className = 'btn btn-outline-primary';

    updateBtn.addEventListener('click',e =>{

        if(e.target.innerText === 'modifier'){

            let cne =  e.target.parentElement.parentElement.children[0].innerText;
            let prenom =  e.target.parentElement.parentElement.children[1].innerText;
            let nom =  e.target.parentElement.parentElement.children[2].innerText;
            let idOption = e.target.parentElement.parentElement.children[3].innerText;

            e.target.parentElement.parentElement.children[0].innerHTML = '<input id="cne_Input_upd" value="'+cne+'" type="text" class="form-control" />'
            e.target.parentElement.parentElement.children[1].innerHTML = '<input id="prenom_Input_upd" value="'+prenom+'" type="text" class="form-control" />'
            e.target.parentElement.parentElement.children[2].innerHTML = '<input id="nom_Input_upd" value="'+nom+'" type="text" class="form-control" />'
            e.target.parentElement.parentElement.children[3].innerHTML = '<input id="option_Input_upd" value="'+idOption+'" type="text" class="form-control" />'
            e.target.innerText = 'ok';
        }

        if(e.target.innerText === 'ok'){

            e.target.addEventListener('click',async ex=>{

                let cne_upd = e.target.parentElement.parentElement.children[0].firstElementChild.value;
                let prenom_upd = e.target.parentElement.parentElement.children[1].firstElementChild.value;
                let nom_upd = e.target.parentElement.parentElement.children[2].firstElementChild.value;
                let idOption_upd = e.target.parentElement.parentElement.children[3].firstElementChild.value;

                let form = new FormData();
                form.append('cne',cne_upd);
                form.append('prenom',prenom_upd);
                form.append('nom',nom_upd);
                form.append('idOption',idOption_upd);

                try {

                    const response = await fetch(url_update,{
                        "method" : 'post',
                        "body" : form
                    });

                    const data = await response.json();

                    if(data == '1'){
                        e.target.parentElement.parentElement.children[0].innerText = cne_upd;
                        e.target.parentElement.parentElement.children[1].innerText = prenom_upd;
                        e.target.parentElement.parentElement.children[2].innerText = nom_upd;
                        e.target.parentElement.parentElement.children[3].innerText = idOption_upd;
                        e.target.innerText = 'modifier';
                    }

                } catch(error){
                    console.log(error);
                }

            })

        }


    })

    //finalize
    td_action.appendChild(updateBtn);
    td_action.appendChild(removeBtn);

    tr.appendChild(td_cne);
    tr.appendChild(td_prenom);
    tr.appendChild(td_nom);
    tr.appendChild(td_option);
    tr.appendChild(td_action);

    tbody.appendChild(tr);

}

window.addEventListener('load',async()=>{
    try {
        const response = await fetch(url_get);

        const data = await response.json();

        data.forEach(etudiant=>{

            let cne = etudiant.cne;
            let prenom = etudiant.prenom;
            let nom = etudiant.nom;
            let idOption = etudiant.idOption.nom;
            let etudiant_ = new Etudiant(cne,prenom,nom);
            etudiant_.setIdOption(idOption);
            addTotable(etudiant_);

        })

    }catch(error) {
        console.log(error);
    }

})

addBtn.addEventListener('click',async ()=>{

try{

    let cne = cneInput.value;
    let prenom = prenomInput.value;
    let nom = nomInput.value;
    let idOption = optionSelect[optionSelect.selectedIndex].text;

    console.log(idOption);

    let form = new FormData();
    form.append("cne",cne);
    form.append("prenom",prenom);
    form.append("nom",nom);
    form.append("idOption",idOption);

    let etudiant = new Etudiant(cne,prenom,nom);
    etudiant.setIdOption(idOption);

    const response = await fetch(url_post,{
        "method" : 'post',
        "body" : form
    });

    const data = await response.json();

    console.log(data);

    if(data == '1'){
        addTotable(etudiant);
    }


}   catch(error){
    console.log(error);
}


})


















