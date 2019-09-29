import { Component, OnInit } from '@angular/core';
import { isNgTemplate } from '@angular/compiler';
import { AddEntityService } from '../add-entity.service';
import { ActivatedRoute, Router } from '@angular/router';


// Add-Movie Component
@Component({
  selector: 'app-add-entity',
  templateUrl: './add-entity.component.html',
  styleUrls: ['./add-entity.component.css']
})
export class AddEntityComponent implements OnInit {
  private isUserLoggedIn: any;
  // imageUrl = '/assets/img/img.jpeg';
  // fileToUpload: File = null;
  selectedSubCategory = 0;
  category = 'movie';
  movieLocation: any;
  movieTitle: any;
  movieDescription: any;
  imageUrl: any;
  subCategory = [];
  domain: any;
  emailId: any;
  brands = [];


  // onSelectCategory(categoryId: number) {
  //   // this.selectedCategory = categoryId;
  //   this.selectedSubCategory = 0;
  //   this.brands = [];
  //   this.subCategory = this.getSubCategory().filter((item) => {
  //     return item.categoryId === Number(categoryId);
  //   });
  // }


  // onSelectSubCategory(subCategoryId: number) {
  //   this.selectedSubCategory = subCategoryId;
  //   this.brands = this.getbrand().filter((item) => {
  //     return item.subCategoryId === Number(subCategoryId);
  //   });
  // }

  // getCategory() {
  //   return [
  //     { id: 1, name: 'Electronics' },
  //     { id: 2, name: 'Restaurants' }
  //   ];
  // }

  getSubCategory() {
    return [
      { id: 1, name: 'Action' },
      { id: 2, name: 'Animation' },
      { id: 3, name: 'Comedy' },
      { id: 4, name: 'Drama' },
      { id: 5, name: 'Historical' },
      { id: 6, name: 'Horror' },
      { id: 7, name: 'Mystery' },
      { id: 8, name: 'Political' },
      { id: 9, name: 'Saga' },
      { id: 10, name: 'Science fiction' },
      { id: 11, name: 'Social' },
      { id: 12, name: 'Thriller' },
    ];
  }

 

  constructor(private addEntity: AddEntityService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.subCategory = this.getSubCategory();
    this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    if (this.isUserLoggedIn == 'true') {
      this.isUserLoggedIn = true;
    }
    if (this.isUserLoggedIn == 'false') {
      this.isUserLoggedIn = false;
    }
    this.domain = this.route.snapshot.paramMap.get('domain');
    this.emailId = this.route.snapshot.paramMap.get('emailId');

  }

  saveEntity(Image: any) {
    console.log(this.category, this.selectedSubCategory, this.movieTitle,
      this.movieDescription, this.imageUrl, this.emailId);
    this.addEntity.saveEntity(this.category, this.selectedSubCategory, this.movieTitle, this.movieDescription, 
        this.imageUrl, this.emailId,  this.movieLocation)
      .subscribe((response) => {
        // Image.value = null;
        // this.imageUrl =  '/assests/img/images.png';
        console.log(response);
        if (response) {
          this.router.navigate(['user-dashboard', {domain: this.domain, isUserLoggedIn: this.isUserLoggedIn, emailId: this.emailId}]);
        }
      }, (err) => {
        console.log(err);
      });
  }

}
