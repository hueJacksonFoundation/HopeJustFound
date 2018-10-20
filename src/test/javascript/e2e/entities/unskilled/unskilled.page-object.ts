import { element, by, ElementFinder } from 'protractor';

export class UnskilledComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-unskilled div table .btn-danger'));
    title = element.all(by.css('jhi-unskilled div h2#page-heading span')).first();

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

export class UnskilledUpdatePage {
    pageTitle = element(by.id('jhi-unskilled-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    climateInput = element(by.id('field_climate'));
    intensityInput = element(by.id('field_intensity'));
    numberInput = element(by.id('field_number'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setClimateInput(climate) {
        await this.climateInput.sendKeys(climate);
    }

    async getClimateInput() {
        return this.climateInput.getAttribute('value');
    }

    async setIntensityInput(intensity) {
        await this.intensityInput.sendKeys(intensity);
    }

    async getIntensityInput() {
        return this.intensityInput.getAttribute('value');
    }

    async setNumberInput(number) {
        await this.numberInput.sendKeys(number);
    }

    async getNumberInput() {
        return this.numberInput.getAttribute('value');
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

export class UnskilledDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-unskilled-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-unskilled'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
