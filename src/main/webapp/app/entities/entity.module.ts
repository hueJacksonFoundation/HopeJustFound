import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HopeJustFoundDonationModule } from './donation/donation.module';
import { HopeJustFoundStatusModule } from './status/status.module';
import { HopeJustFoundContactModule } from './contact/contact.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HopeJustFoundDonationModule,
        HopeJustFoundStatusModule,
        HopeJustFoundContactModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HopeJustFoundEntityModule {}
