import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { ClubsComponent } from './clubs/clubs.component';
import { ClubDetailComponent }  from './club-detail/club-detail.component';
import { CallbackComponent } from './callback/callback.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'clubs', component: ClubsComponent },
  { path: 'clubs/:id', component: ClubDetailComponent },
  { path: 'login/callback', component: CallbackComponent },
  { path: 'profile', component: ProfileComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
