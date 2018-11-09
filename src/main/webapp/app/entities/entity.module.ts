import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HopeJustFoundDonationModule } from './donation/donation.module';
import { HopeJustFoundStatusModule } from './status/status.module';
import { HopeJustFoundContactModule } from './contact/contact.module';
import { StatusComponent } from 'app/entities/status';

@NgModule({
    // prettier-ignore
    imports: [
        HopeJustFoundDonationModule,
        HopeJustFoundStatusModule,
        HopeJustFoundContactModule,
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [StatusComponent]
})
export class HopeJustFoundEntityModule {}
