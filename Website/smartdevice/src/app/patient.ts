import { Medecine } from './medecine';
import { History } from './history';
export class Patient{
  age: number;
  criticle: boolean;
  gender:String;
  histories: History[];
  key:String;
  medicines:Medecine;
  name: String;
  notification:{
    body:String;
    icon:String;
    title:String;
  }
  uid:String;
}
