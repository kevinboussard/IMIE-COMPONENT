package com.imie.component.form;

import com.imie.component.entity.Delivery;
import com.imie.component.entity.Invoice;
import com.imie.component.entity.Order;
import com.imie.component.repository.DeliveryRepository;
import com.imie.component.repository.InvoiceRepository;
import com.imie.component.repository.OrderRepository;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kevin on 24/06/16.
 */
@SpringComponent
@UIScope
public class DeliveryEditor extends VerticalLayout {

    private final DeliveryRepository repository;
    private final OrderRepository orderRepository;
    private final InvoiceRepository invoiceRepository;

    /**
     * The currently edited delivery
     */
    private Delivery delivery;

    /* Fields to edit properties in Delivery entity */
    DateField dateDelivery = new DateField();
    ComboBox orders = new ComboBox();
    ComboBox invoices = new ComboBox();

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public DeliveryEditor(DeliveryRepository repository, OrderRepository orderRepository, InvoiceRepository invoiceRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.invoiceRepository = invoiceRepository;

        orders = new ComboBox("orders", orderRepository.findAll());
        invoices = new ComboBox("invoices", invoiceRepository.findAll());

        addComponents(dateDelivery,orders, invoices, actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                delivery.setOrder((Order)DeliveryEditor.this.orders.getValue());
                delivery.setInvoice((Invoice)DeliveryEditor.this.invoices.getValue());
                DeliveryEditor.this.repository.save(delivery);
            }
        });
        delete.addClickListener(e -> repository.delete(delivery));
        cancel.addClickListener(e -> editDelivery(delivery));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editDelivery(Delivery d) {
        final boolean persisted = d.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            delivery = repository.findOne(d.getId());
        }
        else {
            delivery = d;
        }
        cancel.setVisible(persisted);

        // Bind delivery properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        BeanFieldGroup.bindFieldsUnbuffered(delivery, this);

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