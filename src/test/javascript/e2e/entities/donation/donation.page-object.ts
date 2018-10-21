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
    conditionInput = element(by.id('field_condition'));
    descriptionInput = element(by.id('field_description'));
    experienceInput = element(by.id('field_experience'));
    climateInput = element(by.id('field_climate'));
    intensityInput = element(by.id('field_intensity'));
    numberOfVolunteersInput = element(by.id('field_numberOfVolunteers'));
    useridSelect = element(by.id('field_userid'));

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

    async setExperienceInput(experience) {
        await this.experienceInput.sendKeys(experience);
    }

    async getExperienceInput() {
        return this.experienceInput.getAttribute('value');
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

    async setNumberOfVolunteersInput(numberOfVolunteers) {
        await this.numberOfVolunteersInput.sendKeys(numberOfVolunteers);
    }

    async getNumberOfVolunteersInput() {
        return this.numberOfVolunteersInput.getAttribute('value');
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
