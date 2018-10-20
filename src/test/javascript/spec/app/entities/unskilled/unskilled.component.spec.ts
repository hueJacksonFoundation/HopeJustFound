/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { HopeJustFoundTestModule } from '../../../test.module';
import { UnskilledComponent } from 'app/entities/unskilled/unskilled.component';
import { UnskilledService } from 'app/entities/unskilled/unskilled.service';
import { Unskilled } from 'app/shared/model/unskilled.model';

describe('Component Tests', () => {
    describe('Unskilled Management Component', () => {
        let comp: UnskilledComponent;
        let fixture: ComponentFixture<UnskilledComponent>;
        let service: UnskilledService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HopeJustFoundTestModule],
                declarations: [UnskilledComponent],
                providers: []
            })
                .overrideTemplate(UnskilledComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UnskilledComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UnskilledService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Unskilled(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.unskilleds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
