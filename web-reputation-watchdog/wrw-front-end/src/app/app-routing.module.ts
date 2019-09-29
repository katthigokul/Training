import { AddAReviewComponent } from './add-a-review/add-a-review.component';
import { AddEntityComponent } from './add-entity/add-entity.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import {NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserComponent} from './user/user.component';
import {LoginComponent} from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponentComponent } from './register-component/register-component.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { CardViewComponent } from './card-view/card-view.component';
import { EntityAnalyticsComponent } from './entity-analytics/entity-analytics.component';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import {SearchResultComponent} from './search-result/search-result.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';


const routes: Routes = [
  { path: 'user', component: UserComponent },
  { path: 'login', component: LoginComponent },
  {path:'movie',component:UserDashboardComponent},
  {path : '', component : LandingPageComponent},
  { path: 'profile', component: ProfileComponent },
  {path: 'register', component: RegisterComponentComponent},
{path: 'landing', component: LandingPageComponent},
// {path: 'landing-page/:isUserLoggedIn:domain', component: LandingPageComponent},
{path:    'addentity', component: AddEntityComponent},
{path: 'restaurant', component: RestaurantsComponent},
{
  path: 'addreview', component: AddAReviewComponent
},
{ path: 'user-dashboard' , component: UserDashboardComponent},
{ path: 'view', component: CardViewComponent },
{path : 'view/:title', component: CardViewComponent },
{path : 'search-result', component: SearchResultComponent},
{ path: 'analysis', component: EntityAnalyticsComponent },
{ path: 'addRestaurant', component: AddRestaurantComponent}
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }

export const routingComponents = [UserComponent, LoginComponent];
