import { Component, OnInit, Input } from '@angular/core';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { Router, ActivatedRoute } from '@angular/router';
import {DataService} from '../data.service';
@Component({
  selector: 'app-movierecommend',
  templateUrl: './movierecommend.component.html',
  styleUrls: ['./movierecommend.component.css']
})
export class MovierecommendComponent implements OnInit {
  @Input() public isUserLoggedIn = false;
  @Input() public emailId: any;
  @Input() public domain: any;

  emailID: any;
  cards: any;
  slides: any;
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
    private route: ActivatedRoute
  ) {
    config.max = 5;
    config.readonly = true;
  }

  ngOnInit() {
    this.emailID =  this.emailId;
    this.getRecommendedData();
  }
  getRecommendedData() {
    console.log('I am coming inside');

    this.DataService.getRecommendedMovies(this.emailID).subscribe((response) => {
      console.log('I am at your Door');

      console.log(`Response : ${response}`);
      if (response) {
        console.log();

        this.cards = response;
        console.log(this.cards);
        this.slides = this.chunk(this.cards, 4);
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
