package com.chauffeurservice.view;

import com.chauffeurservice.controller.AppUserController;
import com.chauffeurservice.model.AppUser;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("register")
@JavaScript("./scripts/custom.js")
public class UserCreateView extends VerticalLayout {

    @Autowired
    private AppUserController appUserController;

    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final EmailField email = new EmailField("Email");
    private final PasswordField password = new PasswordField("Password");

    public UserCreateView() {
        FormLayout formLayout = new FormLayout();
        Button saveButton = new Button("Kaydet", event -> registerUser());
        Image logo = new Image("frontend/images/logo.png", null);
        logo.setWidth("500px");
        formLayout.add(
                logo,
                firstName,
                lastName,
                email,
                password,
                saveButton // Kaydet Butonu
        );
        saveButton.getElement().setProperty("title", "Click to create a new user");

        formLayout.addClassName("form-layout");
        formLayout.setMaxWidth("400px");
        formLayout.getStyle().set("margin", "auto"); // Ortaya hizalama
        add(formLayout);
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
        Dialog dialog = new Dialog();
        dialog.add(new Text(response));
        dialog.open();

    }
}

