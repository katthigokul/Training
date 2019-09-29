import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { MDBBootstrapModule} from 'angular-bootstrap-md';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MyMaterialModule } from "./material.module";
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import {
  MatCheckboxModule,
  MatDatepickerModule,
  MatInputModule,
  MatNativeDateModule,
  MatStepperModule
} from "@angular/material";
import { CustomMaterialModule } from "./core/material.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HeaderComponent } from "./header/header.component";
import { LoginComponent } from "./login/login.component";
import { UserComponent } from "./user/user.component";
import { AddAReviewComponent } from "./add-a-review/add-a-review.component";
import { AddEntityComponent } from "./add-entity/add-entity.component";
import { LandingPageComponent } from './landing-page/landing-page.component';
import { MatGridListModule } from "@angular/material";
import { FooterComponent } from "./footer/footer.component";
import { MatIconModule } from "@angular/material/icon";
import {NgPipesModule} from 'ngx-pipes';
import {
  AuthServiceConfig,
  GoogleLoginProvider,
  SocialLoginModule
} from "angularx-social-login";
import { ProfileComponent } from "./profile/profile.component";
import { RegisterComponentComponent } from "./register-component/register-component.component";
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { CardViewComponent } from './card-view/card-view.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RecommendationCardComponentComponent } from './recommendation-card-component/recommendation-card-component.component';
import { RegisterService } from './register.service';
import { ModalModule } from 'ngx-bootstrap/modal';
import { EntityAnalyticsComponent } from './entity-analytics/entity-analytics.component';
import { TruncateModule } from 'ng2-truncate';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import { MovierecommendComponent } from './movierecommend/movierecommend.component';
import { SearchResultComponent } from './search-result/search-result.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import {NgxPaginationModule} from 'ngx-pagination';

const config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider(
      "1081501418390-bl2nvde2qposrhgnrnegfknkk2cicf7g.apps.googleusercontent.com"
    )
  }
]);

export function provideConfig() {
  return config;
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    UserComponent,
    AddAReviewComponent,
    AddEntityComponent,
    LandingPageComponent,
    FooterComponent,
    ProfileComponent,
    RegisterComponentComponent,
    UserDashboardComponent,
    CardViewComponent,
    RecommendationCardComponentComponent,
    EntityAnalyticsComponent,
    RestaurantsComponent,
    MovierecommendComponent,
    SearchResultComponent,
    AddRestaurantComponent,
  ],
  imports: [
    BrowserModule,
    MyMaterialModule,
    MatFormFieldModule,
    FormsModule,
    CustomMaterialModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    MatGridListModule,
    MatIconModule,
    ReactiveFormsModule,
    SocialLoginModule,
    MatInputModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatStepperModule,
    MDBBootstrapModule,
    FontAwesomeModule,
    TruncateModule,
    NgxPaginationModule,
    NgPipesModule,
    ModalModule.forRoot()
  ],
  providers: [
    {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    },
    RegisterService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
