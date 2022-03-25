package Messager;

import Content.Product.UnitOfMeasure;

public class EnglishMessager extends AbstractMessanger{

    public EnglishMessager(){
        setCommandsExplanation();
        setProductFieldExplanation();
    }

    @Override
    protected String getCommandMassageEnding() {
        return "NB! \"(argument)\" must be entered in in the same line as the command";
    }

    @Override
    protected void setCommandsExplanation() {
        commands.put("help", "displays reference about all commands");
        commands.put("info", "displays information about collection");
        commands.put("show", "print all elements of the collection in string representation");
        commands.put("add", "add a new {Product} to the collection");
        commands.put("update_id", "update the value of the collection element whose id is equal to the given one");
        commands.put("remove_by_id", "remove element from collection by its id");
        commands.put("clear", "clear the collection");
        commands.put("save", "save collection to file");
        commands.put("execute_script", "read and execute the script from the specified file");
        commands.put("exit", "terminate program (without saving to file)");
        commands.put("add_if_max", "add a new element to the collection if its value is greater than the value of the largest element in this collection");
        commands.put("remove_lower", "remove all elements from the collection that are smaller than the given one");
        commands.put("history", "print the last 13 commands (without their arguments)");
        commands.put("count_by_manufacture_cost", "display the number of elements whose value of the manufactureCost field is equal to the specified one");
        commands.put("count_greater_than_unit_of_measure", "display the number of elements whose unitOfMeasure field value is greater than the given one");
        commands.put("print_ascending", "display the elements of the collection in ascending order");
    }

    @Override
    protected void setProductFieldExplanation() {
        explanations.put("id", "ID");
        explanations.put("name", "Product name");
        explanations.put("x", "X coordinate");
        explanations.put("y", "Y coordinate");
        explanations.put("date", "Creation date");
        explanations.put("price", "Price");
        explanations.put("part_number", "Part number");
        explanations.put("manufacture_cost", "Manufacture cost");
        explanations.put("unit_of_measure", "Unit of measurement");
        explanations.put("name_person", "Owners name");
        explanations.put("birthday", "Owners birthday");
        explanations.put("height", "Owners height");
        explanations.put("weight", "Owners weight");
        explanations.put("passport_id", "Owners passport id");
    }

    @Override
    public String getCollectionMessage(String type, String size, String creationDate) {
        return "Collection type: " + type + "\nCollection size: " + size + "\nInitialization time: " + creationDate;
    }

    @Override
    public String getNameInputInvitationMessage() {
        return "Enter product name: ";
    }

    @Override
    public String getXInputInvitationMessage() {
        return "Enter X coordinate: ";
    }

    @Override
    public String getYInputInvitationMessage() {
        return "Enter Y coordinate: ";
    }

    @Override
    public String getPriceInputInvitationMessage() {
        return "Enter product price: ";
    }

    @Override
    public String getPartNumberInputInvitationMessage() {
        return "Enter product part number: ";
    }

    @Override
    public String getManufactureCostInputInvitationMessage() {
        return "Enter product manufacture cost: ";
    }

    @Override
    public String getUnitOfMeasureInputInvitationMessage() {
        return "Choose value from list: " + UnitOfMeasure.getTitleinColumn() +  "And enter product unit of measurement: ";
    }

    @Override
    public String getPersonNameInputInvitationMessage() {
        return "Enter owner name: ";
    }

    @Override
    public String getPersonBirthdayInputInvitationMessage() {
        return "Enter owner birthday with separated - \'-\' (Year-Month-Day): ";
    }

    @Override
    public String getPersonHeightInputInvitationMessage() {
        return "Enter owner height: ";
    }

    @Override
    public String getPersonWeightInputInvitationMessage() {
        return "Enter owner weight: ";
    }

    @Override
    public String getPersonPassportIdInputInvitationMessage() {
        return "Enter owner passport id: ";
    }
}
