package Messager;

import Content.Product.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMessanger implements Messanger{
    protected Map<String, String> explanations = new HashMap<>();
    protected Map<String, String> commands = new HashMap<>();

    protected String getFieldMassage(String field, Object value){
        return explanations.get(field) + ": " + value + "\n";
    }

    @Override
    public String getProductMessage(Product product){
        String message = "";
        message += getFieldMassage("id", product.getId());
        message += getFieldMassage("name", product.getName());
        message += getFieldMassage("x", product.getCoordinates().getX());
        message += getFieldMassage("y", product.getCoordinates().getY());
        message += getFieldMassage("date", product.getCreationDate());
        message += getFieldMassage("price", product.getPrice());
        message += getFieldMassage("part_number", product.getPartNumber());
        message += getFieldMassage("manufacture_cost", product.getManufactureCost());
        message += getFieldMassage("unit_of_measure", product.getUnitOfMeasure());
        message += getFieldMassage("name_person", product.getOwner().getName());
        message += getFieldMassage("birthday", product.getOwner().getBirthday().toString());
        message += getFieldMassage("height", product.getOwner().getHeight());
        message += getFieldMassage("weight", product.getOwner().getWeight());
        message += getFieldMassage("passport_id", product.getOwner().getPassportID());
        return message;
    }

    protected String getCommandMessage(String command){
        return command + ": " + commands.get(command) + "\n";
    }

    @Override
    public String getCommandsMessage(){
        return commands.keySet()
                .stream()
                .map(this::getCommandMessage)
                .collect(Collectors.joining(System.lineSeparator(), "", getCommandMassageEnding()));
    }

    protected abstract String getCommandMassageEnding();
    protected abstract void setCommandsExplanation();
    protected abstract void setProductFieldExplanation();

    public String getCountElementWithCondition(Long value){
        return "Found " + value + " elements of the collection." ;
    }
}
