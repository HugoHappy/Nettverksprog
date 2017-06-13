import { Customer } from './customer';
import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class CustomerService {
  static instance: CustomerService = null;
  lastId: number = 0;
  customers: Customer[];

  private customersUrl = "/customers";
  private customerUrl = "/customer/";
  
  constructor(private http: Http) { }
  
  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }

  private handleError (error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

  getCustomers(): Observable<Customer[]> {
    return this.http.get(this.customersUrl).map(this.extractData).catch(this.handleError);
  }
  
  getCustomer(customerId: number): Observable<Customer> {
    return this.http.get(this.customerUrl + customerId).map(this.extractData).catch(this.handleError);
  }
  
  addCustomer(name: string, city: string): Observable<Customer> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    const body=JSON.stringify({name: name, city: city});

    return this.http.post(this.customersUrl, body, options).map(this.extractData).catch(this.handleError);

  }

  deleteCustomer(id: number): Observable<Customer[]>{
    return this.http.delete(this.customerUrl + id).map(this.extractData).catch(this.handleError);
  };

  changeCustomer(id: number, name: string, city: string): Observable<Customer>{
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let body=JSON.stringify({name: name, city: city});
    console.log(body)
    return this.http.put(this.customerUrl + id, body).map(this.extractData).catch(this.handleError);

  };

}
