/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UserContactComponentsPage, UserContactDeleteDialog, UserContactUpdatePage } from './user-contact.page-object';

const expect = chai.expect;

describe('UserContact e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let userContactUpdatePage: UserContactUpdatePage;
    let userContactComponentsPage: UserContactComponentsPage;
    let userContactDeleteDialog: UserContactDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load UserContacts', async () => {
        await navBarPage.goToEntity('user-contact');
        userContactComponentsPage = new UserContactComponentsPage();
        expect(await userContactComponentsPage.getTitle()).to.eq('hopeJustFoundApp.userContact.home.title');
    });

    it('should load create UserContact page', async () => {
        await userContactComponentsPage.clickOnCreateButton();
        userContactUpdatePage = new UserContactUpdatePage();
        expect(await userContactUpdatePage.getPageTitle()).to.eq('hopeJustFoundApp.userContact.home.createOrEditLabel');
        await userContactUpdatePage.cancel();
    });

    it('should create and save UserContacts', async () => {
        const nbButtonsBeforeCreate = await userContactComponentsPage.countDeleteButtons();

        await userContactComponentsPage.clickOnCreateButton();
        await promise.all([
            userContactUpdatePage.setPhoneNumberInput('phoneNumber'),
            userContactUpdatePage.setAddressInput('address'),
            userContactUpdatePage.setCityInput('city'),
            userContactUpdatePage.setStateInput('state'),
            userContactUpdatePage.setZipCodeInput('5'),
            userContactUpdatePage.setContactDaysInput('contactDays'),
            userContactUpdatePage.setContactTimesInput('contactTimes')
        ]);
        expect(await userContactUpdatePage.getPhoneNumberInput()).to.eq('phoneNumber');
        expect(await userContactUpdatePage.getAddressInput()).to.eq('address');
        expect(await userContactUpdatePage.getCityInput()).to.eq('city');
        expect(await userContactUpdatePage.getStateInput()).to.eq('state');
        expect(await userContactUpdatePage.getZipCodeInput()).to.eq('5');
        expect(await userContactUpdatePage.getContactDaysInput()).to.eq('contactDays');
        expect(await userContactUpdatePage.getContactTimesInput()).to.eq('contactTimes');
        await userContactUpdatePage.save();
        expect(await userContactUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await userContactComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last UserContact', async () => {
        const nbButtonsBeforeDelete = await userContactComponentsPage.countDeleteButtons();
        await userContactComponentsPage.clickOnLastDeleteButton();

        userContactDeleteDialog = new UserContactDeleteDialog();
        expect(await userContactDeleteDialog.getDialogTitle()).to.eq('hopeJustFoundApp.userContact.delete.question');
        await userContactDeleteDialog.clickOnConfirmButton();

        expect(await userContactComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
