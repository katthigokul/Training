import { Component, OnInit } from '@angular/core';
import { SearchService } from '../search.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {
  entityName: string;
  emailId: string;
  domain: string;
  card: any;
  isResponse = false;
  public su = 'summa';
  isUserLoggedIn: any;

  constructor(
    private searchService: SearchService,
    private config: NgbRatingConfig,
    private route: ActivatedRoute,
    private router: Router,
    private dataService: DataService
  ) {
    // tslint:disable-next-line: only-arrow-functions
    this.router.routeReuseStrategy.shouldReuseRoute = function() {
      return false;
    };
    this.route.params.subscribe(params => {
      console.log(params);
      console.log(params.entityName);
      console.log(params.isUserLoggedIn);
      this.entityName = params.entityName;
      this.emailId = params.emailId;
      this.domain = params.domain;
      this.isUserLoggedIn = params.isUserLoggedIn;
    });
    config.max = 5;
    config.readonly = true;
  }

  ngOnInit() {
    // this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    if ( this.isUserLoggedIn == 'true') {
      this.isUserLoggedIn = true;
    }
    if ( this.isUserLoggedIn == 'false') {
      this.isUserLoggedIn = false;
    }
    console.log(this.isUserLoggedIn);
    this.cal(this.entityName, this.emailId, this.domain);
  }

  cal(title: string, emailId: string, domain: string) {
    console.log(34);
    this.dataService.getSearchDetails(title, emailId, domain).subscribe(val => {
      console.log(val);
      for (const cardd of val) {
        this.isResponse = true;
        this.card = cardd;
        console.log(val);
        console.log(this.card);
        console.log(cardd.entityName);
      }
    }, (err) => {
      console.log(this.isUserLoggedIn);
      this.isUserLoggedIn = this.isUserLoggedIn;
      console.log(err);
    });
  }

  selectedEntity(title: string) {
    // this.DataService.getCard(title);
    console.log(23);
    console.log(title);
    console.log(this.isUserLoggedIn + ' ' + this.domain + ' ' + title);
    this.router.navigate(['view', { title , isUserLoggedIn: this.isUserLoggedIn, domain: this.domain, emailId: this.emailId}]);
  }
}
