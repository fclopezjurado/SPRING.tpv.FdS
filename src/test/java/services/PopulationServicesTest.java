package services;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MailConfig.class, PersistenceConfig.class, TestsPersistenceConfig.class })
public class PopulationServicesTest {

	@Autowired
	private PopulationService populateDbFromFiles;

	@Autowired
	private DataService dataService;

	@Before
	public void setUp() {
		dataService.deleteAllExceptAdmin();
	}

	@Test
	public void testReadFromPath() throws IOException {
		populateDbFromFiles.readFromPath();
	}

	@After
	public void tearDown() {
		dataService.deleteAllExceptAdmin();
	}

}
