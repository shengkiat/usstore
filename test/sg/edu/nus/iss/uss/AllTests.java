package sg.edu.nus.iss.uss;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sg.edu.nus.iss.uss.dao.filedataaccess.FileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.VendorFileDataAccessTest;
import sg.edu.nus.iss.uss.service.AuthorisedServiceTest;
import sg.edu.nus.iss.uss.service.TransactionServiceTest;
import sg.edu.nus.iss.uss.service.VendorServiceTest;
import sg.edu.nus.iss.uss.util.UssCommonUtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FileDataAccessTest.class,
	VendorFileDataAccessTest.class,
	TransactionFileDataAccessTest.class,
	TransactionServiceTest.class,
	AuthorisedServiceTest.class,
	UssCommonUtilTest.class,
	VendorServiceTest.class
})
public class AllTests {

}
