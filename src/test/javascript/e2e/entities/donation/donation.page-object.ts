import { element, by, ElementFinder } from 'protractor';

export class DonationComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-donation div table .btn-danger'));
    title = element.all(by.css('jhi-donation div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DonationUpdatePage {
    pageTitle = element(by.id('jhi-donation-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    typeInput = element(by.id('field_type'));
    initialDateInput = element(by.id('field_initialDate'));
    expireDateInput = element(by.id('field_expireDate'));
    skilledSelect = element(by.id('field_skilled'));
    unskilledSelect = element(by.id('field_unskilled'));
    goodsSelect = element(by.id('field_goods'));
    userSelect = element(by.id('field_user'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTypeInput(type) {
        await this.typeInput.sendKeys(type);
    }

    async getTypeInput() {
        return this.typeInput.getAttribute('value');
    }

    async setInitialDateInput(initialDate) {
        await this.initialDateInput.sendKeys(initialDate);
    }

    async getInitialDateInput() {
        return this.initialDateInput.getAttribute('value');
    }

    async setExpireDateInput(expireDate) {
        await this.expireDateInput.sendKeys(expireDate);
    }

    async getExpireDateInput() {
        return this.expireDateInput.getAttribute('value');
    }

    async skilledSelectLastOption() {
        await this.skilledSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async skilledSelectOption(option) {
        await this.skilledSelect.sendKeys(option);
    }

    getSkilledSelect(): ElementFinder {
        return this.skilledSelect;
    }

    async getSkilledSelectedOption() {
        return this.skilledSelect.element(by.css('option:checked')).getText();
    }

    async unskilledSelectLastOption() {
        await this.unskilledSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async unskilledSelectOption(option) {
        await this.unskilledSelect.sendKeys(option);
    }

    getUnskilledSelect(): ElementFinder {
        return this.unskilledSelect;
    }

    async getUnskilledSelectedOption() {
        return this.unskilledSelect.element(by.css('option:checked')).getText();
    }

    async goodsSelectLastOption() {
        await this.goodsSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async goodsSelectOption(option) {
        await this.goodsSelect.sendKeys(option);
    }

    getGoodsSelect(): ElementFinder {
        return this.goodsSelect;
    }

    async getGoodsSelectedOption() {
        return this.goodsSelect.element(by.css('option:checked')).getText();
    }

    async userSelectLastOption() {
        await this.userSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async userSelectOption(option) {
        await this.userSelect.sendKeys(option);
    }

    getUserSelect(): ElementFinder {
        return this.userSelect;
    }

    async getUserSelectedOption() {
        return this.userSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class DonationDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-donation-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-donation'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
