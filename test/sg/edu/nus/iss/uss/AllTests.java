package sg.edu.nus.iss.uss;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sg.edu.nus.iss.uss.dao.filedataaccess.FileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.VendorFileDataAccessTest;
import sg.edu.nus.iss.uss.client.ReportTransactionTableModelTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.DiscountFileDataAccessTest;
import sg.edu.nus.iss.uss.model.CheckOutSummaryTest;
import sg.edu.nus.iss.uss.model.DaySpecialDiscountTest;
import sg.edu.nus.iss.uss.model.MemberOnlyDiscountTest;
import sg.edu.nus.iss.uss.service.impl.AuthorisedServiceTest;
import sg.edu.nus.iss.uss.service.impl.CheckOutServiceTest;
import sg.edu.nus.iss.uss.service.impl.DiscountServiceTest;
import sg.edu.nus.iss.uss.service.impl.ReportingServiceTest;
import sg.edu.nus.iss.uss.service.impl.TransactionServiceTest;
import sg.edu.nus.iss.uss.service.impl.VendorServiceTest;
import sg.edu.nus.iss.uss.util.UssCommonUtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FileDataAccessTest.class,
	VendorFileDataAccessTest.class,
	TransactionFileDataAccessTest.class,
	DiscountFileDataAccessTest.class,
	TransactionServiceTest.class,
	AuthorisedServiceTest.class,
	UssCommonUtilTest.class,
	VendorServiceTest.class,
	CheckOutSummaryTest.class,
	DaySpecialDiscountTest.class,
	MemberOnlyDiscountTest.class,
	CheckOutServiceTest.class,
	DiscountServiceTest.class,
	ReportingServiceTest.class,
	ReportTransactionTableModelTest.class
})
public class AllTests {

}
