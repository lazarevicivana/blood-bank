import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient,HttpHeaders} from '@angular/common/http'
import {Customer} from "../model/Customer";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private aplUrl = 'http://localhost:8080/api/v1/customer'

  constructor(private http:HttpClient) { }

  getCustomerById(): Observable<Customer>{
    const url = `${this.aplUrl}/34713840-ddf3-49b2-9cae-47334cb6b31b`

    // @ts-ignore
    return this.http.get(url)

  }
}
