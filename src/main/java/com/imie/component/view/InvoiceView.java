/**
 * Package which contains all views
 */
package com.imie.component.view;

import com.imie.component.entity.Invoice;
import com.imie.component.form.InvoiceEditor;
import com.imie.component.repository.InvoiceRepository;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * <b>Invoice View</b>
 *
 * @author kevin boussard
 * @version 1.0
 */
@SpringView(name = InvoiceView.VIEW_NAME)
public class InvoiceView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "invoice";

    private final InvoiceRepository repo;

    private final InvoiceEditor editor;

    private final Grid grid;

    private final TextField filter;

    private final Button addNewBtn;

    @Autowired
    public InvoiceView(InvoiceRepository repo, InvoiceEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New invoice", FontAwesome.PLUS);
    }

    @PostConstruct
    void init() {

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
        addComponent(mainLayout);

        // Configure layouts and components
        actions.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        grid.setHeight(300, Unit.PIXELS);
        grid.setWidth(100, Unit.PERCENTAGE);

        filter.setInputPrompt("Filter by last name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.addTextChangeListener(e -> listInvoices(e.getText()));

        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editInvoice((Invoice) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Product the new button is clicked
        addNewBtn.addClickListener(e -> editor.editInvoice(new Invoice()));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listInvoices(filter.getValue());
        });

        // Initialize listing
        listInvoices(null);
    }

    private void listInvoices(String text) {
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(
                    new BeanItemContainer(Invoice.class, repo.findAll()));
        }
        else {
            grid.setContainerDataSource(new BeanItemContainer(Invoice.class,
                    repo.findAll()));
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }

}