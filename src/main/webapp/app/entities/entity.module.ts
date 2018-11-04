import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HopeJustFoundDonationModule } from './donation/donation.module';
import { HopeJustFoundStatusModule } from './status/status.module';
import { HopeJustFoundContactModule } from './contact/contact.module';
import { StatusComponent } from 'app/entities/status';
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
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: []
})
export class HopeJustFoundEntityModule {}
