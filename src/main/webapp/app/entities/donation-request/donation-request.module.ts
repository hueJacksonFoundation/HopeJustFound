import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HopeJustFoundSharedModule } from 'app/shared';
import { HopeJustFoundAdminModule } from 'app/admin/admin.module';
import {
    DonationRequestComponent,
    DonationRequestDetailComponent,
    DonationRequestUpdateComponent,
    DonationRequestDeletePopupComponent,
    DonationRequestDeleteDialogComponent,
    donationRequestRoute,
    donationRequestPopupRoute
} from './';

const ENTITY_STATES = [...donationRequestRoute, ...donationRequestPopupRoute];

@NgModule({
    imports: [HopeJustFoundSharedModule, HopeJustFoundAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DonationRequestComponent,
        DonationRequestDetailComponent,
        DonationRequestUpdateComponent,
        DonationRequestDeleteDialogComponent,
        DonationRequestDeletePopupComponent
    ],
    entryComponents: [
        DonationRequestComponent,
        DonationRequestUpdateComponent,
        DonationRequestDeleteDialogComponent,
        DonationRequestDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HopeJustFoundDonationRequestModule {}
