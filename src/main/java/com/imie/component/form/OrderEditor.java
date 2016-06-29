/**
 * Package which contains all Forms Editor
 */
package com.imie.component.form;

import com.imie.component.entity.Customer;
import com.imie.component.entity.Order;
import com.imie.component.repository.CustomerRepository;
import com.imie.component.repository.OrderRepository;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <b>Order Form Editor Class</b>
 *
 * @author kevin boussard
 * @version 1.0
 */
@SpringComponent
@UIScope
public class OrderEditor extends VerticalLayout {

    private final CustomerRepository customerRepository;
    private final OrderRepository repository;

    /**
     * The currently edited customer
     */
    private Order order;

    /* Fields to edit properties in OrderDetail entity */
    DateField dateGreated = new DateField();
    ComboBox customers = new ComboBox();


    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public OrderEditor(OrderRepository repository, CustomerRepository customerRepository) {

        this.repository = repository;
        this.customerRepository = customerRepository;

        this.customers = new ComboBox("customers", customerRepository.findAll());
        addComponents(dateGreated,customers,actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                order.setCustomer((Customer) OrderEditor.this.customers.getValue());
                OrderEditor.this.repository.save(order);
            }
        });
        delete.addClickListener(e -> this.repository.delete(order));
        cancel.addClickListener(e -> editOrder(order));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editOrder(Order o) {
        final boolean persisted = o.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            order = repository.findOne(o.getId());
        }
        else {
            order = o;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        BeanFieldGroup.bindFieldsUnbuffered(order, this);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

}