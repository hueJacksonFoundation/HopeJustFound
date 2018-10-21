import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HopeJustFoundDonationModule } from './donation/donation.module';
import { HopeJustFoundStatusModule } from './status/status.module';
import { HopeJustFoundUserContactModule } from './user-contact/user-contact.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HopeJustFoundDonationModule,
        HopeJustFoundStatusModule,
        HopeJustFoundUserContactModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HopeJustFoundEntityModule {}
