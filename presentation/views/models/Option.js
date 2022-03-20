

export default class Option {

   nom;
   description;

   constructor(nom,description) {
       this.nom = nom;
       this.description = description;
   }

   getNom(){
       return this.nom;
   }

   getDescription(){
       return this.description;
   }

}