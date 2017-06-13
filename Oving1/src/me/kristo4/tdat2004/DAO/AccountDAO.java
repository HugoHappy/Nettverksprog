package me.kristo4.tdat2004.DAO;

import me.kristo4.tdat2004.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by KristofferLaptop on 06-Mar-17.
 */
public class AccountDAO {
    private EntityManagerFactory emf;

    public AccountDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void newAccount(Account acc) {
        EntityManager em = getEM();
        try{
            em.getTransaction().begin();
            em.persist(acc);
            em.getTransaction().commit();
        } finally {
            closeEM(em);
        }
    }

    public Account findAccount(int accNr) {
        EntityManager em = getEM();
        try{
            return em.find(Account.class, accNr);
        } finally {
            closeEM(em);
        }
    }

    public void editAccount(Account acc){
        EntityManager em = getEM();
        try{
            em.getTransaction().begin();
            Account a = em.merge(acc);
            em.getTransaction().commit();
        } finally {
            closeEM(em);
        }
    }

    public void removeAccount(int accNr){
        EntityManager em = getEM();
        try{
            Account a = findAccount(accNr);
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } finally {
            closeEM(em);
        }
    }

    public List<Account> getAllAccounts(){
        EntityManager em = getEM();
        try{
            Query q = em.createQuery("SELECT OBJECT(a) FROM Account a");
            return q.getResultList();
        } finally {
            closeEM(em);
        }
    }

    private void lukkEM(EntityManager em){
        if (em != null && em.isOpen()) em.close();
    }

    public void endreNavn(Account account){
        EntityManager em = getEM();
        try{
            em.getTransaction().begin();
            Account b = em.merge(account);//sørger for å føre entiteten inn i lagringskonteksten
            em.getTransaction().commit();//merk at endringene gjort utenfor transaksjonen blir lagret!!!
        }finally{
            lukkEM(em);
        }
    }


    public List<Account> getAccountGivenBalance(double balance){
        EntityManager em = getEM();
        try{
            Query q = em.createQuery("SELECT OBJECT(a) FROM Account a WHERE a.balance > :balance");
            q.setParameter("balance", balance);
            return q.getResultList();
        } finally {
            closeEM(em);
        }
    }

    public int getAmountOfAccounts(){
        EntityManager em = getEM();
        try{
            Query q = em.createNamedQuery("getAmountOfAccounts");
            Long amt = (Long)q.getSingleResult();
            return amt.intValue();
        } finally {
            closeEM(em);
        }
    }

    public void transferCurrency(int accNrFrom, int accNrTo, double amt) {
        Account accTo = findAccount(accNrTo);
        Account accFrom = findAccount(accNrFrom);
        if(amt > accTo.getBalance()) {
            System.out.println("Unable to transfer: Not enough currency.");
        } else {
            editAccount(new Account(accNrFrom, accFrom.getBalance() - amt, accFrom.getAccOwner()));
            editAccount(new Account(accNrTo, accTo.getBalance() + amt, accTo.getAccOwner()));
            System.out.println("Transfered " + amt + " from account nr. " + accNrFrom + " to account nr. " + accNrTo + ".");
        }
    }

    private EntityManager getEM(){
        return emf.createEntityManager();
    }

    private void closeEM(EntityManager em){
        if (em != null && em.isOpen()) em.close();
    }

    public static void main(String args[]) throws Exception{
        EntityManagerFactory emf = null;
        AccountDAO f = null;
        System.out.println("Starting...");
        try{
            emf = Persistence.createEntityManagerFactory("OvingPU");
            System.out.println("Constructor done " + emf);
            f = new AccountDAO(emf);
            System.out.println("Constructor done");



            //set up accounts
            //create dummy accs
            Account a1 = new Account(0, 20000.0, "Ola Nordmann");
            Account a2 = new Account(0, 60000.0, "Kari Sørmann");
            //save accs
            f.newAccount(a1);
            f.newAccount(a2);

            //print accs
            System.out.println("Found " + f.getAmountOfAccounts() + " accounts in database:");
            List<Account> list1 = f.getAllAccounts();
            for (Account a : list1){
                System.out.println(a);
            }

            //fetch accounts with balance over 40000
            double balance = 40000;
            System.out.println("\nAccounts with balance over " + balance + ":");
            List<Account> list2 = f.getAccountGivenBalance(balance);
            for (Account a : list2){
                System.out.println(a + "\n");
            }

            //change
            Account e = f.findAccount(1111);
            e.setAccOwner("Bjørn Tore");
            f.editAccount(e);
            //transfer
            f.transferCurrency(1111, 2222, 500);

            //print accs again to check currency transfer
            System.out.println("Found " + f.getAmountOfAccounts() + " accounts in database:");
            List<Account> list3 = f.getAllAccounts();
            for (Account a : list3){
                System.out.println(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            emf.close();
        }
    }
}