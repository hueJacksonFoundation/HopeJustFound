import { NgModule } from '@angular/core';

import { HopeJustFoundSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [HopeJustFoundSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [HopeJustFoundSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class HopeJustFoundSharedCommonModule {}
