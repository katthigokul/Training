import { NgModule } from '@angular/core';
import {MatNativeDateModule, MatSnackBarModule, MatIconModule, MatDialogModule,
     MatButtonModule, MatTableModule, MatPaginatorModule, MatSortModule, MatTabsModule,
     MatCheckboxModule, MatToolbarModule, MatCardModule, MatFormFieldModule,
     MatProgressSpinnerModule, MatInputModule, MatGridListModule } from '@angular/material';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatSliderModule} from '@angular/material/slider';
import {MatDividerModule} from '@angular/material/divider';

const MaterialComponents = [MatTabsModule, MatDividerModule, MatSliderModule, MatSelectModule,
    MatRadioModule, MatNativeDateModule, MatDatepickerModule, MatSnackBarModule,
    MatIconModule, MatDialogModule, MatProgressSpinnerModule, MatButtonModule,
    MatSortModule, MatTableModule, MatTabsModule, MatCheckboxModule, MatToolbarModule,
    MatCardModule, MatFormFieldModule, MatProgressSpinnerModule, MatInputModule, MatPaginatorModule, MatGridListModule];
@NgModule({
imports: [MaterialComponents],

    exports: [MaterialComponents]
})

export  class  MyMaterialModule { }
