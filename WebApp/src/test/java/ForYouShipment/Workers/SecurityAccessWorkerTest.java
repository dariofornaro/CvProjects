package ForYouShipment.Workers;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import ForYouShipment.Constants.AccessActionNounEnum;
import ForYouShipment.Constants.AccessActionVerbEnum;
import ForYouShipment.Models.ClientUserModel;
import ForYouShipment.Models.LogisticsUserModel;
import ForYouShipment.Models.UserModel;

public class SecurityAccessWorkerTest {

    @Test
    public void TestUnSignedUserHasAccessTo() {
        
        
        assertTrue(SecurityAccessWorker.UnSignedUserHasAccessTo(AccessActionNounEnum.LOGIN_PAGE,
                                                                 AccessActionVerbEnum.INDEX));

        assertTrue(!SecurityAccessWorker.UnSignedUserHasAccessTo(AccessActionNounEnum.JOURNEY_PAGE,
                                                                    AccessActionVerbEnum.INDEX));


    }

    @Test
    public void TestHasAccessToLogisticsUser() {
        
        UserModel lu = new LogisticsUserModel();
        
        //Logistic User
        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CLIENT_MANAGEMENT,
                                                    AccessActionVerbEnum.SEARCH, lu));

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CLIENT_MANAGEMENT,
                                                    AccessActionVerbEnum.VIEW, lu));

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CLIENT_MANAGEMENT,
                                                    AccessActionVerbEnum.PERSONAL, lu));

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CLIENT_MANAGEMENT,
                                                    AccessActionVerbEnum.DELETE, lu));

        assertTrue(!SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CLIENT_MANAGEMENT,
                                                    AccessActionVerbEnum.CREATE, lu));


        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.JOURNEY_PAGE,
                                                    AccessActionVerbEnum.SEARCH, lu));

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.JOURNEY_PAGE,
                                                    AccessActionVerbEnum.VIEW, lu));

        assertTrue(!SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.JOURNEY_PAGE,
                                                    AccessActionVerbEnum.INDEX, lu));

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CONTAINER_MANAGEMENT,
                                                    AccessActionVerbEnum.GENERAL, lu));

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CONTAINER_PAGE,
                                                    AccessActionVerbEnum.CREATE, lu));
        
        assertTrue(!SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CONTAINER_PAGE,
                                                    AccessActionVerbEnum.GENERAL, lu));
        
    }

    @Test
    public void TestHasAccessToC() {    

        UserModel cu = new ClientUserModel();
        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CLIENT_MANAGEMENT,
                                                      AccessActionVerbEnum.INDEX, cu));

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CLIENT_MANAGEMENT,
                                                        AccessActionVerbEnum.EDIT, cu));

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CLIENT_MANAGEMENT,
                                                        AccessActionVerbEnum.PERSONAL, cu));        
        
        assertTrue(!SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.CLIENT_MANAGEMENT,
                                                        AccessActionVerbEnum.DELETE, cu));         
                                                        
        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.JOURNEY_PAGE,
                                                        AccessActionVerbEnum.INDEX, cu)); 
                                                        
        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.JOURNEY_PAGE,
                                                        AccessActionVerbEnum.CREATE, cu));  

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.JOURNEY_PAGE,
                                                        AccessActionVerbEnum.SEARCH, cu)); 
                                                        
        assertTrue(!SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.JOURNEY_PAGE,
                                                        AccessActionVerbEnum.DELETE, cu));  

        assertTrue(SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.LOGIN_PAGE,
                                                        AccessActionVerbEnum.INDEX, cu)); 
        
        assertTrue(!SecurityAccessWorker.HasAccessTo(AccessActionNounEnum.LOGISTICS_MANAGEMENT,
                                                        AccessActionVerbEnum.INDEX, cu)); 
                                                        

    }
}
