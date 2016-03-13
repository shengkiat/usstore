package sg.edu.nus.iss.uss;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccessTest;
import sg.edu.nus.iss.uss.service.AuthorisedServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TransactionFileDataAccessTest.class,
	AuthorisedServiceTest.class
})
public class AllTests {

}
