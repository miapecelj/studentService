
import { City } from "./city.model";
import { professorSubject } from "./professorSubject.model";
import { Title } from "./title.model";

export interface Professor{
  id:number;
  firstname:string;
  lastname:string;
  email?:string;
  address?:string;
  city?:City;
  phone?:string;
  reelectionDate: Date;
  title: Title;
  subjects:professorSubject[];
}
