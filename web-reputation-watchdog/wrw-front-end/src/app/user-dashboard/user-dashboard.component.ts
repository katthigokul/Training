import { Component, OnInit, Input } from '@angular/core';
import { DataService } from '../data.service';
import { NgbRating, NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { CanActivate,
         ActivatedRouteSnapshot,
         RouterStateSnapshot } from '@angular/router';


export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  tiles: Tile[] = [
    {text: 'One', cols: 3, rows: 1, color: 'lightblue'},
    {text: 'Two', cols: 1, rows: 2, color: 'lightgreen'},
    {text: 'Three', cols: 1, rows: 1, color: 'lightpink'},
    {text: 'Four', cols: 2, rows: 1, color: '#DDBDF1'},
  ];
  recentMovies: any;
  trendingMovies: any;
  cards: any;
  slides: any;
  private isUserLoggedIn: any;
  private domain: string;
  private src1: string;
  private src2: string;
  private src3: string;
  private emailId: string;
  chunk(arr, chunkSize) {
    const R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }
  constructor(
    private DataService: DataService,
    private config: NgbRatingConfig,
    private router: Router,
    private route: ActivatedRoute) {
      this.router.routeReuseStrategy.shouldReuseRoute = function() {
        return false;
      };
      config.max = 5;
      config.readonly = true;
  }

  

  ngOnInit() {
    console.log('UserDashboard before');
    if (this.route.snapshot.paramMap.get('domain') !== null) {
      this.domain = this.route.snapshot.paramMap.get('domain');
      console.log(this.domain);
    }
    this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    this.emailId = this.route.snapshot.paramMap.get('emailId');
    console.log(this.isUserLoggedIn);
    // this.getRecentMovies();
    this.getTrendingMovies();
   // this.getCarousel(this.domain);
  }


  // getRecentMovies() {
  //   this.DataService.getRecentMovies().subscribe((response) => {
  //     console.log(`Response : ${response}`);
  //     if (response) {
  //       this.recentMovies = response;
  //       console.log(this.recentMovies);
  //       this.slides = this.chunk(this.getRecentMovies, 5);

  //       // userForm.reset();
  //     }
  //   }, (err) => {
  //     console.log(err);
  //   });
  // }

  getTrendingMovies() {
    this.DataService.getTrendingMovies().subscribe((response) => {
      console.log(`Response : ${response}`);
      if (response) {
        this.trendingMovies = response;
        // console.log(this.trendingMovies);
        this.slides = this.chunk(this.trendingMovies, 5);

        // userForm.reset();
      }
    }, (err) => {
      console.log(err);
    });
  }

  // getCarousel(domain: string) {
  //   this.DataService.getCarousel(domain).subscribe((response) => {
  //     console.log(`Response : ${response}`);
  //     console.log(response);
  //     if (response) {
  //       this.src1 = response[0].src1;
  //       console.log(this.src1);
  //       this.src2 = response[1].src2;
  //       console.log(this.src2);
  //       this.src3 = response[2].src3;
  //       console.log(this.src3);
  //     }
  //   });
  // }

  // selectedEntity(title: string) {
  //   this.router.navigate(['view', {title: title, isUserLoggedIn: true} ]);
  //   // this.router.navigate(['view', {title} ]);
  // }

  selectedEntity(title: string) {
    this.router.navigate(['view', { title, domain: this.domain, emailId: this.emailId, isUserLoggedIn: this.isUserLoggedIn }]);
  }
}
