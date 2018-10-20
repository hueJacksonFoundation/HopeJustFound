import { element, by, ElementFinder } from 'protractor';

export class GoodsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-goods div table .btn-danger'));
    title = element.all(by.css('jhi-goods div h2#page-heading span')).first();

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

export class GoodsUpdatePage {
    pageTitle = element(by.id('jhi-goods-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    typeInput = element(by.id('field_type'));
    conditionInput = element(by.id('field_condition'));
    descriptionInput = element(by.id('file_description'));
    transportInput = element(by.id('field_transport'));
    imageInput = element(by.id('file_image'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTypeInput(type) {
        await this.typeInput.sendKeys(type);
    }

    async getTypeInput() {
        return this.typeInput.getAttribute('value');
    }

    async setConditionInput(condition) {
        await this.conditionInput.sendKeys(condition);
    }

    async getConditionInput() {
        return this.conditionInput.getAttribute('value');
    }

    async setDescriptionInput(description) {
        await this.descriptionInput.sendKeys(description);
    }

    async getDescriptionInput() {
        return this.descriptionInput.getAttribute('value');
    }

    async setTransportInput(transport) {
        await this.transportInput.sendKeys(transport);
    }

    async getTransportInput() {
        return this.transportInput.getAttribute('value');
    }

    async setImageInput(image) {
        await this.imageInput.sendKeys(image);
    }

    async getImageInput() {
        return this.imageInput.getAttribute('value');
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

export class GoodsDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-goods-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-goods'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
