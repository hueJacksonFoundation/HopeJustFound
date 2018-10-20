import { element, by, ElementFinder } from 'protractor';

export class SkilledComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-skilled div table .btn-danger'));
    title = element.all(by.css('jhi-skilled div h2#page-heading span')).first();

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

export class SkilledUpdatePage {
    pageTitle = element(by.id('jhi-skilled-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    experienceInput = element(by.id('field_experience'));
    typeInput = element(by.id('field_type'));
    numberInput = element(by.id('field_number'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setExperienceInput(experience) {
        await this.experienceInput.sendKeys(experience);
    }

    async getExperienceInput() {
        return this.experienceInput.getAttribute('value');
    }

    async setTypeInput(type) {
        await this.typeInput.sendKeys(type);
    }

    async getTypeInput() {
        return this.typeInput.getAttribute('value');
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

export class SkilledDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-skilled-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-skilled'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
