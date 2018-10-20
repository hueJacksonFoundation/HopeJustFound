/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { StatusComponentsPage, StatusDeleteDialog, StatusUpdatePage } from './status.page-object';

const expect = chai.expect;

describe('Status e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let statusUpdatePage: StatusUpdatePage;
    let statusComponentsPage: StatusComponentsPage;
    let statusDeleteDialog: StatusDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Statuses', async () => {
        await navBarPage.goToEntity('status');
        statusComponentsPage = new StatusComponentsPage();
        expect(await statusComponentsPage.getTitle()).to.eq('hopeJustFoundApp.status.home.title');
    });

    it('should load create Status page', async () => {
        await statusComponentsPage.clickOnCreateButton();
        statusUpdatePage = new StatusUpdatePage();
        expect(await statusUpdatePage.getPageTitle()).to.eq('hopeJustFoundApp.status.home.createOrEditLabel');
        await statusUpdatePage.cancel();
    });

    it('should create and save Statuses', async () => {
        const nbButtonsBeforeCreate = await statusComponentsPage.countDeleteButtons();

        await statusComponentsPage.clickOnCreateButton();
        await promise.all([
            statusUpdatePage.setApprovedInput('2000-12-31'),
            statusUpdatePage.setSubmittedInput('2000-12-31'),
            statusUpdatePage.setApprovedByInput('approvedBy')
        ]);
        expect(await statusUpdatePage.getApprovedInput()).to.eq('2000-12-31');
        expect(await statusUpdatePage.getSubmittedInput()).to.eq('2000-12-31');
        expect(await statusUpdatePage.getApprovedByInput()).to.eq('approvedBy');
        await statusUpdatePage.save();
        expect(await statusUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await statusComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Status', async () => {
        const nbButtonsBeforeDelete = await statusComponentsPage.countDeleteButtons();
        await statusComponentsPage.clickOnLastDeleteButton();

        statusDeleteDialog = new StatusDeleteDialog();
        expect(await statusDeleteDialog.getDialogTitle()).to.eq('hopeJustFoundApp.status.delete.question');
        await statusDeleteDialog.clickOnConfirmButton();

        expect(await statusComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
