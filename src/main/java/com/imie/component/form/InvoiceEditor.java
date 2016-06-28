package com.imie.component.form;

import com.imie.component.entity.Invoice;
import com.imie.component.repository.DeliveryRepository;
import com.imie.component.repository.InvoiceRepository;
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
public class InvoiceEditor extends VerticalLayout {

    private final InvoiceRepository repository;

    /**
     * The currently edited invoice
     */
    private Invoice invoice;

    /* Fields to edit properties in Customer entity */
    DateField dateInvoice = new DateField();

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public InvoiceEditor(InvoiceRepository repository) {
        this.repository = repository;

        addComponents(dateInvoice, actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> this.repository.save(invoice));
        delete.addClickListener(e -> this.repository.delete(invoice));
        cancel.addClickListener(e -> editInvoice(invoice));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editInvoice(Invoice i) {
        final boolean persisted = i.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            invoice = repository.findOne(i.getId());
        }
        else {
            invoice = i;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        BeanFieldGroup.bindFieldsUnbuffered(invoice, this);

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
