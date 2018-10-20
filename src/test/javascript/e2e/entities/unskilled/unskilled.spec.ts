/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UnskilledComponentsPage, UnskilledDeleteDialog, UnskilledUpdatePage } from './unskilled.page-object';

const expect = chai.expect;

describe('Unskilled e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let unskilledUpdatePage: UnskilledUpdatePage;
    let unskilledComponentsPage: UnskilledComponentsPage;
    let unskilledDeleteDialog: UnskilledDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Unskilleds', async () => {
        await navBarPage.goToEntity('unskilled');
        unskilledComponentsPage = new UnskilledComponentsPage();
        expect(await unskilledComponentsPage.getTitle()).to.eq('hopeJustFoundApp.unskilled.home.title');
    });

    it('should load create Unskilled page', async () => {
        await unskilledComponentsPage.clickOnCreateButton();
        unskilledUpdatePage = new UnskilledUpdatePage();
        expect(await unskilledUpdatePage.getPageTitle()).to.eq('hopeJustFoundApp.unskilled.home.createOrEditLabel');
        await unskilledUpdatePage.cancel();
    });

    it('should create and save Unskilleds', async () => {
        const nbButtonsBeforeCreate = await unskilledComponentsPage.countDeleteButtons();

        await unskilledComponentsPage.clickOnCreateButton();
        await promise.all([
            unskilledUpdatePage.setClimateInput('climate'),
            unskilledUpdatePage.setIntensityInput('intensity'),
            unskilledUpdatePage.setNumberInput('5')
        ]);
        expect(await unskilledUpdatePage.getClimateInput()).to.eq('climate');
        expect(await unskilledUpdatePage.getIntensityInput()).to.eq('intensity');
        expect(await unskilledUpdatePage.getNumberInput()).to.eq('5');
        await unskilledUpdatePage.save();
        expect(await unskilledUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await unskilledComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Unskilled', async () => {
        const nbButtonsBeforeDelete = await unskilledComponentsPage.countDeleteButtons();
        await unskilledComponentsPage.clickOnLastDeleteButton();

        unskilledDeleteDialog = new UnskilledDeleteDialog();
        expect(await unskilledDeleteDialog.getDialogTitle()).to.eq('hopeJustFoundApp.unskilled.delete.question');
        await unskilledDeleteDialog.clickOnConfirmButton();

        expect(await unskilledComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
