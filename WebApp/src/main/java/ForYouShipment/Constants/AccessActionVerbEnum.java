package ForYouShipment.Constants;

public enum AccessActionVerbEnum {
    GENERAL,   // When all actions have the same security level (ex: Login)
    INDEX,
    VIEW,
    PERSONAL,  // Owned item(ex: Clients' profile)
    EDIT,
    DELETE,
    CREATE,
    SEARCH
}
