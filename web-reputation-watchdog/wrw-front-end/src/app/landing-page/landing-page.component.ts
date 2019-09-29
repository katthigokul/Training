import { Component, OnInit, Input } from '@angular/core';
import { DataService } from '../data.service';
import { NgbRating, NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { Router, ActivatedRoute } from '@angular/router';

export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {
  // @Input() public isUserLoggedIn = false;
  // @Input() public domain = 'Movie';
  // @Input() public src1: any;
  // @Input() public src2: any;
  // @Input() public src3: any;
  tiles: Tile[] = [
    { text: 'One', cols: 3, rows: 1, color: 'lightblue' },
    { text: 'Two', cols: 1, rows: 2, color: 'lightgreen' },
    { text: 'Three', cols: 1, rows: 1, color: 'lightpink' },
    { text: 'Four', cols: 2, rows: 1, color: '#DDBDF1' },
  ];
  recentEntities: any;
  trendingEntities: any;
  slides: any;
  isUserLoggedIn = false;
  domain = 'guest';
  emailId = 'guest';
  private src1: string;
  private src2: string;
  private src3: string;
  // src1: any;
  // src2: any;
  chunk(arr, chunkSize) {
    const R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }

  constructor(
    // tslint:disable-next-line: no-shadowed-variable
    private DataService: DataService,
    private config: NgbRatingConfig,
    private router: Router,
    private route: ActivatedRoute
  ) {
    // tslint:disable-next-line: only-arrow-functions
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
    config.max = 5;
    config.readonly = true;
  }

  ngOnInit() {
    console.log('Landingpage before');
    if (this.route.snapshot.paramMap.get('domain') !== null) {
      this.domain = this.route.snapshot.paramMap.get('domain');
      console.log(this.domain);
    }
    console.log(this.isUserLoggedIn);
    this.getRecentEntity();
    // this.getTrendingEntity();
    //this.getCarousel(this.domain);
  }

// ADD ENTITY for LoggedIn user and Not LoggedIn User

  routeToAddEntity() {
    if (this.isUserLoggedIn) {
      //console.log('LoggedIn header before route');
      this.router.navigate(['/addentity', { isUserLoggedIn: true, domain: this.domain, emailId: this.emailId}]);
      //console.log('LoggedIn header after route');
    } else {
      //console.log('NotLoggedIn header before route');
      this.router.navigate(['/login', { isUserLoggedIn: false, domain: this.domain, emailId: this.emailId}]);
      console.log('NotLoggedIn header after route');
    }
  }

  // To Fetch the Recent Entities from Data Service

  getRecentEntity() {
    this.DataService.getRecentEntity().subscribe((response) => {
      // console.log(`Response : ${response}`);
      if (response) {
        this.recentEntities = response;
        console.log(this.recentEntities);
        this.slides = this.chunk(this.recentEntities, 5);
        // userForm.reset();
      }
    }, (err) => {
      console.log(err);
    });
  }


  selectedEntity(title: string) {
    // this.DataService.getCard(title);
    //console.log(23);
    //console.log(title);
    this.router.navigate(['view', { title, domain: this.domain, emailId: this.emailId, isUserLoggedIn: this.isUserLoggedIn }]);
  }

}
