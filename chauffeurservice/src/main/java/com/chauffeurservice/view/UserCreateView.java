package com.chauffeurservice.view;

import com.chauffeurservice.controller.AppUserController;
import com.chauffeurservice.model.AppUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("register")
public class UserCreateView extends VerticalLayout {

    @Autowired
    private AppUserController appUserController;

    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final EmailField email = new EmailField("Email");
    private final PasswordField password = new PasswordField("Password");

    public UserCreateView() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstName, lastName, email, password);

        Button saveButton = new Button("Kaydet", event -> registerUser());

        add(formLayout, saveButton);
    }

    private void registerUser() {
        AppUser appUser = new AppUser();
        appUser.setFirstName(firstName.getValue());
        appUser.setLastName(lastName.getValue());
        appUser.setEmail(email.getValue());
        appUser.setPassword(password.getValue());
        appUser.setActive(true);
        appUser.setRole("USER");
        String response = appUserController.registerUser(appUser).getBody();
        Notification.show(response, 3000, Notification.Position.MIDDLE);
    }
}

