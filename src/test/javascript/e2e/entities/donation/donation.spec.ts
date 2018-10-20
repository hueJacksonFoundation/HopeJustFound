/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DonationComponentsPage, DonationDeleteDialog, DonationUpdatePage } from './donation.page-object';

const expect = chai.expect;

describe('Donation e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let donationUpdatePage: DonationUpdatePage;
    let donationComponentsPage: DonationComponentsPage;
    let donationDeleteDialog: DonationDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Donations', async () => {
        await navBarPage.goToEntity('donation');
        donationComponentsPage = new DonationComponentsPage();
        expect(await donationComponentsPage.getTitle()).to.eq('hopeJustFoundApp.donation.home.title');
    });

    it('should load create Donation page', async () => {
        await donationComponentsPage.clickOnCreateButton();
        donationUpdatePage = new DonationUpdatePage();
        expect(await donationUpdatePage.getPageTitle()).to.eq('hopeJustFoundApp.donation.home.createOrEditLabel');
        await donationUpdatePage.cancel();
    });

    it('should create and save Donations', async () => {
        const nbButtonsBeforeCreate = await donationComponentsPage.countDeleteButtons();

        await donationComponentsPage.clickOnCreateButton();
        await promise.all([
            donationUpdatePage.setTypeInput('type'),
            donationUpdatePage.setInitialDateInput('2000-12-31'),
            donationUpdatePage.setExpireDateInput('2000-12-31'),
            donationUpdatePage.skilledSelectLastOption(),
            donationUpdatePage.unskilledSelectLastOption(),
            donationUpdatePage.goodsSelectLastOption(),
            donationUpdatePage.userSelectLastOption()
        ]);
        expect(await donationUpdatePage.getTypeInput()).to.eq('type');
        expect(await donationUpdatePage.getInitialDateInput()).to.eq('2000-12-31');
        expect(await donationUpdatePage.getExpireDateInput()).to.eq('2000-12-31');
        await donationUpdatePage.save();
        expect(await donationUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await donationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Donation', async () => {
        const nbButtonsBeforeDelete = await donationComponentsPage.countDeleteButtons();
        await donationComponentsPage.clickOnLastDeleteButton();

        donationDeleteDialog = new DonationDeleteDialog();
        expect(await donationDeleteDialog.getDialogTitle()).to.eq('hopeJustFoundApp.donation.delete.question');
        await donationDeleteDialog.clickOnConfirmButton();

        expect(await donationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
