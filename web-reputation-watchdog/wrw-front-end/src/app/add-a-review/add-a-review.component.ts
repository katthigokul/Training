import { Component, OnInit } from '@angular/core';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup } from '@angular/forms';
import { AddReviewService } from '../add-review.service';
import { ActivatedRoute, Router } from '@angular/router';
import { EntityDetails } from '../entity-details';
import { DataService } from '../data.service';


@Component({
  selector: 'app-add-a-review',
  templateUrl: './add-a-review.component.html'
  ,
  styleUrls: ['./add-a-review.component.css'],
  providers: [NgbRatingConfig] // add NgbRatingConfig to the component providers
})
export class AddAReviewComponent implements OnInit {
  public isUserLoggedIn: any;
  domain: any;
  emailId: any;
  title: any;
  entityDetails: EntityDetails;
  entityDetail: any;
  entityId: string;
  card: any;
  aspects: any;
  overAllRating: any;
  aspecBasedRating: any;
  reviewText: any = '';
  reviewTitle: any = '';

  constructor(

    private DataService: DataService,
    config: NgbRatingConfig,
    private addReviewService: AddReviewService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // customize default values of ratings used by this component tree
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
    this.domain = this.route.snapshot.paramMap.get('domain');
    this.emailId = this.route.snapshot.paramMap.get('emailId');
    this.title = this.route.snapshot.paramMap.get('title');
    this.entityId = this.route.snapshot.paramMap.get('entityId');
    this.getEntity(this.title);
    // console.log(JSON.stringify(this.route.snapshot.paramMap.get('entityDetails')).toString);
    // this.entityDetail = JSON.parse(JSON.stringify(this.route.snapshot.paramMap.get('entityDetails')));

    // this.entityDetails = JSON.parse(this.route.snapshot.paramMap.get('entityDetails'));
    // console.log(this.entityDetail);
    // console.log(typeof this.entityDetail);
    // console.log(this.entityDetail[1]);

    // console.log(this.entityDetail['entityName']);
    // console.log(this.entityDetail);


    // tslint:disable-next-line: no-eval
    // console.log(eval(this.entityDetail));

    // console.log(this.entityDetail.entityId);
    // console.log(typeof this.entityDetails);




    // console.log(this.entityDetails.entityId + ' ' + this.entityDetails.entityName);

  }
  getEntity(title: string) {
    this.DataService.getCard(title).subscribe((response) => {
      console.log(`Response: ${response}`);
      if (response) {
        for (const cardd of response) {
          if (cardd.entityName == title) {
            console.log(cardd.entityName);
            this.card = cardd;
            this.entityId = this.card.entityId;
            this.overAllRating = this.card.overAllRating;
            this.aspecBasedRating = this.card.entityReview;
            if (this.card.entityReview != null && (typeof this.card.entityReview) != 'undefined') {
              this.aspects = Object.keys(this.card.entityReview);
              break;
            } else {
              console.log(this.card.entityReview);
            }
          } else {
            console.log('dfjk');
          }
        }
      }
    }, (err) => {
      console.log(err);
    });
  }
  goBack() {
    this.router.navigate(['view', {
      title: this.title, domain: this.domain,
      entityId: this.entityId, isUserLoggedIn: this.isUserLoggedIn, emailId: this.emailId
    }]);
  }




  saveReview() {
    console.log(this.reviewText + ' ' + this.reviewTitle + ' ' + this.card.entityId + ' '
      + this.card.entityName + ' ' + this.card.overAllRating);
    console.log(this.reviewText);
    console.log(this.aspecBasedRating);
    this.addReviewService.saveReview(this.title, this.entityId, this.emailId, this.reviewTitle,
      this.reviewText, this.aspecBasedRating).subscribe((response) => {
        console.log(response);

        this.router.navigate(['/view', {
          title: this.title, domain: this.domain,
          entityId: this.entityId, isUserLoggedIn: this.isUserLoggedIn, emailId: this.emailId
        }]);

      }, (err) => {
        this.router.navigate(['/view', {
          title: this.title, domain: this.domain,
          entityId: this.entityId, isUserLoggedIn: this.isUserLoggedIn, emailId: this.emailId
        }]);
        console.log(err);
      });
  }
}
