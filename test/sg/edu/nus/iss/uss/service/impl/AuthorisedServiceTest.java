package sg.edu.nus.iss.uss.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sg.edu.nus.iss.uss.dao.filedataaccess.StoreKeeperFileDataAccess;
import sg.edu.nus.iss.uss.exception.UssException;
import sg.edu.nus.iss.uss.model.StoreKeeper;
import sg.edu.nus.iss.uss.service.IAuthorisedService;

public class AuthorisedServiceTest {

	@Test
	public void testIsAuthorisedWithValidStoreKeepers() throws UssException {

		IAuthorisedService authService = new AuthorisedService(
				new MockStoreKeeperFileDataAccess() {
					@Override
					public List<StoreKeeper> getAll() {

						List<StoreKeeper> storeKeepers = new ArrayList<StoreKeeper>();

						StoreKeeper user = new StoreKeeper("andy", "123");

						storeKeepers.add(user);

						return storeKeepers;

					}
				});

		assertTrue(authService.isAuthorised("andy", "123"));
		assertTrue(authService.isAuthorised("Andy", "123"));
		assertFalse(authService.isAuthorised("andy", "1234"));

	}

	private class MockStoreKeeperFileDataAccess extends
			StoreKeeperFileDataAccess {

		public MockStoreKeeperFileDataAccess() throws UssException {
			super();
		}

		@Override
		protected void initialLoad() {
			// do nothing
		}
	}
}
