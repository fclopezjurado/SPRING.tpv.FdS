package controllers;

import daos.core.*;
import daos.users.AuthorizationDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.core.*;
import entities.users.Authorization;
import entities.users.Role;
import entities.users.Token;
import entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import services.DataService;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Controller
public class AdminController {

    private DataService dataService;
    
    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private AuthorizationDao authorizationDao;

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
    
    @Autowired
    private Environment environment;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public void deleteAllExceptAdmin() {
        dataService.deleteAllExceptAdmin();
    }
    
    public void seedDatabase() {
        this.createUsers(0, 4, Role.CUSTOMER);
        this.createToken(666000000);
        this.createVouchers();
        this.createProviders();
        this.createProducts();
        this.createTickets();
        this.createInvoices();
        this.createAlarms();
    }
    
    public void createUsers(int initial, int size, Role role) {
        User user;
        for (int i = 0; i < size; i++) {
            user = new User(666000000 + initial + i, role.toString().toLowerCase() + (i + initial), "pass");
            userDao.save(user);
            authorizationDao.save(new Authorization(user, role));
        }
    }

    public Token createToken(long mobile) {
        Token token;
        User user = userDao.findByMobile(mobile);
        token = new Token(user);
        tokenDao.save(token);
        return token;
    }

    public void createVouchers() {
        Voucher voucher;
        for (int i = 0; i < 4; i++) {
            voucher = new Voucher(new BigDecimal(i));
            voucherDao.save(voucher);
        }

        voucher = new Voucher(new BigDecimal(100));
        voucher.consume();
        voucherDao.save(voucher);

        voucher = new Voucher(new BigDecimal(50));

        Calendar calen = Calendar.getInstance();

        calen.set(Calendar.YEAR, 2016);
        calen.set(Calendar.MONTH, 5);
        calen.set(Calendar.DAY_OF_MONTH, 0);
        calen.set(Calendar.HOUR, 0);
        calen.set(Calendar.MINUTE, 0);
        calen.set(Calendar.SECOND, 0);

        voucher.setDateOfExpiration(calen);
        voucherDao.save(voucher);

        voucher = new Voucher(new BigDecimal(15.30));

        calen = Calendar.getInstance();

        calen.set(Calendar.YEAR, 2018);
        calen.set(Calendar.MONTH, 5);
        calen.set(Calendar.DAY_OF_MONTH, 2);
        calen.set(Calendar.HOUR, 0);
        calen.set(Calendar.MINUTE, 0);
        calen.set(Calendar.SECOND, 0);

        voucher.setDateOfExpiration(calen);
        voucherDao.save(voucher);

    }

    public void createProviders() {
        Provider provider;
        for (int i = 0; i < 4; i++) {
            provider = new Provider("company" + i, "address" + i, 666100000 + i, 916661000 + i, "No", "No");
            providerDao.save(provider);
        }
    }

    public void createProducts() {
        Article article;
        Provider provider = providerDao.findAll().get(0);
        for (int i = 0; i < 4; i++) {
            article = new Article(84000001111L + i, "article" + i, new BigDecimal(20 + i), "article" + i, new BigDecimal(10 + i), provider);
            articleDao.save(article);
        }
        provider = providerDao.findAll().get(1);
        for (int i = 5; i < 9; i++) {
            article = new Article(84000001111L + i, "article" + i, new BigDecimal(20 + i), "article" + i, new BigDecimal(10 + i), provider);
            articleDao.save(article);
        }
        Embroidery embroidery;
        for (int i = 0; i < 4; i++) {
            embroidery = new Embroidery(84000002222L + i, "embroidery" + i, new BigDecimal(20 + i), "embroidery" + i, i * 1000, i, i * 10);
            embroideryDao.save(embroidery);
        }
        TextilePrinting textilePrinting;
        for (int i = 0; i < 4; i++) {
            textilePrinting = new TextilePrinting(84000003333L + i, "textilePrinting" + i, new BigDecimal(20 + i), "textilePrinting" + i,
                    "ploter");
            textilePrintingDao.save(textilePrinting);
        }

    }

    public void createTickets() {
        Ticket ticket;

        ticket = new Ticket(1L, TicketState.CLOSED);
        for (int i = 0; i < 4; i++) {
            Product product = articleDao.findOne(84000001111L + i);
            ticket.addShopping(new Shopping(1 + i, 0, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        ticket.setUser(userDao.findByMobile(Long.valueOf(environment.getProperty("admin.mobile"))));
        ticketDao.save(ticket);

        ticket = new Ticket(2L, TicketState.OPENED);
        for (int i = 0; i < 4; i++) {
            Product product = embroideryDao.findOne(84000002222L + i);
            ticket.addShopping(new Shopping(1 + i, 0, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        ticketDao.save(ticket);

        ticket = new Ticket(3L, TicketState.COMMITTED);
        for (int i = 0; i < 4; i++) {
            Product product = textilePrintingDao.findOne(84000003333L + i);
            ticket.addShopping(new Shopping(1 + i, 10, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        ticketDao.save(ticket);
    }

    public void createInvoices() {
        invoiceDao.save(new Invoice(1, ticketDao.findOne(1L)));
        invoiceDao.save(new Invoice(3, ticketDao.findOne(3L)));
    }

    public void createAlarms() {
        List<Article> articles = articleDao.findAll();
        alarmDao.save(new Alarm("Alarma Warning", articles, AlarmType.WARNING, 5));
        alarmDao.save(new Alarm("Alarma Critical", null, AlarmType.CRITICAL, 2));
    }
}
