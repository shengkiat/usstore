package sg.edu.nus.iss.uss;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sg.edu.nus.iss.uss.dao.filedataaccess.CategoryFileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.FileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.MemberFileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.ProductFileDatAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.StoreKeeperFileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.TransactionFileDataAccessTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.VendorFileDataAccessTest;
import sg.edu.nus.iss.uss.client.reporting.ReportCategoryTableModelTest;
import sg.edu.nus.iss.uss.client.reporting.ReportMemberTableModelTest;
import sg.edu.nus.iss.uss.client.reporting.ReportProductTableModelTest;
import sg.edu.nus.iss.uss.client.reporting.ReportTransactionTableModelTest;
import sg.edu.nus.iss.uss.dao.filedataaccess.DiscountFileDataAccessTest;
import sg.edu.nus.iss.uss.model.CheckOutSummaryTest;
import sg.edu.nus.iss.uss.model.DaySpecialDiscountTest;
import sg.edu.nus.iss.uss.model.MemberOnlyDiscountTest;
import sg.edu.nus.iss.uss.service.impl.AuthorisedServiceTest;
import sg.edu.nus.iss.uss.service.impl.CategoryServiceTest;
import sg.edu.nus.iss.uss.service.impl.CheckOutServiceTest;
import sg.edu.nus.iss.uss.service.impl.DiscountServiceTest;
import sg.edu.nus.iss.uss.service.impl.MemberServiceTest;
import sg.edu.nus.iss.uss.service.impl.ProductServiceTest;
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
	MemberFileDataAccessTest.class,
	StoreKeeperFileDataAccessTest.class,
	CategoryFileDataAccessTest.class,
	ProductFileDatAccessTest.class,
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
	ProductServiceTest.class,
	MemberServiceTest.class,
	CategoryServiceTest.class,
	ReportTransactionTableModelTest.class,
	ReportMemberTableModelTest.class,
	ReportProductTableModelTest.class,
	ReportCategoryTableModelTest.class
})
public class AllTests {

}
