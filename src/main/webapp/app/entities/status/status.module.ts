import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HopeJustFoundSharedModule } from 'app/shared';
import { HopeJustFoundAdminModule } from 'app/admin/admin.module';
import {
    StatusComponent,
    StatusDetailComponent,
    StatusUpdateComponent,
    StatusDeletePopupComponent,
    StatusDeleteDialogComponent,
    statusRoute,
    statusPopupRoute
} from './';

const ENTITY_STATES = [...statusRoute, ...statusPopupRoute];

@NgModule({
    imports: [HopeJustFoundSharedModule, HopeJustFoundAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [StatusComponent, StatusDetailComponent, StatusUpdateComponent, StatusDeleteDialogComponent, StatusDeletePopupComponent],
    entryComponents: [StatusComponent, StatusUpdateComponent, StatusDeleteDialogComponent, StatusDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [StatusComponent]
})
export class HopeJustFoundStatusModule {}
