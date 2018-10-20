/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SkilledComponentsPage, SkilledDeleteDialog, SkilledUpdatePage } from './skilled.page-object';

const expect = chai.expect;

describe('Skilled e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let skilledUpdatePage: SkilledUpdatePage;
    let skilledComponentsPage: SkilledComponentsPage;
    let skilledDeleteDialog: SkilledDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Skilleds', async () => {
        await navBarPage.goToEntity('skilled');
        skilledComponentsPage = new SkilledComponentsPage();
        expect(await skilledComponentsPage.getTitle()).to.eq('hopeJustFoundApp.skilled.home.title');
    });

    it('should load create Skilled page', async () => {
        await skilledComponentsPage.clickOnCreateButton();
        skilledUpdatePage = new SkilledUpdatePage();
        expect(await skilledUpdatePage.getPageTitle()).to.eq('hopeJustFoundApp.skilled.home.createOrEditLabel');
        await skilledUpdatePage.cancel();
    });

    it('should create and save Skilleds', async () => {
        const nbButtonsBeforeCreate = await skilledComponentsPage.countDeleteButtons();

        await skilledComponentsPage.clickOnCreateButton();
        await promise.all([
            skilledUpdatePage.setExperienceInput('experience'),
            skilledUpdatePage.setTypeInput('type'),
            skilledUpdatePage.setNumberInput('5')
        ]);
        expect(await skilledUpdatePage.getExperienceInput()).to.eq('experience');
        expect(await skilledUpdatePage.getTypeInput()).to.eq('type');
        expect(await skilledUpdatePage.getNumberInput()).to.eq('5');
        await skilledUpdatePage.save();
        expect(await skilledUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await skilledComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Skilled', async () => {
        const nbButtonsBeforeDelete = await skilledComponentsPage.countDeleteButtons();
        await skilledComponentsPage.clickOnLastDeleteButton();

        skilledDeleteDialog = new SkilledDeleteDialog();
        expect(await skilledDeleteDialog.getDialogTitle()).to.eq('hopeJustFoundApp.skilled.delete.question');
        await skilledDeleteDialog.clickOnConfirmButton();

        expect(await skilledComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
