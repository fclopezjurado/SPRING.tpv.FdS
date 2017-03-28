package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.core.AlarmDao;
import daos.core.ArticleDao;
import daos.core.EmbroideryDao;
import daos.core.FamilyDao;
import daos.core.BudgetDao;
import daos.core.CashierBalanceDao;
import daos.core.InvoiceDao;
import daos.core.ProviderDao;
import daos.core.TextilePrintingDao;
import daos.core.TicketDao;
import daos.core.VoucherDao;
import daos.users.AuthorizationDao;
import daos.users.TokenDao;
import daos.users.UserDao;

@Service
public class DataService {

    @Autowired
    private DatabaseSeeder populate;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private UserDao userDao;

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

    public void deleteAllExceptAdmin() {
        cashierBalanceDao.deleteAll();
        invoiceDao.deleteAll();
        ticketDao.deleteAll();
        budgetDao.deleteAll();

        authorizationDao.deleteAll();
        tokenDao.deleteAll();
        userDao.deleteAll();

        voucherDao.deleteAll();
        alarmDao.deleteAll();
        articleDao.deleteAll();
        embroideryDao.deleteAll();
        textilePrintingDao.deleteAll();
        providerDao.deleteAll();
        familyDao.deleteAll();

        populate.createDefaultAdmin();
    }

}
