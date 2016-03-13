package sg.edu.nus.iss.uss;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sg.edu.nus.iss.uss.dao.filedataaccess.FileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccessTest;
import sg.edu.nus.iss.uss.service.AuthorisedServiceTest;
import sg.edu.nus.iss.uss.service.TransactionServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FileDataAccessTest.class,
	TransactionFileDataAccessTest.class,
	TransactionServiceTest.class,
	AuthorisedServiceTest.class
})
public class AllTests {

}
