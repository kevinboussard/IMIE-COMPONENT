package com.imie.component.view;

import com.imie.component.entity.Invoice;
import com.imie.component.form.InvoiceEditor;
import com.imie.component.repository.InvoiceRepository;
import com.imie.component.repository.ProductRepository;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import java.util.List;

/**
 * Created by kevin on 23/06/16.
 */
@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    // we can use either constructor autowiring or field autowiring
    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createNavigationButton("Home",
                DefaultView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Products",
                ProductView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Orders",
                OrderView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Customers",
                CustomerView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("OrderDetails",
                OrderDetailView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Deliveries",
                DeliveryView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Invoices",
                InvoiceView.VIEW_NAME));
        root.addComponent(navigationBar);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        // If you didn't choose Java 8 when creating the project, convert this to an anonymous listener class
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

}
