import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent, routing, MenuComponent, CustomerListComponent, CustomerDetailsComponent, AboutComponent }  from './app.component';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { FormsModule }   from '@angular/forms';
import { HttpModule } from '@angular/http';



@NgModule({
  imports:      [ BrowserModule, routing, FormsModule, HttpModule ],
  declarations: [ MenuComponent, CustomerListComponent, CustomerDetailsComponent, AppComponent, AboutComponent ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy }
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule {}


