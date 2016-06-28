package com.imie.component.form;

import com.imie.component.entity.Order;
import com.imie.component.entity.OrderDetail;
import com.imie.component.entity.Product;
import com.imie.component.repository.OrderDetailRepository;
import com.imie.component.repository.OrderRepository;
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
public class OrderDetailEditor extends VerticalLayout {

    private final OrderDetailRepository repository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    /**
     * The currently edited customer
     */
    private OrderDetail orderDetail;

    /* Fields to edit properties in OrderDetail entity */
    TextField qte = new TextField("quantity");
    ComboBox products = new ComboBox();
    ComboBox orders = new ComboBox();

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public OrderDetailEditor(OrderDetailRepository repository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;

        products = new ComboBox("products", productRepository.findAll());
        orders = new ComboBox("orders", orderRepository.findAll());

        addComponents(qte,products,orders,actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                orderDetail.setOrder((Order)OrderDetailEditor.this.orders.getValue());
                orderDetail.setProduct((Product)OrderDetailEditor.this.products.getValue());
                OrderDetailEditor.this.repository.save(orderDetail);
            }
        });
        delete.addClickListener(e -> repository.delete(orderDetail));
        cancel.addClickListener(e -> editOrderDetail(orderDetail));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editOrderDetail(OrderDetail od) {
        final boolean persisted = od.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            orderDetail = repository.findOne(od.getId());
        }
        else {
            orderDetail = od;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        BeanFieldGroup.bindFieldsUnbuffered(orderDetail, this);

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