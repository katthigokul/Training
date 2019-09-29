import { Register } from './register-component.model';
import { Component, OnInit } from '@angular/core';

import {
 FormBuilder,
 FormGroup,
 Validators,
 FormControl
} from '@angular/forms';
import {
 AuthService,
 GoogleLoginProvider,
 SocialUser
} from 'angularx-social-login';
import { Router } from "@angular/router";
import { ProfileService } from "../profile.service";
import { RegisterService } from "../register.service";
import { log } from 'util';
@Component({
 selector: 'app-register-component',
 templateUrl: './register-component.component.html',
 styleUrls: ['./register-component.component.css']
})
export class RegisterComponentComponent implements OnInit {
 registerForm: FormGroup;
 submitted = false;
 user: Register = new Register();
 firstFormGroup: FormGroup;
 secondFormGroup: FormGroup;
 ThirdFormGroup: FormGroup;
 data: any;
 private isLinear=true;
 constructor(
   private registerService: RegisterService,
   private profileService: ProfileService,
   private router: Router,
   private formBuilder: FormBuilder,
   private authService: AuthService
 ) { }
 ngOnInit() {

   this.firstFormGroup = this.formBuilder.group({
    FirstName: ['', Validators.required],
    LastName: ['', Validators.required],
  });
  this.secondFormGroup = this.formBuilder.group({
    EmailId: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required,Validators.minLength(6)]],
  });
  this.ThirdFormGroup = this.formBuilder.group({
    mobile: ['', [Validators.required,Validators.minLength(10)]],
    
   });
 }
 getf() {
   return this.registerForm.controls;
 }
 onSubmit() {
   console.log(
     this.user
   );
   this.profileService.createUser(this.user).subscribe(
     response => {
       this.data = response;
       console.log('hello');
       console.log(this.data);
       this.router.navigate(['/login']);
     },
     err => {
       console.log(err);
     }
   );
 }

 checkStatus() {
   console.log(this.secondFormGroup.status, this.secondFormGroup.controls)
 }
}
