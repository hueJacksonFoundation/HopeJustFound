/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { HopeJustFoundTestModule } from '../../../test.module';
import { DonationComponent } from 'app/entities/donation/donation.component';
import { DonationService } from 'app/entities/donation/donation.service';
import { Donation } from 'app/shared/model/donation.model';

describe('Component Tests', () => {
    describe('Donation Management Component', () => {
        let comp: DonationComponent;
        let fixture: ComponentFixture<DonationComponent>;
        let service: DonationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HopeJustFoundTestModule],
                declarations: [DonationComponent],
                providers: []
            })
                .overrideTemplate(DonationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DonationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DonationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Donation(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.donations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
