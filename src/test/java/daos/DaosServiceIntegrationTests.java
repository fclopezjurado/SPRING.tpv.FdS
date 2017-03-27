package daos;

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
import org.springframework.stereotype.Service;
import services.DataService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DaosServiceIntegrationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private DataService dataService;

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
    private BudgetDao budgetDao;

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private AlarmDao alarmDao;
    
    @Autowired
    private FamilyDao familyDao;
    
    @Autowired
    private CashierBalanceDao cashierBalanceDao;    

    @PostConstruct
    public void populate() {
        this.createUsers(0, 4, Role.CUSTOMER);
        this.createToken(666000000);
        this.createVouchers();
        this.createProviders();
        this.createProducts();
        this.createTickets();
        this.createBudgets();
        this.createInvoices();
        this.createAlarms();
        this.createFamilies();
        this.createCashierBalances();
    }

    private void createCashierBalances() {
        CashierBalance balance;
        Calendar day = Calendar.getInstance();
        for(int i = 0; i < 4; i++){
            balance = new CashierBalance(50.0, 50.0, 50.0, 50.0, 50.0);
            day.add(Calendar.DATE, -1);
            balance.setDay(day);
            cashierBalanceDao.save(balance);
        }
        
    }

    public void createUsers(int initial, int size, Role role) {
        User user;
        for (int i = 0; i < size; i++) {
            user = new User(666000000 + initial + i, role.toString().toLowerCase() + (i + initial), "pass");
            user.setEmail("user" + i + "@gmail.com");
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
        ticket.setUser(userDao.findByMobile(666000000));
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
    
    public void createBudgets() {
        Budget budget;

        budget = new Budget(1L);
        for (int i = 0; i < 4; i++) {
            Product product = articleDao.findOne(84000001111L + i);
            budget.addShopping(new Shopping(1 + i, 0, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        budget.setUser(userDao.findByMobile(666000000));
        budgetDao.save(budget);

        budget = new Budget(2L);
        for (int i = 0; i < 4; i++) {
            Product product = embroideryDao.findOne(84000002222L + i);
            budget.addShopping(new Shopping(1 + i, 0, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        budgetDao.save(budget);

        budget = new Budget(3L);
        for (int i = 0; i < 4; i++) {
            Product product = textilePrintingDao.findOne(84000003333L + i);
            budget.addShopping(new Shopping(1 + i, 10, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        budgetDao.save(budget);
    }

    public void createInvoices() {
        invoiceDao.save(new Invoice(1, ticketDao.findOne(1L)));
        invoiceDao.save(new Invoice(3, ticketDao.findOne(3L)));
    }

    public void createAlarms() {
        List<Article> articles = articleDao.findAll();
        alarmDao.save(new Alarm("Alarma Warning", articles, AlarmType.WARNING, 5));
        alarmDao.save(new Alarm("Alarma Critical", null, AlarmType.CRITICAL, 2));
        alarmDao.save(new Alarm("Alarma extra", articles, AlarmType.WARNING, 5));
    }
    
    public void createFamilies() {
        List<ComponentProduct> componentProducts = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            componentProducts.add(articleDao.findAll().get(i));
        }
        Family family1 = new Family(1L, "familyName1", "description1", componentProducts);
        List<ComponentProduct> productsAndFamilies = new ArrayList<>();
        for (int i = 4; i < 8; i++) {
            productsAndFamilies.add(articleDao.findAll().get(i));
        }
        productsAndFamilies.add(family1);
        Family family2 = new Family(2L, "familyName2", "description2", productsAndFamilies);
        List<ComponentProduct> families = new ArrayList<>();
        families.add(family1);
        families.add(family2);
        Family family3 = new Family(3L, "familyName3", "description3", families);
        familyDao.save(family1);
        familyDao.save(family2);
        familyDao.save(family3);
    }
}

