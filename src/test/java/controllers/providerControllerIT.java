package controllers;

import api.exceptions.AlreadyExistProviderFieldException;
import api.exceptions.NotFoundProviderIdException;
import api.exceptions.ProviderWithArticlesException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import entities.core.Provider;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wrappers.ProviderWrapper;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class, TestsMailConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class providerControllerIT {

	@Autowired
	private ProviderController providerController;
	
	private Provider provider;
	
	@Before
	public void setUp() {
		provider = new Provider("compañia", "dirección", 666L, 999L, "paymentConditions", "note");
	}
	
	@Test
	public void test01ShouldCreateProvider() {
		try {
			ProviderWrapper providerWrapper = new ProviderWrapper(provider);
			ProviderWrapper providerSaved = providerController.registration(providerWrapper);
			
			assertEquals(providerSaved.getCompany(), providerWrapper.getCompany());
			assertEquals(providerSaved.getMobile(), providerWrapper.getMobile());
		} catch (AlreadyExistProviderFieldException e) {
			fail();
		}
	}

	@Test
	public void test02ShouldEditProvider() throws NotFoundProviderIdException, AlreadyExistProviderFieldException{
		ProviderWrapper providerWrapper = new ProviderWrapper(provider);
		providerWrapper.setId(5);
		providerWrapper.setCompany("nueva1");
		ProviderWrapper providerEdited = providerController.editProvider(providerWrapper);
		
		assertEquals("nueva1", providerEdited.getCompany());
	}
	
	@Test(expected = AlreadyExistProviderFieldException.class)
	public void test03ShouldNotCreateProviderIfMobileIsRepaeted() throws AlreadyExistProviderFieldException {
			ProviderWrapper providerWrapper = new ProviderWrapper(provider);
			providerController.registration(providerWrapper);
	}
	
	@Test
	public void test04ShouldDelete(){
		try {
			providerController.delete("5");
		} catch (ProviderWithArticlesException e) {
			fail();
		}
	}
	
	@Test
	public void test05ShouldGetAll(){
		List<ProviderWrapper> providerList = providerController.getAll();
		
		assertEquals(4, providerList.size());
	}
	
}
