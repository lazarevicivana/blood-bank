export class UserToken {
  jwt:string ='';
  id:string='';
  email:string='';
  role:string='';
  username:string='';

  constructor(jwt: string, id: string, email: string, role: string, username: string) {
    this.jwt = jwt;
    this.id = id;
    this.email = email;
    this.role = role;
    this.username = username;
  }
}
