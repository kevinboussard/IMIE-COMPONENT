package com.imie.component.form;

import com.imie.component.entity.Product;
import com.imie.component.repository.ProductRepository;
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
public class ProductEditor extends VerticalLayout {

    private final ProductRepository repository;

    /**
     * The currently edited customer
     */
    private Product product;

    /* Fields to edit properties in Customer entity */
    TextField name = new TextField("Name");
    TextField description = new TextField("Description");
    TextField price = new TextField("Price");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public ProductEditor(ProductRepository repository) {
        this.repository = repository;

        addComponents(name, description, price, actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> repository.save(product));
        delete.addClickListener(e -> repository.delete(product));
        cancel.addClickListener(e -> editProduct(product));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editProduct(Product p) {
        final boolean persisted = p.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            product = repository.findOne(p.getId());
        }
        else {
            product = p;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        BeanFieldGroup.bindFieldsUnbuffered(product, this);

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