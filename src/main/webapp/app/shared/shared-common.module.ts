import { NgModule } from '@angular/core';

import { HopeJustFoundSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';
import { StatusComponent } from 'app/entities/status';
import { HopeJustFoundStatusModule } from 'app/entities/status/status.module';

@NgModule({
    imports: [HopeJustFoundSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [HopeJustFoundSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class HopeJustFoundSharedCommonModule {}
