/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HopeJustFoundTestModule } from '../../../test.module';
import { SkilledUpdateComponent } from 'app/entities/skilled/skilled-update.component';
import { SkilledService } from 'app/entities/skilled/skilled.service';
import { Skilled } from 'app/shared/model/skilled.model';

describe('Component Tests', () => {
    describe('Skilled Management Update Component', () => {
        let comp: SkilledUpdateComponent;
        let fixture: ComponentFixture<SkilledUpdateComponent>;
        let service: SkilledService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HopeJustFoundTestModule],
                declarations: [SkilledUpdateComponent]
            })
                .overrideTemplate(SkilledUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SkilledUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SkilledService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Skilled(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.skilled = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Skilled();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.skilled = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
