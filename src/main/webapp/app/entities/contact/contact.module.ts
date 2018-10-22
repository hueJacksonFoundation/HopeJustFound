import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HopeJustFoundSharedModule } from 'app/shared';
import { HopeJustFoundAdminModule } from 'app/admin/admin.module';
import {
    ContactComponent,
    ContactDetailComponent,
    ContactUpdateComponent,
    ContactDeletePopupComponent,
    ContactDeleteDialogComponent,
    contactRoute,
    contactPopupRoute
} from './';

const ENTITY_STATES = [...contactRoute, ...contactPopupRoute];

@NgModule({
    imports: [HopeJustFoundSharedModule, HopeJustFoundAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ContactComponent,
        ContactDetailComponent,
        ContactUpdateComponent,
        ContactDeleteDialogComponent,
        ContactDeletePopupComponent
    ],
    entryComponents: [ContactComponent, ContactUpdateComponent, ContactDeleteDialogComponent, ContactDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HopeJustFoundContactModule {}
