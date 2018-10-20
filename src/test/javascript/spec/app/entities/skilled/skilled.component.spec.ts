/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { HopeJustFoundTestModule } from '../../../test.module';
import { SkilledComponent } from 'app/entities/skilled/skilled.component';
import { SkilledService } from 'app/entities/skilled/skilled.service';
import { Skilled } from 'app/shared/model/skilled.model';

describe('Component Tests', () => {
    describe('Skilled Management Component', () => {
        let comp: SkilledComponent;
        let fixture: ComponentFixture<SkilledComponent>;
        let service: SkilledService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HopeJustFoundTestModule],
                declarations: [SkilledComponent],
                providers: []
            })
                .overrideTemplate(SkilledComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SkilledComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SkilledService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Skilled(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.skilleds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
