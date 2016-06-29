/**
 * Package which contains all views
 */
package com.imie.component.view;

import com.imie.component.entity.Customer;
import com.imie.component.form.CustomerEditor;
import com.imie.component.repository.CustomerRepository;
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
 * <b>Customer View</b>
 *
 * @author kevin boussard
 * @version 1.0
 */
@SpringView(name = CustomerView.VIEW_NAME)
public class CustomerView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "customer";

    private final CustomerRepository repo;

    private final CustomerEditor editor;

    private final Grid grid;

    private final TextField filter;

    private final Button addNewBtn;

    @Autowired
    public CustomerView(CustomerRepository repo, CustomerEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", FontAwesome.PLUS);
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
        filter.addTextChangeListener(e -> listCustomers(e.getText()));

        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editCustomer((Customer) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Product the new button is clicked
        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer()));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        // Initialize listing
        listCustomers(null);
    }

    private void listCustomers(String text) {
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(
                    new BeanItemContainer(Customer.class, repo.findAll()));
        }
        else {
            grid.setContainerDataSource(new BeanItemContainer(Customer.class,
                    repo.findAll()));
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
