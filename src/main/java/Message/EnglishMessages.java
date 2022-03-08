package Message;

import Content.UnitOfMeasure;

public class EnglishMessages extends AbstractMessanger{

    @Override
    protected String getCommandMassageEnding() {
        return "NB! \"(argument)\" must be entered in in the same line as the command";
    }

    @Override
    protected void setCommandsExplanation() {
        Commands.put("help", "displays reference about all commands");
        Commands.put("info", "displays information about collection");
        Commands.put("show", "print all elements of the collection in string representation");
        Commands.put("add", "add a new {Product} to the collection");
        Commands.put("update_id", "update the value of the collection element whose id is equal to the given one");
        Commands.put("remove_by_id", "remove element from collection by its id");
        Commands.put("clear", "clear the collection");
        Commands.put("save", "save collection to file");
        Commands.put("execute_script", "read and execute the script from the specified file");
        Commands.put("exit", "terminate program (without saving to file)");
        Commands.put("add_if_max", "add a new element to the collection if its value is greater than the value of the largest element in this collection");
        Commands.put("remove_lower", "remove all elements from the collection that are smaller than the given one");
        Commands.put("history", "print the last 13 commands (without their arguments)");
        Commands.put("count_by_manufacture_cost", "display the number of elements whose value of the manufactureCost field is equal to the specified one");
        Commands.put("count_greater_than_unit_of_measure", "display the number of elements whose unitOfMeasure field value is greater than the given one");
        Commands.put("print_ascending", "display the elements of the collection in ascending order");
    }

    @Override
    protected void setProductFieldExplanation() {
        Explanations.put("id", "ID");
        Explanations.put("name", "Product name");
        Explanations.put("x", "X coordinate");
        Explanations.put("y", "Y coordinate");
        Explanations.put("date", "Creation date");
        Explanations.put("price", "Price");
        Explanations.put("part_number", "Part number");
        Explanations.put("manufacture_cost", "Manufacture cost");
        Explanations.put("unit_of_measure", "Unit of measurement");
        Explanations.put("name_person", "Owners name");
        Explanations.put("birthday", "Owners birthday");
        Explanations.put("height", "Owners height");
        Explanations.put("weight", "Owners weight");
        Explanations.put("passport_id", "Owners passport id");
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
        return "Enter owner birthday: ";
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
