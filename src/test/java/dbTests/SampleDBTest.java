package dbTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import db.DBUtils;
import db.Queries;

public class SampleDBTest {

    @Test(groups = {"db"})
    public void verifyUserRecordInDB() {
        
        boolean isUserPresent = DBUtils.isRecordPresent(
                Queries.GET_USER_BY_EMAIL,
                "ineuron@ineuron.ai"
        );

        Assert.assertTrue(isUserPresent, "User record is not present in database");
    }
}
