package com.masai.service;

import com.masai.dao.BillPaymentDAO;
import com.masai.dao.CustomerDAO;
import com.masai.dao.SessionDAO;
import com.masai.dao.WalletDAO;
import com.masai.exception.BillPaymentException;
import com.masai.exception.LoginException;
import com.masai.exception.TransactionException;
import com.masai.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class BillPaymentServiceImpl implements BillPaymentService {

    @Autowired
    private BillPaymentDAO billPaymentDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private WalletDAO walletDAO;

    public CurrentUserSession isLogin(String key) throws LoginException {

        CurrentUserSession currentUserSession = sessionDAO.findByUuid(key);
        if (currentUserSession != null)
            return currentUserSession;
        else
            return null;
    }

    @Override
    public BillPayment addBillPayment(String key, BillPayment billPayment) throws BillPaymentException, LoginException {
        CurrentUserSession aao = isLogin(key);

        if (aao != null) {
            Customer customer = customerDAO.findByMobileNumber(aao.getUserId());

            Wallet wallet = customer.getWallet();

            wallet.getBillPayments().add(billPayment);

            walletDAO.save(wallet);

            return billPayment;

        } else {
            throw new LoginException("You are not logged in ...");
        }
    }

    @Override
    public Set<BillPayment> viewAllBillPayment(String key) throws BillPaymentException, LoginException {
        CurrentUserSession aao = isLogin(key);

        if (aao != null) {
            Customer customer = customerDAO.findByMobileNumber(aao.getUserId());
            Set<BillPayment> billPayments = customer.getWallet().getBillPayments();
            if (billPayments.isEmpty()) {
                throw new BillPaymentException("No Bill found!");
            } else {
                return billPayments;
            }
        } else {
            throw new LoginException("You are not logged in ...");
        }
    }
}
