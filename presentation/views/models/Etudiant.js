

export default class Etudiant {

    cne;
    prenom;
    nom;
    idOption;

    constructor(cne,prenom,nom) {
        this.cne = cne;
        this.prenom = prenom;
        this.nom = nom;
    }

    setIdOption(option){
        this.idOption = option;
    }

}