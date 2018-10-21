import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HopeJustFoundSharedModule } from 'app/shared';
import { HopeJustFoundAdminModule } from 'app/admin/admin.module';
import {
    UserContactComponent,
    UserContactDetailComponent,
    UserContactUpdateComponent,
    UserContactDeletePopupComponent,
    UserContactDeleteDialogComponent,
    userContactRoute,
    userContactPopupRoute
} from './';

const ENTITY_STATES = [...userContactRoute, ...userContactPopupRoute];

@NgModule({
    imports: [HopeJustFoundSharedModule, HopeJustFoundAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UserContactComponent,
        UserContactDetailComponent,
        UserContactUpdateComponent,
        UserContactDeleteDialogComponent,
        UserContactDeletePopupComponent
    ],
    entryComponents: [UserContactComponent, UserContactUpdateComponent, UserContactDeleteDialogComponent, UserContactDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HopeJustFoundUserContactModule {}
