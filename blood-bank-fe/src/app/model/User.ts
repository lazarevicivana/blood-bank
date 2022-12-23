export class User{
  id:string ='';
  role:string='';
  firstLogIn?:boolean=false

  constructor(id: string, role: string, firstLogIn: boolean) {
    this.id = id;
    this.role = role;
    this.firstLogIn = firstLogIn
  }
}
