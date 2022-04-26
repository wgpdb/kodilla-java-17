package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InvoiceDaoTestSuite {

    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    void testInvoiceDaoSave() {
        //Given
        Product apple = new Product("Apples");
        Product grape = new Product("Grapes");
        Product banana = new Product("Bananas");

        Item apples = new Item(new BigDecimal(5), 2, new BigDecimal(10));
        Item grapes = new Item(new BigDecimal(10), 1, new BigDecimal(10));
        Item bananas = new Item(new BigDecimal(8), 1, new BigDecimal(8));

        Invoice invoice = new Invoice("FV 1/04/2022");

        apples.setProduct(apple);
        grapes.setProduct(grape);
        bananas.setProduct(banana);

        apple.getItems().add(apples);
        grape.getItems().add(grapes);
        banana.getItems().add(bananas);

        invoice.getItems().add(apples);
        invoice.getItems().add(grapes);
        invoice.getItems().add(bananas);

        apples.setInvoice(invoice);
        grapes.setInvoice(invoice);
        bananas.setInvoice(invoice);

        //When
        invoiceDao.save(invoice);
        int id = invoice.getId();

        //Then
        assertNotEquals(0, id);
    }

    @AfterEach
    void cleanup() {
        invoiceDao.deleteAll();
    }
}