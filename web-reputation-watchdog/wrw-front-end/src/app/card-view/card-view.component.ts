import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { NgbRating, NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';
import { faThumbsDown } from '@fortawesome/free-solid-svg-icons';
import { Router, ActivatedRoute } from '@angular/router';
import { EntityDetails } from '../entity-details';

@Component({
  selector: 'app-card-view',
  templateUrl: './card-view.component.html',
  styleUrls: ['./card-view.component.css']
})
export class CardViewComponent implements OnInit {
  faThumbsUp = faThumbsUp;
  faThumbsDown = faThumbsDown;
  // tslint:disable-next-line: no-inferrable-types
  // tslint:disable-next-line: ban-types
  p: Number = 1;
  // tslint:disable-next-line: ban-types
  count: Number = 5;
  cards: any;
  card: any;
  cardd: any;
  reviews: any;
  domain: any;
  emailId: any;
  title: any;
  entityId: any;
  entityName: any;
  aspectBasedRating: any;
  overAllRating: any;
  public isUserLoggedIn: any;
  public entityDetails: EntityDetails;
  public s: string;
  aspects: any;
  constructor(
    private DataService: DataService,
    private config: NgbRatingConfig,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // this.route.params.subscribe(params => {
    //   console.log(params.entity);
    //   if (params.entity) {
    //     console.log('jldfjaeur');
    //     console.log(params.entity.title);
    //     // this.doSearch(params.view);
    //   }
    // });
    this.route.params.subscribe(params => {
      // console.log(params);
      // console.log(params.title);
      // this.getRecentEntity(params.title);
      // this.getTrendingEntity(params.title);
      this.getEntity(params.title);
      // console.log(this.card);
    });
    config.max = 5;
    config.readonly = true;
  }
  ngOnInit() {
    this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    if (this.isUserLoggedIn == 'true') {
      this.isUserLoggedIn = true;
    }
    if (this.isUserLoggedIn == 'false') {
      this.isUserLoggedIn = false;
    }
    this.emailId = this.route.snapshot.paramMap.get('emailId');
    this.domain = this.route.snapshot.paramMap.get('domain');
    this.title = this.route.snapshot.paramMap.get('title');
    console.log(this.isUserLoggedIn + ' ' + this.emailId + ' ' + this.domain + ' ' + this.title);

    // console.log('adf');
    // this.route.params.subscribe(params => console.log(params['card'].title));
    // console.log('iuytreazx');
    // this.getData();
  }

  // To Get the Particular Entity Card
  getEntity(title: string) {
    this.DataService.getCard(title).subscribe((response) => {
      console.log(`Response: ${response}`);
      if (response) {
        this.cards = response;
        console.log(this.cards);
        console.log(typeof this.cards);
        console.log(this.cards[0]);
        for (const cardd of this.cards) {
          // console.log('ranu');
          // console.log(typeof cardd.entityName);
          // console.log(typeof title);
          if (cardd.entityName == title) {
            console.log(cardd.entityName);
            this.card = cardd;
            this.entityId = this.card.entityId;
            this.entityName = this.card.entityName;
            // this.overAllRating = this.card.overAllRating;
            // this.aspectBasedRating = this.card.aspectBasedRating;
            // console.log(typeof this.entityDetails);
            // console.log(typeof this.s);
            // console.log(typeof EntityDetails);

            // this.entityDetails = {
            //   entityDetail: [{
            //     entityId: this.card.entityId,
            //     entityName: this.card.entityName
            //   }]
            // };
            // console.log(typeof this.entityDetails);

            // this.entityDetails.entityName = this.card.entityName;
            // this.entityDetails.entityId = this.card.entityId;

            // console.log(this.card.entityName);
            // console.log(this.entityDetails.entityDetail.keys);
            // console.log(this.entityDetails.entityDetail[0]);

            // console.log(Object.keys(this.entityDetails.entityDetail[0].keys));

            // console.log(this.entityDetails.entityDetail['entityName']);

            // console.log(this.entityDetails.entityName);
            // console.log('vachanu');

            // console.log(this.card);


            this.aspects = Object.keys(this.card.entityReview);

            console.log(this.aspects);
            break;
          } else {
            // console.log('dfjk');
          }
        }
      }
    }, (err) => {
      console.log(err);
    });
  }
//  Method for Analysis of a Review
  entityAnalyzer(title: string) {
    this.router.navigate(['analysis', { title, isUserLoggedIn: this.isUserLoggedIn, emailId: this.emailId, domain: this.domain }]);
  }
  // Method to Add a Review
  routeToAddReview() {
    if (this.isUserLoggedIn) {
      // console.log('LoggedIn header before route');
      // console.log(this.entityDetails.entityName + ' ' + this.entityDetails.entityId);
      this.router.navigate(['/addreview', { title: this.title, isUserLoggedIn: true, emailId: this.emailId,
         domain: this.domain, entityId: this.entityId }]);
      // console.log('LoggedIn header after route');
    } else {
      // console.log('NotLoggedIn header before route');
      this.router.navigate(['/login', { title: this.title, isUserLoggedIn: false,
         emailId: this.emailId, domain: this.domain, to: 'addReview', entityId: this.entityId }]);
      // console.log('NotLoggedIn header after route');
    }
  }
}
