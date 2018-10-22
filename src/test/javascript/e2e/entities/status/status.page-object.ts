import { element, by, ElementFinder } from 'protractor';

export class StatusComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-status div table .btn-danger'));
    title = element.all(by.css('jhi-status div h2#page-heading span')).first();

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

export class StatusUpdatePage {
    pageTitle = element(by.id('jhi-status-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    approvedInput = element(by.id('field_approved'));
    submittedInput = element(by.id('field_submitted'));
    roleInput = element(by.id('field_role'));
    useridSelect = element(by.id('field_userid'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setApprovedInput(approved) {
        await this.approvedInput.sendKeys(approved);
    }

    async getApprovedInput() {
        return this.approvedInput.getAttribute('value');
    }

    async setSubmittedInput(submitted) {
        await this.submittedInput.sendKeys(submitted);
    }

    async getSubmittedInput() {
        return this.submittedInput.getAttribute('value');
    }

    async setRoleInput(role) {
        await this.roleInput.sendKeys(role);
    }

    async getRoleInput() {
        return this.roleInput.getAttribute('value');
    }

    async useridSelectLastOption() {
        await this.useridSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async useridSelectOption(option) {
        await this.useridSelect.sendKeys(option);
    }

    getUseridSelect(): ElementFinder {
        return this.useridSelect;
    }

    async getUseridSelectedOption() {
        return this.useridSelect.element(by.css('option:checked')).getText();
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

export class StatusDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-status-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-status'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
