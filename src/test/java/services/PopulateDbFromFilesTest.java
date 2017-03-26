package services;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class PopulateDbFromFilesTest {
	
	private PopulateDbFromFiles populateDbFromFiles;

	@Before
	public void setUp()
	{
		populateDbFromFiles =new PopulateDbFromFiles();
	}

	@Test
	public void testReadFromPath() throws IOException {
		populateDbFromFiles.readFromPath();
	}

}
