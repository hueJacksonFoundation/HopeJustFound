import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HopeJustFoundSharedModule } from 'app/shared';
import {
    SkilledComponent,
    SkilledDetailComponent,
    SkilledUpdateComponent,
    SkilledDeletePopupComponent,
    SkilledDeleteDialogComponent,
    skilledRoute,
    skilledPopupRoute
} from './';

const ENTITY_STATES = [...skilledRoute, ...skilledPopupRoute];

@NgModule({
    imports: [HopeJustFoundSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SkilledComponent,
        SkilledDetailComponent,
        SkilledUpdateComponent,
        SkilledDeleteDialogComponent,
        SkilledDeletePopupComponent
    ],
    entryComponents: [SkilledComponent, SkilledUpdateComponent, SkilledDeleteDialogComponent, SkilledDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HopeJustFoundSkilledModule {}
