import { Profile } from './../profile.model';
import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { ProfileService } from '../profile.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbRating, NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DataService } from '../data.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  domain: string;
  emailId: string;
  isUserLoggedIn: any;

  cards = [
    {
      title: 'Card Title 1',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 2',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 3',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 4',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 5',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 6',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 7',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 8',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 9',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 10',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 11',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
    {
      title: 'Card Title 12',
      description: 'Some quick example text to build on the card title and make up the bulk of the card content',
      buttonText: 'Button',
      img: 'https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20(34).jpg'
    },
  ];
  slides: any = [[]];

  chunk(arr, chunkSize) {
    let R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }

  @ViewChild('closebutton', { static: true }) closebutton: ElementRef;
  userServices = { emailId: '', firstName: '', lastName: '', mobileNumber: '' };
  updated: any;
  // public profiles=[];
  public cardsData: any = [];
  constructor(
    private xyz: ProfileService,
    private route: ActivatedRoute,
    private router: Router,
    private config: NgbRatingConfig,
    private dataservice: DataService
  ) {
    config.max = 5;
    config.readonly = true;

  }
  profile: Profile = new Profile();

  ngOnInit() {

    // this.emailId = localStorage.getItem('emailId');
    console.log(this.emailId);
    console.log(this.route.snapshot.paramMap.get('emailId'));
    
    this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    this.emailId = this.route.snapshot.paramMap.get('emailId');
    this.domain = this.route.snapshot.paramMap.get('domain');
    if (this.isUserLoggedIn == 'true') {
      this.isUserLoggedIn = true;
    }
    if (this.isUserLoggedIn == 'false') {
      this.isUserLoggedIn = false;
    }
    // console.log('Hello');
    console.log(this.isUserLoggedIn);
    this.getTheProfile();
    this.getEntities(this.emailId);

  }

  getEntities(emailId: string) {

    this.dataservice.getUserEntities(this.emailId).subscribe((data) => {
      // console.log("entered");
      this.cardsData = data;
      console.log(this.cardsData);
      this.slides = this.chunk(this.cardsData, 3);
    });

  }

  selectedEntity(title: string) {
    // this.DataService.getCard(title);
    console.log(23);
    console.log(title);
    this.router.navigate(['view', { title, isUserLoggedIn: this.isUserLoggedIn, domain: this.domain,
      emailId: this.emailId }]);
  }



  getTheProfile() {
    console.log(this.emailId);
    console.log(this.domain);
    this.xyz.getByEmailIdForUserService(this.emailId).subscribe(data => {
      this.userServices = data;
      console.log(this.userServices);
    });

  }




  onSubmitUpdate() {
    console.log('inside submit');
    console.log(this.profile);
    this.xyz.updateTheProfile(this.userServices).subscribe((data) => {
      // console.log('data updated..', data);
      this.updated = data;
      console.log(this.updated);
      this.closebutton.nativeElement.click();
    });

    this.getTheProfile();
    // console.log('after getting back from service', this.updated);
    this.router.navigate(['/profile', { isUserLoggedIn: this.isUserLoggedIn,
       domain: this.domain, emailId: this.emailId}]);

  }

}
