package ForYouShipment.Workers;

import ForYouShipment.Constants.AccessActionNounEnum;
import ForYouShipment.Constants.AccessActionVerbEnum;
import ForYouShipment.Models.UserModel;

public final class SecurityAccessWorker {
    private SecurityAccessWorker() {}

    private static boolean LogisticUserHasAccessTo(AccessActionNounEnum noun,
                                    AccessActionVerbEnum verb) {
        switch(noun) {
            case PORT_MANAGMENT:
            case CONTAINER_MANAGEMENT:
                return true;
            case CLIENT_MANAGEMENT:
                return verb == AccessActionVerbEnum.SEARCH
                                || verb == AccessActionVerbEnum.VIEW 
                                || verb == AccessActionVerbEnum.PERSONAL
                                || verb == AccessActionVerbEnum.DELETE;
            case JOURNEY_PAGE: 
                return verb == AccessActionVerbEnum.SEARCH 
                                || verb == AccessActionVerbEnum.VIEW ;
            case CONTAINER_PAGE:
                return verb == AccessActionVerbEnum.CREATE;
            case HOME_PAGE:
            case LOGIN_PAGE:
            case SIGNUP_PAGE:
            case LOGISTICS_MANAGEMENT:
                return true;
            default:
                return false;
        }
    }

    private static boolean ClientUserHasAccessTo(AccessActionNounEnum noun,
                                    AccessActionVerbEnum verb) {
        switch(noun) {
            case CLIENT_MANAGEMENT: 
                return verb == AccessActionVerbEnum.INDEX || verb == AccessActionVerbEnum.EDIT 
                                || verb == AccessActionVerbEnum.PERSONAL;
            case JOURNEY_PAGE:
                return verb == AccessActionVerbEnum.INDEX || verb == AccessActionVerbEnum.CREATE || verb == AccessActionVerbEnum.SEARCH;
            case HOME_PAGE:
            case LOGIN_PAGE:
                return true;
            default:
                return false;
        }
    }

    public static boolean UnSignedUserHasAccessTo(AccessActionNounEnum noun,
                                    AccessActionVerbEnum verb) {
        switch(noun) {
            case HOME_PAGE:
            case LOGIN_PAGE:
                return true;
            default:
                return false;
        }      
    }

    public static boolean HasAccessTo(AccessActionNounEnum noun,
                                    AccessActionVerbEnum verb, UserModel user) {
        boolean access = false;
        if (user == null) 
            access = UnSignedUserHasAccessTo(noun, verb);
        else if (user.IsLogisticUser()) 
            access =  LogisticUserHasAccessTo(noun, verb);
        else
            access = ClientUserHasAccessTo(noun, verb);
        LoggingWorker.GetInstance().Log(
            "Some user tried to access " + noun.name() + " " +verb.name() + ". Accepted: " + String.valueOf(access) 
        );
        return access;
    }
}
