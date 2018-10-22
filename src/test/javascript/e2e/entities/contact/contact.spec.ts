/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ContactComponentsPage, ContactDeleteDialog, ContactUpdatePage } from './contact.page-object';

const expect = chai.expect;

describe('Contact e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let contactUpdatePage: ContactUpdatePage;
    let contactComponentsPage: ContactComponentsPage;
    let contactDeleteDialog: ContactDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Contacts', async () => {
        await navBarPage.goToEntity('contact');
        contactComponentsPage = new ContactComponentsPage();
        expect(await contactComponentsPage.getTitle()).to.eq('hopeJustFoundApp.contact.home.title');
    });

    it('should load create Contact page', async () => {
        await contactComponentsPage.clickOnCreateButton();
        contactUpdatePage = new ContactUpdatePage();
        expect(await contactUpdatePage.getPageTitle()).to.eq('hopeJustFoundApp.contact.home.createOrEditLabel');
        await contactUpdatePage.cancel();
    });

    it('should create and save Contacts', async () => {
        const nbButtonsBeforeCreate = await contactComponentsPage.countDeleteButtons();

        await contactComponentsPage.clickOnCreateButton();
        await promise.all([
            contactUpdatePage.setPhoneNumberInput('phoneNumber'),
            contactUpdatePage.setAddressInput('address'),
            contactUpdatePage.setCityInput('city'),
            contactUpdatePage.setStateInput('state'),
            contactUpdatePage.setZipCodeInput('5'),
            contactUpdatePage.setContactDaysInput('contactDays'),
            contactUpdatePage.setContactTimesInput('contactTimes'),
            contactUpdatePage.useridSelectLastOption()
        ]);
        expect(await contactUpdatePage.getPhoneNumberInput()).to.eq('phoneNumber');
        expect(await contactUpdatePage.getAddressInput()).to.eq('address');
        expect(await contactUpdatePage.getCityInput()).to.eq('city');
        expect(await contactUpdatePage.getStateInput()).to.eq('state');
        expect(await contactUpdatePage.getZipCodeInput()).to.eq('5');
        expect(await contactUpdatePage.getContactDaysInput()).to.eq('contactDays');
        expect(await contactUpdatePage.getContactTimesInput()).to.eq('contactTimes');
        await contactUpdatePage.save();
        expect(await contactUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await contactComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Contact', async () => {
        const nbButtonsBeforeDelete = await contactComponentsPage.countDeleteButtons();
        await contactComponentsPage.clickOnLastDeleteButton();

        contactDeleteDialog = new ContactDeleteDialog();
        expect(await contactDeleteDialog.getDialogTitle()).to.eq('hopeJustFoundApp.contact.delete.question');
        await contactDeleteDialog.clickOnConfirmButton();

        expect(await contactComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
