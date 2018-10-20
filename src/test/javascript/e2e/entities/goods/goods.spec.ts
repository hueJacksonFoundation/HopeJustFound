/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GoodsComponentsPage, GoodsDeleteDialog, GoodsUpdatePage } from './goods.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('Goods e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let goodsUpdatePage: GoodsUpdatePage;
    let goodsComponentsPage: GoodsComponentsPage;
    let goodsDeleteDialog: GoodsDeleteDialog;
    const fileNameToUpload = 'logo-jhipster.png';
    const fileToUpload = '../../../../../main/webapp/content/images/' + fileNameToUpload;
    const absolutePath = path.resolve(__dirname, fileToUpload);

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Goods', async () => {
        await navBarPage.goToEntity('goods');
        goodsComponentsPage = new GoodsComponentsPage();
        expect(await goodsComponentsPage.getTitle()).to.eq('hopeJustFoundApp.goods.home.title');
    });

    it('should load create Goods page', async () => {
        await goodsComponentsPage.clickOnCreateButton();
        goodsUpdatePage = new GoodsUpdatePage();
        expect(await goodsUpdatePage.getPageTitle()).to.eq('hopeJustFoundApp.goods.home.createOrEditLabel');
        await goodsUpdatePage.cancel();
    });

    it('should create and save Goods', async () => {
        const nbButtonsBeforeCreate = await goodsComponentsPage.countDeleteButtons();

        await goodsComponentsPage.clickOnCreateButton();
        await promise.all([
            goodsUpdatePage.setTypeInput('type'),
            goodsUpdatePage.setConditionInput('condition'),
            goodsUpdatePage.setDescriptionInput(absolutePath),
            goodsUpdatePage.setTransportInput('transport'),
            goodsUpdatePage.setImageInput(absolutePath)
        ]);
        expect(await goodsUpdatePage.getTypeInput()).to.eq('type');
        expect(await goodsUpdatePage.getConditionInput()).to.eq('condition');
        expect(await goodsUpdatePage.getDescriptionInput()).to.endsWith(fileNameToUpload);
        expect(await goodsUpdatePage.getTransportInput()).to.eq('transport');
        expect(await goodsUpdatePage.getImageInput()).to.endsWith(fileNameToUpload);
        await goodsUpdatePage.save();
        expect(await goodsUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await goodsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Goods', async () => {
        const nbButtonsBeforeDelete = await goodsComponentsPage.countDeleteButtons();
        await goodsComponentsPage.clickOnLastDeleteButton();

        goodsDeleteDialog = new GoodsDeleteDialog();
        expect(await goodsDeleteDialog.getDialogTitle()).to.eq('hopeJustFoundApp.goods.delete.question');
        await goodsDeleteDialog.clickOnConfirmButton();

        expect(await goodsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
