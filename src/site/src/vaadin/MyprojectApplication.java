package vaadin;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import managers.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.*;
@Configurable
public class MyprojectApplication extends Application {
    @Autowired
    public DataManager2 dataManager;
    @Override
    public void init() {

//        getMainWindow().showNotification("ValueChangeEvent: " + this.getProperty("app"), Window.Notification.TYPE_ERROR_MESSAGE);

        final Window mainWindow = new Window("Test Application", new VerticalLayout());
        mainWindow.setSizeFull();
        setMainWindow(mainWindow);
        final String NAME_PROPERTY = "Name";
        final String HOURS_PROPERTY = "Hours done";
        final String MODIFIED_PROPERTY = "Last Modified";
        Button b = new Button("wefew");
        b.addListener(new Button.ClickListener(){
            public void buttonClick(Button.ClickEvent clickEvent) {
                dataManager.getDirections();
            }
        });
        final TreeTable treetable;


        // Calendar
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 10, 30, 14, 40, 26);

        // Create the treetable
        treetable = new TreeTable();
        treetable.setWidth("100%");


        // Add Table columns
        treetable.addContainerProperty(NAME_PROPERTY, String.class, "");
        treetable.addContainerProperty(HOURS_PROPERTY, Integer.class, 0);
        treetable.addContainerProperty(MODIFIED_PROPERTY, Button.class, new Button("erwr"));
        treetable.setSelectable(true);

        // Populate table
        Object allProjects = treetable.addItem(new Object[]{"All Projects",
                18, new Button("1")}, null);
        Object year2010 = treetable.addItem(
                new Object[]{"Year 2010", 18, new Button("2")}, null);
        Object customerProject1 = treetable.addItem(new Object[]{
                "Customer Project 1", 13, new Button("3")}, null);
        Object customerProject1Implementation = treetable.addItem(new Object[]{
                "Implementation", 5, b}, null);
        Object customerProject1Planning = treetable.addItem(new Object[]{
                "Planning", 2, b}, null);
        Object customerProject1Prototype = treetable.addItem(new Object[]{
                "Prototype", 5, b}, null);
        Object customerProject2 = treetable.addItem(new Object[]{
                "Customer Project 2", 5, b}, null);
        Object customerProject2Planning = treetable.addItem(new Object[]{
                "Planning", 5, b}, null);

        // Set hierarchy
        treetable.setParent(year2010, allProjects);
        treetable.setParent(customerProject1, year2010);
        treetable.setParent(customerProject1Implementation, customerProject1);
        treetable.setParent(customerProject1Planning, customerProject1);
        treetable.setParent(customerProject1Prototype, customerProject1);
        treetable.setParent(customerProject2, year2010);
        treetable.setParent(customerProject2Planning, customerProject2);

        // Disallow children from leaves
        treetable.setChildrenAllowed(customerProject1Implementation, false);
        treetable.setChildrenAllowed(customerProject1Planning, false);
        treetable.setChildrenAllowed(customerProject1Prototype, false);
        treetable.setChildrenAllowed(customerProject2Planning, false);

        // Expand all
        treetable.setCollapsed(allProjects, false);
        treetable.setCollapsed(year2010, false);
        treetable.setCollapsed(customerProject1, false);
        treetable.setCollapsed(customerProject2, false);
        mainWindow.addComponent(treetable);
    }
}