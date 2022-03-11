package Messager;

import Content.Product.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMessanger implements Messanger{
    protected Map<String, String> Explanations = new HashMap<>();
    protected Map<String, String> Commands = new HashMap<>();

    protected String getFieldMassage(String field, Object value){
        return Explanations.get(field) + ": " + value + "\n";
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
        message += getFieldMassage("partnumber", product.getPartNumber());
        message += getFieldMassage("manufacturecost", product.getManufactureCost());
        message += getFieldMassage("unitofmeasure", product.getUnitOfMeasure());
        message += getFieldMassage("nameperson", product.getOwner().getName());
        message += getFieldMassage("birthday", product.getOwner().getBirthday());
        message += getFieldMassage("height", product.getOwner().getHeight());
        message += getFieldMassage("weight", product.getOwner().getWeight());
        message += getFieldMassage("passportid", product.getOwner().getPassportID());
        return message;
    }

    protected String getCommandMessage(String command){
        return command + ": " + Commands.get(command) + "\n";
    }

    @Override
    public String getCommandsMessage(){
        String Message = "";
        for (String command : Commands.keySet()){
            Message += getCommandMessage(command);
        }
        return Message + getCommandMassageEnding();
    }


    protected abstract String getCommandMassageEnding();
    protected abstract void setCommandsExplanation();
    protected abstract void setProductFieldExplanation();
}
