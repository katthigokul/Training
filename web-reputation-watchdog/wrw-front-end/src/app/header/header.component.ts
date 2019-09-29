import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, Input } from '@angular/core';
import { LandingPageComponent } from '../landing-page/landing-page.component';
import { ProfileComponent } from '../profile/profile.component';
import { NotificationService } from '../notification.service';
import { SearchService } from '../search.service';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [SearchService]})
export class HeaderComponent implements OnInit {
  // private router: Router;
  @Input() public isUserLoggedIn: any;
  @Input() private emailId: string;
  @Input() public domain: string;
  public toggleButton: boolean;
  // public emailId = '';
  public notifications: string[] = [];
  private isDisabled = true;
  private searchResults: string[] = [];
  private entityCategory = '';
  notificationCount = 0;
  isU: boolean;


  constructor(
    private router: Router,
    private notificationService: NotificationService,
    private searchService: SearchService,
    private route: ActivatedRoute

  ) {
    // tslint:disable-next-line: only-arrow-functions
    this.router.routeReuseStrategy.shouldReuseRoute = function() {
      return false;
    };

    this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    console.log(this.isUserLoggedIn , 'In THE CONSTRUCTOR', this.route.snapshot.paramMap.get('isUserLoggedIn'));
  }


  ngOnInit() {
    console.log(`Header ngOnInit: ${this.isUserLoggedIn}`);
    console.log(`Domain ${this.domain}`);
    console.log(this.route.snapshot.paramMap.get('isUserLoggedIn'));

    if (this.route.snapshot.paramMap.get('isUserLoggedIn') != null) {
      // this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
      console.log(this.isUserLoggedIn);
    }
    if (this.route.snapshot.paramMap.get('domain') != null) {
      this.domain = this.route.snapshot.paramMap.get('domain');
    }
    if (this.route.snapshot.paramMap.get('emailId') != null) {
      this.emailId = this.route.snapshot.paramMap.get('emailId');
    }
    console.log(this.entityCategory);
    console.log(this.entityCategory);
    this.notificationService.connect(this.emailId);
    this.notificationService.getMessage().subscribe(notification => {
      this.notifications.push(notification);
      this.notificationCount = this.notifications.length;
      console.log(this.notifications);
      this.isDisabled = false;

      console.log(typeof this.notifications);
      console.log(this.notifications.length);
      if (this.notifications.length > 0) {
        console.log(this.notifications[0]);
      }
    });


    if (this.domain == 'movie' || this.domain == 'Movie') {
      this.toggleButton = false;
    } else {
      this.toggleButton = true;
    }
    if (this.isUserLoggedIn == 'true') {
      this.isUserLoggedIn = true;
      console.log(typeof this.isUserLoggedIn);
    }
    if (this.isUserLoggedIn == 'false') {
      this.isUserLoggedIn = false;
      console.log(typeof this.isUserLoggedIn);
    }
    console.log(`Email:  ${this.emailId}`);
    console.log(`Domain: ${this.domain}`);
    console.log(typeof this.isUserLoggedIn);
  }

  onClick() {
    this.router.navigate(['/login', {isUserLoggedIn: this.isUserLoggedIn, domain: this.domain, emailId: this.emailId}]);
    // this.isUserLoggedIn = true;
  }

  profile() {
    console.log(456);
    console.log(this.emailId);
    this.router.navigate(['/profile', { isUserLoggedIn: this.isUserLoggedIn, domain: this.domain, emailId: this.emailId }]);
  }

  logout() {
    this.router.navigate(['/landing', { isUserLoggedIn: false, domain: 'guest', emailId: 'guest'}]);
  }

  routeTo() {
    if (this.domain == 'movie') {
      this.router.navigate(['/user-dashboard', {isUserLoggedIn: this.isUserLoggedIn, domain: this.domain,
        emailId: this.emailId }]);
    }
    if (this.domain == 'restaurant') {
      this.router.navigate(['/restaurant',  {isUserLoggedIn: this.isUserLoggedIn, domain: this.domain,
        emailId: this.emailId }]);
    }
    if (this.domain == 'guest') {
      this.router.navigate(['/landing',  {isUserLoggedIn: this.isUserLoggedIn, domain: this.domain,
        emailId: this.emailId }]);
    }
  }

  search(entityName: any) {
    console.log(entityName);
    console.log(this.emailId);
    this.searchService
      .search(entityName, this.domain, this.emailId)
      .subscribe(results => {
        this.searchResults = results;
        console.log(this.searchResults);
      });
    if (this.isUserLoggedIn) {
      console.log(this.emailId + ' ' + this.domain);
      console.log(this.isUserLoggedIn);
      console.log(entityName);
      this.router.navigate(['/search-result', { entityName, emailId: this.emailId,
         domain: this.domain, isUserLoggedIn: this.isUserLoggedIn }]);
    } else {
      console.log(this.emailId + ' ' + this.domain);
      console.log(this.isUserLoggedIn);
      console.log(entityName);
      this.router.navigate(['/search-result', { entityName, emailId: 'guest',
       domain: 'guest', isUserLoggedIn: this.isUserLoggedIn }]);
    }
  }

  routeToDomain(domainName: string) {
    console.log(domainName);
    this.domain = domainName;

    if (this.isUserLoggedIn) {
      if (domainName == 'Movie' || domainName == 'movie') {
        console.log('LoggedIn header before route');
        this.router.navigate(['/user-dashboard', { isUserLoggedIn: true, domain: this.domain, emailId: this.emailId }]);
      } else {
        this.router.navigate(['/restaurant', { isUserLoggedIn: true, domain: this.domain, emailId: this.emailId }]);

      }
      console.log('LoggedIn header after route');
    } else {
      console.log('NotLoggedIn header before route');
      console.log(domainName);
      this.router.navigate(['/login', { isUserLoggedIn: false, domain: this.domain, emailId: this.emailId }]);
      console.log('NotLoggedIn header after route');
    }
  }
  routeToAddEntity(category: string) {
    if (this.isUserLoggedIn) {
      console.log('LoggedIn header before route');
      if (category == 'Movie' || category == 'movie') {
        this.router.navigate(['/addentity', { isUserLoggedIn: true, domain: this.domain, emailId: this.emailId }]);
      } else {
        this.router.navigate(['/addRestaurant', { isUserLoggedIn: true, domain: this.domain, emailId: this.emailId }]);
      }
      console.log('LoggedIn header after route');
    } else {
      console.log('NotLoggedIn header before route');
      this.router.navigate(['/login', { isUserLoggedIn: false, domain: this.domain, emailId: this.emailId }]);
      console.log('NotLoggedIn header after route');
    }
  }
}
