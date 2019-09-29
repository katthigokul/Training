import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService, GoogleLoginProvider, SocialUser } from 'angularx-social-login';
import { UserService } from '../user.service';

import {  TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public invalid = false;
  modalRef: BsModalRef;
  private user: SocialUser;
  private email: string;
  private loggedIn: any;
  private loginStatus = true;
  domain: string;
  to: string;
  title: string;
  entityId: string;
  categorySelected = false;
  loginForm: FormGroup;
  signUpForm: FormGroup;
  submitted1 = false;
  submitted2 = false;

  constructor(
    private modalService: BsModalService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      emailId: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
    this.email = this.route.snapshot.paramMap.get('emailId');
    this.domain = this.route.snapshot.paramMap.get('domain');
    this.loggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    this.to = this.route.snapshot.paramMap.get('to');
    this.title = this.route.snapshot.paramMap.get('title');
    this.entityId = this.route.snapshot.paramMap.get('entityId');

    if (this.domain == 'movie' || this.domain == 'restaurant') {
      this.categorySelected = true;
    } else {
      this.categorySelected = false;
    }
    console.log(this.to);
  }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(user => {
      this.user = user;
      this.loggedIn = user != null;
      if (this.loggedIn) {
        this.router.navigate(['/restaurant', { isUserLoggedIn: true, domain: this.domain, emailId: this.email,
         }]);
      }
    });
  }

  changeLoginStatus() {
    this.router.navigate(['/register']);
  }

  signOut(): void {
    this.authService.signOut();
  }
  login(template: TemplateRef<any>) {
    this.submitted1 = true;
    if (this.loginForm.valid) {
      this.userService.authenticate(this.loginForm.value).subscribe(response => {
        console.log(response);
        if (response) {
          this.email = this.loginForm.value.emailId;
          if (this.categorySelected) {
            if (this.domain == 'movie' || this.domain == 'Movie') {
              if (this.to == 'addReview') {
                this.router.navigate(['/addreview', { title: this.title, isUserLoggedIn: true, emailId: this.email,
                  domain: this.domain, entityId: this.entityId }]);
              } else {
                this.router.navigate(['/user-dashboard', { isUserLoggedIn: true, domain: this.domain, emailId: this.email }]);
              }
            }
            if (this.domain == 'restaurant' || this.domain == 'Restaurant') {
              if ( this.to == 'addReview') {
                this.router.navigate(['/addreview', { title: this.title, isUserLoggedIn: true, emailId: this.email,
                  domain: this.domain, entityId: this.entityId }]);
              } else {
                this.router.navigate(['/restaurant', { isUserLoggedIn: true, domain: this.domain, emailId: this.email }]);
              }
            }
          } else {
            this.modalRef = this.modalService.show(template);
          }
          // this.router.navigate(['/user-dashboard', { isUserLoggedIn: true }]);
        }
      });
    } else {
      this.invalid = true;
      console.log(this.loginForm);
    }
  }
  onModal(domain: string) {
    this.domain = domain;
    console.log(domain);
    console.log(this.to);
    this.modalRef.hide();
    if (this.to == 'addReview') {
      this.router.navigate(['/addreview', { title: this.title, isUserLoggedIn: true, emailId: this.email,
        domain: this.domain, entityId: this.entityId }]);
    } else {
      if (domain == 'movie') {
        this.router.navigate(['/user-dashboard', {domain: this.domain, isUserLoggedIn: true, emailId: this.email}]);
      }
      if (domain == 'restaurant') {
        this.router.navigate(['/restaurant', { domain: this.domain, isUserLoggedIn: true, emailId: this.email}]);
      }
    }
  }
  // onModall() {
  //   this.modalRef.hide();
  //   this.router.navigate(['/restaurant', { isUserLoggedIn: true, emailId: this.email}]);
  //
  // }

}

