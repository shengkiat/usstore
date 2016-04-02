package sg.edu.nus.iss.uss.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import sg.edu.nus.iss.uss.dao.filedataaccess.VendorFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.Vendor;
import sg.edu.nus.iss.uss.service.IVendorService;
import sg.edu.nus.iss.uss.service.impl.VendorService;

public class VendorServiceTest {

	@Test
	public void testGetVendorByCategoryCode() throws UssException {

		IVendorService vendorService = new VendorService(
				new MockVendorFileDataAccess() {
					@Override
					public Map<String, List<Vendor>> getAll() {

						Map<String, List<Vendor>> vendorMap = new HashMap<String, List<Vendor>>();

						Vendor vendormug1 = new Vendor();
						vendormug1.setName("Nancy");
						vendormug1
								.setDescription("Nancy's Gift,Best of the best");

						vendorMap.put("MUG", new ArrayList<Vendor>());
						vendorMap.get("MUG").add(vendormug1);

						return vendorMap;

					}
				});

		assertEquals(1, vendorService.getVendorsByCategoryCode("MUG").size());
		assertNotEquals(2, vendorService.getVendorsByCategoryCode("MUG").size());

		assertEquals(0, vendorService.getVendorsByCategoryCode("CLO").size());

	}

	private class MockVendorFileDataAccess extends VendorFileDataAccess {

		public MockVendorFileDataAccess() throws UssException {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void initialLoad() {
			// do nothing
		}
	}

}
