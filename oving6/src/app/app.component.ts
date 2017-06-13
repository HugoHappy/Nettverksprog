import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule, Params } from '@angular/router';
import { CustomerService } from'./customer.service';
import { Customer } from './customer';
import { Observable } from 'rxjs/Observable';


@Component({
  selector: 'app-menu',
  template: '<div>Menu: <a href="/#/">Customers</a> <a href="/#/about/">About</a></div>'
})
export class MenuComponent {}

@Component({
  template: `<div>status: {{status}} </div>
             <ul>
               <li *ngFor="let customer of customers">
                 <a href="#/customer/{{customer.id}}">{{customer.name}}</a>
               </li>
             </ul>
             <form (ngSubmit)="$event.preventDefault(); onNewCustomer();" #newCustomerForm="ngForm">
               <input type="text" id="name" required name="name" [(ngModel)]="newCustomerName" placeholder="Name">
               <input type="text" id="city" required name="city" [(ngModel)]="newCustomerCity" placeholder="City">
               <button type="submit" [disabled]="!newCustomerForm.form.valid">New Customer</button>
             </form>
             <form (ngSubmit)="$event.preventDefault(); onDeleteCustomer();" #deleteCustomerForm="ngForm">
               <input type="number" id="delete" required name="id" [(ngModel)]="deleteCustomerId" placeholder="ID">
               <button type="submit" [disabled]="!deleteCustomerForm.form.valid">Delete</button>
             </form>`,
  styles: ['div {color: red;}']
})
export class CustomerListComponent implements OnInit {
  status: string;
  customers: Customer[];

  newCustomerName: string;
  newCustomerCity: string;
  deleteCustomerId: number;
  
  constructor(private CustomerService: CustomerService) { }

  ngOnInit(){
    this.getCustomers();
  }

  getCustomers(){
    this.CustomerService.getCustomers().subscribe(
      customers => this.customers = customers,
      status => this.status = <any>status);
  }
  
  onNewCustomer() {
    this.CustomerService.addCustomer(this.newCustomerName, this.newCustomerCity).subscribe(
      customer => this.customers.push(customer),
      status => this.status = <any>status);

  }

  onDeleteCustomer() {
    this.CustomerService.deleteCustomer(this.deleteCustomerId).subscribe(
      customers => this.customers = customers,
      status => this.status = <any>status);
    this.getCustomers();
  }

}

@Component({
  template: `<div>status: {{status}}</div>
             <ul>
               <li>name: {{customer?.name}}</li>
               <li>city: {{customer?.city}}</li>
             </ul>
             <form (ngSubmit)="onUpdateCustomer();" #changeCustomerForm="ngForm">
               <input type="text" id="name" required name="name" [(ngModel)]="newCustomerName" placeholder="New Name">
               <input type="text" id="city" required name="city" [(ngModel)]="newCustomerCity" placeholder="New City">
               <button type="submit" [disabled]="!changeCustomerForm.form.valid">Change</button>
             </form>` 
})
export class CustomerDetailsComponent {
  status: string;
  customer: Customer;
  newCustomerCity: string;
  newCustomerName: string;
  customerId: number;

  constructor(route: ActivatedRoute, private CustomerService: CustomerService) {
    const params: Params = route.params;
    this.customerId = params.value.id;

    this.CustomerService.getCustomer(this.customerId).subscribe(
      customer => this.customer = customer,
      status => this.status = <any>status);
  }

  onUpdateCustomer(){
    this.CustomerService.changeCustomer(this.customerId, this.newCustomerName, this.newCustomerCity).subscribe(
      customer => this.customer = customer,
      status => this.status = <any>status); 
      this.newCustomerCity = "";
      this.newCustomerName = "";
  }
}

@Component({
  template: `<div>Hei</div>`
})
export class AboutComponent {}

@Component({
  selector: 'my-app',
  template: `<app-menu></app-menu>
             <router-outlet></router-outlet>`,
  providers: [CustomerService]
})
export class AppComponent {}

export const routing = RouterModule.forRoot([
  { path: '', component: CustomerListComponent },
  { path: 'customer/:id', component: CustomerDetailsComponent },
  { path: 'about', component: AboutComponent}
]);
