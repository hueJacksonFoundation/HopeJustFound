import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HopeJustFoundSharedModule } from 'app/shared';
import {
    UnskilledComponent,
    UnskilledDetailComponent,
    UnskilledUpdateComponent,
    UnskilledDeletePopupComponent,
    UnskilledDeleteDialogComponent,
    unskilledRoute,
    unskilledPopupRoute
} from './';

const ENTITY_STATES = [...unskilledRoute, ...unskilledPopupRoute];

@NgModule({
    imports: [HopeJustFoundSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UnskilledComponent,
        UnskilledDetailComponent,
        UnskilledUpdateComponent,
        UnskilledDeleteDialogComponent,
        UnskilledDeletePopupComponent
    ],
    entryComponents: [UnskilledComponent, UnskilledUpdateComponent, UnskilledDeleteDialogComponent, UnskilledDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HopeJustFoundUnskilledModule {}
