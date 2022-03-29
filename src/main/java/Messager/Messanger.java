package Messager;

import Content.Product.Product;

public interface Messanger {
    String getProductMessage(Product product);
    String getCommandsMessage();
    String getCollectionMessage(String type, String size, String creationDate);
    String getUnitOfMeasureInputInvitationMessage();
    String getPersonBirthdayInputInvitationMessage();
    String getCountElementWithCondition(Long value);
    String getFieldInvitationMessage(String value);
}
