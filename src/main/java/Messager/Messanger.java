package Messager;

import Content.Product.Product;

public interface Messanger {
    String getProductMessage(Product product);
    String getCommandsMessage();
    String getCollectionMessage(String type, String size, String creationDate);
    String getNameInputInvitationMessage();
    String getXInputInvitationMessage();
    String getYInputInvitationMessage();
    String getPriceInputInvitationMessage();
    String getPartNumberInputInvitationMessage();
    String getManufactureCostInputInvitationMessage();
    String getUnitOfMeasureInputInvitationMessage();
    String getPersonNameInputInvitationMessage();
    String getPersonBirthdayInputInvitationMessage();
    String getPersonHeightInputInvitationMessage();
    String getPersonWeightInputInvitationMessage();
    String getPersonPassportIdInputInvitationMessage();
    String getCountElementWithCondition(Long value);
}
