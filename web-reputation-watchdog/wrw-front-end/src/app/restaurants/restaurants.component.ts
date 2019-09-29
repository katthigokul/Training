import { Component, OnInit } from '@angular/core';
import {DataService} from '../data.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styleUrls: ['./restaurants.component.css']
})
export class RestaurantsComponent implements OnInit {

  recentRestaurants: any;
  trendingRestaurants: any;
  slides: any;
  private isUserLoggedIn: any;
  private emailId: string;
  private domain = 'restaurant';
  chunk(arr, chunkSize) {
    const R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }

  constructor(private DataService: DataService,
              private router: Router,
              private route: ActivatedRoute) { }

ngOnInit() {
    this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    this.emailId = this.route.snapshot.paramMap.get('emailId');
    this.domain = this.route.snapshot.paramMap.get('domain');
    console.log(this.isUserLoggedIn);
    // this.getRecentRestaurants();
    this.getTrendingRestaurants();
  }
// getRecentRestaurants() {
//     this.DataService.getRecentRestaurants().subscribe((response) => {
//       console.log(`Response : ${response}`);
//       if (response) {
//         this.recentRestaurants = response;
//         console.log(this.recentRestaurants);
//         this.slides = this.chunk(this.recentRestaurants, 5);

//         // userForm.reset();
//       }
//     }, (err) => {
//       console.log(err);
//     });
//   }

  getTrendingRestaurants() {
    this.DataService.getTrendingRestaurants().subscribe((response) => {
      console.log(`Response : ${response}`);
      if (response) {
        this.trendingRestaurants = response;
        console.log(this.trendingRestaurants);
        this.slides = this.chunk(this.trendingRestaurants, 5);

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
