package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.springframework.core.io.ClassPathResource;

import com.esotericsoftware.yamlbeans.YamlReader;

public class PopulateDbFromFiles {

	public static ClassPathResource PATH = new ClassPathResource("fixtures");
	
	public void readFromPath() throws IOException
	{
		File folder = PATH.getFile();
		File[] listOfFixtures = folder.listFiles();
		
		String allFixturesTogether = new String();
		for(int i=0; i<listOfFixtures.length; i++)
		{
			allFixturesTogether += readFile(listOfFixtures[i]);
		}
		
		YamlReader reader = new YamlReader(allFixturesTogether);
		Object fixtures = reader.read();
		System.out.println(fixtures);
	}
	
	private String readFile(File file) throws IOException
	{
		String everything = new String();
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
		} finally {
		    br.close();
		}
		
		return everything;
	}
}
