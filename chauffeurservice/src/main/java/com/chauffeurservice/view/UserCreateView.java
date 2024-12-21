package com.chauffeurservice.view;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;

@Route("signup")
public class UserCreateView extends VerticalLayout {

    public UserCreateView() {
        // Form elemanları
        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        EmailField email = new EmailField("Email");
        PasswordField password = new PasswordField("Password");

        // Form düzeni
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstName, lastName, email, password);

        // Kaydet butonu
        Button saveButton = new Button("Save", event -> {
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Notification.show("Please fill in all fields!", 3000, Notification.Position.MIDDLE);
            } else {
                // Backend ile iletişim
                // Burada backend'e POST isteği gönderirsiniz
                Notification.show("User saved successfully!", 3000, Notification.Position.MIDDLE);
            }
        });

        // Tüm bileşenleri dikey bir düzen içinde göster
        add(formLayout, saveButton);
    }
}

