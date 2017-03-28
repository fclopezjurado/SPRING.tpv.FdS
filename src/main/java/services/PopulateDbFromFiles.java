package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;

import daos.core.AlarmDao;
import daos.core.ArticleDao;
import daos.core.EmbroideryDao;
import daos.core.InvoiceDao;
import daos.core.ProviderDao;
import daos.core.TextilePrintingDao;
import daos.core.TicketDao;
import daos.core.VoucherDao;
import daos.users.AuthorizationDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.core.Alarm;
import entities.core.Article;
import entities.core.Provider;

@Service
@Transactional
public class PopulateDbFromFiles {

	public static String PATH = new String("fixtures");
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuthorizationDao authorizationDao;
	
	@Autowired
	private TokenDao tokenDao;
	
	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private ProviderDao providerDao;
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private EmbroideryDao embroideryDao;
	
	@Autowired
	private TextilePrintingDao textilePrintingDao;
	
	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private AlarmDao alertDao;
	
	public TreeMap<Integer, String> getFilesOrdered()
	{
		TreeMap<Integer, String> files = new TreeMap<Integer, String>();
		files.put(1, "entities_core_alarm.yaml");
		files.put(1, "entities_core_article.yaml");
		files.put(1, "entities_core_cashierbalance.yaml");
		files.put(1, "entities_core_embroidery.yaml");
		files.put(1, "entities_core_invoice.yaml");
		files.put(1, "entities_core_product.yaml");
		files.put(0, "entities_core_provider.yaml");
		files.put(1, "entities_core_shopping.yaml");
		files.put(1, "entities_core_textileprinting.yaml");
		files.put(1, "entities_core_ticket.yaml");
		files.put(1, "entities_core_voucher.yaml");
		files.put(1, "entities_users_authorization.yaml");
		files.put(1, "entities_users_encrypting.yaml");
		files.put(0, "entities_users_user.yaml");
		
		return files;
		
	}
	public void readFromPath() throws IOException
	{
		String allFixturesTogether = new String();

		for(String file : getFilesOrdered().values())
		{
			ClassPathResource resource = new ClassPathResource(PATH + "/" +file);
			allFixturesTogether += readFile(resource.getFile());
		}

		YamlReader reader = new YamlReader(allFixturesTogether);
		reader.getConfig().setScalarSerializer(BigDecimal.class, getBigDecimalSerializer());
		reader.getConfig().setScalarSerializer(Calendar.class, getCalendarSerializer());
		reader.getConfig().setPrivateFields(true);
		
		Map map = (Map)reader.read();
		
        userDao.save((List)map.get("users"));
        authorizationDao.save((List)map.get("authorizations"));
        tokenDao.save((List)map.get("tokens"));
        voucherDao.save((List)map.get("vouchers"));
        providerDao.save((List)map.get("providers"));
        articleDao.save((List)map.get("articles"));
        embroideryDao.save((List)map.get("embroiderys"));
        textilePrintingDao.save((List)map.get("textilePrinting"));
        ticketDao.save((List)map.get("textilesPrinting"));
        invoiceDao.save((List)map.get("invoices"));
        alertDao.save((List)map.get("alerts"));		
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
	
	private ScalarSerializer<BigDecimal> getBigDecimalSerializer()
	{
		return new ScalarSerializer<BigDecimal>() {
			@Override
			public String write(BigDecimal object) throws YamlException {
				return null;
			}

			@Override
			public BigDecimal read(String value) throws YamlException {
				BigDecimal decimal = BigDecimal.valueOf(Double.valueOf(value));
				return decimal;
			}
			
		};
	}
	
	private ScalarSerializer<Calendar> getCalendarSerializer()
	{
		return new ScalarSerializer<Calendar>() {

			@Override
			public String write(Calendar object) throws YamlException {
				return null;
			}

			@Override
			public Calendar read(String value) throws YamlException {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
				Calendar calendar = Calendar.getInstance();
				try {
					calendar.setTime(sdf.parse(value));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return calendar;
			}
			
		};
	}
}
