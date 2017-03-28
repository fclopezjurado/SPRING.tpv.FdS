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
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

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
import entities.core.Embroidery;
import entities.core.Invoice;
import entities.core.Provider;
import entities.core.TextilePrinting;
import entities.core.Ticket;
import entities.core.Voucher;
import entities.users.Authorization;
import entities.users.Token;
import entities.users.User;

@Service
@Transactional
public class PopulationService {

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
	private AlarmDao alarmDao;
	
	List<User> users;
	List<Authorization> authorizations;
	List<Token> tokens;
	List<Voucher> vouchers;
	List<Provider> providers;
	List<Article> articles;
	List<Embroidery> embroideries;
	List<TextilePrinting> textiles;
	List<Ticket> tickets;
	List<Invoice> invoices;
	List<Alarm> alarms;
	
    
	public Vector<String> getFilesOrdered()
	{
		Vector<String> files = new Vector<>();
		files.add("entities_users_user.yaml");
		files.add("entities_users_token.yaml");
		files.add("entities_core_provider.yaml");
		files.add("entities_core_shopping.yaml");
		files.add("entities_core_ticket.yaml");
		files.add("entities_core_article.yaml");
		files.add("entities_core_textileprinting.yaml");
		files.add("entities_core_cashierbalance.yaml");
		files.add("entities_core_embroidery.yaml");
		files.add("entities_core_invoice.yaml");
		files.add("entities_core_voucher.yaml");
		files.add("entities_users_authorization.yaml");
		files.add("entities_users_encrypting.yaml");
		files.add("entities_core_alarm.yaml");

		
		
		return files;
		
	}
	public void readFromPath() throws IOException
	{
		String allFixturesTogether = new String();

		for(String file : getFilesOrdered())
		{
			ClassPathResource resource = new ClassPathResource(PATH + "/" +file);
			allFixturesTogether += readFile(resource.getFile());
		}

		YamlReader reader = new YamlReader(allFixturesTogether);
		reader.getConfig().setScalarSerializer(BigDecimal.class, getBigDecimalSerializer());
		reader.getConfig().setScalarSerializer(Calendar.class, getCalendarSerializer());
		reader.getConfig().setPrivateFields(true);
		
		Map map = (Map)reader.read();
		
		users = (List)map.get("users");
		authorizations = (List)map.get("authorizations");
		tokens = (List)map.get("tokens");
		vouchers = (List)map.get("vouchers");
		providers = (List)map.get("providers");
		articles = (List)map.get("articles");
		embroideries = (List)map.get("embroiderys");
		textiles = (List)map.get("textilesprinting");
		tickets = (List)map.get("tickets");
		invoices = (List)map.get("invoices");
		alarms = (List)map.get("alarms");

		
		users = userDao.save(users);
        authorizationDao.save(authorizations);
        tokenDao.save(tokens);
        voucherDao.save(vouchers);
        providerDao.save(providers);
        articleDao.save(articles);
        embroideryDao.save(embroideries);
        textilePrintingDao.save(textiles);
        ticketDao.save(tickets);
        invoiceDao.save(invoices);
        alarmDao.save(alarms);		
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
